WITH max_data AS (SELECT ticker, TO_CHAR(date, 'Mon-YYYY') AS highest_mth, open AS highest_open, 
RANK() OVER(PARTITION BY ticker ORDER BY open DESC) AS ranking
FROM stock_prices),
min_data AS (SELECT ticker, TO_CHAR(date, 'Mon-YYYY') AS lowest_mth, open AS lowest_open, 
RANK() OVER(PARTITION BY ticker ORDER BY open ASC) AS ranking
FROM stock_prices)

SELECT max_data.ticker, highest_mth, highest_open, lowest_mth, lowest_open
FROM max_data
JOIN min_data
ON max_data.ticker = min_data.ticker AND max_data.ranking = min_data.ranking
WHERE max_data.ranking = 1