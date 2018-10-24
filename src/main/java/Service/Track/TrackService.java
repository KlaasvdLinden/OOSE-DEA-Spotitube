package Service.Track;

import Dao.Track.TrackDAOMapper;
import Domain.Track.TracksResponse;

import javax.inject.Inject;


public class TrackService implements  ITrackService {

    @Inject
    TrackDAOMapper trackDAOMapper;

    @Override
    public TracksResponse getAll(int playlistID) {
        return new TracksResponse(trackDAOMapper.getAll(playlistID));
    }

}
