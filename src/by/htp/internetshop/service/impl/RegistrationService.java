package by.htp.internetshop.service.impl;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.service.IService;

public class RegistrationService implements IService {

	private static final RegistrationService instance = new RegistrationService();

	public static RegistrationService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		String login;
		String password;
		String passwordAgain;
		String surname;
		String name;
		Date registrationDate;
		String phone;
		String address;
		String email;
		boolean result = true;
		System.out.println("Начальный result =" + result);
		login = request.getParameter("login");
		password = request.getParameter("password");
		passwordAgain = request.getParameter("passwordAgain");
		surname = request.getParameter("surname");
		name = request.getParameter("name");
		registrationDate = new Date(System.currentTimeMillis());
		phone = request.getParameter("phone");
		address = request.getParameter("address");
		email = request.getParameter("email");

		ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();

		if (!password.equals(passwordAgain)) {
			request.getSession(true).setAttribute("errorRegistration", 1);
			result = false;
		}

		try {
			if (!clientDAO.checkUniquenessOfLogin(login)) {
				request.getSession(true).setAttribute("errorRegistration", 2);
				result = false;
			}
		} catch (DAOException e1) {
			e1.printStackTrace();
		}

		if ((login == "") || (password == "") || (surname == "") || (name == "") || (phone == "")) {
			request.getSession(true).setAttribute("errorRegistration", 3);
			result = false;
		}

		if (result) {
			try {
				clientDAO.registration(login, password, surname, name, registrationDate, phone, address, email);
				/*
				 * Client client = clientDAO.getClient(login, password);
				 * request.getSession(true).setAttribute("client", client);
				 */
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}