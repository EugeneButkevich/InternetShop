package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.service.IService;

public class ShowInformationAboutProductService implements IService {

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

		idProduct = Integer.parseInt(request.getParameter("id"));
		System.out.println("id=" + idProduct);
		productDAO = DAOFactory.getInstance().getProductDAO();

		try {
			product = productDAO.getProduct(idProduct);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		if (product != null) {
			request.setAttribute("product", product);
			result = true;
		} else {
			request.setAttribute("product", null);
		}
		return result;
	}
}
