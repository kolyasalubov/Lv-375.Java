package myArticles.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ConnectionManager {
    private static final String FAILED_REGISTRATE_DRIVER = "Failed to Registrate JDBC Driver";
    private static final String FAILED_CREATE_CONNECTION = "Failed to Create Connection";
    private static final String FAILED_CLOSE_CONNECTION = "Failed to Close Connection";
    private static final String FAILED_CONNECTION = "Connection Failed";
    private static volatile ConnectionManager instance = null;
    private DataSource dataSource;
    private final Map <Long, Connection> connections = new HashMap();

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return getInstance(null);
    }

    public static ConnectionManager getInstance(DataSource dataSource) {
        if (instance == null) {
            Class var1 = ConnectionManager.class;
            synchronized (ConnectionManager.class) {
                if (instance == null) {
                    instance = new ConnectionManager();
                }
            }
        }

        instance.checkStatus(dataSource);
        return instance;
    }

    private void checkStatus(DataSource dataSource) {
        if (dataSource == null) {
            if (this.getDataSource() == null) {
                this.setDataSource(DataSourceRepository.getDefault());
            }
        } else if (this.getDataSource() == null || !this.getDataSource().equals(dataSource)) {
            this.setDataSource(dataSource);
        }

    }

    private void setDataSource(DataSource dataSource) {
        Class var2 = ConnectionManager.class;
        synchronized (ConnectionManager.class) {
            this.dataSource = dataSource;
            closeAllConnections();
        }
    }

    private DataSource getDataSource() {
        return this.dataSource;
    }

    private Map <Long, Connection> getAllConnections() {
        return this.connections;
    }

    private void addConnection(Connection connection) {
        this.getAllConnections().put(Thread.currentThread().getId(), connection);
    }

    public Connection getConnection() {
        Connection connection = this.getAllConnections().get(Thread.currentThread().getId());
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(this.getDataSource().getConnectionUrl(), this.getDataSource().getUsername(), this.getDataSource().getPassword());
            } catch (SQLException var3) {
                throw new RuntimeException("Failed to Create Connection", var3);
            }

            this.addConnection(connection);
        }

        return connection;
    }

    public void beginTransaction() {
        try {
            this.getConnection().setAutoCommit(false);
        } catch (SQLException var2) {
            throw new RuntimeException("Connection Failed", var2);
        }
    }

    public void commitTransaction() {
        try {
            this.getConnection().commit();
        } catch (SQLException var2) {
            throw new RuntimeException("Connection Failed", var2);
        }
    }

    public void rollbackTransaction() {
        try {
            this.getConnection().rollback();
        } catch (SQLException var2) {
            throw new RuntimeException("Connection Failed", var2);
        }
    }

    public static void closeAllConnections() {
        if (instance != null) {
            Iterator var1 = instance.getAllConnections().keySet().iterator();

            while (var1.hasNext()) {
                Long key = (Long) var1.next();
                if (instance.getAllConnections().get(key) != null) {
                    try {
                        ((Connection) instance.getAllConnections().get(key)).close();
                    } catch (SQLException var3) {
                        throw new RuntimeException("Failed to Close Connection", var3);
                    }

                    instance.getAllConnections().put(key, null);
                }
            }
        }

    }
}