package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.ShowBlacklistService;

public class ShowBlacklistCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		
		ShowBlacklistService.getInstance().doService(request);
		
		return JspPageName.BLACKLIST_PAGE;
	}

}
