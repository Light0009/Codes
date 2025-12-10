SELECT w.id, p.age, w.coins_needed, w.power
FROM Wands AS w
JOIN Wands_Property AS p
ON w.code = p.code
WHERE p.is_evil = 0 AND w.coins_needed = (SELECT MIN(Wands.coins_needed) FROM Wands
JOIN Wands_Property AS wp ON Wands.code = wp.code WHERE wp.age = p.age AND Wands.power = w.power)
ORDER BY w.power DESC, p.age DESC;
