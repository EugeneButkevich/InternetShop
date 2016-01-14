package by.htp.internetshop.dao;

import by.htp.internetshop.domain.Admin;

public interface AdminDAO {
	
	boolean checkAdmin(String login, String password) throws DAOException;
	Admin getAdmin(String login, String password) throws DAOException;
	
}
