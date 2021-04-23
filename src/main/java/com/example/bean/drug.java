package com.example.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * A typical drug template
 * @author Jie Jin
 * @author Yaqi-SU
 * FIXME: Class name should be UpperCamelCase
 */
@Proxy(lazy=false)
@Entity
@Table(name = "drug")
public class drug {
    @Id
    private String id;
    private String name;
    private boolean biomarker;
    private String drug_url;
    private String Obj_cls;

    public drug(){};

    /**
     * Constructor for a typical drug
     * @param id Index of drug
     * @param name Name of a drug
     * @param biomarker Whether the drug appears on FDA Biomarker List
     * @param drugUrl URL of a drug
     * @param objCls TODO
     */
    public drug(String id, String name, boolean biomarker, String drugUrl, String objCls) {
        this.id = id;
        this.name = name;
        this.biomarker = biomarker;
        this.drug_url = drugUrl;
        this.Obj_cls = objCls;
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
        return this.Obj_cls;
    }

    public void setObjCls(String objCls) {
        this.Obj_cls = objCls;
    }

}
