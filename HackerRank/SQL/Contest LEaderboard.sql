SELECT h.hacker_id, h.name, SUM(max_table.maximum) AS total
FROM (SELECT s.hacker_id AS id, s.challenge_id AS ci, MAX(s.score) AS maximum
FROM Submissions s
GROUP BY s.hacker_id, s.challenge_id) AS max_table
JOIN Hackers h
ON h.hacker_id = max_table.id
GROUP BY h.hacker_id, h.name
HAVING total > 0
ORDER BY total DESC, h.hacker_id;
