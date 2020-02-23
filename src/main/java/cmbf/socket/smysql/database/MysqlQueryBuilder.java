package cmbf.socket.smysql.database;

import cmbf.socket.smysql.AnnotationHelper;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.Subject;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class MysqlQueryBuilder implements QueryBuilder {
    private String mSelectRaw;
    private String mJoinRaw  = "";
    private String mWhereRaw = "";
    private String mFromRaw;
    private String mGroupByRaw  = "";
    private String mOrderByRaw = "";

    private List<String> selectFields;
    private List<String> mWhere;
    private List<String> mJoins;
    private List<String> mGroupBy;
    private List<String> mOrderBy ;

    private MysqlQueryBuilder() {
        selectFields = new ArrayList<>();
        mJoins = new ArrayList<>();
    }

    @Override
    public <T> Subject<List<T>> queryAsync(String query, Class<T> clss, Observer<List<T>> observer) {
        return null;
    }

    @Override
    public <T > Subject<T> queryRowAsync(String query, Class<T> clss, Observer<T> observer) {
        return null;
    }

    @Override
    public <T extends DatabaseModel> List<T> query(String query, Class<T> clss) {
        List<T> items = new ArrayList<>();
        try {
            DataSource.startConnection();
            Statement stmt= DataSource.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int[] columns = AnnotationHelper.addColumns(rs, clss);
            while(rs.next()) {
                items.add(AnnotationHelper.getAnnotationObject(clss, rs, columns));
            }
            DataSource.disposeConnection();
            return items;
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));

        }
        return items;
    }

    @Override
    public <T extends DatabaseModel> T queryRow(String query, Class<T> clss) {
        try {
            DataSource.startConnection();
            Statement stmt= DataSource.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            T object = null;
            int[] columns = AnnotationHelper.addColumns(rs, clss);
            if(rs.next()) {
                object = AnnotationHelper.getAnnotationObject(clss, rs, columns);
            }

            DataSource.disposeConnection();
            return object;
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));

        }
        return null;
    }

    @Override
    public QueryBuilder select(String... args) {
        selectFields.addAll(Arrays.asList(args));
        mSelectRaw = String.join(",", selectFields);
        return this;
    }

    @Override
    public QueryBuilder from(String fromTable) {
        mFromRaw = fromTable;
        return this;
    }

    @Override
    public QueryBuilder join(String table, String foreignKey, String joinKey) {
        mJoins.add("JOIN " + table + " ON " + foreignKey + " = " + joinKey);
        mJoinRaw = String.join("\n", mJoins);
        return this;
    }

    @Override
    public QueryBuilder where(String condition) {
        mWhere.add(condition);
        mWhereRaw = "WHERE " + String.join(" ", mWhere);
        return this;
    }

    @Override
    public <T extends DatabaseModel> List<T> get(Class<T> clss) {
        String query = "SELECT " + mSelectRaw + "\n FROM " + mFromRaw + "\n" + mJoinRaw + "\n" + mWhereRaw;
        return query(query, clss);
    }
}
