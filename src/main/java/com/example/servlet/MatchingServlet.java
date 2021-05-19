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

@WebServlet("/MatchingServlet")
@MultipartConfig(maxFileSize = 2097152000)
public class MatchingServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql =
				"SELECT * FROM drug_label INNER JOIN (select * from result where username='"
						+ request.getSession().getAttribute("username")
						+ "') as t ON drug_label.id=t.drug";
		request.setAttribute("result", DBUtils.result(sql));
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part requestPart;
		try {
			requestPart = request.getPart("annovar");
		} catch (Exception e) {
			requestPart = (Part) request.getSession().getAttribute("ao");
		}
		if (request.getSession().getAttribute("username") == null) {
			request.getRequestDispatcher("login.jsp").include(request, response);
		} else {
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestPart.getInputStream()));
			ArrayList<String> a = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) !=null){
				String[] entries = line.split("\t");
				String refgene = entries[6];
				ArrayList<HashMap<String, String>> rs =
						DBUtils.result("SELECT id,summary_markdown FROM drug_label");
				for (HashMap<String, String> drug : rs) {
					if (drug.get("summary_markdown").contains(refgene) && !a.contains(drug.get("id"))) {
						a.add(drug.get("id"));
						String sql =
								"INSERT INTO result VALUES('"
										+ request.getSession().getAttribute("username")
										+ "','"
										+ drug.get("id")
										+ "')";
						DBUtils.execute(sql);
					}
				}
			}
			String path = request.getContextPath();
			response.sendRedirect(path + "/ResultServlet");
		}
	}
}
