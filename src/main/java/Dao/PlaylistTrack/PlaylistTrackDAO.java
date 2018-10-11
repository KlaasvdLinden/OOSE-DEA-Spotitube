package Dao.PlaylistTrack;

import Dao.DAO;
import Domain.Track.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PlaylistTrackDAO extends DAO {


    private Logger logger = Logger.getLogger(getClass().getName());

    public ArrayList<Track> findAll(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ;
        ArrayList<Track> tracks = new ArrayList<Track>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from spotitube.tracks inner join " +
                    "spotitube.playlisttracks on tracks.id = playlisttracks.trackID\n" +
                    "where playlistID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tracks.add(buildTrack(resultSet));
            }

        } catch (SQLException e) {
            logger.warning("Failed to get all tracks from database");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, resultSet);
            return tracks;
        }
    }

    public void addTrack(int playlistID, int trackID, boolean offlineAvailable) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("insert into playlisttracks values(?, ?, ?)");
            statement.setInt(1, playlistID);
            statement.setInt(2, trackID);
            statement.setBoolean(3, offlineAvailable);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to add track to playlist");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
        }
    }

    public void deleteTrack(int playlistID, int trackID) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("delete from playlisttracks where playlistID = ? " +
                    "and trackID = ?");
            statement.setInt(1, playlistID);
            statement.setInt(2, trackID);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to delete the track");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
        }
    }


    private Track buildTrack(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String performer = resultSet.getString("performer");
        int duration = resultSet.getInt("duration");
        String album = resultSet.getString("album");
        int playcount = resultSet.getInt("playcount");
        String publicationDate = resultSet.getString("publicationDate");
        String description = resultSet.getString("description");
        boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");

        Track track = new Track();
        track.setId(id);
        track.setTitle(title);
        track.setPerformer(performer);
        track.setDuration(duration);
        track.setAlbum(album);
        track.setPlaycount(playcount);
        track.setPublicationDate(publicationDate);
        track.setDescription(description);
        track.setOfflineAvailable(offlineAvailable);

        return track;
    }
}




