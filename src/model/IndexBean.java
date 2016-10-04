package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class 				IndexBean extends BddManager {
	private 				Vector<ProductBean>	lastProduct = new Vector<ProductBean>();
	private 				Vector<ProductBean>	wishList = new Vector<ProductBean>();
	
	Boolean					men = false;
	Boolean					women = false;
	Boolean					children = false;
	
	public boolean 			isMen() {
		return men;
	}

	public void 			setMen(Boolean men) {
		this.women = false;
		this.children = false;
		this.men = men;
	}

	public boolean			 isWomen() {
		return women;
	}

	public void 			setWomen(Boolean women) {
		this.men = false;
		this.children = false;
		this.women = women;
	}

	public boolean 			isChildren() {
		return children;
	}

	public void 			setChildren(Boolean children) {
		this.men = false;
		this.women = false;
		this.children = children;
	}
	
	
	public int				giveIdToPrint()
	{
		if (this.isMen() == true)
		{
			System.out.println("Catgerory 2 | Men = " + this.isMen() + " | Women = " + this.isWomen() + " | Children = " + this.isChildren());
			return 2;
		}
		else if (this.isWomen() == true)
		{
			System.out.println("Catgerory 3 | Men = " + this.isMen() + " | Women = " + this.isWomen() + " | Children = " + this.isChildren());
			return 3;
		}
		else if (this.isChildren() == true)
		{
			System.out.println("Catgerory 4 | Men = " + this.isMen() + " | Women = " + this.isWomen() + " | Children = " + this.isChildren());
			return 4;
		}
		else
		{
			System.out.println("Catgerory ALL | Men = " + this.isMen() + " | Women = " + this.isWomen() + " | Children = " + this.isChildren());
			return 5;
		}
	}
	
	/**
	 * initialize the lastProduct vector
	 * @throws ClassNotFoundException
	 * @throws NumberFormatException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void			loadLastProduct() throws ClassNotFoundException
	{
		String query = "SELECT id, product_brand, product_category, product_name, product_description, product_image1, product_price, product_quantity FROM Products ORDER BY id DESC LIMIT 6;";
		this.conexion(query, "SELECT");
	}
	
	public Vector<ProductBean> getLastProduct() throws ClassNotFoundException
	{
		if (!(lastProduct.size() > 0))
			loadLastProduct();
		return lastProduct;
	}

	public void setLastProduct(Vector<ProductBean> lastProduct) {
		this.lastProduct = lastProduct;
	}

	public Vector<ProductBean> getWishList() {
		return wishList;
	}

	public void setWishList(Vector<ProductBean> wishList) {
		this.wishList = wishList;
	}
	
	public String			interprateQuerySelect(java.sql.Statement stmt, String query)
	{
		try {
			ResultSet 		rs = stmt.executeQuery(query);
			while (rs.next())
			{
				ProductBean product = new ProductBean(Integer.decode(rs.getString(1))
						, Integer.decode(rs.getString(2)), Integer.decode(rs.getString(3)), rs.getString(4), rs.getString(5)
						, Integer.decode(rs.getString(6)), Double.parseDouble(rs.getString(7)), Integer.decode(rs.getString(8)));
				this.lastProduct.add(product);
			}
		}
		catch (SQLException ex){
			System.err.println("SQLException: " + ex.getMessage());}
		return null;
	}
}