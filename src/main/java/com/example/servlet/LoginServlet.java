package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that performs login
 *
 * @author Tianxin HU
 * @author Zhejian YU
 */
@WebServlet(name = "LoginServlet",urlPatterns="/LoginServlet")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("username");
		String pw = request.getParameter("password");

		String sql = "SELECT * FROM registered_user WHERE user_name='" + user + "' AND user_passwd='" + pw + "'";
		// System.out.println(sql);
		if (DBUtils.result(sql).size() > 0) {
			request.getSession().setAttribute("username", user);
			response.sendRedirect("index");
			// System.out.println("sql");
		} else {
			request.setAttribute("errMsg","Failed for some reason");
			request.getRequestDispatcher("login").forward(request, response);
		}
	}
}
