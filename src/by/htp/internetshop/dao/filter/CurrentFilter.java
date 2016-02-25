package by.htp.internetshop.dao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CurrentFilter implements Filter {

	private ServletContext context;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("MyFilter initialized");
		System.out.println("Фильтр инициализировали");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String encoding = request.getCharacterEncoding();

		if (!"UTF-8".equalsIgnoreCase(encoding)) {
			response.setCharacterEncoding("UTF-8");
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
