SELECT user_id
FROM transactions
GROUP BY user_id
HAVING (MAX(transaction_date) :: DATE - MIN(transaction_date) :: DATE = COUNT(transaction_date) - 1) AND
COUNT(transaction_date) >= 3
ORDER BY user_id ASC;