package Dao.Playlist;

import Dao.DAO;
import Dao.Track.TrackDAO;
import Domain.Playlist.Playlist;
import Domain.Playlists.Playlists;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PlaylistDAO extends DAO {

    @Inject
    TrackDAO trackDAO;

    private Logger logger = Logger.getLogger(getClass().getName());


    public Playlists findAll(String token) {
        Playlists playlists = new Playlists();
        playlists.setLength(123);
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select id, playlists.user, name, owner from " +
                    "spotitube.playlists inner join spotitube.token on playlists.user = token.user where token = ?");
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Playlist playlist = buildPlaylist(resultSet);
                playlists.addPlayList(playlist);
            }
            connection.close();
            return playlists;
        } catch (SQLException e) {
            logger.warning("Failed to get all playlists from database");
            e.printStackTrace();
            return null;

        }

    }

    private Playlist buildPlaylist(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        boolean owner = resultSet.getBoolean("owner");
        Playlist playlist = new Playlist();
        playlist.setId(id);
        playlist.setName(name);
        playlist.setOwner(owner);
        return playlist;

    }
}
