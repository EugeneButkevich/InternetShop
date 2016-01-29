package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.GoEditProductService;

public class GoEditProductCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		boolean result = false;

		result = GoEditProductService.getInstance().doService(request);
		if (result) {
			page = JspPageName.EDIT_PRODUCT_PAGE;
		}
		return page;
	}
}
