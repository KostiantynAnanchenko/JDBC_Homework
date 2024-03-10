SELECT p.ID,
       EXTRACT(YEAR FROM AGE(p.FINISH_DATE, p.START_DATE)) * 12 +
       EXTRACT(MONTH FROM AGE(p.FINISH_DATE, p.START_DATE)) AS MONTH_COUNT
FROM project p
WHERE AGE(p.FINISH_DATE, p.START_DATE) = (
    SELECT MAX(AGE(p2.FINISH_DATE, p2.START_DATE))
    FROM project p2
);