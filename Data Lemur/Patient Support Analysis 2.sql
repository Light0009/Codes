SELECT ROUND(COUNT(case_id) FILTER(WHERE call_category = 'n/a' OR call_category ISNULL) ::NUMERIC / 
COUNT(case_id) * 100.0, 1) AS uncategorised_call_pct
FROM callers;