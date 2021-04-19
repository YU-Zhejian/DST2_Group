package com.example.task;

import com.example.bean.DrugLabel;
import com.example.service.drugLabelService;
import com.example.util.httpClientDownloadPage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class drugLabelTask {
    @Autowired
    private httpClientDownloadPage DownloadPage;

    @Autowired
    private drugLabelService drugLabelService;

    @Scheduled(initialDelay = 8000,fixedDelay = 60*60*24*7*1000)
    public void drugLabelTask() throws Exception{
        //获取所有drug的id集并遍历
        Iterator it = drugTask.Ids.iterator();

       while (it.hasNext()){
           String id = (String) it.next();
           String content = this.DownloadPage.getURLContent(String.format("https://api.pharmgkb.org/v1/site/page/drugLabels/%s?view=base", id));
           Gson gson = new Gson();
           Map result = (Map)gson.fromJson(content, Map.class);
           Map data = (Map)result.get("data");
           List<Map> drugLabels = (List)data.get("drugLabels");
           //log.info("Fetch label of drug {}", id);
           drugLabels.stream().forEach((x) -> {
               //log.info("Going to save label: {}", (String)x.get("id"));
               String labelId = (String)x.get("id");
               String name = (String)x.get("id");
               String objCls = (String)x.get("objCls");
               boolean alternateDrugAvailable = (Boolean)x.get("alternateDrugAvailable");
               boolean dosingInformation = (Boolean)x.get("dosingInformation");
               String prescribingMarkdown = "";
               if (x.containsKey("prescribingMarkdown")) {
                   prescribingMarkdown = (String)((Map)x.get("prescribingMarkdown")).get("html");
               }

               String source = (String)x.get("source");
               String textMarkdown = (String)((Map)x.get("textMarkdown")).get("html");
               String summaryMarkdown = (String)((Map)x.get("summaryMarkdown")).get("html");
               String raw = gson.toJson(x);
               String drugId = (String)((Map)((List)x.get("relatedChemicals")).get(0)).get("id");
               DrugLabel drugLabelBean = new DrugLabel(labelId, name, objCls, alternateDrugAvailable, dosingInformation, prescribingMarkdown, source, textMarkdown, summaryMarkdown, raw, drugId);
               this.drugLabelService.save(drugLabelBean);
           });
       }
    }

       }




