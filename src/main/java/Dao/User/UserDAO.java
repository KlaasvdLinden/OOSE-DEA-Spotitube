package Dao.User;

import Dao.DAO;
import Dao.Entity.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends DAO implements UserDAOMapper {

    private Logger logger = Logger.getLogger(getClass().getName());
    private static final String GET_USER_QUERY = "SELECT * from users where user = ? and password = ?";

    @Override
    public User getUser(String user, String password){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null ;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_USER_QUERY);
            statement.setString(1, user);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return buildRequestLogin(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + e);
        } finally {
           this.closeConnection(connection, statement, resultSet);
        }
        return null;
    }

    private User buildRequestLogin(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("user");
        String password = resultSet.getString("password");
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}
