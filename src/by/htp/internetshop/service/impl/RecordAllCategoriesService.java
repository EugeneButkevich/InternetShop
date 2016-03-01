package by.htp.internetshop.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.ProductCategory;
import by.htp.internetshop.service.IService;

public class RecordAllCategoriesService implements IService {

	private static final Logger logger = Logger.getLogger(RecordAllCategoriesService.class);

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
			logger.error("ProductDAO didn't return all categories. Message: " + e.getMessage());
		}

		ServletContext application = request.getServletContext();
		application.setAttribute(RequestParameterName.ALL_CATEGORIES, categoryList);
		return result;
	}
}
