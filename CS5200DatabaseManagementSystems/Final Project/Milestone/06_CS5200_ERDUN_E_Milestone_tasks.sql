-- Required Tasks

-- a) Add a newly discovered roaster
-- I added Starbucks as a roaster in Seattle, United States. Becuase there is no Seattle location in the database, I added it as a new location.
MariaDB [CoffeeDB]> INSERT INTO Locations (city, country)
    -> VALUES ('Seattle', 'United States');
Query OK, 1 row affected (0.014 sec)
MariaDB [CoffeeDB]> SELECT * FROM Locations WHERE city = 'Seattle' AND country = 'United States';
+-------------+---------+--------+---------------+
| location_id | city    | region | country       |
+-------------+---------+--------+---------------+
|          68 | Seattle | NULL   | United States |
+-------------+---------+--------+---------------+
1 row in set (0.003 sec)

MariaDB [CoffeeDB]> INSERT INTO Roasters (location_id, roaster_name, website, is_abandoned)
    -> VALUES (68, 'Starbucks', 'https://www.starbucks.com/', 0);
Query OK, 1 row affected (0.005 sec)

MariaDB [CoffeeDB]> SELECT * FROM Roasters WHERE roaster_name = 'Starbucks';
+------------+-------------+--------------+----------------------------+--------------+
| roaster_id | location_id | roaster_name | website                    | is_abandoned |
+------------+-------------+--------------+----------------------------+--------------+
|         21 |          68 | Starbucks    | https://www.starbucks.com/ |            0 |
+------------+-------------+--------------+----------------------------+--------------+
1 row in set (0.001 sec)

-- b) Add a newly purchased roast
-- I added new product Starbucks Blonde Roast to the database. And assigned beans to it.
MariaDB [CoffeeDB]> INSERT INTO Products (roaster_id, product_name, roast_date, roast_level, price_per_100g)
    -> VALUES (21, 'Starbucks Blonde Roast', '2025-03-13', 'Light', 8.99);
Query OK, 1 row affected (0.012 sec)

MariaDB [CoffeeDB]> SELECT product_id FROM Products WHERE product_name = 'Starbucks Blonde Roast';
+------------+
| product_id |
+------------+
|         63 |
+------------+
1 row in set (0.002 sec)

MariaDB [CoffeeDB]> INSERT INTO Products_Beans (product_id, bean_id, percentage, role)
    -> VALUES
    ->     (63, 5, 60.00, 'Primary'),
    ->     (63, 12, 40.00, 'Blend');
Query OK, 2 rows affected (0.006 sec)
Records: 2  Duplicates: 0  Warnings: 0

-- c) Update the rating description
-- I updated the descriptions of the ratings which provided by professor Nate to make them more descriptive.
MariaDB [CoffeeDB]> SELECT * FROM Ratings;
+-----------+-------+-------------------------------------------------------------------+
| rating_id | emoji | description                                                       |
+-----------+-------+-------------------------------------------------------------------+
|         1 | 🥺     | Gross, no; watery, flavorless (Keurig)                            |
|         2 | 🫤     | Drink if you forced me; some flavor, but not good (Dunkin, diner) |
|         3 | 😐     | It’s coffee; uninteresting flavor (Starbucks, Pavement)           |
|         4 | 😁     | Would drink anytime, delicious                                    |
|         5 | 🤤     | Ambrosia                                                          |
+-----------+-------+-------------------------------------------------------------------+
5 rows in set (0.003 sec)

MariaDB [CoffeeDB]> UPDATE Ratings SET description = 'Watery and flavorless, barely coffee.' WHERE rating_id = 1;
Query OK, 1 row affected (0.007 sec)
Rows matched: 1  Changed: 1  Warnings: 0

MariaDB [CoffeeDB]> UPDATE Ratings SET description = 'Weak flavor, not enjoyable but drinkable.' WHERE rating_id = 2;
Query OK, 1 row affected (0.003 sec)
Rows matched: 1  Changed: 1  Warnings: 0

