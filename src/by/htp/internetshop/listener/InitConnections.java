package by.htp.internetshop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.htp.internetshop.dao.impl.connectionpool.ConnectionPool;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPoolException;

public class InitConnections implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
	}
}
