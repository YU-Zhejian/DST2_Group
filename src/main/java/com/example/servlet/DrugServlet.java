package com.example.servlet;

import com.example.service.DrugService;
import com.example.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that reads drug from database
 *
 * @author Tianxin HU
 */
@WebServlet(name = "DrugServlet",urlPatterns="/DrugServlet")
public class DrugServlet extends HttpServlet {
	@Autowired
	private DrugService drugService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("drugs", drugService.findAll());
		request.getRequestDispatcher("drug").forward(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
}
