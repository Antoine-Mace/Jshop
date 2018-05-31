package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class				ProductBean extends BddManager {
	private String			name;
	private	String			description;
	private	Double			price;
	private	int				id;
	private	int				brand;
	private int				quantity;
	private	int				category;
	private String			picturePath;
	
	/**
	 * Create a poduct with all parameters
	 * @param id
	 * @param brand
	 * @param category
	 * @param name
	 * @param description
	 * @param picturePath
	 * @param price
	 * @param quantity
	 */
	public					ProductBean(int id, int brand, int category, String name, String description, int idPicture, Double price, int quantity)
	{
		String					query = "SELECT image_path FROM Images WHERE id = " + idPicture + ";";		
		Connection				con;
		java.sql.Statement		stmt;
		try
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection("jdbc:mysql://XX.XX.XX.XX:XXXX/Jshop", /*user*/, /*password*/);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
				this.picturePath = rs.getString(1);
			stmt.close();
			con.close();
		}
		catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());}
		
		this.id 			= id;
		this.name 			= name;
		this.description 	= description;
		this.price			= price;
		this.brand			= brand;
		this.quantity		= quantity;
		this.category		= category;
	}
	
	/**
	 * Create a product with only the product id in the DataBase
	 * @param id
	 * @throws ClassNotFoundException
	 */
	public					ProductBean(int id) throws ClassNotFoundException
	{
		ProductBean			product = convIdToProduct(id);
		this.id 			= product.id;
		this.name 			= product.name;
		this.description 	= product.description;
		this.picturePath 	= product.picturePath;
		this.price			= product.price;
		this.brand			= product.brand;
		this.quantity		= product.quantity;
		this.category		= product.category;
	}
	
	/**
	 * Convert an product id to an instance of Class ProductBean
	 * @param id
	 * @return ProductBean
	 * @throws ClassNotFoundException
	 */
	private ProductBean			convIdToProduct(int id) throws ClassNotFoundException
	{
		String					query = "SELECT id, product_brand, product_category, product_name, product_description, product_image1, product_price, product_quantity FROM Products WHERE id = " + id + ";";		
		Connection				con;
		java.sql.Statement		stmt;
		ProductBean				product = null;
			
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://62.210.238.5:3306/JWeb", "root", "22121994");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
				product = new ProductBean(Integer.decode(rs.getString(1))
							, Integer.decode(rs.getString(2)), Integer.decode(rs.getString(3)), rs.getString(4), rs.getString(5)
							, Integer.decode(rs.getString(6)), Double.parseDouble(rs.getString(7)), Integer.decode(rs.getString(8)));
			stmt.close();
			con.close();
		}
		catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());}
		return product;
	}
	
	public int 				getCategory() {
		return category;
	}

	public void 			setCategory(int category) {
		this.category = category;
	}
	
	public int 				getBrand() {
		return brand;
	}

	public void 			setBrand(int brand) {
		this.brand = brand;
	}
	
	public int 				getQuantity() {
		return quantity;
	}

	public void 			setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String			getName() 
	{
		return name;
	}
	
	public void				setName(String name) 
	{
		this.name = name;
	}
	
	public String			getDescription() 
	{
		return description;
	}
	
	public void				setDescription(String description) 
	{
		this.description = description;
	}
	
	public String				getPicturePath() {
		return picturePath;
	}

	public void				setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	public Double			getPrice() 
	{
		return price;
	}
	
	public void				setPrice(Double price) 
	{
		this.price = price;
	}

	public int				getId() 
	{
		return id;
	}
	
	public void				setId(int nb) 
	{
		this.id = nb;
	}

	
	public String			getPathFromId() throws ClassNotFoundException
	{
		String				result;
		String				query2;
		String				query = "SELECT product_image1 FROM Products WHERE id = " + this.getId() + ";";

		result = this.conexion(query, "SELECT");
		query2 = "SELECT image_path FROM Images WHERE id = " + result + ";";
		return (this.conexion(query2, "SELECT"));
	}

	public String			interprateQuerySelect(java.sql.Statement stmt, String query) throws ClassNotFoundException
	{
		try {
			ResultSet 		rs = stmt.executeQuery(query);
			while (rs.next())
				return (rs.getString(1));
		}
		catch (SQLException ex){
			System.err.println("SQLException: " + ex.getMessage());}
		return null;
	}
	
	
	/**
	 * Create the query to add a customer in the DB
	 * @return
	 */
	public String				createQueryToAddProduct()
	{
		String			query;
		
		query = "INSERT INTO Products (product_brand, product_category, product_name,";
		query = query +	"product_description, product_image1, product_price, product_quantity)";
		query = query + "VALUES ('" + this.getBrand() + "', '" + this.getCategory() + "', '" + this.getName() + "'";
		query = query + ", '" + this.getDescription() + "', '" + this.getPicturePath() + "', '" + this.getPrice() + "', '" + this.getQuantity() + "');"; 
		return query;
	}
	
	public String				addOnDataBase() throws ClassNotFoundException
	{
		String					query;
		
		query = this.createQueryToAddProduct();
		this.conexion(query, "INSERT");
		return("Success");
	}
}
