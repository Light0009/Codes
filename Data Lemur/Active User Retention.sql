WITH june AS (SELECT DISTINCT user_id
FROM user_actions
WHERE DATE_PART('month', event_date) = '6' AND (event_type = 'sign-in' OR event_type = 'like' OR event_type = 'comment')),
july AS(SELECT DISTINCT user_id
FROM user_actions
WHERE DATE_PART('month', event_date) = '7' AND (event_type = 'sign-in' OR event_type = 'like' OR event_type = 'comment')),
combine AS (SELECT june.user_id
FROM june
JOIN july
ON june.user_id = july.user_id)

SELECT 7 AS month, COUNT(user_id) AS monthly_active_users
FROM combine