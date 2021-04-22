package com.example.task;

import com.example.bean.drug;
import com.example.service.drugService;
import com.example.util.httpClientDownloadPage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * To download drug information by general-purposed crawler
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Component
public class drugTask {
    @Autowired
    private httpClientDownloadPage DownloadPage;

    @Autowired
    private drugService drugService;

    public static List<String> Ids=new ArrayList<>();

    /**
     * To download drug information by general-purposed crawler
     * TODO: May get it renamed
     * @throws Exception TODO
     */
    @Scheduled(initialDelay = 2000,fixedDelay = 60*60*24*7*1000)
    public void drugTask() throws Exception{
        String url = "https://api.pharmgkb.org/v1/site/labelsByDrug"; // To return all drug information
        String html = httpClientDownloadPage.getURLContent(url); // FIXME: html is quite misleading. Use jsonContent instead
        Gson gson = new Gson();
        // Map used below should not be altered
        Map drugLabels = (Map)gson.fromJson(html, Map.class);
        List<Map> data = (List)drugLabels.get("data");
        for (Map x:data) {
            // data.stream().forEach((x) -> { replaced by IDEA
            Map drug = (Map)x.get("drug");
            String id = (String)drug.get("id");
            this.Ids.add(id); // FIXME: access static member via class instance is prohibited
            // TODO: Why use static?
            String name = (String)drug.get("name");
            String objCls = (String)drug.get("objCls");
            String drugUrl = (String)x.get("drugUrl");
            boolean biomarker = (Boolean)x.get("biomarker");
            com.example.bean.drug drugBean = new drug(id, name, biomarker, drugUrl, objCls);
            this.drugService.save(drugBean);
        }
    }
}
