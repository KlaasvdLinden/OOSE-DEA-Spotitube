package Service;

import Dao.Playlist.PlaylistDAO;
import Domain.Playlists.Playlists;

import javax.inject.Inject;


public class PlaylistService {

    @Inject
    UserService userService;

    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public Playlists getPlaylist(String token){
        return playlistDAO.findAll(token);
    }

    public void editPlaylist(int id, String name){
        playlistDAO.editPlaylist(id , name);
    }

    public void addPlaylist(String name) {
        playlistDAO.addPlaylist(name, userService.getUserName());
    }

    public void deletePlaylist(int id) {
        playlistDAO.deletePlaylist(id);
    }
}
