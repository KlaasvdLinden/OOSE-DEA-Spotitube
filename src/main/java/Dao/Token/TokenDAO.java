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
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String INSERT_TOKEN_QUERY = "INSERT INTO token (user, token) VALUES (?, ?)";
    private static final String UPDATE_TOKEN_QUERY = "UPDATE token SET token = ? WHERE user = ?";
    private final int TOKEN_LENGTH = 24;

    public ResponseLogin generateOrUpdateToken(String user) {
        ResponseLogin responseLogin;
        responseLogin = getToken(user);
        if (responseLogin == null) {
            responseLogin = generateAndSaveToken(user, INSERT_TOKEN_QUERY);
        } else{
            responseLogin = generateAndSaveToken(user, UPDATE_TOKEN_QUERY);
        }

        return responseLogin;
    }


    private ResponseLogin getToken(String user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * from token where user = ?");
            statement.setString(1, user);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return buildResponseLogin(resultSet);
            }
            return null;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + e);
        } finally {
            this.closeConnection(connection, statement, resultSet);
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

    private void saveToken(ResponseLogin responseLogin, String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            if(query.equals(INSERT_TOKEN_QUERY)){
                statement.setString(1, responseLogin.getUser());
                statement.setString(2, responseLogin.getToken());
            } else{
                statement.setString(1, responseLogin.getToken());
                statement.setString(2, responseLogin.getUser());
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to save authenticationToken to database");
            e.printStackTrace();
        } finally {
            this.closeConnection(connection, statement, null);
        }
    }

    private ResponseLogin generateAndSaveToken(String user, String query) {
        ResponseLogin responseLogin = new ResponseLogin();
        responseLogin.setToken(generateToken(TOKEN_LENGTH));
        responseLogin.setUser(user);
        saveToken(responseLogin, query);

        return responseLogin;
    }

    private String generateToken(int count){
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
