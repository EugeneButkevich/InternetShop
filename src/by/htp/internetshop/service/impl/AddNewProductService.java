package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.service.IService;

public class AddNewProductService implements IService {

	private static final AddNewProductService instance = new AddNewProductService();

	public static AddNewProductService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		boolean result = true;
		Product product = new Product();
		String idCategory = null;
		String nameProduct = null;
		String costOfProduct = null;
		String quantityOfProduct = null;
		ProductDAO productDAO = null;

		idCategory = request.getParameter("id_category");
		product.setIdCategory(Integer.parseInt(idCategory));

		nameProduct = request.getParameter("name_product");
		if (nameProduct == "") {
			request.getSession(true).setAttribute("errorAddProduct", 1);
			return false;
		}
		product.setName(nameProduct);

		costOfProduct = request.getParameter("cost_product");
		if (costOfProduct == "") {
			request.getSession(true).setAttribute("errorAddProduct", 2);
			return false;
		}
		try {
			if (Integer.parseInt(costOfProduct) < 0) {
				request.getSession(true).setAttribute("errorAddProduct", 3);
				return false;
			}
			product.setPrice(Integer.parseInt(costOfProduct));
		} catch (Exception e) {
			request.getSession(true).setAttribute("errorAddProduct", 5);
			return false;
		}

		quantityOfProduct = request.getParameter("quantity");
		if (quantityOfProduct == "") {
			product.setQuantityInStock(0);
		}
		try {
			if (Integer.parseInt(quantityOfProduct) < 0) {
				request.getSession(true).setAttribute("errorAddProduct", 4);
				return false;
			}
			product.setQuantityInStock(Integer.parseInt(quantityOfProduct));
		} catch (Exception e) {
			request.getSession(true).setAttribute("errorAddProduct", 6);
			return false;
		}

		if (result) {
			productDAO = DAOFactory.getInstance().getProductDAO();
			try {
				productDAO.addNewProduct(product);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
