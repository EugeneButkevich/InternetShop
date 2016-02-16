package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.AdminDAO;
import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.domain.Admin;
import by.htp.internetshop.domain.Client;
import by.htp.internetshop.service.IService;

public class AuthorizationService implements IService {

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
				return true;
			} else if (clientDAO.checkClient(login, password)) {
				if (clientDAO.thereIsClientInBlacklist(login, password)) {
					request.getSession(true).setAttribute(RequestParameterName.ERROR_AUTHORIZATION, 2);
					return false;
				} else {
					Client client = clientDAO.getClient(login, password);
					request.getSession(true).setAttribute(RequestParameterName.CLIENT, client);
					return true;
				}
			} else {
				request.getSession(true).setAttribute(RequestParameterName.ERROR_AUTHORIZATION, 1);
				return false;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
