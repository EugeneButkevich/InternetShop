package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.service.IService;

public class ShowInformationAboutProductService implements IService {

	private static final Logger logger = Logger.getLogger(ShowInformationAboutProductService.class);

	private final static ShowInformationAboutProductService instance = new ShowInformationAboutProductService();

	public static ShowInformationAboutProductService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		Product product = null;
		int idProduct = 0;
		boolean result = false;
		ProductDAO productDAO;

		idProduct = Integer.parseInt(request.getParameter(RequestParameterName.ID_PRODUCT));
		productDAO = DAOFactory.getInstance().getProductDAO();

		try {
			product = productDAO.getProduct(idProduct);
		} catch (DAOException e) {
			logger.error("ProductDAO didn't return a product. Message: " + e.getMessage());
		}

		if (product != null) {
			request.setAttribute(RequestParameterName.PRODUCT, product);
			result = true;
		} else {
			request.setAttribute(RequestParameterName.PRODUCT, null);
		}
		return result;
	}
}
