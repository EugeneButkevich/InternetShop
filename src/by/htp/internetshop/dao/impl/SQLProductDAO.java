package by.htp.internetshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPool;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPoolException;
import by.htp.internetshop.domain.Order;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.domain.ProductCategory;

public class SQLProductDAO implements ProductDAO {

	private static final String GET_ALL_CATEGORIES_SQL = "SELECT * FROM categoryproduct";
	private static final String GET_PRODUCTS_OF_CATEGORY_SQL = "SELECT * FROM product WHERE id_category=?";
	private static final String GET_PRODUCT_SQL = "SELECT * FROM product WHERE id_product=?";
	private static final String ADD_NEW_CATEGORY_PRODUCT_SQL = "INSERT INTO categoryproduct (name) VALUES (?)";
	private static final String ADD_NEW_PRODUCT_SQL = "INSERT INTO product (id_category, name, price, quantityInStock) VALUES (?,?,?,?)";
	private static final String EDIT_PRODUCT_SQL = "UPDATE product SET name=?, price=?, quantityInStock=? WHERE id_product=?";
	private static final String REMOVE_PRODUCT_SQL = "DELETE FROM product WHERE id_product=?";
	private static final String UPDATE_QUANTITY_OF_PRODUCTS_IN_STOCK_SQL = "UPDATE product SET quantityInStock=? WHERE id_product=?";
	private static final String GET_PRODUCT_OF_ORDER_SQL = "SELECT * FROM product WHERE id_product IN (SELECT id_product FROM order_has_product WHERE id_order=?)";

	private static final Logger logger = Logger.getLogger(SQLProductDAO.class);

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
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
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
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
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
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
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
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
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
			System.out.println("idCategory=" + product.getIdCategory());
			statement.setString(2, product.getName());
			System.out.println("name=" + product.getName());
			statement.setInt(3, product.getPrice());
			System.out.println("cost=" + product.getPrice());
			statement.setInt(4, product.getQuantityInStock());
			System.out.println("quantity=" + product.getQuantityInStock());
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
			}
		}
	}

	@Override
	public void editProduct(Product product) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(EDIT_PRODUCT_SQL);
			statement.setString(1, product.getName());
			statement.setInt(2, product.getPrice());
			statement.setInt(3, product.getQuantityInStock());
			statement.setInt(4, product.getId());
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
			}
		}
	}

	@Override
	public void removeProduct(int idProduct) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(REMOVE_PRODUCT_SQL);
			statement.setInt(1, idProduct);
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
			}
		}
	}

	@Override
	public void updateQuantityOfProductsInStock(int idProduct, int newValueOfQuantity) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(UPDATE_QUANTITY_OF_PRODUCTS_IN_STOCK_SQL);
			statement.setInt(1, newValueOfQuantity);
			statement.setInt(2, idProduct);
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
			}
		}
	}

	@Override
	public Map<Integer, List<Object>> getDataOfAllOrdersOfOneClient(List<Order> orderList) throws DAOException {
		Map<Integer, List<Object>> allOrdersOfOneClient = new HashMap<Integer, List<Object>>();
		int quantityOfProductsInOrder = 0;
		List<Object> dataOfOrdersOfOneClient = null;
		Product product = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_PRODUCT_OF_ORDER_SQL);
			for (Order order : orderList) {
				statement.setInt(1, order.getIdOrder());
				resultSet = statement.executeQuery();
				product = new Product();
				dataOfOrdersOfOneClient = new ArrayList<>();
				while (resultSet.next()) {
					product.setId(resultSet.getInt(1));
					product.setIdCategory(resultSet.getInt(2));
					product.setName(resultSet.getString(3));
					product.setPrice(resultSet.getInt(4));
					product.setQuantityInStock(resultSet.getInt(5));
					dataOfOrdersOfOneClient.add(product);
					dataOfOrdersOfOneClient.add(order.getAmount());
					quantityOfProductsInOrder = order.getAmount() / product.getPrice();
					dataOfOrdersOfOneClient.add(quantityOfProductsInOrder);
					allOrdersOfOneClient.put(order.getIdOrder(), dataOfOrdersOfOneClient);
				}
			}
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLProductDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Statement didn't close. Message: " + e.getMessage());
				}
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Connection didn't close. Message: " + e.getMessage());
			}
		}
		return allOrdersOfOneClient;
	}
}
