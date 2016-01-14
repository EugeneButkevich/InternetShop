package by.htp.internetshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPool;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPoolException;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.domain.ProductCategory;

public class SQLProductDAO implements ProductDAO {

	private static final String GET_ALL_CATEGORIES_SQL = "SELECT * FROM categoryproduct";
	private static final String GET_PRODUCTS_OF_CATEGORY_SQL = "SELECT * FROM product WHERE id_category=?";
	private static final String GET_PRODUCT_SQL = "SELECT * FROM product WHERE id_product=?";
	private static final String ADD_NEW_CATEGORY_PRODUCT_SQL = "INSERT INTO categoryproduct (name) VALUES (?)";
	private static final String ADD_NEW_PRODUCT_SQL = "INSERT INTO product (id_category, name, price, quantityInStock) VALUES (?,?,?,?)";

	private static final SQLProductDAO instance = new SQLProductDAO();

	public static SQLProductDAO getInstance() {
		return instance;
	}

	@Override
	public List<ProductCategory> getAllCategories() throws DAOException {
		List<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		ProductCategory category = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_ALL_CATEGORIES_SQL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				category = new ProductCategory();
				category.setId(resultSet.getInt(1));
				category.setName(resultSet.getString(2));
				categoryList.add(category);
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// logging ERROR
				}
			}
			// return connection into connection pool
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categoryList;
	}

	@Override
	public List<Product> getProductsOfCategory(int idCategory) throws DAOException {
		List<Product> productList = new ArrayList<Product>();
		Product product = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_PRODUCTS_OF_CATEGORY_SQL);
			statement.setInt(1, idCategory);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt(1));
				product.setIdCategory(resultSet.getInt(2));
				product.setName(resultSet.getString(3));
				product.setPrice(resultSet.getInt(4));
				product.setQuantityInStock(resultSet.getInt(5));
				productList.add(product);
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// logging ERROR
				}
			}
			// return connection into connection pool
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

	@Override
	public Product getProduct(int idProduct) throws DAOException {
		Product product = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_PRODUCT_SQL);
			statement.setInt(1, idProduct);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt(1));
				product.setIdCategory(resultSet.getInt(2));
				product.setName(resultSet.getString(3));
				product.setPrice(resultSet.getInt(4));
				product.setQuantityInStock(resultSet.getInt(5));
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// logging ERROR
				}
			}
			// return connection into connection pool
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	@Override
	public void addNewCategory(String nameCategory) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(ADD_NEW_CATEGORY_PRODUCT_SQL);
			statement.setString(1, nameCategory);
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// logging ERROR
				}
			}
			// return connection into connection pool
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addNewProduct(Product product) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(ADD_NEW_PRODUCT_SQL);
			statement.setInt(1, product.getIdCategory());
			System.out.println("idCategory="+product.getIdCategory());
			statement.setString(2, product.getName());
			System.out.println("name="+product.getName());
			statement.setInt(3, product.getPrice());
			System.out.println("cost="+product.getPrice());
			statement.setInt(4, product.getQuantityInStock());
			System.out.println("quantity="+product.getQuantityInStock());
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// logging ERROR
				}
			}
			// return connection into connection pool
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
