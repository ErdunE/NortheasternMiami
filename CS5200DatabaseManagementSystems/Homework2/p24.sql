SELECT 
        t.NAME           AS trackName,
        al.title         AS albumTitle,
        ar.NAME          AS artistName,
        g.NAME           AS genreName,
        i.billingcountry AS billingCountry
FROM   
        track t
        INNER JOIN album al       ON t.albumid = al.albumid
        INNER JOIN artist ar      ON al.artistid = ar.artistid
        INNER JOIN genre g        ON t.genreid = g.genreid
        INNER JOIN invoiceline il ON t.trackid = il.trackid
        INNER JOIN invoice i      ON il.invoiceid = i.invoiceid
GROUP  BY 
        t.trackid,
        i.billingcountry
HAVING 
        Count(i.invoiceid) >= 2
ORDER  BY 
        genrename ASC,
        artistname ASC,
        trackname ASC 
