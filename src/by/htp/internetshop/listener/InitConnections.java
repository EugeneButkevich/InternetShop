package by.htp.internetshop.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPool;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPoolException;
import by.htp.internetshop.domain.ProductCategory;

public class InitConnections implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		List<ProductCategory> categoryList = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
		ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
		try {
			categoryList = productDAO.getAllCategories();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		ServletContext application = contextEvent.getServletContext();
		application.setAttribute("allCategories", categoryList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
