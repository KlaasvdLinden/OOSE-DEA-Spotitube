package Service.Track;

import Dao.Track.TrackDAOMapper;
import Domain.Track.TracksResponse;
import IdentityMappers.TrackIdentityMapper;

import javax.inject.Inject;


public class TrackService implements  ITrackService {

    @Inject
    TrackIdentityMapper trackIdentityMapper;

    @Override
    public TracksResponse getAll(int playlistID) {
        return trackIdentityMapper.getAll(playlistID);
    }

}
