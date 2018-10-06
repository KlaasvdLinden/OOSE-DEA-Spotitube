package Domain.Track;

import java.util.List;

public class TracksResponse{

    private List<Track> tracks;

    public TracksResponse(List<Track> tracks) {
        this.tracks = tracks;
    }



    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}