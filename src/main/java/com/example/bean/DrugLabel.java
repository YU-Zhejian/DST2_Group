package com.example.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drug_label")
public class DrugLabel {
    @Id
    private String id;
    private String name;
    private String obj_cls;
    private boolean alternate_drug_available;
    private boolean dosing_information;
    private String prescribing_markdown;
    private String source;
    private String text_markdown;
    private String summary_markdown;
    private String raw;
    private String drug_id;

    public DrugLabel() {
    }

    public DrugLabel(String id, String name, String objCls, boolean alternateDrugAvailable, boolean dosingInformation, String prescribingMarkdown, String source, String textMarkdown, String summaryMarkdown, String raw, String drugId) {
        this.id = id;
        this.name = name;
        this.obj_cls = objCls;
        this.alternate_drug_available = alternateDrugAvailable;
        this.dosing_information = dosingInformation;
        this.prescribing_markdown = prescribingMarkdown;
        this.source = source;
        this.text_markdown = textMarkdown;
        this.summary_markdown = summaryMarkdown;
        this.raw = raw;
        this.drug_id = drugId;
    }

    public String getDrugId() {
        return this.drug_id;
    }

    public void setDrugId(String drugId) {
        this.drug_id = drugId;
    }

    public String getRaw() {
        return this.raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    @Id
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
        return this.obj_cls;
    }

    public void setObjCls(String objCls) {
        this.obj_cls = objCls;
    }

    public boolean isAlternateDrugAvailable() {
        return this.alternate_drug_available;
    }

    public void setAlternateDrugAvailable(boolean alternateDrugAvailable) {
        this.alternate_drug_available = alternateDrugAvailable;
    }

    public boolean isDosingInformation() {
        return this.dosing_information;
    }

    public void setDosingInformation(boolean dosingInformation) {
        this.dosing_information = dosingInformation;
    }

    public String getPrescribingMarkdown() {
        return this.prescribing_markdown;
    }

    public void setPrescribingMarkdown(String prescribingMarkdown) {
        this.prescribing_markdown = prescribingMarkdown;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTextMarkdown() {
        return this.text_markdown;
    }

    public void setTextMarkdown(String textMarkdown) {
        this.text_markdown = textMarkdown;
    }

    public String getSummaryMarkdown() {
        return this.summary_markdown;
    }

    public void setSummaryMarkdown(String summaryMarkdown) {
        this.summary_markdown = summaryMarkdown;
    }


}


