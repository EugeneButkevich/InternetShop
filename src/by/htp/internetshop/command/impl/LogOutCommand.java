package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.controller.RequestParameterName;

public class LogOutCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		if ((request.getSession(false).getAttribute(RequestParameterName.CLIENT) != null)
				|| (request.getSession(false).getAttribute(RequestParameterName.ADMIN) != null)) {
			request.getSession(false).invalidate();
		}
		return JspPageName.MAIN_PAGE;
	}
}
