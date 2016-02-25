package by.htp.internetshop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.domain.Client;
import by.htp.internetshop.service.IService;

public class ShowCorrectClientsService implements IService {

	private static final ShowCorrectClientsService instance = new ShowCorrectClientsService();

	public static ShowCorrectClientsService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
		List<Client> clientList = null;
		
		if (request.getSession(false).getAttribute(RequestParameterName.ADMIN) == null) {
			return false;
		}
		
		try {
			clientList = clientDAO.getClientsThatAreNotIncludedInBlacklist();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		if (!clientList.isEmpty()) {
			request.getSession(false).setAttribute(RequestParameterName.CLIENTS, clientList);
			return true;
		} else {
			request.getSession(false).setAttribute(RequestParameterName.CLIENTS, null);
			return false;
		}
	}
}
