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
    @Id @Column(name = "id", length = 100, nullable=false)
    private String id; // TODO: Consider naming it to drugId?

    @Column(name = "name", length = 500, nullable=false)
    private String name;

    @Column(name = "is_biomarker")
    private boolean isBiomarker;

    @Column(name = "drug_url", length = 100, nullable=false)
    private String drugUrl;

    @Column(name = "obj_cls", length = 100, nullable=false)
    private String objCls;

    public Drug() {}

    /**
     * Constructor for a typical drug
     *
     * @param id Index of drug
     * @param name Name of a drug
     * @param isBiomarker Whether the drug appears on FDA Biomarker List
     * @param drugUrl URL of a drug without leading https://api.pharmgkb.org/v1/data
     * @param objCls TODO
     */
    public Drug(String id, String name, boolean isBiomarker, String drugUrl, String objCls) {
        this.id = id;
        this.name = name;
        this.isBiomarker = isBiomarker;
        this.drugUrl = drugUrl;
        this.objCls = objCls;
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
        return this.isBiomarker;
    }

    public void setBiomarker(boolean biomarker) {
        this.isBiomarker = biomarker;
    }

    public String getDrugUrl() {
        return this.drugUrl;
    }

    public void setDrugUrl(String drugUrl) {
        this.drugUrl = drugUrl;
    }

    public String getObjCls() {
        return this.objCls;
    }

    public void setObjCls(String objCls) {
        this.objCls = objCls;
    }
}
