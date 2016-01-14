package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.RecordAllCategoriesInSession;

public class LogOutCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		if ((request.getSession(false).getAttribute("client") != null) || (request.getSession(false).getAttribute("admin") != null)) {
			request.getSession(false).invalidate();
		}
		RecordAllCategoriesInSession.getInstanse().doService(request);
		return JspPageName.MAIN_PAGE;
	}
}
