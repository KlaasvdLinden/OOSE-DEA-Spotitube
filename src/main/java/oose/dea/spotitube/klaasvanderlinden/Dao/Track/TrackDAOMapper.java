package oose.dea.spotitube.klaasvanderlinden.Dao.Track;

import oose.dea.spotitube.klaasvanderlinden.Domain.Track.Track;

import java.util.ArrayList;

public interface TrackDAOMapper {

    ArrayList<Track> getAll(int playlistID);
}
