package oose.dea.spotitube.klaasvanderlinden.Service.Playlist;

import oose.dea.spotitube.klaasvanderlinden.Domain.Playlists.Playlists;

public interface IPlaylistService {
    Playlists getPlaylists();

    void editPlaylist(int id, String name);

    void addPlaylist(String name);

    void deletePlaylist(int id);
}
