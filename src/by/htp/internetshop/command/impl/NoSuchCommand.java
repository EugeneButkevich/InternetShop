package by.htp.internetshop.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.controller.JspPageName;

public class NoSuchCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		return JspPageName.ERROR_PAGE;
	}

}
