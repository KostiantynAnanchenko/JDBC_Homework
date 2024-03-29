WITH project_prices AS (
    SELECT p.ID,
           SUM(w.SALARY) * (EXTRACT(YEAR FROM p.FINISH_DATE) * 12 + EXTRACT(MONTH FROM p.FINISH_DATE) - EXTRACT(YEAR FROM p.START_DATE) * 12 - EXTRACT(MONTH FROM p.START_DATE)) AS PRICE
    FROM project p
    JOIN project_worker pw ON p.ID = pw.PROJECT_ID
    JOIN worker w ON pw.WORKER_ID = w.ID
    GROUP BY p.ID
)
SELECT ID, PRICE
FROM project_prices
ORDER BY PRICE DESC;