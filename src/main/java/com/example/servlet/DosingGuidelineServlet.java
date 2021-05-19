package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/DosingGuidelineServlet")
public class DosingGuidelineServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT * FROM dosing_guideline";
		ArrayList a = JDBC.result(sql);
		request.setAttribute("guidelines", a);
		request.getRequestDispatcher("DosingGuidelineServlet.jsp").forward(request, response);
	}
}
