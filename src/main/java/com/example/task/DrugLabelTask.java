package com.example.task;

import com.example.bean.DrugLabel;
import com.example.service.DrugLabelService;
import com.example.util.HttpCrawler;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * To download drug label information by general-purposed crawler
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Component
public class DrugLabelTask {

    @Autowired
    private DrugLabelService drugLabelService;

    /**
     * To download drug information by general-purposed crawler
     *
     * @throws Exception TODO
     */
    @Scheduled(initialDelay = 8000, fixedDelay = 60 * 60 * 24 * 7 * 1000)
    public void drugLabelTask() throws Exception {
        // Retrieve and iterate over all drug IDs
        for (String id : DrugTask.Ids) {
            // While was replaced with for by IDEA
            String url = String.format("https://api.pharmgkb.org/v1/site/page/drugLabels/%s?view=base", id);  // To return all drug label information
            String jsonContent = HttpCrawler.getURLContent(url);
            Gson gson = new Gson();
            // Map used below should not be altered
            Map result = gson.fromJson(jsonContent, Map.class);
            Map data = (Map) result.get("data");
            List<Map> drugLabels = (List) data.get("drugLabels");
            // log.info("Fetch label of drug {}", id);
            for (Map x : drugLabels) {
                // data.stream().forEach((x) -> { replaced by IDEA
                // log.info("Going to save label: {}", (String)x.get("id"));
                String labelId = (String) x.get("id");
                String name = (String) x.get("id");
                String objCls = (String) x.get("objCls");
                boolean alternateDrugAvailable = (Boolean) x.get("alternateDrugAvailable");
                boolean dosingInformation = (Boolean) x.get("dosingInformation");
                String prescribingMarkdown = "";
                if (x.containsKey("prescribingMarkdown")) {
                    prescribingMarkdown = (String) ((Map) x.get("prescribingMarkdown")).get("html");
                }
                String source = (String) x.get("source");
                String textMarkdown = (String) ((Map) x.get("textMarkdown")).get("html");
                String summaryMarkdown = (String) ((Map) x.get("summaryMarkdown")).get("html");
                String raw = gson.toJson(x);
                String drugId = (String) ((Map) ((List) x.get("relatedChemicals")).get(0)).get("id");
                DrugLabel drugLabelBean = new DrugLabel(labelId, name, objCls, alternateDrugAvailable, dosingInformation, prescribingMarkdown, source, textMarkdown, summaryMarkdown, raw, drugId);
                this.drugLabelService.save(drugLabelBean);
            }
        }
    }
}
