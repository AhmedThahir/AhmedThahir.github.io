# Hierarchical
### Aggregation

- Array concat, array_agg
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
)
```

## Hierarchical De-Duplicated Aggregation
### Method 1

```mysql
WITH
orders AS (
SELECT	
	-- Dimensions: order constant
	a.country_code,
	a.month,
	a.account_id,
	
	-- Measures: order constant
	a.order_id,
	a.gmv_amount_lc,
	
	-- Dimensions: hierarchy varying
	IF(GROUPING(a.dim_1) = 1, 'All', a.dim_1) AS dim_1,
	IF(GROUPING(a.dim_2) = 1, 'All', a.dim_2) AS dim_2,
	
	-- Measures: hierarchy varying
	SUM(a.discount_amount_lc) AS discount_amount_lc,

FROM base a
GROUP BY GROUPING SETS (
	(1, 2, 3, 4, 5),
	(1, 2, 3, 4, 5, a.dim_1, a.dim_2)
)
),
users AS (
SELECT
	-- Dimensions
	a.country_code,
	a.month,
	a.account_id,
	
	a.dim_1,
	a.dim_2,
	
	-- Measures: overall count, primary key
	COUNT(a.order_id) AS order_count,
	-- COUNT(DISTINCT a.order_id) AS order_count_distinct, for validation
	
	-- Measures: overall additive
	SUM(a.gmv_amount_lc) AS gmv_amount_lc,
	SUM(a.discount_amount_lc) AS discount_amount_lc,
	
FROM orders a
GROUP BY ALL -- already has the dim all
),
agg AS (
SELECT
	-- Dimensions
	a.country_code,
	a.month,
	
	a.dim_1,
	a.dim_2,
	
	-- Measures: overall non-additive, overall unique
	COUNT(a.account_id) AS user_count,
	-- COUNT(DISTINCT a.account_id) AS user_count_distinct, -- for validation
	
	-- Measures: overall additive
	SUM(a.order_count) AS order_count,
	SUM(a.gmv_amount_lc) AS gmv_amount_lc,
	SUM(a.discount_amount_lc) AS discount_amount_lc,
	
FROM users a
GROUP BY ALL -- already has the dim all
)
SELECT
	*
FROM agg
```

- This should theoretically avoid the expensive `COUNT(DISTINCT a.account_id)` for different levels, but not necessarily
	- It's just a nice hint to give to the optimizer
	- Else, just directly query from orders, rather can creating user-level

Benchmark dataset

```mysql
CREATE OR REPLACE TABLE `dulcet-antler-481417-p8.test.testing` AS

WITH 
-- 1. Define your small seed values
countries AS (
    SELECT 'US' as country_code, '2026-01' as month UNION ALL
    SELECT 'DE' as country_code, '2026-02' as month
),
dims AS (
    SELECT 'Web' as dim_1, 'Organic' as dim_2 UNION ALL
    SELECT 'Web' as dim_1, 'Paid' as dim_2 UNION ALL
    SELECT 'App' as dim_1, 'Organic' as dim_2 UNION ALL
    SELECT 'App' as dim_1, 'Paid' as dim_2
),
-- 2. Create a sequence of numbers to act as row multipliers
numbers AS (
    SELECT n FROM UNNEST(GENERATE_ARRAY(1, 1000)) as n -- Generates 100 repetitions
),
-- 3. Cross Join to create the base
base AS (
    SELECT 
        c.country_code,
        c.month,
        -- Create unique IDs by concatenating the index
        CONCAT('ACC_', CAST(DIV(n.n, 100) AS STRING)) AS account_id,
        CONCAT('ORD_', CAST(n.n AS STRING), '_', c.country_code) AS order_id,
        -- Generate some semi-random measures
        (n.n * 15.5) AS gmv_amount_lc,
        (n.n * 1.5) AS discount_amount_lc,
        d.dim_1,
        d.dim_2
    FROM countries c
    CROSS JOIN dims d
    CROSS JOIN numbers n
)
-- Now apply your existing Grouping Sets logic...
SELECT * FROM base
```

### Method 2, 3

Does not work when there are multiple hierarchies
- The order_id row for one level may not not be the one selected by the filter

- `ROW_NUMBER() OVER ( PARTITION BY a.order_id ORDER BY NULL ) = 1 AS is_order_unique`
- `CAST(SUM(1 / order_occurence_count) AS INT64)`
