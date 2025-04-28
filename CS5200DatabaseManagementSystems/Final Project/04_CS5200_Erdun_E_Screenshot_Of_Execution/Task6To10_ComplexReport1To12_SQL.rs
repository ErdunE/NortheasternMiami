question 6
MariaDB [beanvibes]> SELECT * FROM Location RIGHT JOIN RegionLocation ON Location.location_id = RegionLocation.location_id;
+-------------+-------------+-------------+-----------------+
| location_id | country     | location_id | region          |
+-------------+-------------+-------------+-----------------+
|           9 | Panama      |           9 | Finca Isabel    |
|          15 | Colombia    |          15 | Huila           |
|          16 | Ethiopia    |          16 | Gedeb           |
|          17 | Colombia    |          17 | Huila           |
|          18 | Tanzania    |          18 | Mbeya           |
|          19 | Kenya       |          19 | Kirinyaga       |
|          20 | Ecuador     |          20 | Loja            |
|          22 | Philippines |          22 | Sitio San Roque |
|          24 | Costa Rica  |          24 | Tarrazú         |
+-------------+-------------+-------------+-----------------+
9 rows in set (0.006 sec)

question 7 
MariaDB [beanvibes]> SELECT rating_id, emoji, description
    -> FROM Rating
    -> ORDER BY rating_id DESC
    -> LIMIT 2;
+-----------+-------+----------------------+
| rating_id | emoji | description          |
+-----------+-------+----------------------+
|         5 | 🤩     | Excellent / Loved it |
|         4 | 😋     | Pretty good          |
+-----------+-------+----------------------+
2 rows in set (0.005 sec)


question 8
MariaDB [beanvibes]> SELECT v.name AS varietal_name, COUNT(*) AS top_rating_count
    -> FROM TastingLogs tl
    -> JOIN RoastBatch rb ON tl.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN ProductBean pb ON p.product_id = pb.product_id
    -> JOIN Beans b ON pb.bean_id = b.bean_id
    -> JOIN Varietal v ON b.varietal_id = v.varietal_id
    -> WHERE tl.rating_id IN (4, 5)
    -> GROUP BY v.name
    -> ORDER BY top_rating_count DESC;
+---------------+------------------+
| varietal_name | top_rating_count |
+---------------+------------------+
| Caturra       |               12 |
| Bourbon       |                5 |
| Catuai        |                4 |
| Típica        |                4 |
| Geisha        |                3 |
| Castillo      |                1 |
| Heirloom      |                1 |
| Landrace      |                1 |
| Sidra         |                1 |
+---------------+------------------+
9 rows in set (0.006 sec)

Question 9
MariaDB [beanvibes]> SELECT tl.log_id, tl.test_date, tl.rating_id, r.name AS roaster_name
    -> FROM TastingLogs tl
    -> JOIN RoastBatch rb ON tl.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> WHERE r.name = 'De Fer Coffee & Tea'
    ->   AND tl.test_date BETWEEN '2024-01-01' AND '2025-04-13';
+--------+------------+-----------+---------------------+
| log_id | test_date  | rating_id | roaster_name        |
+--------+------------+-----------+---------------------+
|      3 | 2025-01-01 |         4 | De Fer Coffee & Tea |
|      4 | 2025-04-12 |         1 | De Fer Coffee & Tea |
|      5 | 2025-04-12 |         5 | De Fer Coffee & Tea |
|      6 | 2025-04-12 |         3 | De Fer Coffee & Tea |
|      7 | 2025-04-12 |         4 | De Fer Coffee & Tea |
|      8 | 2025-04-12 |         5 | De Fer Coffee & Tea |
|      9 | 2025-04-13 |         2 | De Fer Coffee & Tea |
|     10 | 2025-02-21 |         4 | De Fer Coffee & Tea |
|     11 | 2025-02-18 |         4 | De Fer Coffee & Tea |
+--------+------------+-----------+---------------------+
9 rows in set (0.003 sec)

