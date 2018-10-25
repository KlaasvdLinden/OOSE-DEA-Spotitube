package IdentityMappers;

import Dao.PlaylistTrack.PlaylistTrackDAOMapper;
import Dao.Track.TrackDAOMapper;
import Domain.Track.Track;
import Domain.Track.TracksResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;

@Singleton
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

    public TracksResponse getAllNotInPlaylist(int playlistID) {
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
            System.out.println("uit database tracks for playlist" +  updateNeeded);
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
