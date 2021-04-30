package com.example.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A typical drug label template
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Proxy(lazy = false)
@Entity
@Table(name = "drug_label")
public class DrugLabel {
    @Id @Column(name = "id", length = 100, nullable=false)
    private String id;

    @Column(name = "name", length = 200, nullable=false)
    private String name;

    @Column(name = "obj_cls", length = 200, nullable=false)
    private String objCls;

    @Column(name = "drug_id", length = 100, nullable=false)
    private String drugId;

    @Column(name = "have_alternate_drug")
    private boolean haveAlternateDrug;

    @Column(name = "have_dosing_information")
    private boolean haveDosingInformation;

    @Column(name = "prescribing_markdown", columnDefinition = "text", nullable=false)
    private String prescribingMarkdown;

    @Column(name = "source", length = 100, nullable=false)
    private String source;

    @Column(name = "text_markdown", columnDefinition = "text", nullable=false)
    private String textMarkdown;

    @Column(name = "summary_markdown", columnDefinition = "text", nullable=false)
    private String summaryMarkdown;

    @Column(name = "raw", columnDefinition = "text", nullable=false)
    private String raw;

    public DrugLabel() {}

    /**
     * Constructor for a typical drug label template
     *
     * @param id Index of the label
     * @param name Index of the label TODO: Have it removed? Or errors in DrugLabelTask?
     * @param objCls TODO
     * @param haveAlternateDrug TODO
     * @param haveDosingInformation TODO
     * @param prescribingMarkdown TODO
     * @param source By which governmental organization this label is approved
     * @param textMarkdown TODO
     * @param summaryMarkdown TODO
     * @param raw This label in JSON format
     * @param drugId Index of the drug at {@link Drug}
     */
    public DrugLabel(
            String id,
            String name,
            String objCls,
            boolean haveAlternateDrug,
            boolean haveDosingInformation,
            String prescribingMarkdown,
            String source,
            String textMarkdown,
            String summaryMarkdown,
            String raw,
            String drugId) {
        this.id = id;
        this.name = name;
        this.objCls = objCls;
        this.haveAlternateDrug = haveAlternateDrug;
        this.haveDosingInformation = haveDosingInformation;
        this.prescribingMarkdown = prescribingMarkdown;
        this.source = source;
        this.textMarkdown = textMarkdown;
        this.summaryMarkdown = summaryMarkdown;
        this.raw = raw;
        this.drugId = drugId;
    }

    public String getDrugId() {
        return this.drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjCls() {
        return this.objCls;
    }

    public void setObjCls(String objCls) {
        this.objCls = objCls;
    }

    public boolean isAlternateDrugAvailable() {
        return this.haveAlternateDrug;
    }

    public void setHaveAlternateDrug(boolean haveAlternateDrug) {
        this.haveAlternateDrug = haveAlternateDrug;
    }

    public boolean isDosingInformation() {
        return this.haveDosingInformation;
    }

    public void setHaveDosingInformation(boolean haveDosingInformation) {
        this.haveDosingInformation = haveDosingInformation;
    }

    public String getPrescribingMarkdown() {
        return this.prescribingMarkdown;
    }

    public void setPrescribingMarkdown(String prescribingMarkdown) {
        this.prescribingMarkdown = prescribingMarkdown;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTextMarkdown() {
        return this.textMarkdown;
    }

    public void setTextMarkdown(String textMarkdown) {
        this.textMarkdown = textMarkdown;
    }

    public String getSummaryMarkdown() {
        return this.summaryMarkdown;
    }

    public void setSummaryMarkdown(String summaryMarkdown) {
        this.summaryMarkdown = summaryMarkdown;
    }
}
