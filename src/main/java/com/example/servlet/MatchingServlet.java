package com.example.servlet;

import com.example.util.DBUtils;

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
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Servlet that performs drug label matching. Will pass results to {@link ResultServlet} as an ArrayList
 *
 * @author Tianxin HU
 * @author Zhejian YU
 */
@WebServlet(name = "MatchingServlet",urlPatterns="/MatchingServlet")
@MultipartConfig(maxFileSize = 2097152000)
public class MatchingServlet extends HttpServlet {
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
			ArrayList<String> matchedIDs = new ArrayList<>();
			String line;
			ArrayList<HashMap<String, String>> rs =
					DBUtils.result("SELECT drug_id,summary_markdown FROM drug_label");
	        Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	        String time = ft.format(date);
			while ((line = reader.readLine()) !=null){
				String[] entries = line.split("\t");
				String refgene = entries[6];
				for (HashMap<String, String> drug : rs) {
					if (drug.get("summary_markdown").contains(refgene) && !matchedIDs.contains(drug.get("drug_id"))) {
						matchedIDs.add(drug.get("drug_id"));
						String sql="INSERT INTO sample VALUES(null,'"+time+"','"+drug.get("drug_id")+"','"+(String)request.getSession().getAttribute("username")+"')";
						DBUtils.execute(sql);
					}
				}
			}
			request.setAttribute("matchedIDs",matchedIDs);
			request.getRequestDispatcher("ResultServlet").forward(request, response);
		}
	}
}
