package Service.Playlist;

import Domain.Playlists.Playlists;
import IdentityMappers.PlaylistIdentityMapper;
import Service.User.UserService;

import javax.inject.Inject;


public class PlaylistService implements IPlaylistService {

    @Inject
    UserService userService;

    @Inject
    PlaylistIdentityMapper playlistIdentityMapper;

    @Override
    public Playlists getPlaylist(){
        return playlistIdentityMapper.getPlaylists(userService.getUserID());
    }

    @Override
    public void editPlaylist(int id, String name){
        playlistIdentityMapper.editPlaylist(id , name);
    }

    @Override
    public void addPlaylist(String name) {
        playlistIdentityMapper.addPlaylist(userService.getUserID(), name);
    }

    @Override
    public void deletePlaylist(int id) {
        playlistIdentityMapper.deletePlaylist(id);
    }


}