MariaDB [CoffeeDB]> UPDATE Ratings SET description = 'Mediocre coffee, lacks complexity or uniqueness.' WHERE rating_id = 3;
Query OK, 1 row affected (0.003 sec)
Rows matched: 1  Changed: 1  Warnings: 0

MariaDB [CoffeeDB]> UPDATE Ratings SET description = 'Balanced and enjoyable, would drink anytime.' WHERE rating_id = 4;
Query OK, 1 row affected (0.003 sec)
Rows matched: 1  Changed: 1  Warnings: 0

MariaDB [CoffeeDB]> UPDATE Ratings SET description = 'Outstanding, rich, and full of depth. A masterpiece.' WHERE rating_id = 5;
Query OK, 1 row affected (0.004 sec)
Rows matched: 1  Changed: 1  Warnings: 0

MariaDB [CoffeeDB]> SELECT * FROM Ratings;
+-----------+-------+------------------------------------------------------+
| rating_id | emoji | description                                          |
+-----------+-------+------------------------------------------------------+
|         1 | 🥺     | Watery and flavorless, barely coffee.                |
|         2 | 🫤     | Weak flavor, not enjoyable but drinkable.            |
|         3 | 😐     | Mediocre coffee, lacks complexity or uniqueness.     |
|         4 | 😁     | Balanced and enjoyable, would drink anytime.         |
|         5 | 🤤     | Outstanding, rich, and full of depth. A masterpiece. |
+-----------+-------+------------------------------------------------------+
5 rows in set (0.001 sec)


-- d) Log a tasting
-- I logged a tasting for user 3, product 10 with a rating of 4, V60 brew method, medium grind size, 92 water temperature, 2 bloom count, and user notes. Then I added tasting notes to the tasting log.
MariaDB [CoffeeDB]> INSERT INTO TastingLogs (user_id, product_id, tasting_date, rating_id, brew_method, grind_size, water_temperature, bloom_count, user_notes)
    -> VALUES (3, 10, '2025-03-13', 4, 'V60', 'Medium', 92, 2, 'Smooth and well-balanced, with a hint of caramel.');
Query OK, 1 row affected (0.011 sec)

MariaDB [CoffeeDB]> SELECT log_id FROM TastingLogs WHERE user_id = 3 AND product_id = 10 ORDER BY tasting_date DESC LIMIT 1;
+--------+
| log_id |
+--------+
|     11 |
+--------+
1 row in set (0.008 sec)

MariaDB [CoffeeDB]> INSERT INTO TastingLogs_TastingNotes (log_id, note_id) 
    -> VALUES
    ->     (11, 5), 
    ->     (11, 9), 
    ->     (11, 11);
Query OK, 3 rows affected (0.005 sec)
Records: 3  Duplicates: 0  Warnings: 0

MariaDB [CoffeeDB]> SELECT * FROM TastingLogs WHERE log_id = 11;
+--------+---------+------------+--------------+-----------+-------------+------------+-------------------+-------------+---------------------------------------------------+
| log_id | user_id | product_id | tasting_date | rating_id | brew_method | grind_size | water_temperature | bloom_count | user_notes                                        |
+--------+---------+------------+--------------+-----------+-------------+------------+-------------------+-------------+---------------------------------------------------+
|     11 |       3 |         10 | 2025-03-13   |         4 | V60         | Medium     |                92 |           2 | Smooth and well-balanced, with a hint of caramel. |
+--------+---------+------------+--------------+-----------+-------------+------------+-------------------+-------------+---------------------------------------------------+
1 row in set (0.002 sec)

MariaDB [CoffeeDB]> SELECT * FROM TastingLogs_TastingNotes WHERE log_id = 11;
+--------+---------+
| log_id | note_id |
+--------+---------+
|     11 |       5 |
|     11 |       9 |
|     11 |      11 |
+--------+---------+
3 rows in set (0.001 sec)

-- e) Give up on a roaster
-- I marked Five Elephant as abandoned in the database.
MariaDB [CoffeeDB]> UPDATE Roasters 
    -> SET is_abandoned = 1 
    -> WHERE roaster_id = 12;
