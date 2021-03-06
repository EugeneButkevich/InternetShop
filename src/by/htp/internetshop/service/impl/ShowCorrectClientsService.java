package by.htp.internetshop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.domain.Client;
import by.htp.internetshop.service.IService;

public class ShowCorrectClientsService implements IService {

	private static final Logger logger = Logger.getLogger(ShowCorrectClientsService.class);

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
			logger.error("ClientDAO didn't return clients which aren't included in the blacklist. Message: " + e.getMessage());
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
