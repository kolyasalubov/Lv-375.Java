package com.it.academy.db;

public final class DataSource {

    private String connectionUrl;
    private String username;
    private String password;

    public DataSource(String connectionUrl, String username, String password) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataSource that = (DataSource) o;

        return (connectionUrl != null ? connectionUrl.equals(that.connectionUrl) : that.connectionUrl == null)
                && (username != null ? username.equals(that.username) : that.username == null)
                && (password != null ? password.equals(that.password) : that.password == null);
    }

}
