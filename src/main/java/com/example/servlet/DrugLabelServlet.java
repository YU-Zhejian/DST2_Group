package com.example.servlet;

import com.example.service.DrugLabelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Servlet that reads drug labels from database
 *
 * @author Tianxin HU
 */
@WebServlet(name ="DrugLabelServlet",urlPatterns="/DrugLabelServlet")
public class DrugLabelServlet extends HttpServlet {
	@Autowired
	private DrugLabelService drugLabelService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("labels", drugLabelService.findAll());
		request.getRequestDispatcher("drugLabel").forward(request, response);
	}
}
