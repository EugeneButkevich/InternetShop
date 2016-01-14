package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.service.IService;

public class AddToBlacklistService implements IService {

	private static final AddToBlacklistService instance = new AddToBlacklistService();

	public static AddToBlacklistService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		int idClient = 0;
		boolean result = false;
		ClientDAO clientDAO;

		idClient=Integer.parseInt(request.getParameter("id"));
		clientDAO = DAOFactory.getInstance().getClientDAO();
		
		try {
			clientDAO.addToBlacklict(idClient);
			result = true;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
