package com.example.servlet;

import com.example.bean.RegisteredUser;
import com.example.service.RegisteredUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
	@Autowired
	private RegisteredUserService registeredUserService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String pw = request.getParameter("password");

		RegisteredUser matchedUser = registeredUserService.findRegisteredUserByUserName(userName);

		if (matchedUser != null && matchedUser.getUserPasswd().equals(DigestUtils.md5Hex(pw))) {
			request.getSession().setAttribute("username", userName);
			response.sendRedirect("index");
		} else {
			request.setAttribute("errMsg","User name or password is incorrect.");
			request.getRequestDispatcher("login").forward(request, response);
		}
	}
}
