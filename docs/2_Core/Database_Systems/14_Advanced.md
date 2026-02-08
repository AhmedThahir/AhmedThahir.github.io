## Syntax

### Window Functions

Faster and more readable alternative to self-joins

```
SELECT
	function(col_a) over w AS output
FROM table
WINDOW
	w AS ( PARTITION BY col_b ORDER BY col_c ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED SUCCEEDING )
QUALIFY output > 1
```

3 types
- Navigation function
- Numbering function
- Aggregate functions

Running totals

```mysql
-- risky default
SUM(gmv_amount_lc) OVER( PARTITION BY account_id ORDER BY order_id )
SUM(gmv_amount_lc) OVER( PARTITION BY account_id ORDER BY order_id RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) -- equivalent

-- better
SUM(gmv_amount_lc) OVER( PARTITION BY account_id ORDER BY order_id ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)
```

### Joins

- Semi join
- Anti join
	- `LEFT JOIN b where b.account_id IS NULL`
	- `NOT EXISTS`
	- `NOT IN`
- Correlated subquery
- Except all

### Aggregation

- Grouping sets
- Rollup
- Cube

### Approx Functions

- `APPROX_COUNT_DISTINCT`
- `HYPERLOGLOG++`, etc

## Validation

- Fan-out?
	- Ideally, all these should be equal
		- `COUNT(primary_key)`
			- Use this for best readability, and check with the others
		- `COUNT(DISTINCT primary_key)`
		- `COUNT(1)`
		- `COUNT(*)`
- Numbers?
	- Decompose binary outcomes into components and verify if they add up

## Performance

|                                                                                              | Wrong                                                                                                                                                              | Correct                                                                                                                           |
| -------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------- |
| Expressions in your WHERE clauses should be ordered with the most selective expression first |                                                                                                                                                                    |                                                                                                                                   |
| Better to specify that dates are dates                                                       | `'2025-01-01'`                                                                                                                                                     | `DATE '2025-01-01'`                                                                                                               |
| Use partitioning and clustering                                                              |                                                                                                                                                                    | ![](assets/Pasted%20image%2020260117152834.png)                                                                                   |
| Do not perform functions on filter partition columns                                         | `DATETRUNC(order_date, MONTH) BETWEEN DATE '2025-01-01' AND DATE '2025-12-01'`                                                                                     | `order_date BETWEEN DATE '2025-01-01' AND LAST_DAY(DATE '2025-12-01', MONTH)`                                                     |
| Use clustering                                                                               |                                                                                                                                                                    |                                                                                                                                   |
| Filter before aggregation                                                                    | SELECT *<br>FROM (<br>  SELECT event_date, COUNT(*) AS total<br>  FROM `project.dataset.events`<br>  GROUP BY event_date<br>)<br>WHERE event_date >= '2025-11-01'; | SELECT event_date, COUNT(*) AS total<br>FROM `project.dataset.events`<br>WHERE event_date >= '2025-11-01'<br>GROUP BY event_date; |
artition