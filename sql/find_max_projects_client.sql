WITH max_kol AS (SELECT cl.name
                      , count(1) kol
                 FROM client cl
                          JOIN project pr ON cl.id = pr.client_id
                 GROUP BY cl.name)
SELECT name
      ,kol
FROM max_kol
WHERE max_kol.kol = (SELECT max(max_kol.kol)
                     FROM max_kol);