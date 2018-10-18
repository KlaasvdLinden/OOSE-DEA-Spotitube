package Service.Playlist;

import Dao.Playlist.PlaylistDAO;
import Domain.Playlists.Playlists;
import Service.User.UserService;

import javax.inject.Inject;


public class PlaylistService implements IPlaylistService {

    @Inject
    UserService userService;

    private PlaylistDAO playlistDAO;

    @Override
    public Playlists getPlaylist(){
        return playlistDAO.findAll(userService.getUserID());
    }

    @Override
    public void editPlaylist(int id, String name){
        playlistDAO.editPlaylist(id , name);
    }

    @Override
    public void addPlaylist(String name) {
        playlistDAO.addPlaylist(userService.getUserID(), name);
    }

    @Override
    public void deletePlaylist(int id) {
        playlistDAO.deletePlaylist(id);
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO){
        this.playlistDAO = playlistDAO;
    }
}
