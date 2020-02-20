package cmbf.socket.smysql.database;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.Subject;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class MysqlQueryBuilder implements QueryBuilder {
    @Override
    public <T> Subject<List<T>> queryAsync(String query, Class<T> clss, Observer<List<T>> observer) {
        return null;
    }

    @Override
    public <T> Subject<T> queryRowAsync(String query, Class<T> clss, Observer<T> observer) {
        return null;
    }

    @Override
    public <T> List<T> query(String query, Class<T> clss) {
        return null;
    }

    @Override
    public <T> T queryRow(String query, Class<T> clss) {
        return null;
    }

    private void executeQuery() {
        try {
            Statement stmt= DataSource.createStatement();
            ResultSet rs=stmt.executeQuery("select * from emp");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
        }catch(Exception e){
                System.out.println(e.getMessage());
        }

    }
}
