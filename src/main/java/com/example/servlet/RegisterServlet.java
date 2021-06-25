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
 * Servlet that performs register
 *
 * @author Tianxin HU
 * @author Zhejian YU
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	@Autowired
	private RegisteredUserService registeredUserService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		RegisteredUser user = new RegisteredUser();
		user.setUserName(request.getParameter("username"));
		user.setUserPasswd(DigestUtils.md5Hex(
				request.getParameter("password")
		));
		user.setEncryptAlgorithm("MD5");
		registeredUserService.save(user);

		response.sendRedirect("index");
	}
}
