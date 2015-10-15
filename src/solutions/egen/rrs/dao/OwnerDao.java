/**
 * 
 */
package solutions.egen.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import solutions.egen.rrs.exceptions.RRSException;
import solutions.egen.rrs.model.Owner;
import solutions.egen.rrs.utils.DBUtil;
import solutions.egen.rrs.utils.ERROR_CODES;
import solutions.egen.rrs.utils.ERROR_MESSSAGES;

/**
 * @author Kesava
 *
 */
public class OwnerDao
{

	/**
	 * Add owner into database
	 * @param owner
	 * @throws RRSException 
	 */
	public Owner addOwner(Owner owner) throws RRSException
	{		
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this owner is already present in database
			ps = con.prepareStatement("INSERT INTO owner "
					+ "(email, password) "
					+ "VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, owner.getEmail());
			ps.setString(2, owner.getPassword());
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RRSException(e.getMessage(), e.getCause());
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		return owner;
	}

	/**
	 * Edit the owner in database
	 * @param owner
	 * @return
	 * @throws RRSException 
	 */
	public Owner editOwner(Owner owner) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this owner is already present in database
			ps = con.prepareStatement("UPDATE owner SET password = ?"
					+ " WHERE email = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, owner.getPassword());
			ps.setString(2, owner.getEmail());
			int status = ps.executeUpdate();
			if(status != 1)
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_OWNER));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RRSException(e.getMessage(), e.getCause());
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		return owner;
	}

	/**
	 * Delete a owner from database
	 * @param owner
	 * @throws RRSException 
	 */
	public void deleteOwner(String email) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this owner is already present in database
			ps = con.prepareStatement("DELETE FROM owner WHERE "
					+ "email = ?");
			ps.setString(1, email);
			int status = ps.executeUpdate();
			if(status != 1)
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_OWNER));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RRSException(e.getMessage(), e.getCause());
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
	}

	/**
	 * Check if the username and password match in database
	 * @param email
	 * @param pass
	 * @return
	 * @throws RRSException
	 */
	public boolean checkLogin(String email, String pass) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean found = false;
		try
		{
			//First check if this owner is already present in database
			ps = con.prepareStatement("SELECT * FROM owner "
					+ "WHERE email = ? AND password = ?");
			ps.setString(1, email);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next())
			{
				found =  true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RRSException(e.getMessage(), e.getCause());
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		return found;
	}


}
