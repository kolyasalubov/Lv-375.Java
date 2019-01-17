package myArticles.edu.db;

/**
 * Class with contains all info about connect with Database
 */
public final class DataSource {
    private String connectionUrl;
    private String username;
    private String password;

    public DataSource( String connectionUrl, String username, String password) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionUrl() {
        return this.connectionUrl;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean equals(Object dataSource) {
        boolean result = false;
        if (dataSource instanceof DataSource) {
            result = this.getConnectionUrl().equals(((DataSource)dataSource).getConnectionUrl()) && this.getUsername().equals(((DataSource)dataSource).getUsername()) && this.getPassword().equals(((DataSource)dataSource).getPassword());
        }

        return result;
    }
}