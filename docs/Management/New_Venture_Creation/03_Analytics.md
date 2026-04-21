# Analytics

- Financial Reporting
- Strategy
	- Opportunity sizing
	- Incremental revenue estimation
	- Forecasting
- Product Analytics
	- Customer Journey
	- Customer Growth
## IDK

- Define KPIs = key metrics
	- If a KPI going up/down does not instigate any action, then it is useless
- Have a Northstar KPI
	- Best growth KPI:            Retained `MAU` `YOY`
	- Best performance KPI: Total `GMV`
- Decompose KPI into drivers to form a metric tree
	- For eg:
		- Total `GMV`
			- = `# Orders` x `AOV`
			- `# Orders`
				- = `# Customers` x `Order Frequency`


## Tracking

- Column:
	- Time period
- Rows:
	- Metric
	- Compared to
		- Change in metric
		- % change in metric
	- Impact of change in metric on change in north-star
		- KPI decomposition
- Values
	- Spark line/bar
	- Value
- Color
	- For every metric, the ranges must be defined
		- Red = Horrible
		- Orange = Poor
		- No color = Neutral
		- Light green = Good
		- Green = Great
- Comparison
	- Comparison value
		- Target for period
		- Base period common for all
		- PoP (Period-on-Period: Current period vs Previous period)
	- Resolution
		- YoY
		- YTDoYTD
		- MoM
		- MTDoMTD
		- WoW
		- WTDoWTD
		- DoD
		- DTHoDTH
		- HoH

![](assets/nv_analytics_tracking.png)

### Metrics

1. North star
2. Top-Level
	- Orders
	- Users
	- Conversion
3. Financials
	- Net Profit
	- Gross Profit
	- Revenue
4. Performance
	- GMV
	- Basket Value
5. Drivers/Levers
	- `#` Vendors
	- CARC %
	- Discount %
6. Product
	- [03_Product_Analytics](03_Product_Analytics.md)

## Incrementality using Diff-in-Diff

Cohort
- Order Month

Segments
- Country, in $T-1$
- Order frequency, in $T-1$
	- 0-1
	- 2-3
	- etc
- Lifecycle, in $T$

- Overall incrementality is a weighted average
- The estimated incrementality is the ATT

| Lifecycle   | Sub-Group                                                                                                                                | User Definition                                                              | ==ATT== in ==T+h==                                                                                                                                                                                                                                                  |
| ----------- | ---------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Retention   | Control                                                                                                                                  | Not interacted with treatment until $T+h$, but retention customer            | 0                                                                                                                                                                                                                                                                   |
|             | Treatment attributed                                                                                                                     | Retention order via treatment                                                | **Diff-in-diff**<br>(Order frequency of cohort in $T+h$) - (Order frequency of cohort in $T-1$)<br>-<br>(Order frequency of `Control` in $T+h$) - (Order frequency of `Control` in $T-1$)                                                                           |
|             | Non-Treatment attributed                                                                                                                 | Retention order not via treatment, but interacted with treatment otherwise   | **Diff-in-diff**<br>(Order frequency of cohort in $T+h$) - (Order frequency of cohort in $T-1$)<br>-<br>(Order frequency of `Control` in $T+h$) - (Order frequency of `Control` in $T-1$)                                                                           |
| Winback     | Control, $T-p$<br><br>Use as many $p$ lag cohorts as required, for eg: $(T-2), (T-3), (T-{4,6}), (T-{7,12}), (T-{13+})$                  | Not interacted with treatment until $T+h$, but winback customer              | 0                                                                                                                                                                                                                                                                   |
|             | Treatment attributed, $T-p$<br><br>Use as many $p$ lag cohorts as required, for eg: $(T-2), (T-3), (T-{4,6}), (T-{7,12}), (T-{13+})$     | Winback order via treatment                                                  | Order frequency of cohort in $T+h$<br><br>OR<br><br>**Diff-in-diff**<br>(Order frequency of cohort in $T+h$) - (Order frequency of cohort in $T-p$)<br>-<br>(Order frequency of `Control` in $T+h$) - (Order frequency of `Control` in $T-p$)                       |
|             | Non-Treatment attributed, $T-p$<br><br>Use as many $p$ lag cohorts as required, for eg: $(T-2), (T-3), (T-{4,6}), (T-{7,12}), (T-{13+})$ | Winback order not via treatment, but interacted with treatment otherwise     | Same increment as `Retention - Non-Treatment attributed`<br><br>OR<br><br>**Diff-in-diff**<br>(Order frequency of cohort in $T+h$) - (Order frequency of cohort in $T-p$)<br>-<br>(Order frequency of `Control` in $T+h$) - (Order frequency of `Control` in $T-p$) |
| Acquisition | Control                                                                                                                                  | Not interacted with treatment until $T+h$, but acquired customer             | 0                                                                                                                                                                                                                                                                   |
|             | Treatment attributed                                                                                                                     | Acquisition order via treatment                                              | Order frequency of cohort in $T+h$<br><br>OR<br><br>**Diff-in-diff**<br>(Order frequency of cohort in $T+h$) - (0)<br>-<br>(Order frequency of `Control` in $T+h$) - (0)                                                                                            |
|             | Non-Treatment attributed                                                                                                                 | Acquisition order not via treatment, but interacted with treatment otherwise | **Diff-in-diff**<br>(Order frequency of cohort in $T+h$) - (0)<br>-<br>(Order frequency of `Control` in $T+h$) - (0)                                                                                                                                                |

