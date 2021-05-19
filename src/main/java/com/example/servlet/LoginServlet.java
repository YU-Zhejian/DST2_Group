package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("username");
		String pw = request.getParameter("password");

		String sql = "SELECT * FROM registered_user WHERE user_name='" + user + "' AND user_passwd='" + pw + "'";
		// System.out.println(sql);
		if (DBUtils.result(sql).size() > 0) {
			request.getSession().setAttribute("username", user);
			response.sendRedirect("index.jsp");
			// System.out.println("sql");
			/*
			 * if(Pattern.matches(".*match",request.getHeader("Referer"))) {
			 * response.sendRedirect(request.getContextPath()+"/MatchingServlet"); }else {
			 * response.sendRedirect(request.getContextPath()); }
			 */
		} else {
			/*
			 * out.print("<html>"+ "<form method=\"POST\">\r\n" +
			 * "			<h2>whether use the username and password to register automatically?</h2>\r\n"
			 * + "			<input type=\"hidden\" name=\"username\" value=\""+user+"\">" +
			 * "			<input type=\"hidden\" name=\"password\" value=\""+pw+"\">" +
			 * "			<button formaction=\"users\">yes</button>\r\n" +
			 * "			<button formaction=\"SignIn.jsp\">no</button>\r\n" +
			 * "		</form><html>");
			 */

			request.setAttribute("errMsg","Failed for some reason");
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}
	}
}
