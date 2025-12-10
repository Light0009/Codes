WITH combined AS(SELECT c.caller_id, i.country_id AS caller_country, c.receiver_id, p.country_id AS receiver_country 
FROM phone_calls AS c
LEFT JOIN phone_info AS i
ON c.caller_id = i.caller_id
LEFT JOIN phone_info AS p
ON c.receiver_id = p.caller_id),
pct AS (SELECT caller_id, caller_country, receiver_id, receiver_country,
CASE WHEN caller_country = receiver_country THEN 0 ELSE 1 END AS international
FROM combined)

SELECT ROUND(COUNT(international) FILTER(WHERE international = 1) :: NUMERIC/ COUNT(international) * 100.0, 1) AS international_calls_pct
FROM pct