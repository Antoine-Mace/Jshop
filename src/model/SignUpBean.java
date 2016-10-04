package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

public class 			SignUpBean extends BddManager {
	private	String		firstName;
	private	String		lastName;
	private String		phone;
	private String		email;
	private	String		password;	
	private	String		city;
	private	String		country;	
	private	String		adress;
	private	String		zip;
	private	Boolean		news;
	private Boolean		exist = false;

	
	public Boolean getExist() {
		return exist;
	}

	public void setExist(Boolean exist) {
		this.exist = exist;
	}

	public String 		getFirstName() {
		return firstName;
	}

	public void 		setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String 		getLastName() {
		return lastName;
	}

	public void 		setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String 		getPhone() {
		return phone;
	}

	public void 		setPhone(String phone) {
		this.phone = phone;
	}

	public String 		getEmail() {
		return email;
	}

	public void 		setEmail(String email) {
		this.email = email;
	}

	public String 		getPassword() {
		return password;
	}

	public void 		setPassword(String password) {
		this.password = password;
	}

	public String		getCity() {
		return city;
	}

	public void 		setCity(String city) {
		this.city = city;
	}

	public String 		getCountry() {
		return country;
	}

	public void 		setCountry(String country) {
		this.country = country;
	}

	public String 		getAdress() {
		return adress;
	}

	public void 		setAdress(String adress) {
		this.adress = adress;
	}

	public String 		getZip() {
		return zip;
	}

	public void 		setZip(String zip) {
		this.zip = zip;
	}

	public Boolean 			getNews() {
		return news;
	}

	public void 		setNews(Boolean news) {
		this.news = news;
	}

	public int			returnNewsToInt()
	{
		if (this.news.equals(true))
			return 1;
		else
			return 0;
	}
	
	public Boolean				customAlreadyExist(String str) throws ClassNotFoundException
	{
		this.conexion(str, "SELECT");
		if (this.getExist() == true)
			return true;
		else
			return false;
	}
	
	public void				validatePassword(ComponentSystemEvent event)
	{
		this.setExist(false);
		FacesContext fc = FacesContext.getCurrentInstance();  
		UIComponent components = event.getComponent();
		UIInput uiInputPassword = (UIInput) components.findComponent("password");
		String password = uiInputPassword.getLocalValue() == null ? ""
			: uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();
		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
			: uiInputConfirmPassword.getLocalValue().toString();
		if (password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		  }
		if (!password.equals(confirmPassword)) {
			FacesMessage msg = new FacesMessage("Password must match confirm password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();
		  }
	}

	public String		interprateQuerySelect(java.sql.Statement stmt, String query) throws ClassNotFoundException
	{
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				if (rs.getString(1).equals(this.getEmail()))
					this.setExist(true);
			}
		}
		catch (SQLException ex){
			System.err.println("SQLException: " + ex.getMessage());}
		return null;
	}

	
	public String		createQueryForSignUp()
	{
		String			query;
		
		query = "INSERT INTO Customers (customer_password, customer_firstname, customer_lastname,";
		query = query + "customer_email, customer_phone, customer_log, customer_adress, ";
		query = query + "customer_city, customer_zipcode, customer_country, customer_news) ";
		query = query + "VALUES (MD5('"+ this.getPassword() +"'), '" + this.getFirstName() + "',  '" + this.getLastName() + "',";
		query = query + "'" + this.getEmail() + "', '" + this.getPhone() + "', NOW(), '" + this.getAdress() + "', '" + this.getCity() + "',";
		query = query+ "'" + this.getZip() + "', '" + this.getCountry() + "', '" + this.returnNewsToInt() + "');";
		return query;
	}
	
	public String		submitForm() throws ClassNotFoundException
	{
		String			query;

		query = this.createQueryForSignUp();
		if (this.customAlreadyExist("SELECT customer_email FROM Customers;") == true)
			return ("Error");
		else
			this.conexion(query, "INSERT");
		return ("Success");
	}
}
