package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.controller.RequestParameterName;

public class GoToAdminPageCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		if (request.getSession().getAttribute(RequestParameterName.ADMIN) != null) {
			page = JspPageName.ADMIN_PAGE;
		}
		return page;
	}

}
