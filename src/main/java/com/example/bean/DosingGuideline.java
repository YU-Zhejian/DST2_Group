package com.example.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Proxy(lazy = false)
@Entity
@Table(name = "dosing_guideline")
public class DosingGuideline {
    @Id @Column(length = 100, nullable=false)
    private String id;

    @Column(length = 100, nullable=false)
    private String obj_cls;

    @Column(length = 500, nullable=false)
    private String name;

    private boolean recommendation;

    @Column(length = 100, nullable=false)
    private String drug_id;

    @Column(length = 500, nullable=false)
    private String source;

    @Column(columnDefinition = "text", nullable=false)
    private String summary_markdown;

    @Column(columnDefinition = "text", nullable=false)
    private String text_markdown;

    @Column(columnDefinition = "text", nullable=false)
    private String raw;

    public DosingGuideline() {
    }

    public DosingGuideline(String id, String objCls, String name, boolean recommendation, String drugId, String source, String summaryMarkdown, String textMarkdown, String raw) {
        this.id = id;
        this.obj_cls = objCls;
        this.name = name;
        this.recommendation = recommendation;
        this.drug_id = drugId;
        this.source = source;
        this.summary_markdown = summaryMarkdown;
        this.text_markdown = textMarkdown;
        this.raw = raw;
    }

    public String getRaw() {
        return this.raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjCls() {
        return this.obj_cls;
    }

    public void setObjCls(String objCls) {
        this.obj_cls = objCls;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRecommendation() {
        return this.recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
    }

    public String getDrugId() {
        return this.drug_id;
    }

    public void setDrugId(String drugId) {
        this.drug_id = drugId;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSummaryMarkdown() {
        return this.summary_markdown;
    }

    public void setSummaryMarkdown(String summaryMarkdown) {
        this.summary_markdown = summaryMarkdown;
    }

    public String getTextMarkdown() {
        return this.text_markdown;
    }

    public void setTextMarkdown(String textMarkdown) {
        this.text_markdown = textMarkdown;
    }
}
