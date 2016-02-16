package by.htp.internetshop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		if (request.getSession().isNew()) {
			System.out.println("Новая сессия");
		}
		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		System.out.println("commandName=" + commandName);
		ICommand command = ControllerHelper.getInstance().getCommand(commandName);
		System.out.println("command=" + command);
		String page = null;
		try {
			page = command.execute(request);
			System.out.println("page=" + page);
		} catch (CommandException e) {
			page = JspPageName.ERROR_PAGE;
		} catch (Exception e) {
			page = JspPageName.ERROR_PAGE;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			errorMessageDiretlyFromResponse(response);
		}

	}

	private void errorMessageDiretlyFromResponse(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.getWriter().println("E R R O R");
	}
}
