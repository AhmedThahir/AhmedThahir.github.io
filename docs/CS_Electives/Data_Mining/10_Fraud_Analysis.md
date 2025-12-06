
## Account Sharing

```sql
WITH RECURSIVE dim_account AS (
  -- Cluster A: The "Chain" (1 links to 3 via Email, 3 links to 10 via Phone, 10 links to 11 via Email)
  SELECT 1 AS account_id, 'user_a@test.com' AS email, '111-111' AS phone UNION ALL
  SELECT 3, 'user_a@test.com', '222-222' AS phone UNION ALL
  SELECT 10, 'user_q@test.com', '222-222' AS phone UNION ALL
  SELECT 11, 'user_q@test.com', '999-999' AS phone UNION ALL

  -- Cluster B: The "Duplicates" (Identical info, should merge into one)
  SELECT 5, 'shared@test.com', '555-555' UNION ALL
  SELECT 6, 'shared@test.com', '555-555' UNION ALL

  -- Cluster C: The "Star" (Multiple accounts all tied to one central phone number)
  SELECT 20, 'ceo@corp.com', '000-000' UNION ALL
  SELECT 21, 'hr@corp.com', '000-000' UNION ALL
  SELECT 22, 'dev@corp.com', '000-000' UNION ALL

  -- The "Orphans" (Should be filtered out)
  SELECT 99, 'lonely@test.com', '987-654' UNION ALL
  SELECT 100, 'solo@test.com', '012-345'
),


-- Step 1: Attribute-Level Bridging
-- We find the minimum ID for every cluster of Email and every cluster of Phone.
-- This ensures exhaustiveness while drastically reducing the initial edge count.
-- NEW STEP: Filter out orphans immediately
-- We only keep accounts where the email or phone appears more than once in the filtered_accountstaset.
filtered_accounts AS (
SELECT
    *,
    COUNT(1) OVER(PARTITION BY email) AS email_count,
    COUNT(1) OVER(PARTITION BY phone) AS phone_count,
FROM dim_account
QUALIFY GREATEST(
    email_count,
    phone_count
) > 1
),

-- Step 1: Attribute-Level Bridging (Now using filtered_accounts)
raw_bridges AS (
  SELECT 
    account_id,
    MIN(account_id) OVER(PARTITION BY email) as email_min,
    MIN(account_id) OVER(PARTITION BY phone) as phone_min
  FROM filtered_accounts
),

-- Step 2: Minimal Edge List
-- We only create an edge if an account bridges two different minimums.
-- We also ensure a 'downhill' direction (src > dst) to guarantee termination.
edges AS (
  SELECT DISTINCT src, dst
  FROM (
    SELECT account_id AS src, email_min AS dst FROM raw_bridges
    UNION ALL
    SELECT account_id AS src, phone_min AS dst FROM raw_bridges
  )
  WHERE src > dst
),

-- Step 3: Efficient Recursion
-- Because the edge list is pruned to only "meaningful" shifts, 
-- the recursion depth is minimized.
iteration AS (
  -- Base case: Every account starts as its own potential root
  SELECT account_id AS node, account_id AS root
  FROM filtered_accounts

  UNION ALL

  -- Recursive step: Join current root to a smaller target (dst)
  SELECT i.node, e.dst
  FROM iteration i
  INNER JOIN edges e ON i.root = e.src
),

-- Step 4: Final Grouping
groupings AS (
  SELECT 
    node AS account_id,
    MIN(root) AS group_id,
  FROM iteration
  GROUP BY ALL
),

-- Step 5: Aggregation
-- Group by the group_id to collect all associated accounts and contact info

agg AS (
SELECT
    g.group_id,

    STRING_AGG(DISTINCT CAST(g.account_id AS STRING), ', ' ORDER BY CAST(g.account_id AS STRING)) AS account_ids,
    
    -- Only aggregate emails that appear > 1 time in the whole filtered_accountstaset
    STRING_AGG(DISTINCT CASE WHEN filtered_accounts.email_count > 1 THEN filtered_accounts.email END, ', ' ORDER BY CASE WHEN filtered_accounts.email_count > 1 THEN filtered_accounts.email END) AS repeated_emails,
    
    -- Only aggregate phones that appear > 1 time in the whole filtered_accountstaset
    STRING_AGG(DISTINCT CASE WHEN filtered_accounts.phone_count > 1 THEN filtered_accounts.phone END, ', '  ORDER BY CASE WHEN filtered_accounts.phone_count > 1 THEN filtered_accounts.phone END) AS repeated_phone_numbers
FROM groupings g
LEFT JOIN filtered_accounts ON filtered_accounts.account_id = g.account_id
GROUP BY g.group_id
ORDER BY g.group_id
)

SELECT *
FROM agg;
```


## References

- [ ] https://youtu.be/ylUaoy1musw?si=MNQE0EpHctlB1D0e