package com.it.academy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {

    private final static String FAILED_CREATE_CONNECTION = "Failed to Create Connection";
    private final static String FAILED_CLOSE_CONNECTION = "Failed to Close Connection";

    private static volatile ConnectionManager instance = null;

    private DataSource dataSource;
    private final Map<Long, Connection> connections;

    private ConnectionManager() {
        this.connections = new HashMap<Long, Connection>();
    }

    public static ConnectionManager getInstance() {
        return getInstance(null);
    }

    public static ConnectionManager getInstance(DataSource dataSource) {
        if (instance == null) {
            synchronized (ConnectionManager.class) {
                if (instance == null) {
                    instance = new ConnectionManager();
                }
            }
        }
        instance.checkDataSourceStatus(dataSource);
        return instance;
    }

    private void checkDataSourceStatus(DataSource dataSource) {
		/*-		dataSource		this.dataSource		    Action
		 * 			null			null				create default
		 * 			null			not null			nothing
		 * 			not null		null				save dataSource
		 * 			not null		not null			if equals then nothing
		 */
        if ((dataSource == null) && (getDataSource() == null)) {
            setDataSource(DataSourceRepository.getSource());
        } else if ((getDataSource() == null) ||
                (!getDataSource().equals(dataSource)) && (dataSource != null)) {
            setDataSource(dataSource);
        }
    }

    private void setDataSource(DataSource dataSource) {
        synchronized (ConnectionManager.class) {
            this.dataSource = dataSource;
            closeAllConnections();
        }
    }

    private DataSource getDataSource() {
        return dataSource;
    }


    private Map<Long, Connection> getAllConnections() {
        return this.connections;
    }

    private void addConnection(Connection connection) {
        getAllConnections().put(Thread.currentThread().getId(), connection);
    }

    public Connection getConnection() {
        Connection connection = getAllConnections().get(Thread.currentThread().getId());
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(
                        getDataSource().getConnectionUrl(),
                        getDataSource().getUsername(),
                        getDataSource().getPassword());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(FAILED_CREATE_CONNECTION, e);
            }
            addConnection(connection);
        }
        return connection;
    }


    public static void closeAllConnections() {
        if (instance != null) {
            for (Long key : instance.getAllConnections().keySet()) {
                if (instance.getAllConnections().get(key) != null) {
                    try {
                        instance.getAllConnections().get(key).close();
                    } catch (SQLException e) {
                        throw new RuntimeException(FAILED_CLOSE_CONNECTION, e);
                    }
                    instance.getAllConnections().put(key, null);
                }
            }
        }
    }
}
