package Service;

import Dao.Playlist.PlaylistDAO;
import Domain.Playlists.Playlists;

import javax.inject.Inject;


public class PlaylistService {

    @Inject
    UserService userService;

    private PlaylistDAO playlistDAO;

    public Playlists getPlaylist(){
        return playlistDAO.findAll(userService.getUserID());
    }

    public void editPlaylist(int id, String name){
        playlistDAO.editPlaylist(id , name);
    }

    public void addPlaylist(String name) {
        playlistDAO.addPlaylist(userService.getUserID(), name);
    }

    public void deletePlaylist(int id) {
        playlistDAO.deletePlaylist(id);
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO){
        this.playlistDAO = playlistDAO;
    }
}
