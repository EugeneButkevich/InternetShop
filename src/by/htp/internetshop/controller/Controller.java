package by.htp.internetshop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.internetshop.command.CommandException;
import by.htp.internetshop.command.ICommand;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(Controller.class);

	public Controller() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger.info("Controller initialized");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		ICommand command = ControllerHelper.getInstance().getCommand(commandName);
		String page = null;
		try {
			page = command.execute(request);
		} catch (CommandException e) {
			page = JspPageName.ERROR_PAGE;
			logger.error("CommandException in Controller. Message:" + e.getMessage());
		} catch (Exception e) {
			page = JspPageName.ERROR_PAGE;
			logger.error("Exception in Controller. Message:" + e.getMessage());
		}

		if (logger.isEnabledFor(Level.DEBUG)) {
			logger.debug("commandName=" + commandName);
			logger.debug("command=" + command.getClass().getSimpleName());
			logger.debug("page=" + page);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			logger.error("Instance of RequestDispatcher equals null");
			errorMessageDiretlyFromResponse(response);
		}

	}

	private void errorMessageDiretlyFromResponse(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.getWriter().println("E R R O R");
	}

	@Override
	public void destroy() {
		super.destroy();
		logger.info("Controller destroyed");
	}
}