Question 10
MariaDB [beanvibes]> SELECT
    ->     p.product_id,
    ->     p.name AS product_name,
    ->     r.name AS roaster_name,
    ->     COUNT(DISTINCT tn.note_id) AS matched_note_count
    -> FROM Products p
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN ProductTastingNote ptn ON p.product_id = ptn.product_id
    -> JOIN TastingNote tn ON ptn.note_id = tn.note_id
    -> JOIN ProductBean pb ON p.product_id = pb.product_id
    -> JOIN Beans b ON pb.bean_id = b.bean_id
    -> JOIN ProcessMethod pm ON b.process_method_id = pm.process_method_id
    -> WHERE LOWER(tn.note_name) IN ('chocolate', 'apple', 'brown sugar')
    ->   AND LOWER(pm.name) = 'washed'
    -> GROUP BY p.product_id
    -> ORDER BY matched_note_count DESC;
+------------+--------------------+---------------------+--------------------+
| product_id | product_name       | roaster_name        | matched_note_count |
+------------+--------------------+---------------------+--------------------+
|          2 | From Panama To PGH | De Fer Coffee & Tea |                  3 |
|         33 | Geisha Lot 4       | Prodigal            |                  1 |
|         34 | Philippines Set    | Blue Bottle Coffee  |                  1 |
+------------+--------------------+---------------------+--------------------+

Complex 1
Brew Method Recommendation Via Home Page
MariaDB [beanvibes]> SELECT 
    ->     p.product_id,
    ->     p.name AS product_name,
    ->     r.name AS roaster_name,
    ->     bm.name AS brew_method,
    ->     COUNT(*) AS total_logs,
    ->     ROUND(AVG(rating.rating_id), 2) AS avg_rating
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN BrewMethod bm ON t.brew_method_id = bm.brew_method_id
    -> JOIN Rating rating ON t.rating_id = rating.rating_id
    -> JOIN (
    ->     SELECT bm2.name
    ->     FROM TastingLogs t2
    ->     JOIN BrewMethod bm2 ON t2.brew_method_id = bm2.brew_method_id
    ->     WHERE t2.user_id = 2
    ->     GROUP BY bm2.name
    ->     ORDER BY COUNT(*) DESC
    ->     LIMIT 1
    -> ) AS top_brew ON LOWER(bm.name) = LOWER(top_brew.name)
    -> WHERE t.user_id <> 2
    -> GROUP BY p.product_id, p.name, r.name, bm.name
    -> ORDER BY avg_rating DESC, total_logs DESC
    -> LIMIT 5;
+------------+-----------------+--------------------+-------------+------------+------------+
| product_id | product_name    | roaster_name       | brew_method | total_logs | avg_rating |
+------------+-----------------+--------------------+-------------+------------+------------+
|         34 | Philippines Set | Blue Bottle Coffee | Cold Brew   |          1 |       4.00 |
+------------+-----------------+--------------------+-------------+------------+------------+
1 row in set (0.004 sec)


Complex 2
Varietal Recommendation Via Home Page
MariaDB [beanvibes]> SELECT 
    ->     p.product_id,
    ->     p.name AS product_name,
    ->     r.name AS roaster_name,
    ->     v.name AS varietal_name,
    ->     COUNT(*) AS total_logs,
    ->     ROUND(AVG(rt.rating_id), 2) AS avg_rating
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN ProductBean pb ON p.product_id = pb.product_id
    -> JOIN Beans b ON pb.bean_id = b.bean_id
    -> JOIN Varietal v ON b.varietal_id = v.varietal_id
    -> JOIN Rating rt ON t.rating_id = rt.rating_id
    -> WHERE t.user_id <> 2
    ->   AND v.name = (
    ->       SELECT v2.name
    ->       FROM TastingLogs t2
    ->       JOIN RoastBatch rb2 ON t2.batch_id = rb2.batch_id
    ->       JOIN Products p2 ON rb2.product_id = p2.product_id
    ->       JOIN ProductBean pb2 ON p2.product_id = pb2.product_id
    ->       JOIN Beans b2 ON pb2.bean_id = b2.bean_id
    ->       JOIN Varietal v2 ON b2.varietal_id = v2.varietal_id
    ->       WHERE t2.user_id = 2
    ->       GROUP BY v2.name
    ->       ORDER BY COUNT(*) DESC
    ->       LIMIT 1
    ->   )
    -> GROUP BY p.product_id, p.name, r.name, v.name
    -> ORDER BY avg_rating DESC, total_logs DESC
    -> LIMIT 5;
