package Service.PlaylistTrack;

import Dao.PlaylistTrack.PlaylistTrackDAO;
import Domain.Track.TracksResponse;

import javax.inject.Inject;


public class PlaylistTrackService implements  IPlaylistTrackService {

    PlaylistTrackDAO playlistTrackDAO;

    @Override
    public TracksResponse getTracks(int id){
        return new TracksResponse(playlistTrackDAO.findAll(id));
    }

    @Override
    public void addTrack(int playlistID, int trackID, boolean offlineAvailable) {
        playlistTrackDAO.addTrack(playlistID, trackID, offlineAvailable);
    }

    @Override
    public void deleteTrack(int playlistID, int trackID) {
        playlistTrackDAO.deleteTrack(playlistID, trackID);
    }

    @Inject
    public void setPlaylistTrackDAO(PlaylistTrackDAO playlistTrackDAO){
        this.playlistTrackDAO = playlistTrackDAO;
    }

}
