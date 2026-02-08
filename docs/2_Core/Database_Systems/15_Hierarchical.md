# Hierarchical
### Aggregation

- Grouping sets
- Rollup
- Cube

```mysql
SELECT
  IF(GROUPING(a.country_code)=1, 'All', a.country_code) AS country_code,
  COUNT(a.order_id),
  SUM(a.gmv_amount_lc),
FROM base a
GROUP BY GROUPING SETS (
  (),
  (a.country_code)
```

## Hierarchical De-Duplicated Aggregation
### Method 1

```mysql
WITH agg_pre AS (
SELECT
	a.order_id,
	
	-- flags: order constant
	ANY_VALUE(a.country_code) AS country_code,
	ANY_VALUE(a.order_date  ) AS month,
	
	-- flags: hierarchy varying
	IF(GROUPING(a.b2b_type    ) = 1, 'All', a.b2b_type    ) AS b2b_type,
	IF(GROUPING(a.b2b_model   ) = 1, 'All', a.b2b_model   ) AS b2b_model,
	IF(GROUPING(a.partner_name) = 1, 'All', a.partner_name) AS partner_name,
	
	-- metrics: order constant
	ANY_VALUE(a.account_id   ) AS account_id,
	ANY_VALUE(a.gmv_amount_lc) AS gmv_amount_lc,

	-- metrics: hierarchy varying
	SUM(a.credit_used_amount_lc) AS credit_used_amount_lc,

FROM base a
GROUP BY GROUPING SETS (
	(a.order_id),
	(a.order_id, a.b2b_type, a.b2b_model, a.partner_name)
)
)
SELECT
	-- flags
	a.country_code,
	a.month,
	
	a.b2b_type,
	a.b2b_model,
	a.partner_name,
	
	-- metrics: overall non-additive, primary key
	COUNT(*) AS order_count,
	-- COUNT(DISTINCT a.order_id) AS order_count_distinct -- for validation
	
	-- metrics: overall non-additive, overall unique
	COUNT(DISTINCT a.account_id) AS user_count,
	
	-- metrics: overall additive
	SUM(a.gmv_amount_lc) AS gmv_amount_lc,
	SUM(a.credit_used_amount_lc) AS credit_used_amount_lc,
	
FROM agg_pre a
GROUP BY ALL
```

### Method 2, 3

Does not work when there are multiple hierarchies
- The order_id row for one level may not not be the one selected by the filter

- `ROW_NUMBER() OVER ( PARTITION BY a.order_id ORDER BY NULL ) = 1 AS is_order_unique`
- `CAST(SUM(1 / order_occurence_count) AS INT64)`
