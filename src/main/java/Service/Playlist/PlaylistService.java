package Service.Playlist;

import Domain.Playlists.Playlists;
import IdentityMappers.PlaylistIdentityMapper;
import IdentityMappers.UserIdentityMapper;
import Service.User.UserService;

import javax.inject.Inject;


public class PlaylistService implements IPlaylistService {

    @Inject
    UserIdentityMapper userIdentityMapper;

    @Inject
    PlaylistIdentityMapper playlistIdentityMapper;

    @Override
    public Playlists getPlaylist(){
        return playlistIdentityMapper.getPlaylists(userIdentityMapper.getCurrentUserID());
    }

    @Override
    public void editPlaylist(int id, String name){
        playlistIdentityMapper.editPlaylist(id , name);
    }

    @Override
    public void addPlaylist(String name) {
        playlistIdentityMapper.addPlaylist(userIdentityMapper.getCurrentUserID(), name);
    }

    @Override
    public void deletePlaylist(int id) {
        playlistIdentityMapper.deletePlaylist(id);
    }


}
