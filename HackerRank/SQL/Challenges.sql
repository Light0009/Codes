WITH cte AS (SELECT h.hacker_id AS id, h.name AS name, COUNT(DISTINCT(c.challenge_id)) AS total
FROM Hackers h
JOIN Challenges c
ON h.hacker_id = c.hacker_id
GROUP BY h.hacker_id, h.name)

SELECT id, name, total
FROM cte
WHERE total = (SELECT max(total) FROM cte)
OR
total IN (SELECT total FROM cte
GROUP BY total
HAVING COUNT(total) = 1)
ORDER BY total DESC, id
