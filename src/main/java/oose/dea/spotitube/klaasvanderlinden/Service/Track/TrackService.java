package oose.dea.spotitube.klaasvanderlinden.Service.Track;

import oose.dea.spotitube.klaasvanderlinden.Domain.Track.TracksResponse;
import oose.dea.spotitube.klaasvanderlinden.IdentityMappers.TrackIdentityMapper;

import javax.inject.Inject;


public class TrackService implements  ITrackService {

    @Inject
    TrackIdentityMapper trackIdentityMapper;

    @Override
    public TracksResponse getAll(int playlistID) {
        return trackIdentityMapper.getAllNotInPlaylist(playlistID);
    }

}
