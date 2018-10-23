package Dao.PlaylistTrack;

import Domain.Track.Track;

import java.util.ArrayList;

public interface PlaylistTrackMapper {

    ArrayList<Track> findAll(int id);

    void addTrack(int playlistID, int trackID, boolean offlineAvailable);

    void deleteTrack(int playlistID, int trackID);
}
