package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.AdminDAO;
import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.domain.Admin;
import by.htp.internetshop.domain.Client;
import by.htp.internetshop.service.IService;

public class AuthorizationService implements IService {

	private static final Logger logger = Logger.getLogger(AuthorizationService.class);

	private final static AuthorizationService instance = new AuthorizationService();

	public static AuthorizationService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
		ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		try {
			if (adminDAO.checkAdmin(login, password)) {
				Admin admin = adminDAO.getAdmin(login, password);
				request.getSession(true).setAttribute(RequestParameterName.ADMIN, admin);
				logger.info("AdminDAO checked an administrator");
				return true;
			} else if (clientDAO.checkClient(login, password)) {
				if (clientDAO.thereIsClientOnBlacklist(login, password)) {
					request.getSession(true).setAttribute(RequestParameterName.ERROR_AUTHORIZATION, 2);
					logger.warn("Client can't log in. He is on the blacklist");
					return false;
				} else {
					Client client = clientDAO.getClient(login, password);
					request.getSession(true).setAttribute(RequestParameterName.CLIENT, client);
					logger.info("ClientDAO checked a client");
					return true;
				}
			} else {
				request.getSession(true).setAttribute(RequestParameterName.ERROR_AUTHORIZATION, 1);
				logger.warn("Client made mistake when entering login or password");
				return false;
			}
		} catch (DAOException e) {
			logger.error("AdminDAO or ClientDAO didn't check administrator or client respectively. Message: "
					+ e.getMessage());
		}
		return false;
	}
}
