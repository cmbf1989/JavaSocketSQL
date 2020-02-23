package cmbf.socket.smysql;

import cmbf.socket.smysql.database.Column;
import cmbf.socket.smysql.database.DatabaseModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnotationHelper {

    public static <T extends DatabaseModel> T getAnnotationObject(Class<T> clss, ResultSet resultSet, int[] columnMap) throws Exception {
        T object = clss.getDeclaredConstructor().newInstance();
        for (int i = 0; i < columnMap.length; i++) {
            Object value = resultSet.getObject(i + 1);
            String key = resultSet.getMetaData().getColumnName(i + 1);
            if (columnMap[i] > 0) {
                clss.getField(key).set(object, value);
            } else {
                object.putExtraField(key, value);
            }
        }
        return object;
    }

    public static <T> int[] addColumns(ResultSet rs, Class<T> clss) throws Exception {
        int columns = rs.getMetaData().getColumnCount() + 1;
        int[]columnsMap = new int[columns - 1];
        Field[] fields = clss.getDeclaredFields();
        for(int i = 0; i < columnsMap.length; i++) {
            String columnMetaName = rs.getMetaData().getColumnName(i+ 1);
            columnsMap[i] = checkField(fields, i, columnMetaName);
        }
        return columnsMap;
    }

    public static int checkField(Field[] fields, int i, String columnMetaName) {
        for(int a = 0; a < fields.length; a++) {
            if (fields[a].isAnnotationPresent(Column.class)) {
                String columnName = fields[a].getAnnotation(Column.class).name();
                if (columnMetaName.equals(columnName)) {
                    return i + 1;
                }
            }
        }
        return 0;
    }
}
