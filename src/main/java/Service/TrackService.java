package Service;

import Dao.Track.TrackDAO;
import Domain.Track.Track;
import Domain.Track.TracksResponse;

import java.util.ArrayList;

public class TrackService {

    TrackDAO trackDAO = new TrackDAO();
    public TracksResponse getAll(int playlistID) {
        return new TracksResponse(trackDAO.getAll(playlistID));
    }
}
