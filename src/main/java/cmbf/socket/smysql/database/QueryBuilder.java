package cmbf.socket.smysql.database;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.HashMap;
import java.util.List;

public interface QueryBuilder {
    <T> Subject<List<T>> queryAsync(String query, Class<T> clss, Observer<List<T>> observer);
    <T> Subject<T> queryRowAsync(String query, Class<T> clss, Observer<T> observer);
    <T  extends DatabaseModel> List<T> query(String query, Class<T> clss);
    <T extends  DatabaseModel> T queryRow(String query, Class<T> clss);
    QueryBuilder select(String ... args);
    QueryBuilder from(String fromTable);
    QueryBuilder join(String table, String foreignKey, String joinKey);
    QueryBuilder where(String condition);
    <T extends  DatabaseModel>  List<T> get(Class<T> clss);
}
