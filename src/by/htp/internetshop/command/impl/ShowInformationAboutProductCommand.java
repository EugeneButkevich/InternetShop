package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.ShowInformationAboutProductService;

public class ShowInformationAboutProductCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		boolean result = false;
		String page = null;
		
		result = ShowInformationAboutProductService.getInstance().doService(request);
		if (result) {
			page = JspPageName.SINGLE_PRODUCT_PAGE;
		}
		return page;
	}
}
