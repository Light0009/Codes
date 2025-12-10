WITH breakdown AS (SELECT DATE(measurement_time) AS measurement_day,
RANK() OVER(PARTITION BY DATE(measurement_time) ORDER BY measurement_time),
measurement_time, measurement_value
FROM measurements)

SELECT measurement_day, SUM(measurement_value) FILTER(WHERE rank % 2 <> 0) AS odd_sum,
SUM(measurement_value) FILTER(WHERE rank % 2 = 0) AS even_sum
FROM breakdown
GROUP BY measurement_day