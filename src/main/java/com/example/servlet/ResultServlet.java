package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		String sql="SELECT DISTINCT * FROM drug_label "
				+ "INNER JOIN (select * from sample where user_name='"
				+ request.getSession().getAttribute("username")
				+ "') as t ON drug_label.drug_id=t.matched_id";
		request.setAttribute("result", DBUtils.result(sql));
		request.getRequestDispatcher("result").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
