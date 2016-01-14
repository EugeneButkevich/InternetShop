package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;
import by.htp.internetshop.service.impl.RegistrationService;

public class RegistrationCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		boolean result = RegistrationService.getInstance().doService(request);

		System.out.println("Итоговый result ="+result);
		if (result) {
			page = JspPageName.SUCCESSFUL_REGISTRATION_PAGE;
		} else {
			page = JspPageName.REGISTRATION_PAGE;
		}
		return page;
	}
}
