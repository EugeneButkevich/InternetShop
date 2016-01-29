package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.service.IService;

public class EditProductService implements IService {

	private static final EditProductService instance = new EditProductService();

	public static EditProductService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		boolean result = true;
		Product product = new Product();
		String idCategory = null;
		String idProduct = null;
		String nameProduct = null;
		String costOfProduct = null;
		String quantityOfProduct = null;
		ProductDAO productDAO = null;

		idCategory = request.getParameter("id_category");
		product.setIdCategory(Integer.parseInt(idCategory));

		idProduct = request.getParameter("id_product");
		product.setId(Integer.parseInt(idProduct));

		nameProduct = request.getParameter("name_product");
		if (nameProduct == "") {
			request.getSession(true).setAttribute("errorAddOrEditProduct", 1);
			return false;
		}
		product.setName(nameProduct);

		costOfProduct = request.getParameter("cost_product");
		if (costOfProduct == "") {
			request.getSession(true).setAttribute("errorAddOrEditProduct", 2);
			return false;
		}
		try {
			if (Integer.parseInt(costOfProduct) < 0) {
				request.getSession(true).setAttribute("errorAddOrEditProduct", 3);
				return false;
			}
			product.setPrice(Integer.parseInt(costOfProduct));
		} catch (Exception e) {
			request.getSession(true).setAttribute("errorAddOrEditProduct", 5);
			return false;
		}

		quantityOfProduct = request.getParameter("quantity");
		if (quantityOfProduct == "") {
			product.setQuantityInStock(0);
		} else {
			try {
				if (Integer.parseInt(quantityOfProduct) < 0) {
					request.getSession(true).setAttribute("errorAddOrEditProduct", 4);
					return false;
				}
				product.setQuantityInStock(Integer.parseInt(quantityOfProduct));
			} catch (Exception e) {
				request.getSession(true).setAttribute("errorAddOrEditProduct", 6);
				return false;
			}
		}

		if (result) {
			productDAO = DAOFactory.getInstance().getProductDAO();
			try {
				productDAO.editProduct(product);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
