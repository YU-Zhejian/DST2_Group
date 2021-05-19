package com.example.servlet;

import com.example.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		for (String s : (ArrayList<String>) request.getAttribute("matchedIDs")) {
			if (s != null && !"".equals(s)) {
				sb.append(',').append(s);
			}
		}
		sb.deleteCharAt(0);

		String sql="SELECT * FROM drug_label WHERE id = ANY(STRING_TO_ARRAY('"+ sb +"',','))";
		System.out.println(sql);
		request.setAttribute("result", DBUtils.result(sql));
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
