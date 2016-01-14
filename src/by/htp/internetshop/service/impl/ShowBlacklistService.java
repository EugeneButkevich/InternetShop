package by.htp.internetshop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.domain.Client;
import by.htp.internetshop.service.IService;

public class ShowBlacklistService implements IService {

	private static final ShowBlacklistService instance = new ShowBlacklistService();

	public static ShowBlacklistService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
		List<Client> clientList = null;

		try {
			clientList = clientDAO.getBlacklist();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		if (!clientList.isEmpty()) {
			request.getSession(false).setAttribute("clients", clientList);
			return true;
		} else {
			request.getSession(false).setAttribute("clients", null);
			return false;
		}
	}
}
