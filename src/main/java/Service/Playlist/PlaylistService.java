package Service.Playlist;

import Dao.Playlist.PlaylistDAO;
import Dao.Playlist.PlaylistMapper;
import Domain.Playlist.Playlist;
import Domain.Playlists.Playlists;
import Service.User.UserService;

import javax.inject.Inject;


public class PlaylistService implements IPlaylistService {

    @Inject
    UserService userService;

    @Inject
    PlaylistMapper playlistMapper;

    @Override
    public Playlists getPlaylist(){
        return playlistMapper.findAll(userService.getUserID());
    }

    @Override
    public void editPlaylist(int id, String name){
        playlistMapper.editPlaylist(id , name);
    }

    @Override
    public void addPlaylist(String name) {
        playlistMapper.addPlaylist(userService.getUserID(), name);
    }

    @Override
    public void deletePlaylist(int id) {
        playlistMapper.deletePlaylist(id);
    }


}
