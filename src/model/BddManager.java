package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BddManager {
	/**
	 * Call this function if you want to communicate with the Database
	 * @param query
	 * @param type
	 * @throws ClassNotFoundException
	 * @return result of the query if type is select
	 */
	public String				conexion(String query, String type) throws ClassNotFoundException
	{
		Connection				con;
		java.sql.Statement		stmt;
		String					result = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://XX.XX.XX.XX:XXXX/Jshop", /*user*/, /*password*/);
			stmt = con.createStatement();
			if (type.equals("INSERT"))
				this.interprateQueryInsert(stmt, query);
			else
				result = this.interprateQuerySelect(stmt, query);
			stmt.close();
			con.close();
		}
		catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());}
		return result;
	}
	
	public void			interprateQueryInsert(java.sql.Statement stmt, String query) throws ClassNotFoundException
	{
		try {
			stmt.executeUpdate(query);
		}
		catch (SQLException ex){
			System.err.println("SQLException: " + ex.getMessage());}
	}

	public String		interprateQuerySelect(java.sql.Statement stmt, String query) throws ClassNotFoundException
	{
		try {
			ResultSet 	rs = stmt.executeQuery(query);
			while (rs.next())
				return (rs.getString(1));
		}
		catch (SQLException ex){
			System.err.println("SQLException: " + ex.getMessage());}
		return null;
	}
}
