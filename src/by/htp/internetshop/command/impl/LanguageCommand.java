package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.service.impl.RecordAllCategoriesInSession;

public class LanguageCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		request.getSession(true).setAttribute(RequestParameterName.LOCAL_NAME, request.getParameter(RequestParameterName.LOCAL_NAME));
		if (request.getSession(true).getAttribute("admin") != null){
			return JspPageName.ADMIN_PAGE;
		} else {
			RecordAllCategoriesInSession.getInstanse().doService(request);
			return JspPageName.MAIN_PAGE;
		}
	}
}
