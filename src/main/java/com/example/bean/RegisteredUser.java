package com.example.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Annotated Variant Calling Format (VCF) uploaded by the users
 *
 * We got such a weird name because of the PostgreSQL
 *
 * The user system mainly models the password and shadow file (/etc/passwd, /etc/shadow) on GNU/Linux with following differences:
 *
 * Group system is not added in this version.
 *
 * @author Zhejian YU
 */
@Proxy(lazy = false)
@Entity
@Table(name = "registered_user")
public class RegisteredUser {
    @Id @Column(name = "id", length = 100, nullable=false)
    private long id;

    @Column(name = "user_name", length = 100, nullable=false)
    private String userName;

    @Column(name = "user_passwd", length = 100, nullable=false)
    private String userPasswd;

    @Column(name = "encrypt_algorithm", length = 100, nullable=false)
    private String encryptAlgorithm;

    @Column(name = "last_change_date")
    private Date lastChangeDate;

    @Column(name = "min_age")
    private Date minAge;

    @Column(name = "max_age")
    private Date maxAge;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "is_active")
    private boolean isActive;

    public RegisteredUser() {}

    /**
     * Constructor for Annotated Variant Calling Format (VCF) uploaded by the users
     * @param id Index of the user inside database
     * @param userName Name of the user
     * @param userPasswd Password of the user. Not implemented in this version
     * @param encryptAlgorithm Encrypt method of the password. Not implemented in this version
     * @param lastChangeDate The date of most recent password change. Not implemented in this version
     * @param minAge Minimum password age. Not implemented in this version
     * @param maxAge Maximum password age. Not implemented in this version
     * @param expireDate Date when the user password expires. Not implemented in this version
     * @param isActive Whether the user is active. Not implemented in this version
     */
    public RegisteredUser(long id, String userName, String userPasswd, String encryptAlgorithm, Date lastChangeDate, Date minAge, Date maxAge, Date expireDate, boolean isActive) {
        this.id = id;
        this.userName = userName;
        this.userPasswd = userPasswd;
        this.encryptAlgorithm = encryptAlgorithm;
        this.lastChangeDate = lastChangeDate;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.expireDate = expireDate;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptAlgorithm() {
        return encryptAlgorithm;
    }

    public void setEncryptAlgorithm(String encryptAlgorithm) {
        this.encryptAlgorithm = encryptAlgorithm;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public Date getMinAge() {
        return minAge;
    }

    public void setMinAge(Date minAge) {
        this.minAge = minAge;
    }

    public Date getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Date maxAge) {
        this.maxAge = maxAge;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
