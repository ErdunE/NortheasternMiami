SELECT DISTINCT Genre.Name AS classyGenre
FROM Playlist
INNER JOIN PlaylistTrack ON Playlist.PlaylistId = PlaylistTrack.PlaylistId
INNER JOIN Track ON PlaylistTrack.TrackId = Track.TrackId
INNER JOIN Genre ON Track.GenreId = Genre.GenreId
WHERE Playlist.Name = 'Classical'
ORDER BY Genre.Name
