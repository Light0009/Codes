WITH ranking AS (SELECT category, product, SUM(spend) AS total_spend, DENSE_RANK() OVER (PARTITION BY category ORDER BY SUM(spend) DESC) AS rank_in_cat
FROM product_spend
WHERE DATE_PART('year', transaction_date) = '2022'
GROUP BY category, product)

SELECT category, product, total_spend
FROM ranking
WHERE rank_in_cat <= 2;