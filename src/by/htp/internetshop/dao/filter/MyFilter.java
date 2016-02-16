package by.htp.internetshop.dao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {

	private ServletContext context;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("MyFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
/*		HttpServletResponse httpRepsonse = (HttpServletResponse) response;
	    httpRepsonse.sendRedirect("controller");
		RecordAllCategoriesInSession.getInstanse().doService((HttpServletRequest)request);*/
	}

	@Override
	public void destroy() {
	}
}
