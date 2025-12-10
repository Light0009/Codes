WITH ranking AS (SELECT item_count, order_occurrences,
DENSE_RANK() OVER(ORDER BY order_occurrences DESC) AS o_rank
FROM items_per_order)

SELECT item_count AS mode
FROM ranking
WHERE o_rank = 1
