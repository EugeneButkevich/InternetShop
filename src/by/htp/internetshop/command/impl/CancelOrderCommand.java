package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.CancelOrderService;
import by.htp.internetshop.service.impl.ShowOrdersOfOneClientService;

public class CancelOrderCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		boolean result = false;

		result = CancelOrderService.getInstance().doService(request);
		if (result) {
			page = JspPageName.SHOPPING_CART_PAGE;
			ShowOrdersOfOneClientService.getInstance().doService(request);
		}
		return page;
	}
}
