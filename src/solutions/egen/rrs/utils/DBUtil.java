/**
 * 
 */
package solutions.egen.rrs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kesava
 *	Data base utils class.
 *	This class is responsible for making a connection with
 *	the database with the provided credentials and url
 */
public class DBUtil
{
	public static final String url = "jdbc:mysql://localhost:3306/egen_proj";
	public static final String user = "root";
	public static final String password = "root";
	
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the database connection object
	 * @return Java.sql.Connection object
	 */
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(url,user,password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}

	public static void releaseResources(Connection con, PreparedStatement ps, ResultSet rs)
	{
		try {
			releaseResources(ps, rs);
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void releaseResources(PreparedStatement ps, ResultSet rs)
	{
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
