package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.service.IService;

public class RemoveProductService implements IService {

	private static final Logger logger = Logger.getLogger(RemoveProductService.class);
			
	private static final RemoveProductService instance = new RemoveProductService();

	public static RemoveProductService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		boolean result = false;
		int idProduct = 0;
		ProductDAO productDAO = null;

		idProduct = Integer.parseInt(request.getParameter(RequestParameterName.ID_PRODUCT));
		productDAO = DAOFactory.getInstance().getProductDAO();
		try {
			productDAO.removeProduct(idProduct);
			result = true;
		} catch (DAOException e) {
			logger.error("ProductDAO didn't remove product. Message: " + e.getMessage());
		}
		return result;
	}
}
