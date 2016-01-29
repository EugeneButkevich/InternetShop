package by.htp.internetshop.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.OrderDAO;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPool;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPoolException;
import by.htp.internetshop.domain.Client;
import by.htp.internetshop.domain.Product;

public class SQLOrderDAO implements OrderDAO {

	private static final String ORDER1_SQL = "INSERT INTO order_ (id_client, address, order_date, status, amount) VALUES (?,?,?,?,?)";
	private static final String GET_LAST_ID_ORDER_SQL = "SELECT id_order FROM order_ ORDER BY id_order DESC LIMIT 1";
	private static final String ORDER2_SQL = "INSERT INTO order_has_product (id_order, id_product, count) VALUES (?,?,?)";
		
	
	private static final String INITIAL_ORDER_STATUS = "В процессе";

	private final static SQLOrderDAO instance = new SQLOrderDAO();

	public static SQLOrderDAO getInstance() {
		return instance;
	}

	@SuppressWarnings("resource")
	@Override
	public void getOrder(Client client, Product product, int numberOfInstances) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int idOrder = 0;
		int amount = numberOfInstances * product.getPrice();
		Date orderDate = new Date(System.currentTimeMillis());

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(ORDER1_SQL);
			statement.setInt(1, client.getId());
			statement.setString(2, client.getAddress());
			statement.setDate(3, orderDate);
			statement.setString(4, INITIAL_ORDER_STATUS);
			statement.setInt(5, amount);
			statement.executeUpdate();

			statement = connection.prepareStatement(GET_LAST_ID_ORDER_SQL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				idOrder = resultSet.getInt(1);
			}

			statement = connection.prepareStatement(ORDER2_SQL);
			statement.setInt(1, idOrder);
			statement.setInt(2, product.getId());
			statement.setInt(3, numberOfInstances);
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
