package Manager;

import Dao.Track.TrackDAO;
import Domain.Track.TracksResponse;


public class TrackManager {

    TrackDAO trackDAO = new TrackDAO();

    public TracksResponse getTracks(int id){
        return new TracksResponse(trackDAO.findAll(id));
    }
}
