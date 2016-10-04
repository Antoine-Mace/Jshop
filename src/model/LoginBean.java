package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class 			LoginBean extends BddManager
{
	private	String		firstName;
	private	String		lastName;
	private String		phone;
    private String 		email;
    private String 		password;
	private	String		city;
	private	String		country;	
	private	String		adress;
	private	String		zip;
	private	int			news;
	private int			id;
	
	/**
	 * Boolean who permit to know if the customer is login or not
	 */
	private boolean		login = false;
	
	
	public boolean 		isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getNews() {
		return news;
	}

	public void setNews(int news) {
		this.news = news;
	}
    
	/**
	 * To set every data to null, and logout the user
	 */
	public void		logOut()
	{
	    setFirstName(null);
		setLastName(null);
		setPhone(null);
	    setEmail(null);
	    setPassword(null);
		setCity(null);
		setCountry(null);	
		setAdress(null);
		setZip(null);
		setId(0);
		login = false;
		System.out.println("logOut");
	}
	
	public String			interprateQuerySelect(java.sql.Statement stmt, String query) throws ClassNotFoundException
	{
		try {
			ResultSet 		rs = stmt.executeQuery(query);
			 if (rs.next())
			 {
				 setId(Integer.decode(rs.getString(1)));
				 setFirstName(rs.getString(4));
				 setLastName(rs.getString(5));
				 setPhone(rs.getString(7));
				 setAdress(rs.getString(9));
				 setCity(rs.getString(10));
				 setZip(rs.getString(11));
				 setCountry(rs.getString(12));
				 setNews(Integer.decode(rs.getString(13)));
				 login = true;
				return "Success";
			 }
		}
		catch (SQLException ex){
			System.err.println("SQLException: " + ex.getMessage());}
		return "Error";
	}
	
    public String	submitForm() throws ClassNotFoundException
    {
		 String query = "SELECT * FROM Customers WHERE customer_email = '" + getEmail() + "' AND customer_password = (MD5('" + getPassword() + "'));";
		 return this.conexion(query, "SELECT");
   }
}
