SELECT CASE WHEN order_id = (SELECT MAX(order_id) FROM orders) AND order_id % 2 <> 0 THEN order_id
WHEN order_id % 2 = 0 THEN order_id - 1
ELSE order_id + 1 END AS corrected_order_id, item
FROM orders
ORDER BY corrected_order_id