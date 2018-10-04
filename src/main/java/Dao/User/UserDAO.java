package Dao.User;

import Dao.DAO;
import Dao.util.DatabaseProperties;
import Domain.Login.RequestLogin;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends DAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    public RequestLogin getUser(String user){
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * from users where user = ?");
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return buildRequestLogin(resultSet);
            }
            statement.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + e);
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
