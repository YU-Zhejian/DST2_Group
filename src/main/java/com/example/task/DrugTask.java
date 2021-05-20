package com.example.task;

import com.example.bean.Drug;
import com.example.service.DrugService;
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
 * To download drug information by general-purposed crawler
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Component
public class DrugTask {

	private final Logger log = LoggerFactory.getLogger(DrugTask.class);

	@Autowired
	private DrugService drugService;

	public static List<String> Ids=new ArrayList<>();

	/**
	 * To download drug information by general-purposed crawler
	 * TODO: May get it renamed
	 *
	 * @throws Exception TODO
	 */
	//@Scheduled(initialDelay = 2000,fixedDelay = 60*60*24*7*1000)
	public void drugTask() throws Exception{
		log.info("Start fetching drugs");
		String url = "https://api.pharmgkb.org/v1/site/labelsByDrug"; // To return all drug information
		String jsonContent = HttpCrawler.getURLContent(url);
		Gson gson = new Gson();
		// Map used below should not be altered
		Map drugLabels = gson.fromJson(jsonContent, Map.class);
		List<Map> data = (List) drugLabels.get("data");
		for (Map x : data) {
			Map drug = (Map)x.get("drug");
			String id;
			try{
				id = (String)drug.get("id"); // FIXME: java.lang.NullPointerException
			}
			catch (NullPointerException e){
				log.error("NullPointerException", e);
				continue;
			}
			this.Ids.add(id); // FIXME: access static member via class instance is prohibited
			// TODO: Why use static?
			log.info("Fetch drug {}", id);
			String name = (String) drug.get("name");
			String objCls = (String) drug.get("objCls");
			String drugUrl = (String) x.get("drugUrl");
			boolean biomarker = (Boolean) x.get("biomarker");
			Drug drugBean = new Drug(id, name, biomarker, drugUrl, objCls);
			this.drugService.save(drugBean);
		}
		log.info("Finished fetching drugs");
	}
}
