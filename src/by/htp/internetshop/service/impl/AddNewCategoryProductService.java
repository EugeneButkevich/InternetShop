package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.htp.internetshop.controller.RequestParameterName;
import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.service.IService;

public class AddNewCategoryProductService implements IService {

	private static final Logger logger = Logger.getLogger(AddNewCategoryProductService.class);

	private static final AddNewCategoryProductService instance = new AddNewCategoryProductService();

	public static AddNewCategoryProductService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		String nameCategory = null;
		boolean result = false;
		ProductDAO productDAO = null;

		nameCategory = request.getParameter(RequestParameterName.NAME_CATEGORY);
		if (!nameCategory.isEmpty()) {
			productDAO = DAOFactory.getInstance().getProductDAO();
			try {
				productDAO.addNewCategory(nameCategory);
				result = true;
			} catch (DAOException e) {
				logger.error("ProductDAO didn't add a new category of products. Message: "+e.getMessage());
			}
		} else {
			logger.warn("Didn't enter the name of category ");
			request.getSession(true).setAttribute(RequestParameterName.ERROR_ADD_CATEGORY, 1);
		}
		return result;
	}

}
