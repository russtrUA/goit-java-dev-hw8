SELECT w.name
     , w.level
     , w.salary
FROM worker w
WHERE w.salary = (SELECT max(salary)
                  FROM worker);