import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

@WebServlet("/drugs")
public class drugs extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String filepath=request.getContextPath()+"\\src\\main\\webapp\\WEB-INF\\drugs.tsv";
		List<String> lines=Files.readAllLines(Path.of(filepath));
		System.out.println(lines.get(0));
		for(String s:lines.subList(1,10)){
			
		}

	}

}