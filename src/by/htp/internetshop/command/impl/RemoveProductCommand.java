package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.RemoveProductService;

public class RemoveProductCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		boolean result = false;

		result = RemoveProductService.getInstance().doService(request);
		if (result) {
			page = JspPageName.MAIN_PAGE;
		}
		return page;
	}
}
