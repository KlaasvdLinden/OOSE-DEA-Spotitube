package Service.Track;

import Dao.Track.TrackDAO;
import Dao.Track.TrackMapper;
import Domain.Track.TracksResponse;

import javax.inject.Inject;


public class TrackService implements  ITrackService {

    @Inject
    TrackMapper trackMapper;

    @Override
    public TracksResponse getAll(int playlistID) {
        return new TracksResponse(trackMapper.getAll(playlistID));
    }

}
