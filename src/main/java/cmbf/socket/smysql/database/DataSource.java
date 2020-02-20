package cmbf.socket.smysql.database;

import javax.management.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class DataSource {
    private final static DataSource instance = new DataSource();
    private boolean mPersistent = true;
    private DatabaseConfiguration mConfiguration;
    private QueryBuilder mBuilder;
    private Connection mConnection;

    public DataSource() {
        mBuilder = new MysqlQueryBuilder();
    }

    public static void setConnectionPersistance(boolean enabled) {
        instance.mPersistent = enabled;
    }

    public static boolean init(String host, String database, String user, String password) {
        try {
            instance.mConfiguration = new DatabaseConfiguration(host, database, user, password);
            if (instance.mPersistent) {
                instance.mConnection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }

        return true;
    }


    public static boolean init(String host, String database, String user) {
        return init(host, database, user, "");
    }

    public static boolean dispose() {

        if (instance.mConnection != null) {
            try {
                instance.mConnection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
                return false;
            }
        }
        return true;
    }

    public static Connection getConnection() {
        return instance.mConnection;
    }

    public static Statement createStatement() throws SQLException {
        return instance.mConnection.createStatement();
    }

    public static QueryBuilder builder() {
        return instance.mBuilder;
    }


    public static DataSource getInstance() {
        return instance;
    }
}
