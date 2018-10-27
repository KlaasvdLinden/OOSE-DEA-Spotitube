package oose.dea.spotitube.klaasvanderlinden.Service.PlaylistTrack;

import oose.dea.spotitube.klaasvanderlinden.Domain.Track.TracksResponse;

public interface IPlaylistTrackService {

    TracksResponse getTracks(int id);

    void addTrack(int playlistID, int trackID, boolean offlineAvailable);

    void deleteTrack(int playlistID, int trackID);
}
