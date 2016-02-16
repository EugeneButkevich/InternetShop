package by.htp.internetshop.service.impl;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.controller.RequestParameterName;
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
		login = request.getParameter(RequestParameterName.LOGIN);
		password = request.getParameter(RequestParameterName.PASSWORD);
		passwordAgain = request.getParameter(RequestParameterName.PASSWORD_AGAIN);
		surname = request.getParameter(RequestParameterName.SURNAME);
		name = request.getParameter(RequestParameterName.NAME);
		registrationDate = new Date(System.currentTimeMillis());
		phone = request.getParameter(RequestParameterName.PHONE);
		address = request.getParameter(RequestParameterName.ADDRESS);
		email = request.getParameter(RequestParameterName.EMAIL);

		ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();

		if (!password.equals(passwordAgain)) {
			request.setAttribute(RequestParameterName.ERROR_REGISTRATION, 1);
			result = false;
		}

		try {
			if (!clientDAO.checkUniquenessOfLogin(login)) {
				request.setAttribute(RequestParameterName.ERROR_REGISTRATION, 2);
				result = false;
			}
		} catch (DAOException e1) {
			e1.printStackTrace();
		}

		if ((login == "") || (password == "") || (surname == "") || (name == "") || (phone == "")) {
			request.setAttribute(RequestParameterName.ERROR_REGISTRATION, 3);
			result = false;
		}

		Pattern p = Pattern.compile("[\\d\\s()\\-\\+]+");
		Matcher matcher = p.matcher(phone);
		if (!matcher.matches()) {
			request.setAttribute(RequestParameterName.ERROR_REGISTRATION, 4);
			result = false;
		}

		if (result) {
			try {
				clientDAO.registration(login, password, surname, name, registrationDate, phone, address, email);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
