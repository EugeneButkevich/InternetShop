package by.htp.internetshop.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.ProductCategory;
import by.htp.internetshop.service.IService;

public class RecordAllCategoriesService implements IService {

	private static final RecordAllCategoriesService instance = new RecordAllCategoriesService();

	public static RecordAllCategoriesService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		boolean result = false;
		List<ProductCategory> categoryList = null;
		ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();

		try {
			categoryList = productDAO.getAllCategories();
			result = true;
		} catch (DAOException e) {
			e.printStackTrace();
		}

		ServletContext application = request.getServletContext();
		application.setAttribute(RequestParameterName.ALL_CATEGORIES, categoryList);
		return result;
	}
}
