package oose.dea.spotitube.klaasvanderlinden.Dao;

import oose.dea.spotitube.klaasvanderlinden.Dao.util.DatabaseProperties;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;

    public DAO() {
        this.databaseProperties = new DatabaseProperties();
        tryLoadJdbcDriver(databaseProperties);
    }
    private void tryLoadJdbcDriver(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.driver());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't load JDBC Driver " + databaseProperties.driver(), e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseProperties.connectionString());
    }

    protected void closeConnection(Connection connection, PreparedStatement statement, ResultSet result) {
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
            if (result != null) result.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

}