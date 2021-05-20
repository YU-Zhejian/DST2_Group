package com.example.servlet;

import com.example.util.DBUtils;

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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sql="SELECT * FROM drug";
		request.setAttribute("drugs", DBUtils.result(sql));
		request.getRequestDispatcher("drug").forward(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
}
