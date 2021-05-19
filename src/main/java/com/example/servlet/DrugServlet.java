package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 

@WebServlet("/DrugServlet")
public class DrugServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sql="SELECT * FROM drug";
		request.setAttribute("drugs", DBUtils.result(sql));
		request.getRequestDispatcher("drugs.jsp").forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
}
