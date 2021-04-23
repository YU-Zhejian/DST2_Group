package com.example.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A typical drug template
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Proxy(lazy = false)
@Entity
@Table(name = "drug")
public class Drug {
    @Id @Column(length = 100, nullable=false)
    private String id;

    @Column(length = 500, nullable=false)
    private String name;

    private boolean biomarker;

    @Column(length = 100, nullable=false)
    private String drug_url;

    @Column(length = 100, nullable=false)
    private String obj_cls;

    public Drug() {}

    /**
     * Constructor for a typical drug
     *
     * @param id Index of drug
     * @param name Name of a drug
     * @param biomarker Whether the drug appears on FDA Biomarker List
     * @param drugUrl URL of a drug
     * @param objCls TODO
     */
    public Drug(String id, String name, boolean biomarker, String drugUrl, String objCls) {
        this.id = id;
        this.name = name;
        this.biomarker = biomarker;
        this.drug_url = drugUrl;
        this.obj_cls = objCls;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBiomarker() {
        return this.biomarker;
    }

    public void setBiomarker(boolean biomarker) {
        this.biomarker = biomarker;
    }

    public String getDrugUrl() {
        return this.drug_url;
    }

    public void setDrugUrl(String drugUrl) {
        this.drug_url = drugUrl;
    }

    public String getObjCls() {
        return this.obj_cls;
    }

    public void setObjCls(String objCls) {
        this.obj_cls = objCls;
    }
}
