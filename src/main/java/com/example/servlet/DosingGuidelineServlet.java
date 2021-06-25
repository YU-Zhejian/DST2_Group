package com.example.servlet;

import com.example.service.DosingGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that reads dosing guideline from database
 *
 * @author Tianxin HU
 */
@WebServlet(name = "DosingGuidelineServlet" ,urlPatterns="/DosingGuidelineServlet")
public class DosingGuidelineServlet extends HttpServlet {
	@Autowired
	private DosingGuidelineService dosingGuidelineService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("guidelines", dosingGuidelineService.findAll());
		request.getRequestDispatcher("dosingGuideline").forward(request, response);
	}
}
