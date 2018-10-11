package Dao.Track;

import Dao.DAO;
import Domain.Track.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TrackDAO extends DAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    public ArrayList<Track> getAll(int playlistID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from spotitube.tracks where id not in (\n" +
                    "select trackID from spotitube.playlisttracks where playlistID = ? )");
            statement.setInt(1, playlistID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tracks.add(buildTrack(resultSet));
            }
            return tracks;
        } catch (SQLException e) {
            logger.warning("Failed to get all tracks from database");
            e.printStackTrace();
            return null;
        } finally {
            this.closeConnection(connection, statement, resultSet);
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

        Track track = new Track();
        track.setId(id);
        track.setTitle(title);
        track.setPerformer(performer);
        track.setDuration(duration);
        track.setAlbum(album);
        track.setPlaycount(playcount);
        track.setPublicationDate(publicationDate);
        track.setDescription(description);


        return track;
    }
}
