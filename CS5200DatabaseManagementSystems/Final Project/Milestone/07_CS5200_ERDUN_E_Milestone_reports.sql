-- reports.sql

-- 1) Which coffee varietal has received the highest rating for each processing method?
-- This query determines which coffee varietal has received the highest rating for each processing method. It joins tasting logs, products, beans, and ratings tables to compute the highest average rating per processing method.
-- First, compute the average rating of each varietal for every processing method base on tasting logs
MariaDB [CoffeeDB]> SELECT 
    ->     b.processing_method, 
    ->     b.varietal, 
    ->     AVG(rt.rating_id) AS avg_rating
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Products_Beans pb ON p.product_id = pb.product_id
    -> JOIN Beans b ON pb.bean_id = b.bean_id
    -> JOIN Ratings rt ON tl.rating_id = rt.rating_id
    -> GROUP BY b.processing_method, b.varietal;
+-------------------+----------------+------------+
| processing_method | varietal       | avg_rating |
+-------------------+----------------+------------+
| Experimental      | Geisha         |     4.0000 |
| Natural           | Catuai         |     1.0000 |
| Natural           | Geisha         |     1.0000 |
| Natural           | Gesha          |     2.5000 |
| Natural           | Heirloom       |     3.4000 |
| Pulped Natural    | Yellow Bourbon |     2.0000 |
| Washed            | Bourbon        |     3.0000 |
| Washed            | Catuai         |     4.0000 |
| Washed            | Caturra        |     4.0000 |
| Washed            | Heirloom       |     3.0000 |
| Washed            | Pacamara       |     3.0000 |
| Washed            | Pacas          |     2.0000 |
+-------------------+----------------+------------+
12 rows in set (0.007 sec)
-- After have average ratings for all varietals, filter out the highest-rated one for each processing method
MariaDB [CoffeeDB]> SELECT 
    ->     b.processing_method AS `Processing Method`, 
    ->     b.varietal AS `Coffee Varietal`, 
    ->     AVG(rt.rating_id) AS `Avg Rating`
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Products_Beans pb ON p.product_id = pb.product_id
    -> JOIN Beans b ON pb.bean_id = b.bean_id
    -> JOIN Ratings rt ON tl.rating_id = rt.rating_id
    -> GROUP BY b.processing_method, b.varietal
    -> HAVING AVG(rt.rating_id) = (
    ->     SELECT MAX(avg_rating)
    ->     FROM (
    ->         SELECT 
    ->             b2.processing_method, 
    ->             b2.varietal, 
    ->             AVG(rt2.rating_id) AS avg_rating
    ->         FROM TastingLogs tl2
    ->         JOIN Products p2 ON tl2.product_id = p2.product_id
    ->         JOIN Products_Beans pb2 ON p2.product_id = pb2.product_id
    ->         JOIN Beans b2 ON pb2.bean_id = b2.bean_id
    ->         JOIN Ratings rt2 ON tl2.rating_id = rt2.rating_id
    ->         GROUP BY b2.processing_method, b2.varietal
    ->     ) AS avg_ratings
    ->     WHERE avg_ratings.processing_method = b.processing_method
    -> )
    -> ORDER BY b.processing_method;
+-------------------+-----------------+------------+
| Processing Method | Coffee Varietal | Avg Rating |
+-------------------+-----------------+------------+
| Experimental      | Geisha          |     4.0000 |
| Natural           | Heirloom        |     3.4000 |
| Pulped Natural    | Yellow Bourbon  |     2.0000 |
| Washed            | Catuai          |     4.0000 |
| Washed            | Caturra         |     4.0000 |
+-------------------+-----------------+------------+
5 rows in set (0.007 sec)

-- 2) How do different roast levels (Light, Medium, Dark, etc.) impact coffee ratings?
-- This query analyzes the rating distribution for different roast levels. It calculates the average rating for each roast level to see if certain roast levels are generally rated higher than others.
-- First, join the tasting logs, products, and ratings tables to get the roast level and rating ID
MariaDB [CoffeeDB]> SELECT p.roast_level, rt.rating_id
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Ratings rt ON tl.rating_id = rt.rating_id;
+-------------+-----------+
| roast_level | rating_id |
+-------------+-----------+
| Dark        |         1 |
| Medium      |         1 |
| Medium-Dark |         2 |
| Medium-Dark |         2 |
| Medium      |         3 |
| Dark        |         3 |
| Medium      |         4 |
| Medium      |         4 |
| Medium      |         4 |
| Dark        |         5 |
| Medium      |         5 |
+-------------+-----------+
11 rows in set (0.002 sec)
-- Then, calculate the average rating and rating count for each roast level
MariaDB [CoffeeDB]> SELECT p.roast_level, 
    ->        AVG(rt.rating_id) AS avg_rating, 
    ->        COUNT(*) AS rating_count
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Ratings rt ON tl.rating_id = rt.rating_id
    -> GROUP BY p.roast_level
    -> ORDER BY avg_rating DESC;
