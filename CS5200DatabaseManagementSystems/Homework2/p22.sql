SELECT 
       ar.name AS lazyArtist
FROM   
       artist ar
       LEFT JOIN album al ON ar.artistid = al.artistid
WHERE  
       al.albumid IS NULL
ORDER  BY 
       lazyartist ASC
