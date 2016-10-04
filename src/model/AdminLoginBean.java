package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginBean extends BddManager {
	private int			id;
    private String 		password;
	private	String		firstname;
	private	String		lastname;
	private String		phone;
    private String 		email;
	private	String		right;
	
	/**
	 * Boolean who permit to know if the customer is login or not
	 */
	private boolean		login = false;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	/**
	 * To set every data to null, and logout the user
	 */
	public void		logOut()
	{
	    setFirstname(null);
		setLastname(null);
		setPhone(null);
		setEmail(null);
		setPassword(null);
		setRight(null);
		setId(0);
		login = false;
	}
	
	public String			interprateQuerySelect(java.sql.Statement stmt, String query) throws ClassNotFoundException
	{
		try {
			ResultSet 		rs = stmt.executeQuery(query);
			 if (rs.next())
			 {
				 setId(Integer.decode(rs.getString(1)));
				 setFirstname(rs.getString(3));
				 setLastname(rs.getString(4));
				 setPhone(rs.getString(6));
				 setRight(rs.getString(7));
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
		// String query = "SELECT * FROM Users WHERE customer_email = '" + getEmail() + "' AND customer_password = (MD5('" + getPassword() + "'));";
    	String query = "SELECT * FROM Users WHERE user_email = '" + getEmail() + "' AND user_password = '" + getPassword() + "';";
		 return this.conexion(query, "SELECT");
   }
}
