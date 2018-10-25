package IdentityMappers;

import Dao.PlaylistTrack.PlaylistTrackDAOMapper;
import Dao.Track.TrackDAOMapper;
import Domain.Track.Track;
import Domain.Track.TracksResponse;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;

public class TrackIdentityMapper {

    @Inject
    TrackDAOMapper trackDAOMapper;

    @Inject
    PlaylistTrackDAOMapper playlistTrackDAOMapper;

    private static TrackIdentityMapper instance = new TrackIdentityMapper();
    private static HashMap<Integer, Track> tracksAvailableToAddToPlaylist = new HashMap<>();
    private static HashMap<Integer, Track> playlistTracks = new HashMap<>();
    private static int currentPlaylistID;
    private static int currentPlaylistIDAvailableTracks;
    private boolean updateNeeded = true;

    private TrackIdentityMapper() {
    }

    public TrackIdentityMapper getInstance() {
        return this.instance;
    }

    public TracksResponse getAll(int playlistID) {
        TracksResponse tracksResponse;
        if (playlistID != currentPlaylistIDAvailableTracks) {
            currentPlaylistIDAvailableTracks = playlistID;
            tracksResponse = new TracksResponse(trackDAOMapper.getAll(playlistID));
            tracksAvailableToAddToPlaylist.clear();
            for (Track track : tracksResponse.getTracks()) {
                tracksAvailableToAddToPlaylist.put(track.getId(), track);
            }
            return tracksResponse;
        }
        ArrayList<Track> tracks = new ArrayList<>();
        for (Track track : tracksAvailableToAddToPlaylist.values()) {
            tracks.add(track);
        }
        tracksResponse = new TracksResponse(tracks);
        return tracksResponse;
    }

    public TracksResponse getTracksForPlaylist(int playlistId) {
        TracksResponse tracksResponse;
        if (playlistId != currentPlaylistID || updateNeeded) {
            System.out.println("uit database tracks for playlist");
            updateNeeded = false;
            currentPlaylistID = playlistId;
            tracksResponse = new TracksResponse(playlistTrackDAOMapper.findAll(playlistId));
            playlistTracks.clear();
            for (Track track : tracksResponse.getTracks()) {
                playlistTracks.put(track.getId(), track);
            }
            return tracksResponse;
        }
        ArrayList<Track> tracks = new ArrayList<>();
        for (Track track : playlistTracks.values()) {
            tracks.add(track);
        }
        tracksResponse = new TracksResponse(tracks);
        return tracksResponse;
    }

}
