package by.htp.internetshop.dao;

import java.util.List;
import java.util.Map;

import by.htp.internetshop.domain.Order;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.domain.ProductCategory;

public interface ProductDAO {

	List<ProductCategory> getAllCategories() throws DAOException;
	List<Product> getProductsOfCategory(int idCategory) throws DAOException;
	Product getProduct(int idProduct) throws DAOException;
	void addNewCategory(String nameCategory) throws DAOException;
	void addNewProduct(Product product) throws DAOException;
	void editProduct(Product product) throws DAOException;
	void removeProduct(int idProduct) throws DAOException;
	void updateQuantityOfProductsInStock(int idProduct, int newValueOfQuantity) throws DAOException;
	Map<Integer, List<Object>> getDataOfAllOrdersOfOneClient(List<Order> orderList) throws DAOException;

}
