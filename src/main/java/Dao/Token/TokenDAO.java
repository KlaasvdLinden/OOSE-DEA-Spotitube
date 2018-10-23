package Dao.Token;

import Dao.DAO;
import Dao.Entity.User;
import Domain.Login.RequestLogin;
import Domain.Login.ResponseLogin;

import javax.persistence.EntityNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenDAO extends DAO implements TokenMapper {

    private Logger logger = Logger.getLogger(getClass().getName());
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String UPDATE_TOKEN_QUERY = "UPDATE users SET token = ? WHERE id = ?";
    private final int TOKEN_LENGTH = 24;

    @Override
    public ResponseLogin updateToken(int userID, String username) {
        ResponseLogin responseLogin;
        responseLogin = generateAndSaveToken(userID, username);
        return responseLogin;
    }


    private void saveToken(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_TOKEN_QUERY);
            statement.setString(1, user.getToken());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to save authenticationToken to database");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
        }
    }

    private ResponseLogin generateAndSaveToken(int userID, String username) {
        User user = new User();
        user.setToken(generateToken(TOKEN_LENGTH));
        user.setId(userID);
        saveToken(user);

        ResponseLogin responseLogin = new ResponseLogin();
        responseLogin.setToken(user.getToken());
        responseLogin.setUser(username);
        return responseLogin;
    }

    private String generateToken(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
