package Service;

import Dao.PlaylistTrack.PlaylistTrackDAO;
import Domain.Track.TracksResponse;


public class PlaylistTrackService {

    PlaylistTrackDAO playlistTrackDAO = new PlaylistTrackDAO();

    public TracksResponse getTracks(int id){
        return new TracksResponse(playlistTrackDAO.findAll(id));
    }

    public void addTrack(int playlistID, int trackID, boolean offlineAvailable) {
        playlistTrackDAO.addTrack(playlistID, trackID, offlineAvailable);
    }

    public void deleteTrack(int playlistID, int trackID) {
        playlistTrackDAO.deleteTrack(playlistID, trackID);
    }
}
