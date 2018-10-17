package Service;

import Dao.Track.TrackDAO;
import Domain.Track.TracksResponse;

import javax.inject.Inject;


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
