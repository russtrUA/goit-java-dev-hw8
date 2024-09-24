SELECT 'YOUNGEST' as TYPE
      ,w.name
      ,w.birthday
FROM worker w
WHERE w.birthday = (SELECT max(birthday)
                    FROM worker)
UNION
SELECT 'ELDEST' as TYPE
     ,w.name
     ,w.birthday
FROM worker w
WHERE w.birthday = (SELECT min(birthday)
                    FROM worker)