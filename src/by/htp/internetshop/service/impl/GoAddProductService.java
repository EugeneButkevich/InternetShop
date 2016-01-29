package by.htp.internetshop.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.internetshop.service.IService;

public class GoAddProductService implements IService {

	private static final GoAddProductService instance = new GoAddProductService();

	public static GoAddProductService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		boolean result = false;
		String idCategory = null;

		idCategory = request.getParameter("id_category");
		System.out.println("idCategory="+idCategory);

		if (idCategory != null) {
			request.setAttribute("id_category", idCategory);
			result = true;
		}
		return result;
	}
}