+------------+----------------------+-----------------+---------------+------------+------------+
| product_id | product_name         | roaster_name    | varietal_name | total_logs | avg_rating |
+------------+----------------------+-----------------+---------------+------------+------------+
|         28 | Aponte Village       | Onyx Coffee Lab | Caturra       |          1 |       3.00 |
|         35 | Velvet Bliss         | Prodigal        | Caturra       |          1 |       3.00 |
|         39 | Cordillera Del Fuego | Broadsheet      | Caturra       |          1 |       3.00 |
+------------+----------------------+-----------------+---------------+------------+------------+
3 rows in set (0.007 sec)

Complex 3
Rating Recommendation Via Home Page
MariaDB [beanvibes]> SELECT 
    ->     p.product_id,
    ->     p.name AS product_name,
    ->     r.name AS roaster_name,
    ->     ROUND(AVG(t.rating_id), 2) AS avg_rating,
    ->     COUNT(*) AS log_count
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> WHERE t.user_id IN (
    ->     SELECT DISTINCT t2.user_id
    ->     FROM TastingLogs t2
    ->     JOIN RoastBatch rb2 ON t2.batch_id = rb2.batch_id
    ->     WHERE rb2.product_id = (
    ->         SELECT rb3.product_id
    ->         FROM TastingLogs t3
    ->         JOIN RoastBatch rb3 ON t3.batch_id = rb3.batch_id
    ->         WHERE t3.user_id = 2
    ->           AND t3.rating_id IN (4, 5)
    ->         GROUP BY rb3.product_id
    ->         ORDER BY AVG(t3.rating_id) DESC, COUNT(*) DESC
    ->         LIMIT 1
    ->     )
    ->     AND t2.rating_id IN (4, 5)
    ->     AND t2.user_id != 2
    -> )
    -> AND p.product_id != (
    ->     SELECT rb3.product_id
    ->     FROM TastingLogs t3
    ->     JOIN RoastBatch rb3 ON t3.batch_id = rb3.batch_id
    ->     WHERE t3.user_id = 2
    ->       AND t3.rating_id IN (4, 5)
    ->     GROUP BY rb3.product_id
    ->     ORDER BY AVG(t3.rating_id) DESC, COUNT(*) DESC
    ->     LIMIT 1
    -> )
    -> AND t.rating_id IN (4, 5)
    -> GROUP BY p.product_id, p.name, r.name
    -> ORDER BY avg_rating DESC, log_count DESC
    -> LIMIT 5;
+------------+-----------------+---------------------+------------+-----------+
| product_id | product_name    | roaster_name        | avg_rating | log_count |
+------------+-----------------+---------------------+------------+-----------+
|         31 | Vinka Sidra     | Prodigal            |       5.00 |         1 |
|         22 | Chami           | De Fer Coffee & Tea |       4.00 |         1 |
|         37 | Arbegona        | Broadsheet          |       4.00 |         1 |
|         23 | Worka Sakaro    | De Fer Coffee & Tea |       4.00 |         1 |
|         32 | Hachi Geisha #1 | Prodigal            |       4.00 |         1 |
+------------+-----------------+---------------------+------------+-----------+
5 rows in set (0.008 sec)

Complex 4
Roaster Recommendation Via Home Page
MariaDB [beanvibes]> SELECT 
    ->     p.product_id,
    ->     p.name AS product_name,
    ->     r.name AS roaster_name,
    ->     ROUND(AVG(t.rating_id), 2) AS avg_rating,
    ->     COUNT(*) AS log_count
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> WHERE t.user_id IN (
    ->     SELECT DISTINCT t2.user_id
    ->     FROM TastingLogs t2
    ->     JOIN RoastBatch rb2 ON t2.batch_id = rb2.batch_id
    ->     WHERE rb2.product_id = (
    ->         SELECT rb3.product_id
    ->         FROM TastingLogs t3
    ->         JOIN RoastBatch rb3 ON t3.batch_id = rb3.batch_id
    ->         WHERE t3.user_id = 2
    ->           AND t3.rating_id IN (4, 5)
    ->         GROUP BY rb3.product_id
    ->         ORDER BY AVG(t3.rating_id) DESC, COUNT(*) DESC
    ->         LIMIT 1
    ->     )
    ->     AND t2.rating_id IN (4, 5)
    ->     AND t2.user_id != 2
    -> )
    -> AND p.product_id != (
    ->     SELECT rb3.product_id
    ->     FROM TastingLogs t3
    ->     JOIN RoastBatch rb3 ON t3.batch_id = rb3.batch_id
    ->     WHERE t3.user_id = 2
    ->       AND t3.rating_id IN (4, 5)
    ->     GROUP BY rb3.product_id
    ->     ORDER BY AVG(t3.rating_id) DESC, COUNT(*) DESC
    ->     LIMIT 1
    -> )
    -> AND t.rating_id IN (4, 5)
    -> GROUP BY p.product_id, p.name, r.name
    -> ORDER BY avg_rating DESC, log_count DESC
    -> LIMIT 5;
