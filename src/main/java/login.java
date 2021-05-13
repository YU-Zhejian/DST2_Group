import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String user=request.getParameter("username");
		String pw=request.getParameter("password");
		PrintWriter out=response.getWriter();
		
		String sql="SELECT * FROM users WHERE username='"+user+"' AND password='"+pw+"'";
		if(JDBC.result(sql).size()>0) {
			request.getSession().setAttribute("username",user);
			response.sendRedirect("index2.jsp");
			System.out.println("111");
			/*
			 * if(Pattern.matches(".*match",request.getHeader("Referer"))) {
			 * response.sendRedirect(request.getContextPath()+"/match"); }else {
			 * response.sendRedirect(request.getContextPath()); }
			 */
		}else {
			/*
			 * out.print("<html>"+ "<form method=\"POST\">\r\n" +
			 * "			<h2>whether use the username and password to register automatically?</h2>\r\n"
			 * + "			<input type=\"hidden\" name=\"username\" value=\""+user+"\">" +
			 * "			<input type=\"hidden\" name=\"password\" value=\""+pw+"\">" +
			 * "			<button formaction=\"users\">yes</button>\r\n" +
			 * "			<button formaction=\"SignIn.jsp\">no</button>\r\n" +
			 * "		</form><html>");
			 */
			
			response.sendRedirect("welcome.jsp");
			System.out.println("djfs");
		} 
	}
		
}
