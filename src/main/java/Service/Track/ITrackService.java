package Service.Track;

import Domain.Track.TracksResponse;

public interface ITrackService {
    TracksResponse getAll(int playlistID);
}