Query OK, 1 row affected (0.004 sec)
Rows matched: 1  Changed: 1  Warnings: 0

MariaDB [CoffeeDB]> SELECT * FROM Roasters WHERE roaster_id = 12;
+------------+-------------+---------------+-------------------------------+--------------+
| roaster_id | location_id | roaster_name  | website                       | is_abandoned |
+------------+-------------+---------------+-------------------------------+--------------+
|         12 |           6 | Five Elephant | https://www.fiveelephant.com/ |            1 |
+------------+-------------+---------------+-------------------------------+--------------+
1 row in set (0.001 sec)

-- f) Find all grower’s in a particular country
-- I found all the farms in Colombia.
MariaDB [CoffeeDB]> SELECT f.farm_id, f.farm_name, l.country, l.region, f.elevation
    -> FROM Farms f
    -> JOIN Locations l ON f.location_id = l.location_id
    -> WHERE l.country = 'Colombia';
+---------+----------------------+----------+--------------+-----------+
| farm_id | farm_name            | country  | region       | elevation |
+---------+----------------------+----------+--------------+-----------+
|      11 | La Palma y El Tucán  | Colombia | Cundinamarca |   1650.00 |
|      12 | Finca Tamana         | Colombia | Huila        |   1700.00 |
|      23 | La Palma             | Colombia | Tolima       |   1750.00 |
+---------+----------------------+----------+--------------+-----------+
3 rows in set (0.009 sec)

-- g) Produce a list of all the top-rated products
-- I produced a list of all the products that have received a top rating of 5.
MariaDB [CoffeeDB]> SELECT p.product_id, p.product_name, r.roaster_name
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> WHERE tl.rating_id = 5
    -> GROUP BY p.product_id, p.product_name, r.roaster_name;
+------------+----------------------------+-----------------------+
| product_id | product_name               | roaster_name          |
+------------+----------------------------+-----------------------+
|          4 | Hayes Valley Espresso      | Blue Bottle Coffee    |
|          9 | Black Cat Classic Espresso | Intelligentsia Coffee |
+------------+----------------------------+-----------------------+
2 rows in set (0.006 sec)


-- h) Produce a list of the number of times each varietal has received one of the top-2 ratings
-- I used the COUNT function to count the number of times each varietal has received one of the top-2 ratings.
MariaDB [CoffeeDB]> SELECT b.varietal, COUNT(*) AS top_rated_count
    -> FROM TastingLogs tl
    -> JOIN Products_Beans pb ON tl.product_id = pb.product_id
    -> JOIN Beans b ON pb.bean_id = b.bean_id
    -> WHERE tl.rating_id IN (4, 5) 
    -> GROUP BY b.varietal
    -> ORDER BY top_rated_count DESC;
+----------+-----------------+
| varietal | top_rated_count |
+----------+-----------------+
| Heirloom |               3 |
| Geisha   |               1 |
| Caturra  |               1 |
| Gesha    |               1 |
| Catuai   |               1 |
+----------+-----------------+
5 rows in set (0.007 sec)

-- i) Produce a log of tastings for a particular roaster in a time range
-- I produced the tasting logs for Blue Bottle Coffee between March 1, 2024, and March 13, 2025.
MariaDB [CoffeeDB]> SELECT tl.log_id, u.username, r.roaster_name, p.product_name, tl.tasting_date, rt.emoji, rt.description, 
    ->        tl.brew_method, tl.grind_size, tl.water_temperature, tl.bloom_count, tl.user_notes
    -> FROM TastingLogs tl
    -> JOIN Products p ON tl.product_id = p.product_id
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN Users u ON tl.user_id = u.user_id
    -> JOIN Ratings rt ON tl.rating_id = rt.rating_id
    -> WHERE r.roaster_name = 'Blue Bottle Coffee' 
    -> AND tl.tasting_date BETWEEN '2024-03-01' AND '2025-03-13'  
    -> ORDER BY tl.tasting_date ASC;
