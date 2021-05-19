package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String user=request.getParameter("username");
		String pw=request.getParameter("password");
		String sql="INSERT INTO registered_user (encrypt_algorithm, user_name, user_passwd) VALUES('PLAIN','"+user+"','"+pw+"')";
		int i= DBUtils.execute(sql);
		// System.out.println(i);
		if (i == 0){
			request.setAttribute("errMsg","Failed for some reason");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		response.sendRedirect("index.jsp");
	}
}
