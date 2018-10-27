package oose.dea.spotitube.klaasvanderlinden.Service.Playlist;

import oose.dea.spotitube.klaasvanderlinden.Domain.Playlists.Playlists;
import oose.dea.spotitube.klaasvanderlinden.IdentityMappers.PlaylistIdentityMapper;
import oose.dea.spotitube.klaasvanderlinden.IdentityMappers.UserIdentityMapper;

import javax.inject.Inject;


public class PlaylistService implements IPlaylistService {

    @Inject
    UserIdentityMapper userIdentityMapper;

    @Inject
    PlaylistIdentityMapper playlistIdentityMapper;

    @Override
    public Playlists getPlaylists(){
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