+------------+-----------------+---------------------+------------+-----------+
| product_id | product_name    | roaster_name        | avg_rating | log_count |
+------------+-----------------+---------------------+------------+-----------+
|         31 | Vinka Sidra     | Prodigal            |       5.00 |         1 |
|         22 | Chami           | De Fer Coffee & Tea |       4.00 |         1 |
|         37 | Arbegona        | Broadsheet          |       4.00 |         1 |
|         23 | Worka Sakaro    | De Fer Coffee & Tea |       4.00 |         1 |
|         32 | Hachi Geisha #1 | Prodigal            |       4.00 |         1 |
+------------+-----------------+---------------------+------------+-----------+
5 rows in set (0.008 sec)

Complex 5
Colombia Recommendation Via Search Page
MariaDB [beanvibes]> SELECT                                                                                                                                                                                                           ->     p.product_id AS productId,
    ->     p.name AS productName,
    ->     r.name AS roasterName,
    ->     ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->     COUNT(*) AS logCount
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN Rating rt ON t.rating_id = rt.rating_id
    -> JOIN ProductBean pb ON p.product_id = pb.product_id
    -> JOIN Beans b ON pb.bean_id = b.bean_id
    -> JOIN Farms f ON b.farm_id = f.farm_id
    -> JOIN Location l ON f.location_id = l.location_id
    -> WHERE LOWER(l.country) = 'colombia'
    -> GROUP BY p.product_id, p.name, r.name
    -> ORDER BY avgRating DESC, logCount DESC
    -> LIMIT 5;
+-----------+-----------------+---------------------+-----------+----------+
| productId | productName     | roasterName         | avgRating | logCount |
+-----------+-----------------+---------------------+-----------+----------+
|        33 | Geisha Lot 4    | Prodigal            |      4.00 |        1 |
|        24 | Aroma Nativo    | Hatch Coffee        |      4.00 |        1 |
|        22 | Chami           | De Fer Coffee & Tea |      4.00 |        1 |
|        27 | Tio Conejo      | Onyx Coffee Lab     |      4.00 |        1 |
|        32 | Hachi Geisha #1 | Prodigal            |      4.00 |        1 |
+-----------+-----------------+---------------------+-----------+----------+
5 rows in set (0.006 sec)

Complex 5
Roaster Recommendation Via Search Page
MariaDB [beanvibes]> SELECT
    ->     p.product_id AS productId,
    ->     p.name AS productName,
    ->     r.name AS roasterName,
    ->     ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->     COUNT(*) AS logCount
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN Rating rt ON t.rating_id = rt.rating_id
    -> GROUP BY r.roaster_id, p.product_id
    -> ORDER BY logCount DESC, avgRating DESC
    -> LIMIT 5;
+-----------+--------------------+---------------------+-----------+----------+
| productId | productName        | roasterName         | avgRating | logCount |
+-----------+--------------------+---------------------+-----------+----------+
|         2 | From Panama To PGH | De Fer Coffee & Tea |      3.50 |        8 |
|        34 | Philippines Set    | Blue Bottle Coffee  |      3.00 |        2 |
|        31 | Vinka Sidra        | Prodigal            |      5.00 |        1 |
|        32 | Hachi Geisha #1    | Prodigal            |      4.00 |        1 |
|        23 | Worka Sakaro       | De Fer Coffee & Tea |      4.00 |        1 |
+-----------+--------------------+---------------------+-----------+----------+
5 rows in set (0.006 sec)

Complex 6
Cold Brew Recommendation Via Search Page
MariaDB [beanvibes]> SELECT
    ->     p.product_id AS productId,
    ->     p.name AS productName,
    ->     r.name AS roasterName,
    ->     ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->     COUNT(*) AS logCount
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN Rating rt ON t.rating_id = rt.rating_id
    -> GROUP BY r.roaster_id, p.product_id
    -> ORDER BY logCount DESC, avgRating DESC
    -> LIMIT 5;
