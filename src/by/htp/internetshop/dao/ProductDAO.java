package by.htp.internetshop.dao;

import java.util.List;

import by.htp.internetshop.domain.Product;
import by.htp.internetshop.domain.ProductCategory;

public interface ProductDAO {

	List<ProductCategory> getAllCategories() throws DAOException;
	List<Product> getProductsOfCategory(int idCategory) throws DAOException;
	Product getProduct(int idProduct) throws DAOException;
	void addNewCategory(String nameCategory) throws DAOException;
	void addNewProduct(Product product) throws DAOException;

}
