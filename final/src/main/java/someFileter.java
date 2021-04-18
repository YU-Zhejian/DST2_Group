import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// FIXME: Do not use * when importing!

@WebFilter("/hello")
public class someFileter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    //		if(request.getSession())
    HttpServletRequest hreq = (HttpServletRequest) request;
    HttpServletResponse hres = (HttpServletResponse) response;
    if (hreq.getSession().getAttribute("username") != null) {
      chain.doFilter(request, response);
    } else {
      hres.sendRedirect(hreq.getContextPath() + "/login.jsp");
    }
  }

  @Override
  public void destroy() {}
}
