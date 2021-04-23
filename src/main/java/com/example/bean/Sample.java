package com.example.bean;

import javax.persistence.Id;
import java.util.Date;

/**
 * Annotated Variant Calling Format (VCF) uploaded by the users
 *
 * @author Jie Jin
 * @author Zhejian YU TODO: Sample content? TODO: Matched content? TODO: Log in & Register? TODO:
 *     Need to be integrated into database after fruther discussion
 */
public class Sample {
    @Id private int id;
    private Date createdAt;
    private String userName;

    public Sample() {}

    /**
     * Constructor for Annotated Variant Calling Format (VCF) uploaded by the users
     *
     * @param id TODO
     * @param createdAt Date it was uploaded
     * @param userName By whom it was uploaded. See also: {@link User}
     */
    public Sample(int id, Date createdAt, String userName) {
        this.id = id;
        this.createdAt = createdAt;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
