package cmbf.socket.smysql.repository;

import cmbf.socket.smysql.database.Column;

public class UserType {

    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

}
