package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.service.IService;

public class RemoveFromBlacklistService implements IService {

	private static final Logger logger = Logger.getLogger(RemoveFromBlacklistService.class);

	private static final RemoveFromBlacklistService instance = new RemoveFromBlacklistService();

	public static RemoveFromBlacklistService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		int id_client = 0;
		boolean result = false;
		ClientDAO clientdDAO = null;

		id_client = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
		clientdDAO = DAOFactory.getInstance().getClientDAO();

		try {
			clientdDAO.removeFromBlacklist(id_client);
			result = true;
		} catch (DAOException e) {
			logger.error("ClientDAO didn't remove client from blacklist. Message: " + e.getMessage());
		}
		return result;
	}
}
