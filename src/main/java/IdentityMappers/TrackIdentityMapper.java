package IdentityMappers;

import Dao.PlaylistTrack.PlaylistTrackDAOMapper;
import Dao.Track.TrackDAOMapper;
import Domain.Track.Track;
import Domain.Track.TracksResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Singleton
public class TrackIdentityMapper {

    @Inject
    TrackDAOMapper trackDAOMapper;

    @Inject
    PlaylistTrackDAOMapper playlistTrackDAOMapper;

    private static TrackIdentityMapper instance = new TrackIdentityMapper();
    private static HashMap<Integer, Track> tracksAvailableToAddToPlaylist = new HashMap<>();
    private static LinkedHashMap<Integer, Track> playlistTracks = new LinkedHashMap<>();
    private static int currentPlaylistID;
    private static int currentPlaylistIDAvailableTracks;
    private boolean updateNeeded = true;

    private TrackIdentityMapper() {
    }

    public TrackIdentityMapper getInstance() {
        return this.instance;
    }

    public TracksResponse getAllNotInPlaylist(int playlistID) {
        TracksResponse tracksResponse;
        if (playlistID != currentPlaylistIDAvailableTracks) {
            currentPlaylistIDAvailableTracks = playlistID;
            tracksAvailableToAddToPlaylist.clear();
            for (Track track : trackDAOMapper.getAll(playlistID)) {
                tracksAvailableToAddToPlaylist.put(track.getId(), track);
            }
        }
        ArrayList<Track> tracks = new ArrayList<>(tracksAvailableToAddToPlaylist.values());
        tracksResponse = new TracksResponse(tracks);
        return tracksResponse;
    }

    public TracksResponse getTracksForPlaylist(int playlistId) {
        TracksResponse tracksResponse;
        if (playlistId != currentPlaylistID || updateNeeded) {
            updateNeeded = false;
            currentPlaylistID = playlistId;
            playlistTracks.clear();
            for (Track track : playlistTrackDAOMapper.findAll(playlistId)) {
                playlistTracks.put(track.getId(), track);
            }
        }
        ArrayList<Track> tracks = new ArrayList<>(playlistTracks.values());
        tracksResponse = new TracksResponse(tracks);
        return tracksResponse;
    }

    public void addTrack(int playlistID, int trackID, boolean offlineAvailable){
        playlistTrackDAOMapper.addTrack(playlistID, trackID, offlineAvailable);
        tracksAvailableToAddToPlaylist.remove(trackID);
        updateNeeded = true;
    }

    public void deleteTrack(int playlistID, int trackID){
        playlistTrackDAOMapper.deleteTrack(playlistID ,trackID);
        Track track = playlistTracks.get(trackID);
        playlistTracks.remove(trackID);
        tracksAvailableToAddToPlaylist.put(track.getId(), track);
        updateNeeded = false;
    }

}
