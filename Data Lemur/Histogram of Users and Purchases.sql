WITH ranking AS (SELECT transaction_date, user_id, product_id, 
DENSE_RANK() OVER(PARTITION BY user_id ORDER BY transaction_date DESC) AS date_rank
FROM user_transactions)

SELECT transaction_date, user_id, COUNT(product_id)
FROM ranking
WHERE date_rank = 1
GROUP BY user_id, transaction_date
ORDER BY transaction_date 