package Service;

import Dao.Track.TrackDAO;
import Domain.Track.Track;
import Domain.Track.TracksResponse;

import javax.inject.Inject;
import java.util.ArrayList;

public class TrackService {

    TrackDAO trackDAO;
    public TracksResponse getAll(int playlistID) {
        return new TracksResponse(trackDAO.getAll(playlistID));
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO){
        this.trackDAO = trackDAO;
    }
}
