package com.example.task;

import com.example.bean.DosingGuideline;
import com.example.service.DosingGuidelineService;
import com.example.util.HttpCrawler;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * To download dosing guideline information by general-purposed crawler
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Component
public class DosingGuidelineTask {

	private final Logger log = LoggerFactory.getLogger(DosingGuidelineTask.class);


	@Autowired
	private DosingGuidelineService dosingGuidelineService;

	/**
	 * To download dosing guideline information by general-purposed crawler
	 * TODO: May get it renamed
	 *
	 * @throws Exception TODO
	 */
	//@Scheduled(initialDelay = 3000, fixedDelay = 60 * 60 * 24 * 7 * 1000)
	public void dosingGuidelineTask() throws Exception {
		log.info("Start fetching dosing guidelines");
		String url = "https://api.pharmgkb.org/v1/site/guidelinesByDrugs"; // To return all dosing guideline information
		String jsonContent = HttpCrawler.getURLContent(url);
		Gson gson = new Gson();
		// Map used below should not be altered
		Map drugLabels = gson.fromJson(jsonContent, Map.class);
		List<Map> data = (List) drugLabels.get("data");
		List<String> li = new ArrayList<>();
		li.add("cpic");
		li.add("cpnds");
		li.add("dpwg");
		li.add("fda");
		li.add("pro");
		for (Map x : data) {
			log.info("Selected label {}", x);
			for (String source : li) {
				List<Map> guidelineList = (List) x.get(source);
				guidelineList.forEach((guideline) -> {
					String guidelineUrl = (String) guideline.get("url");
					this.doCrawlerDosingGuideline(guidelineUrl);
				});
			}
		};
		log.info("Finished fetching dosing guidelines");
	}

	public void doCrawlerDosingGuideline(String url) {
		log.info("Fetching dosing guidelines at {}", url);
		url=String.format("https://api.pharmgkb.org/v1/data%s", url); // Changed to align with previous contents
		String jsonContent = HttpCrawler.getURLContent(url);
		Gson gson = new Gson();
		// Map used below should not be altered
		Map guideline = gson.fromJson(jsonContent, Map.class);
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
