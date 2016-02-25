package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.ShowOrdersOfOneClientService;

public class ShowOrdersOfOneClientCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		boolean result = false;
		String page = null;

		result = ShowOrdersOfOneClientService.getInstance().doService(request);
		if (result) {
			page = JspPageName.SHOPPING_CART_PAGE;
		}
		return page;
	}
}
