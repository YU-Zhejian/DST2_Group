package com.example.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A typical Dosing guideline template
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Proxy(lazy = false)
@Entity
@Table(name = "dosing_guideline")
public class DosingGuideline {
    // FIXME: Whether to use Google standard. If so, use lowerCamelCase
    @Id @Column(name = "id", length = 100, nullable=false)
    private String id;

    @Column(name = "name", length = 500, nullable=false)
    private String name;

    @Column(name = "obj_cls", length = 200, nullable=false)
    private String objCls;

    @Column(name = "is_recommendation")
    private boolean isRecommendation;

    @Column(name = "drug_id", length = 100, nullable=false)
    private String drugId;

    @Column(name = "source", length = 500, nullable=false)
    private String source;

    @Column(name = "text_markdown", columnDefinition = "text", nullable=false)
    private String textMarkdown;

    @Column(name = "summary_markdown", columnDefinition = "text", nullable=false)
    private String summaryMarkdown;

    @Column(name = "raw",columnDefinition = "text", nullable=false)
    private String raw;

    public DosingGuideline() {}

    /**
     * Constructor for a typical dosing guideline
     *
     * @param id Index of the guideline
     * @param objCls TODO
     * @param name Name of the dofing guideline
     * @param isRecommendation TODO
     * @param drugId Index of the drug at {@link Drug}
     * @param source By which hospital & institute & research group this guideline is purposed
     * @param summaryMarkdown TODO
     * @param textMarkdown TODO Not shown in the website
     * @param raw This guideline in JSON format
     */
    public DosingGuideline(
            String id,
            String name,
            String objCls,
            boolean isRecommendation,
            String drugId,
            String source,
            String summaryMarkdown,
            String textMarkdown,
            String raw) {
        this.id = id;
        this.name = name;
        this.objCls = objCls;
        this.isRecommendation = isRecommendation;
        this.drugId = drugId;
        this.source = source;
        this.textMarkdown = summaryMarkdown;
        this.summaryMarkdown = textMarkdown;
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
        return this.objCls;
    }

    public void setObjCls(String objCls) {
        this.objCls = objCls;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRecommendation() {
        return this.isRecommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.isRecommendation = recommendation;
    }

    public String getDrugId() {
        return this.drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSummaryMarkdown() {
        return this.textMarkdown;
    }

    public void setSummaryMarkdown(String summaryMarkdown) {
        this.textMarkdown = summaryMarkdown;
    }

    public String getTextMarkdown() {
        return this.summaryMarkdown;
    }

    public void setTextMarkdown(String textMarkdown) {
        this.summaryMarkdown = textMarkdown;
    }
}
