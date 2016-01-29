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

public class SpecialJSPTagForAdmin extends TagSupport {
	private static final long serialVersionUID = 1L;
	private ProductCategory category;
	private String nameedit;
	private String namedelete;
	private String nameadd;

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public String getNameedit() {
		return nameedit;
	}

	public void setNameedit(String nameedit) {
		this.nameedit = nameedit;
	}

	public String getNamedelete() {
		return namedelete;
	}

	public void setNamedelete(String namedelete) {
		this.namedelete = namedelete;
	}
	
	public String getNameadd() {
		return nameadd;
	}

	public void setNameadd(String nameadd) {
		this.nameadd = nameadd;
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
				out.write("<td>");
				out.write("<form action=\"controller\" method=\"post\">");
				out.write("<input type=\"hidden\" name=\"command\" value=\"go_edit_product\" />");
				out.write("<input type=\"hidden\" name=\"id_product\" value=\"" + product.getId() + "\" />");
				out.write("<input type=\"submit\" value=\"" + nameedit + "\" />");
				out.write("</form>");
				out.write("</td>");
				out.write("<td>");
				out.write("<form action=\"controller\" method=\"post\">");
				out.write("<input type=\"hidden\" name=\"command\" value=\"remove_product\" />");
				out.write("<input type=\"hidden\" name=\"id_product\" value=\"" + product.getId() + "\" />");
				out.write("<input type=\"submit\" value=\"" + namedelete + "\" />");
				out.write("</form>");
				out.write("</td>");
				out.write("</tr>");
			}
			out.write("<tr><td></td>");
			out.write("<td>");
			out.write("<form action=\"controller\" method=\"post\">");
			out.write("<input type=\"hidden\" name=\"command\" value=\"go_add_product\" />");
			out.write("<input type=\"hidden\" name=\"id_category\" value=\"" + category.getId() + "\" />");
			out.write("<small><input type=\"submit\" value=\"" + nameadd + "\" /></small>");
			out.write("</form>");
			out.write("</td>");
			out.write("</tr>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
