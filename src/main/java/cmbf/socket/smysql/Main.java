package cmbf.socket.smysql;

import cmbf.socket.smysql.database.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource.setConnectionPersistance(true);
        DataSource.init("127.0.0.1", "test", "root");
    }
}
