package Dao.Playlist;

import Dao.DAO;
import Domain.Playlist.Playlist;
import Domain.Playlists.Playlists;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PlaylistDAO extends DAO {


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

    public void editPlaylist(int id, String name) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("update playlists set name = ? where id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.warning("Failed to edit the playlist name");
            e.printStackTrace();
        }
    }

    public void addPlaylist(String name, String owner) {
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into playlists values(null, ?, ?, 1)");
            statement.setString(1, owner);
            statement.setString(2, name);
            statement.executeUpdate();
            connection.close();
        } catch(SQLException e){
            logger.warning("Failed to insert the playlist");
            e.printStackTrace();
        }

    }

    public void deletePlaylist(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from playlists where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        } catch(SQLException e){
            logger.warning("Failed to delete the playlist");
            e.printStackTrace();
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
