package oose.dea.spotitube.klaasvanderlinden.Domain.Playlist;

import oose.dea.spotitube.klaasvanderlinden.Domain.Track.Track;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private boolean owner;
    private List<Track> tracks;

    public Playlist(){
        tracks = new ArrayList<Track>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public void addTrack(Track track){
        tracks.add(track);
    }

    public List getTracks(){
        return tracks;
    }

}
