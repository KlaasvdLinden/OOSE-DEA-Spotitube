package Playlist;

import java.util.ArrayList;

public class Playlists {
    private ArrayList<Playlist> playlists;
    private int length;

    public Playlists() {
        playlists = new ArrayList<Playlist>();
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void addPlayList(Playlist playlist) {
        playlists.add(playlist);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