+--------+---------------+--------------------+-----------------------+--------------+-------+------------------------------------------------------+--------------+-------------+-------------------+-------------+---------------------------------------------------+
| log_id | username      | roaster_name       | product_name          | tasting_date | emoji | description                                          | brew_method  | grind_size  | water_temperature | bloom_count | user_notes                                        |
+--------+---------------+--------------------+-----------------------+--------------+-------+------------------------------------------------------+--------------+-------------+-------------------+-------------+---------------------------------------------------+
|      1 | ErdunE        | Blue Bottle Coffee | Giant Steps           | 2024-03-02   | 🥺    | Watery and flavorless, barely coffee.                | Pour Over    | Medium      |                93 |           2 | Strong and bitter but has a rich flavor.          |
|      2 | Kai           | Blue Bottle Coffee | Bella Donovan         | 2024-03-03   | 😁    | Balanced and enjoyable, would drink anytime.         | Espresso     | Fine        |                90 |           1 | Smooth with a sweet aftertaste, perfect espresso. |
|      3 | Will          | Blue Bottle Coffee | Three Africas         | 2024-03-04   | 🫤    | Weak flavor, not enjoyable but drinkable.            | French Press | Coarse      |                92 |           3 | A bit too watery but has a nice fruity flavor.    |
|      4 | Nav           | Blue Bottle Coffee | Hayes Valley Espresso | 2024-03-05   | 🤤    | Outstanding, rich, and full of depth. A masterpiece. | Aeropress    | Medium-Fine |                95 |           2 | Delicious, I could drink this all day long.       |
|      5 | ProfessorNate | Blue Bottle Coffee | Kenya Nyeri Peaberry  | 2024-03-06   | 😐    | Mediocre coffee, lacks complexity or uniqueness.     | Cold Brew    | Coarse      |                80 |           1 | Smooth, but lacks complexity, very drinkable.     |
+--------+---------------+--------------------+-----------------------+--------------+-------+------------------------------------------------------+--------------+-------------+-------------------+-------------+---------------------------------------------------+
5 rows in set (0.014 sec)

-- j) Produce a ranked list of products, given a set of notes and optionally a processing method
-- I used a subquery to get the top 5 most common tasting notes and a processing method of 'Washed' to produced a ranked list to filter the products.
MariaDB [CoffeeDB]> SELECT p.product_id, p.product_name, r.roaster_name, 
    ->        COUNT(DISTINCT tln.note_id) AS matching_notes_count, 
    ->        COUNT(DISTINCT tl.log_id) AS tasting_count
    -> FROM Products p
    -> JOIN Roasters r ON p.roaster_id = r.roaster_id
    -> JOIN TastingLogs tl ON p.product_id = tl.product_id
    -> JOIN TastingLogs_TastingNotes tln ON tl.log_id = tln.log_id
    -> JOIN TastingNotes tn ON tln.note_id = tn.note_id
    -> LEFT JOIN Products_Beans pb ON p.product_id = pb.product_id
    -> LEFT JOIN Beans b ON pb.bean_id = b.bean_id
    -> WHERE tn.note_id IN (
    ->     SELECT note_id FROM (
    ->         SELECT note_id, COUNT(*) AS note_count
    ->         FROM TastingLogs_TastingNotes
    ->         GROUP BY note_id
    ->         ORDER BY note_count DESC
    ->         LIMIT 5
    ->     ) AS TopNotes
    -> )  
    -> AND (b.processing_method = 'Washed' OR 'Washed' IS NULL)
    -> GROUP BY p.product_id, p.product_name, r.roaster_name
    -> ORDER BY matching_notes_count DESC, tasting_count DESC;
+------------+-----------------+---------------------------+----------------------+---------------+
| product_id | product_name    | roaster_name              | matching_notes_count | tasting_count |
+------------+-----------------+---------------------------+----------------------+---------------+
|          7 | Holler Mountain | Stumptown Coffee Roasters |                    2 |             1 |
|          3 | Three Africas   | Blue Bottle Coffee        |                    1 |             1 |
|          8 | French Roast    | Stumptown Coffee Roasters |                    1 |             1 |
+------------+-----------------+---------------------------+----------------------+---------------+
3 rows in set (0.014 sec)