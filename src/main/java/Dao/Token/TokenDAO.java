package Dao.Token;

import Dao.DAO;
import Domain.Login.RequestLogin;
import Domain.Login.ResponseLogin;

import javax.persistence.EntityNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenDAO extends DAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    public ResponseLogin generateOrGetToken(String user) {
        ResponseLogin responseLogin;
        responseLogin = getToken(user);
        if (responseLogin == null) {
            responseLogin = generateAndSaveToken(user);
        }

        return responseLogin;
    }


    private ResponseLogin getToken(String user) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * from token where user = ?");
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return buildResponseLogin(resultSet);
            }
            statement.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + e);
        }
        return null;
    }

    private ResponseLogin buildResponseLogin(ResultSet resultSet) throws SQLException {
        String user = resultSet.getString("user");
        String token = resultSet.getString("token");
        ResponseLogin rl = new ResponseLogin();
        rl.setUser(user);
        rl.setToken(token);
        return rl;
    }

    private void saveToken(ResponseLogin responseLogin) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO token (user, token) VALUES (?, ?)");
            statement.setString(1, responseLogin.getUser());
            statement.setString(2, responseLogin.getToken());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.warning("Failed to save authenticationToken to database");
            e.printStackTrace();
        }
    }

    private ResponseLogin generateAndSaveToken(String user) {
        ResponseLogin responseLogin = new ResponseLogin();
        responseLogin.setToken("123-123");
        responseLogin.setUser(user);
        saveToken(responseLogin);

        return responseLogin;
    }
}
