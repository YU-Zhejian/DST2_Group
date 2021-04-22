package com.example.task;

import com.example.bean.DosingGuideline;
import com.example.service.dosingGuidelineService;
import com.example.util.httpClientDownloadPage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * To download dosing guideline information by general-purposed crawler
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Component
public class dosingGuidelineTask {
    @Autowired
    private httpClientDownloadPage DownloadPage; // Use lowerCamelCase.

    @Autowired
    private dosingGuidelineService dosingGuidelineService;

    /**
     * To download dosing guideline information by general-purposed crawler
     * TODO: May get it renamed
     * @throws Exception TODO
     */
    @Scheduled(initialDelay = 3000, fixedDelay = 60 * 60 * 24 * 7 * 1000)
    public void dosingGuidelineTask() throws Exception {
        String url = "https://api.pharmgkb.org/v1/site/guidelinesByDrugs"; // To return all dosing guideline information
        String content = httpClientDownloadPage.getURLContent(url); // FIXME: content is quite misleading. Use jsonContent instead
        Gson gson = new Gson();
        // Map used below should not be altered
        Map drugLabels = (Map) gson.fromJson(content, Map.class);
        List<Map> data = (List) drugLabels.get("data");
        List<String> li = new ArrayList<>();
        li.add("cpic");
        li.add("cpnds");
        li.add("dpwg");
        li.add("fda");
        li.add("pro");
        for (Map x:data) {
            // data.stream().forEach((x) -> { replaced by IDEA
            // log.info("{}", x);
            li.forEach((source) -> {
                List<Map> guidelineList = (List) x.get(source);
                guidelineList.forEach((guideline) -> {
                    String guidelineUrl = (String) guideline.get("url");
                    this.doCrawlerDosingGuideline(guidelineUrl);
                });
            });
        };

    }

    public void doCrawlerDosingGuideline(String url) {
        url=String.format("https://api.pharmgkb.org/v1/data%s", url); // Changed to align with previous contents
        String content = httpClientDownloadPage.getURLContent(url); // FIXME: content is quite misleading. Use jsonContent instead
        Gson gson = new Gson();
        // Map used below should not be altered
        Map guideline = (Map) gson.fromJson(content, Map.class);
        Map data = (Map) guideline.get("data");
        String id = (String) data.get("id");
        String objCls = (String) data.get("objCls");
        String name = (String) data.get("name");
        boolean recommendation = (Boolean) data.get("recommendation");
        String drugId = (String) ((Map) ((List) data.get("relatedChemicals")).get(0)).get("id");
        String source = (String) data.get("source");
        String summaryMarkdown = (String) ((Map) data.get("summaryMarkdown")).get("html");
        String textMarkdown = (String) ((Map) data.get("textMarkdown")).get("html");
        String raw = gson.toJson(guideline);
        DosingGuideline dosingGuideline = new DosingGuideline(id, objCls, name, recommendation, drugId, source, summaryMarkdown, textMarkdown, raw);
        this.dosingGuidelineService.save(dosingGuideline);
    }
}
