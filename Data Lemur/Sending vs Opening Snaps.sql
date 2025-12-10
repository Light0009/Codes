WITH breakdown AS (SELECT user_id,
SUM(time_spent) FILTER (WHERE activity_type = 'open') AS open,
SUM(time_spent) FILTER (WHERE activity_type = 'send') AS send,
SUM(time_spent) FILTER (WHERE activity_type = 'send' OR activity_type = 'open') AS total
FROM activities
GROUP BY user_id)

SELECT age_bucket, ROUND(SUM(send) / SUM(total) * 100.0, 2) AS send_perc, ROUND(SUM(open) / SUM(total) * 100.0, 2) AS open_perc
FROM breakdown
JOIN age_breakdown
ON breakdown.user_id = age_breakdown.user_id
GROUP BY age_bucket