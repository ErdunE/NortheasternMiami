SELECT Album.Title AS albTitle,
       Track.Name AS trackName,
       Track.Composer AS trackComposer,
       Genre.Name AS genreName
FROM Track
INNER JOIN Album ON Track.AlbumId = Album.AlbumId
INNER JOIN Artist ON Album.ArtistId = Artist.ArtistId
INNER JOIN Genre ON Track.GenreId = Genre.GenreId
WHERE Artist.Name = 'Various Artists'
  AND Track.Composer IS NOT NULL
  AND Genre.Name = 'Latin'
ORDER BY Track.Composer;
