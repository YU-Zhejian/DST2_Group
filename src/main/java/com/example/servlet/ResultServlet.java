package com.example.servlet;

import com.example.bean.Sample;
import com.example.service.DrugLabelService;
import com.example.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet that search drug label ids from {@link MatchingServlet} inside the database
 *
 * @author Tianxin HU
 * @author Zhejian YU
 */
@WebServlet(name = "ResultServlet",urlPatterns="/ResultServlet")
public class ResultServlet extends HttpServlet {
	@Autowired
	private DrugLabelService drugLabelService;

	@Autowired
	private SampleService sampleService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = (String)request.getSession().getAttribute("username");
		List<Sample> samples = sampleService.findSamplesByUserName(userName);

		request.setAttribute("result", samples);
		request.getRequestDispatcher("result").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
