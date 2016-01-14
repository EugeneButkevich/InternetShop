package by.htp.internetshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.internetshop.dao.AdminDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPool;
import by.htp.internetshop.dao.impl.connectionpool.ConnectionPoolException;
import by.htp.internetshop.domain.Admin;

public class SQLAdminDAO implements AdminDAO {

	private static final String LOGIN_PASSWORD_CHECK_SQL = "SELECT id_administrator FROM Administrator WHERE login=? AND password=?";
	private static final String GET_ADMIN_DATA_SQL = "SELECT * FROM administrator WHERE login=? AND password=?";

	private final static SQLAdminDAO instance = new SQLAdminDAO();

	public static AdminDAO getInstance() {
		return instance;
	}

	@Override
	public boolean checkAdmin(String login, String password) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(LOGIN_PASSWORD_CHECK_SQL);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();

			return resultSet.next();

		} catch (SQLException e) {
			throw new DAOException("Error!");
		} catch (ConnectionPoolException e) {
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
		return false;
	}

	@Override
	public Admin getAdmin(String login, String password) throws DAOException {
		Admin admin = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_ADMIN_DATA_SQL);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				admin = new Admin();
				admin.setId(resultSet.getInt(1));
				admin.setLogin(resultSet.getString(2));
				admin.setPassword(resultSet.getString(3));
				System.out.println("id=" + admin.getId());
				System.out.println("login=" + admin.getLogin());
				System.out.println("password=" + admin.getPassword());
			}

		} catch (SQLException e) {
			throw new DAOException("Error!");
		} catch (ConnectionPoolException e) {
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
		return admin;
	}
}
