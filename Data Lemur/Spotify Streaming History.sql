WITH combined AS (SELECT c.user_id, c.song_id, COUNT(c.listen_time) AS song_plays
FROM songs_weekly as c
WHERE listen_time < '2022-08-05 00:00:00'
GROUP BY user_id, song_id
UNION ALL
SELECT h.user_id, h.song_id, h.song_plays
FROM songs_history AS h)

SELECT user_id, song_id, SUM(song_plays) AS song_plays
FROM combined
GROUP BY user_id, song_id
ORDER BY song_plays DESC;