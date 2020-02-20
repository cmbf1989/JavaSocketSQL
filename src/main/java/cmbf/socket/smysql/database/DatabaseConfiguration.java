package cmbf.socket.smysql.database;

public class DatabaseConfiguration {

    private String user;
    private String password;
    private String database;
    private String host;

    public DatabaseConfiguration(String user, String password, String database, String host) {
        this.user = user;
        this.password = password;
        this.database = database;
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
