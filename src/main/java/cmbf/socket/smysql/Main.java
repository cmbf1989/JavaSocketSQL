package cmbf.socket.smysql;

import cmbf.socket.smysql.database.DataSource;
import cmbf.socket.smysql.repository.User;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //DataSource.setConnectionPersistance(true);
        DataSource.init("127.0.0.1", "test", "root");

        long timeElapsed = System.currentTimeMillis();

        List<User> user = DataSource.builder()
                                .select("user.*", "user_type.name")
                                .from("user")
                                .join("user_type", "user.type_id", "user_type.id")
                                .get(User.class);
        DataSource.stop();
        timeElapsed = System.currentTimeMillis() - timeElapsed;

        System.out.println("List size:" + user.size());
        System.out.println("Elapsed: " + timeElapsed);
    }
}
