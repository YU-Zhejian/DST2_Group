package com.example.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Annotated Variant Calling Format (VCF) uploaded by the users
 * TODO: Matched content
 *
 * @author Jie Jin
 * @author Zhejian YU
 */
@Proxy(lazy = false)
@Entity
@Table(name = "sample")
public class Sample {
    @Id @Column(name = "id", length = 100, nullable=false)
    private String id;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "user_name", length = 100, nullable=false)
    private String userName;
    @Column(name = "vcf_content", columnDefinition = "text", nullable=false)
    private String vcfContent;
    @Column(name = "matched_id", length = 100, nullable=false)
    private String matchedId;

    public Sample() {}

    /**
     * Constructor for Annotated Variant Calling Format (VCF) uploaded by the users
     * @param id TODO
     * @param createdAt Date it was uploaded
     * @param userName By whom it was uploaded. See also: {@link RegisteredUser}
     * @param vcfContent Annotated VCF content
     * @param matchedId Matched drug label IDs separated by semicolon (;). See also: {@link DrugLabel}
     */
    public Sample(String id, Date createdAt, String userName, String vcfContent, String matchedId) {
        this.id = id;
        this.createdAt = createdAt;
        this.userName = userName;
        this.vcfContent = vcfContent;
        this.matchedId = matchedId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getVcfContent() {
        return vcfContent;
    }

    public void setVcfContent(String vcfContent) {
        this.vcfContent = vcfContent;
    }

    public String getMatchedId() {
        return matchedId;
    }

    public void setMatchedId(String macthedId) {
        this.matchedId = macthedId;
    }
}
