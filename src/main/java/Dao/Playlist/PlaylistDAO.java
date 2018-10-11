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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Playlists playlists = new Playlists();
        playlists.setLength(totalPlaylistLength(token));
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select id, playlists.user, name, owner from " +
                    "spotitube.playlists inner join spotitube.token on playlists.user = token.user where token = ?");
            statement.setString(1, token);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Playlist playlist = buildPlaylist(resultSet);
                playlists.addPlayList(playlist);
            }

        } catch (SQLException e) {
            logger.warning("Failed to get all playlists from database");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, resultSet);
            return playlists;
        }
    }

    public int totalPlaylistLength(String token){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalLength = 0;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("select sum(duration) as totalDuration from spotitube.tracks " +
                    "inner join spotitube.playlisttracks on tracks.id = playlisttracks.trackID \n" +
                    "where playlisttracks.playlistID in (\n" +
                    "select playlistid from \n" +
                    "spotitube.playlists inner join spotitube.token on playlists.user = token.user\n" +
                    "where token =  ?\n" +
                    ")");
            statement.setString(1, token);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                totalLength = resultSet.getInt("totalDuration");
            }
        } catch(SQLException e){
            logger.warning("Failed to get total length of all playlists");
            e.printStackTrace();
        } finally{
            closeConnection(connection, statement, resultSet);
            return totalLength;
        }
    }


    public void editPlaylist(int id, String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("update playlists set name = ? where id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to edit the playlist name");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
        }
    }

    public void addPlaylist(String name, String owner) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("insert into playlists values(null, ?, ?, 1)");
            statement.setString(1, owner);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to insert the playlist");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
        }
    }

    public void deletePlaylist(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("delete from playlists where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to delete the playlist");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
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
