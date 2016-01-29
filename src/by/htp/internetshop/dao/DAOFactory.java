package by.htp.internetshop.dao;

import by.htp.internetshop.dao.impl.SQLAdminDAO;
import by.htp.internetshop.dao.impl.SQLClientDAO;
import by.htp.internetshop.dao.impl.SQLOrderDAO;
import by.htp.internetshop.dao.impl.SQLProductDAO;

public class DAOFactory {
	private final static DAOFactory instance = new DAOFactory();

	public static DAOFactory getInstance() {
		return instance;
	}
	
	public AdminDAO getAdminDAO(){
		return SQLAdminDAO.getInstance();
	}

	public ClientDAO getClientDAO(){
		return SQLClientDAO.getInstance();
	}
	
	public ProductDAO getProductDAO(){
		return SQLProductDAO.getInstance();
	}
	
	public OrderDAO getOrderDAO(){
		return SQLOrderDAO.getInstance();
	}
}
