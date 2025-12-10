WITH ordered AS (SELECT *, RANK() OVER(PARTITION BY user_id ORDER BY transaction_date) AS trans_order 
FROM transactions)

SELECT user_id, spend, transaction_date
FROM ordered
WHERE trans_order = 3;