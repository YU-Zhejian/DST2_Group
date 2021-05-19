package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sql="SELECT DISTINCT * FROM drug_label "
				+ "INNER JOIN (select * from result where username='"
				+ request.getSession().getAttribute("username")
				+ "') as t ON drug_label.id=t.drug";

		request.setAttribute("result", DBUtils.result(sql));
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
