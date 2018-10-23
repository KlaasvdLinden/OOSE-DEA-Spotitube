package Dao.Playlist;

import Domain.Playlists.Playlists;

public interface PlaylistMapper {

    Playlists findAll(int userID);

    void editPlaylist(int id, String name);

    void addPlaylist(int userID, String name);

    void deletePlaylist(int id);
}
