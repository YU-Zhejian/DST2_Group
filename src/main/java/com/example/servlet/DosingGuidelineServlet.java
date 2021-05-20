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
 * Servlet that reads dosing guideline from database
 *
 * @author Tianxin HU
 */
@WebServlet(name = "DosingGuidelineServlet" ,urlPatterns="/DosingGuidelineServlet")
public class DosingGuidelineServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT * FROM dosing_guideline";
		ArrayList<HashMap<String, String>> guidelines = DBUtils.result(sql);
		request.setAttribute("guidelines", guidelines);
		request.getRequestDispatcher("dosingGuideline.jsp").forward(request, response);
	}
}
