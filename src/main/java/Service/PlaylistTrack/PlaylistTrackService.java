package Service.PlaylistTrack;

import Dao.PlaylistTrack.PlaylistTrackDAO;
import Dao.PlaylistTrack.PlaylistTrackMapper;
import Domain.Track.TracksResponse;

import javax.inject.Inject;


public class PlaylistTrackService implements IPlaylistTrackService {

    @Inject
    PlaylistTrackMapper playlistTrackMapper;

    @Override
    public TracksResponse getTracks(int id){
        return new TracksResponse(playlistTrackMapper.findAll(id));
    }

    @Override
    public void addTrack(int playlistID, int trackID, boolean offlineAvailable) {
        playlistTrackMapper.addTrack(playlistID, trackID, offlineAvailable);
    }

    @Override
    public void deleteTrack(int playlistID, int trackID) {
        playlistTrackMapper.deleteTrack(playlistID, trackID);
    }


}
