import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

@WebServlet("/drugs")
public class drugs extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sql;
		
		sql="SELECT * FROM drug";
 		request.setAttribute("drugs",JDBC.result(sql));
 		
 		sql="SELECT * FROM drug_label";
		request.setAttribute("labels",JDBC.result(sql));
		
 		sql="SELECT * FROM dosing_guideline";
		request.setAttribute("guidelines",JDBC.result(sql));
		
		request.getRequestDispatcher("index2.jsp").forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
