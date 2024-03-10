WITH youngest AS (
    SELECT 'YOUNGEST' AS TYPE,
           NAME,
           BIRTHDAY
    FROM worker
    WHERE BIRTHDAY = (SELECT MIN(BIRTHDAY) FROM worker)
),
eldest AS (
    SELECT 'ELDEST' AS TYPE,
           NAME,
           BIRTHDAY
    FROM worker
    WHERE BIRTHDAY = (SELECT MAX(BIRTHDAY) FROM worker)
)
SELECT * FROM youngest
UNION ALL
SELECT * FROM eldest;