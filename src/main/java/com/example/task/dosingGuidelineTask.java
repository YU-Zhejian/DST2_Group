package com.example.task;

import com.example.bean.DosingGuideline;
import com.example.service.dosingGuidelineService;
import com.example.util.httpClientDownloadPage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class dosingGuidelineTask {
    @Autowired
    private httpClientDownloadPage DownloadPage;

    @Autowired
    private dosingGuidelineService dosingGuidelineService;

    @Scheduled(initialDelay = 3000,fixedDelay = 60*60*24*7*1000)
    public void dosingGuidelineTask() throws Exception{
        String content = this.DownloadPage.getURLContent("https://api.pharmgkb.org/v1/site/guidelinesByDrugs");
        Gson gson = new Gson();
        Map drugLabels = (Map)gson.fromJson(content, Map.class);
        List<Map> data = (List)drugLabels.get("data");
        List<String> li = new ArrayList<>();
        li.add("cpic");
        li.add("cpnds");
        li.add("dpwg");
        li.add("fda");
        li.add("pro");
        data.stream().forEach((x) -> {
            //log.info("{}", x);
            li.forEach((source) -> {
                List<Map> guidelineList = (List)x.get(source);
                guidelineList.forEach((guideline) -> {
                    String url = (String)guideline.get("url");
                    this.doCrawlerDosingGuideline(url);
                });
            });
        });

    }

    public void doCrawlerDosingGuideline(String url) {
        String content = this.DownloadPage.getURLContent(String.format("https://api.pharmgkb.org/v1/data%s", url));
        Gson gson = new Gson();
        Map guideline = (Map)gson.fromJson(content, Map.class);
        Map data = (Map)guideline.get("data");
        String id = (String)data.get("id");
        String objCls = (String)data.get("objCls");
        String name = (String)data.get("name");
        boolean recommendation = (Boolean)data.get("recommendation");
        String drugId = (String)((Map)((List)data.get("relatedChemicals")).get(0)).get("id");
        String source = (String)data.get("source");
        String summaryMarkdown = (String)((Map)data.get("summaryMarkdown")).get("html");
        String textMarkdown = (String)((Map)data.get("textMarkdown")).get("html");
        String raw = gson.toJson(guideline);
        DosingGuideline dosingGuideline = new DosingGuideline(id, objCls, name, recommendation, drugId, source, summaryMarkdown, textMarkdown, raw);
        this.dosingGuidelineService.save(dosingGuideline);

    }


    }

