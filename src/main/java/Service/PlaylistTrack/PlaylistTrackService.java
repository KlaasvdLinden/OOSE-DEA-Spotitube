package Service.PlaylistTrack;

import Dao.PlaylistTrack.PlaylistTrackDAOMapper;
import Domain.Track.TracksResponse;
import IdentityMappers.TrackIdentityMapper;

import javax.inject.Inject;


public class PlaylistTrackService implements IPlaylistTrackService {

    @Inject
    PlaylistTrackDAOMapper playlistTrackDAOMapper;

    @Inject
    TrackIdentityMapper trackIdentityMapper;

    @Override
    public TracksResponse getTracks(int id){
        return trackIdentityMapper.getTracksForPlaylist(id);
    }

    @Override
    public void addTrack(int playlistID, int trackID, boolean offlineAvailable) {
        playlistTrackDAOMapper.addTrack(playlistID, trackID, offlineAvailable);
    }

    @Override
    public void deleteTrack(int playlistID, int trackID) {
        playlistTrackDAOMapper.deleteTrack(playlistID, trackID);
    }


}
