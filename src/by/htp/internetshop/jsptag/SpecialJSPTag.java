package by.htp.internetshop.jsptag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.htp.internetshop.dao.DAOException;
import by.htp.internetshop.dao.DAOFactory;
import by.htp.internetshop.dao.ProductDAO;
import by.htp.internetshop.domain.Product;
import by.htp.internetshop.domain.ProductCategory;

public class SpecialJSPTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private ProductCategory category;

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		List<Product> productList = null;
		ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
		try {
			productList = productDAO.getProductsOfCategory(category.getId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			for (Product product : productList) {
				out.write("<tr>");
				out.write("<td> </td>");
				out.write("<td><font size=\"+1\">");
				out.write("<a href=\"controller?command=show_information_about_product&id_product=" + product.getId() + "\"> " + product.getName() + "</a> <br />");
				out.write("</font></td>");
				out.write("</tr>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}