+-----------+--------------------+---------------------+-----------+----------+
| productId | productName        | roasterName         | avgRating | logCount |
+-----------+--------------------+---------------------+-----------+----------+
|         2 | From Panama To PGH | De Fer Coffee & Tea |      3.50 |        8 |
|        34 | Philippines Set    | Blue Bottle Coffee  |      3.00 |        2 |
|        31 | Vinka Sidra        | Prodigal            |      5.00 |        1 |
|        32 | Hachi Geisha #1    | Prodigal            |      4.00 |        1 |
|        23 | Worka Sakaro       | De Fer Coffee & Tea |      4.00 |        1 |
+-----------+--------------------+---------------------+-----------+----------+
5 rows in set (0.006 sec)

Complex 7
Cold Brew Recommendation Via Search Page
MariaDB [beanvibes]> SELECT
    ->     p.product_id AS productId,
    ->     p.name AS productName,
    ->     r.name AS roasterName,
    ->     ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->     COUNT(*) AS logCount
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN Rating rt ON t.rating_id = rt.rating_id
    -> JOIN BrewMethod bm ON t.brew_method_id = bm.brew_method_id
    -> WHERE LOWER(bm.name) = 'cold brew'
    -> GROUP BY p.product_id, r.roaster_id
    -> ORDER BY logCount DESC, avgRating DESC
    -> LIMIT 5;
+-----------+--------------------+---------------------+-----------+----------+
| productId | productName        | roasterName         | avgRating | logCount |
+-----------+--------------------+---------------------+-----------+----------+
|         2 | From Panama To PGH | De Fer Coffee & Tea |      3.75 |        4 |
|        28 | Aponte Village     | Onyx Coffee Lab     |      5.00 |        1 |
|        34 | Philippines Set    | Blue Bottle Coffee  |      4.00 |        1 |
+-----------+--------------------+---------------------+-----------+----------+
3 rows in set (0.006 sec)

Complex 8
Fruit Flavor Recommendation Via Search Page
MariaDB [beanvibes]> SELECT
    ->     p.product_id AS productId,
    ->     p.name AS productName,
    ->     r.name AS roasterName,
    ->     ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->     COUNT(*) AS logCount
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN Rating rt ON t.rating_id = rt.rating_id
    -> JOIN TastingLogNote tln ON t.log_id = tln.log_id
    -> JOIN TastingNote tn ON tln.note_id = tn.note_id
    -> WHERE LOWER(tn.note_name) REGEXP 'berry|fruit|peach|cherry|grape|plum|apple|apricot|blueberry|cranberry|lychee|mango|melon|orange|pear|pineapple|guava|lemon|raspberry|strawberry|tropical|watermelon'
    -> GROUP BY p.product_id, r.roaster_id
    -> ORDER BY logCount DESC, avgRating DESC
    -> LIMIT 5;
+-----------+--------------------+---------------------+-----------+----------+
| productId | productName        | roasterName         | avgRating | logCount |
+-----------+--------------------+---------------------+-----------+----------+
|         2 | From Panama To PGH | De Fer Coffee & Tea |      3.29 |        7 |
|        28 | Aponte Village     | Onyx Coffee Lab     |      5.00 |        2 |
|        31 | Vinka Sidra        | Prodigal            |      5.00 |        1 |
|        37 | Arbegona           | Broadsheet          |      4.00 |        1 |
|        24 | Aroma Nativo       | Hatch Coffee        |      4.00 |        1 |
+-----------+--------------------+---------------------+-----------+----------+
5 rows in set (0.011 sec)

Complex 9
High Rating Recommendation Via Search Page
MariaDB [beanvibes]> SELECT 
    ->         p.product_id AS productId,
    ->         p.name AS productName,
    ->         r.name AS roasterName,
    ->         ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->         COUNT(*) AS logCount
    ->     FROM TastingLogs t
    ->     JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    ->     JOIN Products p ON rb.product_id = p.product_id
    ->     JOIN Roasters r ON p.roaster_id = r.roaster_id
    ->     JOIN Rating rt ON t.rating_id = rt.rating_id
    ->     WHERE rt.rating_id = 5
    ->     GROUP BY p.product_id, r.roaster_id
    ->     ORDER BY logCount DESC, avgRating DESC
    ->     LIMIT 5
    -> ;
