package by.htp.internetshop.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.OrderDAO;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.Client;
import by.htp.internetshop.domain.Order;
import by.htp.internetshop.service.IService;

public class ShowOrdersOfOneClientService implements IService {

	private final static ShowOrdersOfOneClientService instance = new ShowOrdersOfOneClientService();

	public static ShowOrdersOfOneClientService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		boolean result = false;
		int idClient = 0;
		int idClientInSession = 0;
		OrderDAO orderDAO = null;
		ProductDAO productDAO = null;
		List<Order> orderList = null;
		Map<Integer, List<Object>> dataOfOrdersOfOneClient = null;

		idClient = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
		idClientInSession = ((Client) request.getSession().getAttribute(RequestParameterName.CLIENT)).getId();
		System.out.println("1)" + idClient);
		System.out.println("2)" + idClientInSession);
		
		if (idClient != idClientInSession) {
			return false;
		}
		
		orderDAO = DAOFactory.getInstance().getOrderDAO();
		productDAO = DAOFactory.getInstance().getProductDAO();
		try {
			orderList = orderDAO.getAllOrdersOfOneClient(idClient);
			dataOfOrdersOfOneClient = productDAO.getDataOfAllOrdersOfOneClient(orderList);
			request.setAttribute(RequestParameterName.ALL_ORDERS_OF_ONE_CLIENT, dataOfOrdersOfOneClient);
			result = true;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
