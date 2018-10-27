package oose.dea.spotitube.klaasvanderlinden.Dao.PlaylistTrack;

import oose.dea.spotitube.klaasvanderlinden.Domain.Track.Track;

import java.util.ArrayList;

public interface PlaylistTrackDAOMapper {

    ArrayList<Track> findAll(int id);

    void addTrack(int playlistID, int trackID, boolean offlineAvailable);

    void deleteTrack(int playlistID, int trackID);
}
