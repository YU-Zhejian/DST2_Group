package com.example.servlet;

import com.example.bean.DrugLabel;
import com.example.bean.Sample;
import com.example.service.DrugLabelService;
import com.example.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Servlet that performs drug label matching. Will pass results to {@link ResultServlet} as an ArrayList
 *
 * @author Tianxin HU
 * @author Zhejian YU
 */
@WebServlet(name = "MatchingServlet",urlPatterns="/MatchingServlet")
@MultipartConfig(maxFileSize = 2097152000)
public class MatchingServlet extends HttpServlet {
	@Autowired
	private DrugLabelService drugLabelService;

	@Autowired
	private SampleService sampleService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part requestPart;
		try {
			requestPart = request.getPart("annovar");
		} catch (Exception e) {
			requestPart = (Part) request.getSession().getAttribute("ao");
		}
		if (request.getSession().getAttribute("username") == null) {
			request.getRequestDispatcher("login").include(request, response);
		} else {
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestPart.getInputStream()));

			String line;
			List<DrugLabel> rs = drugLabelService.findAll();
			HashSet<String> matchedGenes = new HashSet<>();

			while ((line = reader.readLine()) !=null){
				String[] entries = line.split("\t");
				String refgene = entries[6];
				if (!matchedGenes.contains(refgene)) {
					matchedGenes.add(refgene);

					for (DrugLabel drug : rs) {
						if (drug.getSummaryMarkdown().contains(refgene)) {
							Sample sample = new Sample();
							sample.setCreatedAt(new Date());
							sample.setMatchedId(drug.getId());
							sample.setMatchedDrug(drug);
							sample.setUserName((String)request.getSession().getAttribute("username"));
							sampleService.save(sample);
						}
					}
				}
			}
			request.getRequestDispatcher("ResultServlet").forward(request, response);
		}
	}
}
