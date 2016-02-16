package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.ClientDAO;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.OrderDAO;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.Client;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.service.IService;

public class OrderService implements IService {

	private static final OrderService instance = new OrderService();

	public static OrderService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		boolean result = true;
		int idProduct = 0;
		Client client = new Client();
		Product product = new Product();
		String numberOfInstances = null;
		ClientDAO clientDAO = null;
		OrderDAO orderDAO = null;
		ProductDAO productDAO = null;

		client.setId(Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT)));
		idProduct = Integer.parseInt(request.getParameter(RequestParameterName.ID_PRODUCT));
		numberOfInstances = request.getParameter(RequestParameterName.NUMBER_OF_INSTANCES);

		if (numberOfInstances == "" || numberOfInstances.equals("0")) {
			request.setAttribute(RequestParameterName.ERROR_ORDER, 1);
			return false;
		}

		try {
			if (Integer.parseInt(numberOfInstances) < 0) {
				request.setAttribute(RequestParameterName.ERROR_ORDER, 2);
				return false;
			}
		} catch (Exception e) {
			request.setAttribute(RequestParameterName.ERROR_ORDER, 3);
			return false;
		}

		if (result) {
			result = false;
			clientDAO = DAOFactory.getInstance().getClientDAO();
			orderDAO = DAOFactory.getInstance().getOrderDAO();
			productDAO = DAOFactory.getInstance().getProductDAO();
			try {
				client.setAddress(clientDAO.getAddressOfClient(client.getId()));
				product = productDAO.getProduct(idProduct);
				if (Integer.parseInt(numberOfInstances) > product.getQuantityInStock()) {
					request.setAttribute(RequestParameterName.ERROR_ORDER, 4);
					return false;
				}
				orderDAO.getOrder(client, product, Integer.parseInt(numberOfInstances));
				productDAO.updateQuantityOfProductsInStock(product,
						product.getQuantityInStock() - Integer.parseInt(numberOfInstances));
				result = true;
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
