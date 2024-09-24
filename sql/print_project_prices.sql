SELECT pr.name,
       CAST((EXTRACT(YEAR FROM AGE(pr.finish_date, pr.start_date)) * 12 +
       EXTRACT(MONTH FROM AGE(pr.finish_date, pr.start_date))) * t.total_salary AS INTEGER) AS TOTAL_PRICE
FROM
(SELECT pr.id,
       COALESCE(sum(w.salary), 0) as total_salary

FROM project pr
LEFT JOIN project_worker pw ON pr.id = pw.project_id
LEFT JOIN worker w ON pw.worker_id = w.id
GROUP BY pr.id) t
JOIN project pr ON pr.id = t.id
ORDER BY TOTAL_PRICE DESC