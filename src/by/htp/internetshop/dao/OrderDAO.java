package by.htp.internetshop.dao;

import by.htp.internetshop.domain.Client;
import by.htp.internetshop.domain.Product;

public interface OrderDAO {
	void getOrder(Client client, Product product, int numberOfInstances) throws DAOException;
}
