package oose.dea.spotitube.klaasvanderlinden.Service.Track;

import oose.dea.spotitube.klaasvanderlinden.Domain.Track.TracksResponse;

public interface ITrackService {
    TracksResponse getAll(int playlistID);
}
