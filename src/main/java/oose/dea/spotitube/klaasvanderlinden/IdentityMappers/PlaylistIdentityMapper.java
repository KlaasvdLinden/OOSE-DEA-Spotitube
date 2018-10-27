package oose.dea.spotitube.klaasvanderlinden.IdentityMappers;

import oose.dea.spotitube.klaasvanderlinden.Dao.Playlist.PlaylistDAOMapper;
import oose.dea.spotitube.klaasvanderlinden.Domain.Playlist.Playlist;
import oose.dea.spotitube.klaasvanderlinden.Domain.Playlists.Playlists;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedHashMap;

@Singleton
public class PlaylistIdentityMapper {

    @Inject
    PlaylistDAOMapper playlistDAOMapper;

    private static PlaylistIdentityMapper instance = new PlaylistIdentityMapper();
    private static LinkedHashMap<Integer, Playlist> playlistMap = new LinkedHashMap <>();
    private static int storedUserID;

    private boolean updateNeeded = true;

    private PlaylistIdentityMapper() {
    }

    public PlaylistIdentityMapper getInstance() {
        return this.instance;
    }

    public Playlists getPlaylists(int userID) {
        Playlists playlists = new Playlists();
        if(updateNeeded || storedUserID != userID){
            storedUserID = userID;
            updateMap(userID);
            updateNeeded = false;
        }
        for (Playlist playlist : playlistMap.values()) {
            playlists.getPlaylists().add(playlist);
        }
        playlists.setLength(playlistDAOMapper.totalPlaylistLength());
        return playlists;
    }

    public void addPlaylist(int userID, String name) {
        playlistDAOMapper.addPlaylist(userID, name);
        updateNeeded = true;
    }

    public void deletePlaylist(int id){
        playlistDAOMapper.deletePlaylist(id);
        playlistMap.remove(id);
        updateNeeded = false;
    }

    public void editPlaylist(int id, String name){
        playlistDAOMapper.editPlaylist(id, name);
        playlistMap.get(id).setName(name);
        updateNeeded = false;
    }

    private void updateMap(int userID) {
        playlistMap.clear();
        for (Playlist playlist : playlistDAOMapper.findAll(userID).getPlaylists()){
            playlistMap.put(playlist.getId(), playlist);
        }
    }
}