### Output

| Cohort     |    $T-3$    |    $T-2$    |    $T-1$    | ==$T$== | $T+1$ | $T+2$ | $T+3$ |
| ---------- | :---------: | :---------: | :---------: | :-----: | :---: | :---: | :---: |
| 2025-01-01 | $\approx 0$ | $\approx 0$ | $\approx 0$ |         |       |       |       |
| 2025-02-01 | $\approx 0$ | $\approx 0$ | $\approx 0$ |         |       |       |       |
| 2025-02-01 | $\approx 0$ | $\approx 0$ | $\approx 0$ |         |       |       |       |

## A/B Testing

```mysql
WITH
agg_users AS (
SELECT
	a.account_id,
	
	COUNT(a.order_id) AS order_count,
	SUM(a.gmv_amount_eur) AS gmv_amount_eur,
FROM orders a
GROUP BY ALL
),
agg_users_2 AS (
SELECT
	a.*,
	a.gmv_amount_eur/a.order_count AS aov_eur,
FROM agg_users a
),
agg_user_buckets AS (
SELECT
	a.*,
	N_TILE(10) OVER (ORDER BY order_count    DESC) AS order_count_bucket,
	N_TILE(5)  OVER (ORDER BY gmv_amount_eur DESC) AS gmv_amount_eur_bucket,
	N_TILE(5)  OVER (ORDER BY aov_eur        DESC) AS aov_eur_bucket,
FROM agg_users_2
),
agg_user_buckets_2 AS (
SELECT
	a.*,
	CONCAT(order_count_bucket, '-', gmv_amount_eur_bucket, '-', aov_eur_bucket) AS bucket_id,
FROM agg_user_buckets
),
agg_hash AS (
SELECT
	a.*
	FARM_FINGERPRINT( CONCAT(a.bucket_id, 'seed_2026-04-21') ) AS bucket_hashed,
	FARM_FINGERPRINT( CONCAT(CAST(a.account_id AS STRING), 'seed_2026-04-21') ) AS account_id_hashed,
FROM agg_user_buckets_2 a
)
agg_rn AS (
SELECT
	a.*,
	
	ROW_NUMBER() OVER w AS rn_user,
	DENSE_RANK() OVER (ORDER BY bucket_hashed) AS rank_bucket, -- all users within the same bucket should get the same value for this
FROM agg_hash a
WINDOW
	w AS (PARTITION BY a.bucket_id ORDER BY a.account_id_hashed) -- Randomize within strata
)

SELECT
	a.*,
	CASE MOD(a.rn_user + DIV(a.rn_user, 2) + a.rank_bucket, 2)
		-- 2. keep alternating between Treatment, Control and Control, Treatment
		-- 3. last-one-out of each strata is assigned to treatment or control randomly
		WHEN 1 THEN 'Treatment'
		ELSE        'Control'
	END AS experiment_group,
FROM agg_rn
```