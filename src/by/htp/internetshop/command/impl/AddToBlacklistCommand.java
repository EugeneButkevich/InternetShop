package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.AddToBlacklistService;
import by.htp.internetshop.service.impl.ShowCorrectClientsService;

public class AddToBlacklistCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		AddToBlacklistService.getInstance().doService(request);
		ShowCorrectClientsService.getInstance().doService(request);

		return JspPageName.ALL_CLIENTS_PAGE;
	}

}
