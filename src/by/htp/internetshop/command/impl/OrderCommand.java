package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.OrderService;
import by.htp.internetshop.service.impl.ShowInformationAboutProductService;

public class OrderCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		boolean result = false;

		result = OrderService.getInstance().doService(request);

		if (result) {
			page = JspPageName.SUCCESSFUL_ORDER_PAGE;
		} else {
			ShowInformationAboutProductService.getInstance().doService(request);
			page = JspPageName.SINGLE_PRODUCT_PAGE;
		}
		return page;
	}
}
