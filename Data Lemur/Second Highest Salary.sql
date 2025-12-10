WITH ranking AS (SELECT *, DENSE_RANK() OVER(ORDER BY salary DESC) AS s_ranks
FROM employee)

SELECT salary AS second_highest_salary
FROM ranking
WHERE s_ranks = 2;