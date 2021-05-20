package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet that search drug label ids from {@link MatchingServlet} inside the database
 *
 * @author Tianxin HU
 * @author Zhejian YU
 */
@WebServlet(name = "ResultServlet",urlPatterns="/ResultServlet")
public class ResultServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		// Do not modify this
		for (String s : (ArrayList<String>) request.getAttribute("matchedIDs")) {
			if (s != null && !"".equals(s)) {
				sb.append(',').append(s);
			}
		}
		sb.deleteCharAt(0);

		String sql="SELECT * FROM drug_label WHERE id = ANY(STRING_TO_ARRAY('"+ sb +"',','))";
		System.out.println(sql);
		request.setAttribute("result", DBUtils.result(sql));
		request.getRequestDispatcher("result").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
