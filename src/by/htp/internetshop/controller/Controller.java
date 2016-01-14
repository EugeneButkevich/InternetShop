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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		request.setCharacterEncoding("UTF-8");
		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		System.out.println("commandName="+commandName);
		ICommand command = ControllerHelper.getInstance().getCommand(commandName);
		System.out.println("command="+command);
		String page = null;
		try {
			page = command.execute(request);
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
/*
 * final String dbURL="jdbc:mysql://localhost:3306/internetshop"; final
 * String dbUserName="root"; final String dbPassword="3594242tduty";
 * String
 * queryInsert="INSERT INTO Product (name, price, quantityInStock)"+
 * "VALUES ('bread', 9000, 50);"; String
 * querySelect="SELECT * FROM Product"; Connection myConnection; try {
 * myConnection=DriverManager.getConnection(dbURL, dbUserName,
 * dbPassword); Statement statement=myConnection.createStatement();
 * statement.executeUpdate(queryInsert); ResultSet
 * rs=statement.executeQuery(querySelect); while (rs.next()) {
 * System.out.println("id_product: " + rs.getString("id_product"));
 * System.out.println("id_Category: " + rs.getString("id_Category"));
 * System.out.println("name: " + rs.getString("name"));
 * System.out.println("price: " + rs.getInt("price"));
 * System.out.println("quantityInStock: " +
 * rs.getString("quantityInStock")); } } catch(SQLException e){
 * e.printStackTrace(); }
 */

/*final String dbURL = "jdbc:mysql://localhost:3306/internetshop";
final String dbUserName = "root";
final String dbPassword = "3594242tduty";
String queryInsert = "INSERT INTO administrator (login, password)" + "VALUES ('bread', '9000rg');";
String querySelect = "SELECT * FROM administrator";
Connection myConnection;
try {
	myConnection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	Statement statement = myConnection.createStatement();
	statement.executeUpdate(queryInsert);
	ResultSet rs = statement.executeQuery(querySelect);
	while (rs.next()) {
		System.out.println("id: " + rs.getString("id_administrator"));
		System.out.println("login " + rs.getString("login"));
		System.out.println("password: " + rs.getString("password"));
	}
} catch (SQLException e) {
	e.printStackTrace();
}*/