+-----------+--------------------+---------------------+-----------+----------+
| productId | productName        | roasterName         | avgRating | logCount |
+-----------+--------------------+---------------------+-----------+----------+
|         2 | From Panama To PGH | De Fer Coffee & Tea |      5.00 |        2 |
|        31 | Vinka Sidra        | Prodigal            |      5.00 |        1 |
|        28 | Aponte Village     | Onyx Coffee Lab     |      5.00 |        1 |
+-----------+--------------------+---------------------+-----------+----------+
3 rows in set (0.006 sec)

Complex 10
High Rating this week Recommendation Via Search Page
MariaDB [beanvibes]> SELECT
    ->     p.product_id AS productId,
    ->     p.name AS productName,
    ->     r.name AS roasterName,
    ->     ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->     COUNT(*) AS logCount
    -> FROM TastingLogs t
    -> JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    -> JOIN Products p ON rb.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN Rating rt ON t.rating_id = rt.rating_id
    -> WHERE t.test_date >= CURDATE() - INTERVAL 7 DAY
    -> GROUP BY p.product_id, r.roaster_id
    -> ORDER BY logCount DESC, avgRating DESC
    -> LIMIT 5;
+-----------+--------------------+---------------------+-----------+----------+
| productId | productName        | roasterName         | avgRating | logCount |
+-----------+--------------------+---------------------+-----------+----------+
|         2 | From Panama To PGH | De Fer Coffee & Tea |      3.43 |        7 |
|        28 | Aponte Village     | Onyx Coffee Lab     |      5.00 |        1 |
|        34 | Philippines Set    | Blue Bottle Coffee  |      2.00 |        1 |
+-----------+--------------------+---------------------+-----------+----------+
3 rows in set (0.006 sec)

Complex 11
Before 2024 Recommendation Via Search Page
MariaDB [beanvibes]> SELECT
    ->         p.product_id AS productId,
    ->         p.name AS productName,
    ->         r.name AS roasterName,
    ->         ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->         COUNT(*) AS logCount
    ->     FROM TastingLogs t
    ->     JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    ->     JOIN Products p ON rb.product_id = p.product_id
    ->     JOIN Roasters r ON p.roaster_id = r.roaster_id
    ->     JOIN Rating rt ON t.rating_id = rt.rating_id
    ->     WHERE t.test_date < '2024-01-01'
    ->     GROUP BY p.product_id, r.roaster_id
    ->     ORDER BY logCount DESC, avgRating DESC
    ->     LIMIT 5
    -> ;
+-----------+----------------------+-------------+-----------+----------+
| productId | productName          | roasterName | avgRating | logCount |
+-----------+----------------------+-------------+-----------+----------+
|        37 | Arbegona             | Broadsheet  |      4.00 |        1 |
|        38 | Wilton Benitez       | Broadsheet  |      3.00 |        1 |
|        39 | Cordillera Del Fuego | Broadsheet  |      3.00 |        1 |
+-----------+----------------------+-------------+-----------+----------+
3 rows in set (0.008 sec)

Complex 12
New User Recommendation Via Search Page
MariaDB [beanvibes]> SELECT
    ->         p.product_id AS productId,
    ->         p.name AS productName,
    ->         r.name AS roasterName,
    ->         ROUND(AVG(rt.rating_id), 2) AS avgRating,
    ->         COUNT(*) AS logCount
    ->     FROM TastingLogs t
    ->     JOIN RoastBatch rb ON t.batch_id = rb.batch_id
    ->     JOIN Products p ON rb.product_id = p.product_id
    ->     JOIN Roasters r ON p.roaster_id = r.roaster_id
    ->     JOIN Rating rt ON t.rating_id = rt.rating_id
    ->     WHERE t.test_date >= CURDATE() - INTERVAL 60 DAY
    ->     GROUP BY p.product_id, r.roaster_id
    ->     HAVING COUNT(DISTINCT t.user_id) >= 2 AND avgRating >= 3.5
    ->     ORDER BY logCount DESC, avgRating DESC
    ->     LIMIT 5;
+-----------+----------------+-----------------+-----------+----------+
| productId | productName    | roasterName     | avgRating | logCount |
+-----------+----------------+-----------------+-----------+----------+
|        28 | Aponte Village | Onyx Coffee Lab |      5.00 |        2 |
+-----------+----------------+-----------------+-----------+----------+
1 row in set (0.004 sec)