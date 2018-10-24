package Dao.Track;

import Domain.Track.Track;

import java.util.ArrayList;

public interface TrackDAOMapper {

    ArrayList<Track> getAll(int playlistID);
}
