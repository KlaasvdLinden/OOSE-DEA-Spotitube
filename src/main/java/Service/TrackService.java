package Service;

import Dao.Track.TrackDAO;
import Domain.Track.TracksResponse;


public class TrackService {

    TrackDAO trackDAO = new TrackDAO();

    public TracksResponse getTracks(int id){
        return new TracksResponse(trackDAO.findAll(id));
    }
}
