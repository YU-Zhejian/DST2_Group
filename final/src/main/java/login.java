import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class login extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String user = request.getParameter("username");
    String pw = request.getParameter("password");

    String sql = "SELECT * FROM users WHERE username='" + user + "' AND password='" + pw + "'";
    if (JDBC.exist(sql)) {
      request.getSession().setAttribute("username", user);
    }
  }
}
