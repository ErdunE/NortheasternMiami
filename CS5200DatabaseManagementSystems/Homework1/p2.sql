SELECT Track.Name AS trackName,
       MediaType.Name AS trackType,
       Genre.Name AS genreName,
       PRINTF('$%.2f', Track.UnitPrice) AS trackPrice
FROM Track
INNER JOIN MediaType ON Track.MediaTypeId = MediaType.MediaTypeId
INNER JOIN Genre ON Track.GenreId = Genre.GenreId
INNER JOIN PlaylistTrack ON Track.TrackId = PlaylistTrack.TrackId
INNER JOIN Playlist ON PlaylistTrack.PlaylistId = Playlist.PlaylistId
WHERE Playlist.Name = 'Heavy Metal Classic'
  AND Genre.Name LIKE '%Metal%'
  AND MediaType.Name NOT LIKE '%Protected%'
  AND MediaType.Name NOT LIKE '%Purchased%'
ORDER BY Track.Milliseconds DESC
