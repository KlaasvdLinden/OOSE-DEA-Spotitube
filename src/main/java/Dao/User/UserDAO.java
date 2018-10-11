package Dao.User;

import Dao.DAO;
import Dao.util.DatabaseProperties;
import Domain.Login.RequestLogin;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends DAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    public RequestLogin getUser(String user, String password){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null ;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * from users where user = ? and password = ?");
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

    private RequestLogin buildRequestLogin(ResultSet resultSet) throws SQLException {
        String user = resultSet.getString("user");
        String password = resultSet.getString("password");
        RequestLogin rl = new RequestLogin();
        rl.setUser(user);
        rl.setPassword(password);
        return rl;
    }

}
