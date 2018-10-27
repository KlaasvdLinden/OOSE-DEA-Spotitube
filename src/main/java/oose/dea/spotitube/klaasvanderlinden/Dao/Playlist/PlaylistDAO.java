package oose.dea.spotitube.klaasvanderlinden.Dao.Playlist;

import oose.dea.spotitube.klaasvanderlinden.Dao.DAO;
import oose.dea.spotitube.klaasvanderlinden.Domain.Playlist.Playlist;
import oose.dea.spotitube.klaasvanderlinden.Domain.Playlists.Playlists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PlaylistDAO extends DAO implements PlaylistDAOMapper {


    private Logger logger = Logger.getLogger(getClass().getName());
    private static final String FIND_ALL_PLAYLISTS_QUERY = "SELECT * FROM playlists";
    private static final String EDIT_PLAYLIST_QUERY = "UPDATE playlists SET name = ? WHERE id = ?";
    private static final String ADD_PLAYLIST_QUERY = "INSERT INTO playlists VALUE (NULL, ?, ?)";
    private static final String DELETE_PLAYLIST_QUERY = "DELETE FROM playlists WHERE id = ?";
    private static final String TOTAL_PLAYLIST_LENGTH_QUERY =  "SELECT sum(duration) as totalDuration FROM tracks " +
            "inner join playlisttracks on tracks.id = playlisttracks.trackID";

    @Override
    public Playlists findAll(int userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Playlists playlists = new Playlists();
        playlists.setLength(totalPlaylistLength());
        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_ALL_PLAYLISTS_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Playlist playlist = buildPlaylist(resultSet, userID);
                playlists.addPlayList(playlist);
            }

        } catch (SQLException e) {
            logger.warning("Failed to get all playlists from database");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, resultSet);
        }
        return playlists;
    }

    @Override
    public void editPlaylist(int id, String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(EDIT_PLAYLIST_QUERY);
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

    @Override
    public void addPlaylist(int userId, String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ADD_PLAYLIST_QUERY);
            statement.setInt(1, userId);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to insert the playlist");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
        }
    }

    @Override
    public void deletePlaylist(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_PLAYLIST_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to delete the playlist");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
        }
    }

    @Override
    public int totalPlaylistLength(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalLength = 0;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(TOTAL_PLAYLIST_LENGTH_QUERY);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                totalLength = resultSet.getInt("totalDuration");
            }
        } catch(SQLException e){
            logger.warning("Failed to get total length of all playlists");
            e.printStackTrace();
        } finally{
            closeConnection(connection, statement, resultSet);
        }
        return totalLength;
    }

    private Playlist buildPlaylist(ResultSet resultSet, int user) throws SQLException {
        int id = resultSet.getInt("id");
        int userID = resultSet.getInt("userID");
        String name = resultSet.getString("name");
        Playlist playlist = new Playlist();
        playlist.setId(id);
        playlist.setName(name);
        if(userID == user){
            playlist.setOwner(true);
        } else{
            playlist.setOwner(false);
        }
        return playlist;
    }
}