+-------------+------------+--------------+
| roast_level | avg_rating | rating_count |
+-------------+------------+--------------+
| Medium      |     3.5000 |            6 |
| Dark        |     3.0000 |            3 |
| Medium-Dark |     2.0000 |            2 |
+-------------+------------+--------------+
3 rows in set (0.003 sec)
-- To include roast levels with no ratings, use a LEFT JOIN and COALESCE to handle NULL values
MariaDB [CoffeeDB]> SELECT p.roast_level AS `Roast Level`, 
    ->        COALESCE(AVG(rt.rating_id), 0) AS `Avg Rating`, 
    ->        COUNT(rt.rating_id) AS `Rating Count`
    -> FROM Products p
    -> LEFT JOIN TastingLogs tl ON p.product_id = tl.product_id
    -> LEFT JOIN Ratings rt ON tl.rating_id = rt.rating_id
    -> GROUP BY p.roast_level
    -> ORDER BY COALESCE(AVG(rt.rating_id), 0) DESC;
+-------------+------------+--------------+
| Roast Level | Avg Rating | Rating Count |
+-------------+------------+--------------+
| Medium      |     3.5000 |            6 |
| Dark        |     3.0000 |            3 |
| Medium-Dark |     2.0000 |            2 |
| Light       |     0.0000 |            0 |
+-------------+------------+--------------+
4 rows in set (0.011 sec)

-- 3) Which coffee-producing country has the highest average rating based on recorded tastings?
-- This query finds the highest-rated coffee-producing country based on the average rating of beans sourced from farms in that country.It joins the farms, beans, and tasting logs tables to calculate the average rating per country.
MariaDB [CoffeeDB]> SELECT loc.country AS `Country`,
    ->        COALESCE(AVG(rt.rating_id), 0) AS `Avg Rating`,
    ->        COUNT(rt.rating_id) AS `Rating Count`
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Products_Beans pb ON p.product_id = pb.product_id
    -> JOIN Beans b ON pb.bean_id = b.bean_id
    -> JOIN Farms f ON b.farm_id = f.farm_id
    -> JOIN Locations loc ON f.location_id = loc.location_id
    -> JOIN Ratings rt ON tl.rating_id = rt.rating_id
    -> GROUP BY loc.country
    -> ORDER BY `Avg Rating` DESC, `Rating Count` DESC;
+-----------+------------+--------------+
| Country   | Avg Rating | Rating Count |
+-----------+------------+--------------+
| Nicaragua |     4.0000 |            1 |
| Ethiopia  |     3.3333 |            6 |
| Honduras  |     3.0000 |            2 |
| Guatemala |     3.0000 |            2 |
| Colombia  |     2.5000 |            2 |
| Panama    |     2.0000 |            3 |
| Brazil    |     2.0000 |            1 |
+-----------+------------+--------------+
7 rows in set (0.014 sec)

-- 4) Is there a correlation between the price of coffee (per 100g) and its average rating?
-- This query investigates the correlation between the price of coffee (per 100g) and its average rating.It calculates the average price for each rating level to determine whether higher-priced coffee tends to receive higher ratings.
MariaDB [CoffeeDB]> SELECT 
    ->     rt.rating_id AS `Rating`, 
    ->     COUNT(tl.log_id) AS `Tasting Count`,
    ->     AVG(p.price_per_100g) AS `Avg Price (per 100g)`
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Ratings rt ON tl.rating_id = rt.rating_id
    -> GROUP BY rt.rating_id
    -> ORDER BY rt.rating_id DESC;
+--------+---------------+----------------------+
| Rating | Tasting Count | Avg Price (per 100g) |
+--------+---------------+----------------------+
|      5 |             2 |            11.750000 |
|      4 |             3 |            10.166667 |
|      3 |             2 |            12.000000 |
|      2 |             2 |            10.250000 |
|      1 |             2 |             9.750000 |
+--------+---------------+----------------------+
5 rows in set (0.007 sec)

-- 5) Which coffee roaster produces the highest-rated coffees based on average user ratings?
-- This query examines a specific user's rating history to determine their preferred coffee varietals and roast levels.It aggregates the user's tasting logs and analyzes which varietals and roast levels they have rated the highest.
MariaDB [CoffeeDB]> SELECT 
    ->     r.roaster_name AS `Roaster Name`, 
    ->     COUNT(tl.log_id) AS `Tasting Count`, 
    ->     AVG(rt.rating_id) AS `Avg Rating`
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN Ratings rt ON tl.rating_id = rt.rating_id
    -> GROUP BY r.roaster_name
    -> ORDER BY `Avg Rating` DESC, `Tasting Count` DESC;
+---------------------------+---------------+------------+
| Roaster Name              | Tasting Count | Avg Rating |
+---------------------------+---------------+------------+
| Intelligentsia Coffee     |             3 |     3.3333 |
| Blue Bottle Coffee        |             5 |     3.0000 |
| Stumptown Coffee Roasters |             3 |     3.0000 |
+---------------------------+---------------+------------+
3 rows in set (0.004 sec)




