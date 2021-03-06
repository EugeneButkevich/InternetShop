package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.ShowBlacklistService;

public class ShowBlacklistCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		boolean result = false;
		String page = null;

		result = ShowBlacklistService.getInstance().doService(request);

		if (result) {
			page = JspPageName.BLACKLIST_PAGE;
		}
		return page;
	}

}
