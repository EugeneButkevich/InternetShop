package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.service.impl.AuthorizationService;
import by.htp.internetshop.service.impl.RecordAllCategoriesInSession;

public class AuthorizationCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		boolean result = false;
		String page = null;
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		if ((login != null) & (password != null)) {
			AuthorizationService authorization = AuthorizationService.getInstance();
			result = authorization.doService(request);
			if (result) {
				if (request.getSession(true).getAttribute("admin") != null) {
					page = JspPageName.ADMIN_PAGE;
				} else if (request.getSession(true).getAttribute("client") != null) {
					RecordAllCategoriesInSession.getInstanse().doService(request);
					page = JspPageName.MAIN_PAGE;
				}
			} else {
				page = JspPageName.AUTHORIZATION_PAGE;
			}
		}
		return page;
	}
}
