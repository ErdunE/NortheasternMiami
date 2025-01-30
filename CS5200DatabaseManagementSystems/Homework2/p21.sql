SELECT 
        ar.NAME          AS artistName,
        Count(t.trackid) AS trackCount
FROM   
        artist ar
        INNER JOIN album al ON ar.artistid = al.artistid
        INNER JOIN track t  ON al.albumid = t.albumid
GROUP  BY 
        ar.NAME
HAVING 
        trackCount > 80
ORDER  BY 
        trackcount DESC,
        artistname ASC 
