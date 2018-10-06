package Service;

import Dao.Playlist.PlaylistDAO;
import Domain.Playlists.Playlists;



public class PlaylistService {


    PlaylistDAO playlistDAO = new PlaylistDAO();

    public Playlists getPlaylist(String token){
        return playlistDAO.findAll(token);
    }
}
