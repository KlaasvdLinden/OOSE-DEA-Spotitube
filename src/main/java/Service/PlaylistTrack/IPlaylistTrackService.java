package Service.PlaylistTrack;

import Domain.Track.TracksResponse;

public interface IPlaylistTrackService {

    TracksResponse getTracks(int id);

    void addTrack(int playlistID, int trackID, boolean offlineAvailable);

    void deleteTrack(int playlistID, int trackID);
}
