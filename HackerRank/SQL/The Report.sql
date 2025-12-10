SELECT S.Name, G.Grade, S.Marks
FROM Students AS S
LEFT JOIN Grades AS G
ON S.Marks <= G.Max_Mark AND S.Marks >= Min_Mark
WHERE G.Grade >= 8
ORDER BY G.Grade DESC, Name;
SELECT NULL AS Name, G1.Grade, S1.Marks
FROM Students AS S1
LEFT JOIN Grades AS G1
ON S1.Marks <= G1.Max_Mark AND S1.Marks >= Min_Mark
WHERE G1.Grade < 8
ORDER BY G1.Grade DESC, S1.Marks;
