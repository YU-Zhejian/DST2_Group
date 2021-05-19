package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/DrugLabelServlet")
public class DrugLabelServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT * FROM drug_label";
		ArrayList<HashMap<String, String>> a = DBUtils.result(sql);
		request.setAttribute("labels", a);
		request.getRequestDispatcher("drugLabel.jsp").forward(request, response);
	}
}
