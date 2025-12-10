WITH ranking AS (SELECT d.department_name, e.name, e.salary, DENSE_RANK() OVER(PARTITION BY e.department_id ORDER BY salary DESC) AS s_rank
FROM employee AS e
JOIN department AS d
ON e.department_id = d.department_id)

SELECT department_name, name, salary
FROM ranking
WHERE s_rank <= 3
ORDER BY department_name ASC, salary DESC, name ASC; 