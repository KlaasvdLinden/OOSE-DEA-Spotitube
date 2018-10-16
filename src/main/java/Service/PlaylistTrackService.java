package Service;

import Dao.PlaylistTrack.PlaylistTrackDAO;
import Domain.Track.TracksResponse;

import javax.inject.Inject;


public class PlaylistTrackService {

    PlaylistTrackDAO playlistTrackDAO;

    public TracksResponse getTracks(int id){
        return new TracksResponse(playlistTrackDAO.findAll(id));
    }

    public void addTrack(int playlistID, int trackID, boolean offlineAvailable) {
        playlistTrackDAO.addTrack(playlistID, trackID, offlineAvailable);
    }

    public void deleteTrack(int playlistID, int trackID) {
        playlistTrackDAO.deleteTrack(playlistID, trackID);
    }

    @Inject
    public void setPlaylistTrackDAO(PlaylistTrackDAO playlistTrackDAO){
        this.playlistTrackDAO = playlistTrackDAO;
    }

}
