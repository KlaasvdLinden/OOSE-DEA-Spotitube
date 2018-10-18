package Service.Playlist;

import Domain.Playlists.Playlists;

public interface IPlaylistService {
    Playlists getPlaylist();

    void editPlaylist(int id, String name);

    void addPlaylist(String name);

    void deletePlaylist(int id);
}
