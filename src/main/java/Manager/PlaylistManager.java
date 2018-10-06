package Manager;

import Dao.Playlist.PlaylistDAO;
import Domain.Playlists.Playlists;



public class PlaylistManager {


    PlaylistDAO playlistDAO = new PlaylistDAO();

    public Playlists getPlaylist(String token){
        return playlistDAO.findAll(token);
    }
}
