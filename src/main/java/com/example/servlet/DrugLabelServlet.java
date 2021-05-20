package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Servlet that reads drug labels from database
 *
 * @author Tianxin HU
 */
@WebServlet(name ="DrugLabelServlet",urlPatterns="/DrugLabelServlet")
public class DrugLabelServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT * FROM drug_label";
		ArrayList<HashMap<String, String>> labels = DBUtils.result(sql);
		request.setAttribute("labels", labels);
		request.getRequestDispatcher("drugLabel.jsp").forward(request, response);
	}
}
