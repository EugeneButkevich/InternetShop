package by.htp.internetshop.dao;

import java.util.List;

import by.htp.internetshop.domain.Client;
import by.htp.internetshop.domain.Order;
import by.htp.internetshop.domain.Product;

public interface OrderDAO {
	
	void addOrder(Client client, Product product, int numberOfInstances) throws DAOException;
	List<Order> getAllOrdersOfOneClient(int id_client) throws DAOException;
	void cancelOrder(int idOrder) throws DAOException;
	
}
