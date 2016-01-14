package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.RemoveFromBlacklistService;
import by.htp.internetshop.service.impl.ShowBlacklistService;

public class RemoveFromBlacklistCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		RemoveFromBlacklistService.getInstance().doService(request);
		ShowBlacklistService.getInstance().doService(request);

		return JspPageName.BLACKLIST_PAGE;
	}
}
