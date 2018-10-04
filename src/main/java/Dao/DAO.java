package Dao;

import Dao.util.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

}