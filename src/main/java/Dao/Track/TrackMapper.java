package Dao.Track;

import Domain.Track.Track;

import java.util.ArrayList;

public interface TrackMapper {

    ArrayList<Track> getAll(int playlistID);
}
