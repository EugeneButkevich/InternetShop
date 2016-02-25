package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.OrderDAO;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.service.IService;

public class CancelOrderService implements IService {

	private static final CancelOrderService instance = new CancelOrderService();

	public static CancelOrderService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		boolean result = false;
		int idOrder = 0;
		int idProduct = 0;
		int quantityOfProductsInOrder = 0;
		int quantityOfProductsInStock = 0;
		OrderDAO orderDAO = null;
		ProductDAO productDAO = null;

		idOrder = Integer.parseInt(request.getParameter(RequestParameterName.ID_ORDER));
		idProduct = Integer.parseInt(request.getParameter(RequestParameterName.ID_PRODUCT));
		quantityOfProductsInOrder = Integer.parseInt(request.getParameter(RequestParameterName.QUANTITY_OF_PRODUCTS_IN_ORDER));
		quantityOfProductsInStock = Integer.parseInt(request.getParameter(RequestParameterName.QUANTITY_OF_PRODUCTS_IN_STOCK));

		orderDAO = DAOFactory.getInstance().getOrderDAO();
		productDAO = DAOFactory.getInstance().getProductDAO();
		try {
			orderDAO.cancelOrder(idOrder);
			productDAO.updateQuantityOfProductsInStock(idProduct,
					quantityOfProductsInStock + quantityOfProductsInOrder);
			result = true;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
