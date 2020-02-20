package cmbf.socket.smysql.database;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.List;

public interface QueryBuilder {
    <T> Subject<List<T>> queryAsync(String query, Class<T> clss, Observer<List<T>> observer);
    <T> Subject<T> queryRowAsync(String query, Class<T> clss, Observer<T> observer);
    <T> List<T> query(String query, Class<T> clss);
    <T> T queryRow(String query, Class<T> clss);
}
