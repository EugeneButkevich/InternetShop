package by.htp.internetshop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.ProductCategory;
import by.htp.internetshop.service.IService;

public class RecordAllCategoriesInSession implements IService {

	private static final RecordAllCategoriesInSession instance = new RecordAllCategoriesInSession();

	public static RecordAllCategoriesInSession getInstanse() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		List<ProductCategory> categoryList = null;
		boolean result = true;

		ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
		try {
			categoryList = productDAO.getAllCategories();
			request.getSession(true).setAttribute("allCategories", categoryList);
		} catch (DAOException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
