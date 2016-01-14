package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.AddNewCategoryProductService;
import by.htp.internetshop.service.impl.RecordAllCategoriesInSession;

public class AddNewCategoryProductCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		boolean result = false;

		result = AddNewCategoryProductService.getInstance().doService(request);

		if (result) {
			RecordAllCategoriesInSession.getInstanse().doService(request);
		}
		return JspPageName.MAIN_PAGE;
	}
}
