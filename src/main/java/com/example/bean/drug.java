package com.example.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Proxy(lazy = false)
@Entity
@Table(name = "drug")
public class drug {
    @Id @Column(length = 100, nullable=false)
    private String id;

    @Column(length = 500, nullable=false)
    private String name;

    private boolean biomarker;

    @Column(length = 100, nullable=false)
    private String drug_url;

    @Column(length = 100, nullable=false)
    private String Obj_cls;

    public drug(){}

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