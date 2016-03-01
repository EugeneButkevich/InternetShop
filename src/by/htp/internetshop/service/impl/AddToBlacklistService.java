package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.service.IService;

public class AddToBlacklistService implements IService {

	private static final Logger logger = Logger.getLogger(AddToBlacklistService.class);

	private static final AddToBlacklistService instance = new AddToBlacklistService();

	public static AddToBlacklistService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		int idClient = 0;
		boolean result = false;
		ClientDAO clientDAO;

		idClient = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
		clientDAO = DAOFactory.getInstance().getClientDAO();

		try {
			clientDAO.addToBlacklict(idClient);
			result = true;
		} catch (DAOException e) {
			logger.error("ClientDAO didn't client to blacklist. Message: " + e.getMessage());
		}
		return result;
	}
}
