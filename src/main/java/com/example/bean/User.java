package com.example.bean;

import javax.persistence.Id;

/**
 * Annotated Variant Calling Format (VCF) uploaded by the users
 *
 * @author Zhejian YU
 */
public class User {
    @Id
    private int id;
    private String userName;
    private String passwd;

    public User() {}

    /**
     * Constructor for Annotated Variant Calling Format (VCF) uploaded by the users
     *
     * @param id Index inside database
     * @param userName Date it was uploaded
     * @param passwd By whom it was uploaded
     */
    public User(int id, String userName, String passwd) {
        this.id = id;
        this.userName = userName;
        this.passwd = passwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
