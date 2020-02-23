package cmbf.socket.smysql.repository;

import cmbf.socket.smysql.database.Column;
import cmbf.socket.smysql.database.DatabaseModel;

public class User extends DatabaseModel {

    @Column(name = "id")
    public Integer id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "type_id")
    public Integer type_id;
}
