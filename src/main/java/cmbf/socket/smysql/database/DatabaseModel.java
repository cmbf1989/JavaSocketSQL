package cmbf.socket.smysql.database;

import java.util.HashMap;

public abstract class DatabaseModel {

    protected HashMap<String, Object> mExtraFields;

    public DatabaseModel() {
        this.mExtraFields = new HashMap<>();
    }

    public HashMap<String, Object> getExtraFields() {
        return mExtraFields;
    }

    public void putExtraField(String key, Object element) {
        this.mExtraFields.put(key, element);
    }

    public <T> T getExtraField(String key) {
        return (T) this.mExtraFields.get(key);
    }
}
