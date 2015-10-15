/**
 * 
 */
package solutions.egen.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.egen.rrs.model.Restaurant;
import solutions.egen.rrs.model.Table;
import solutions.egen.rrs.utils.DBUtil;

/**
 * @author Kesava
 *
 */
public class TableDao
{
	/**
	 * Add new tables in the restaurant
	 * @param result
	 */
	public void addTables(Restaurant result)
	{
		int restId = result.getId();
		int table_1 = result.getTable_1();
		int table_2 = result.getTable_2();
		int table_4 = result.getTable_4();
		int table_6 = result.getTable_6();
		int table_8 = result.getTable_8();
		
		int table_size = 1;
		createTables(restId,table_size,table_1);
		
		table_size = 2;
		createTables(restId,table_size,table_2);
		
		table_size = 4;
		createTables(restId,table_size,table_4);
		
		table_size = 6;
		createTables(restId,table_size,table_6);
		
		table_size = 8;
		createTables(restId,table_size,table_8);
		
	}

	/**
	 * Create tables in database for the reservation with the table_size
	 * @param restId
	 * @param table_size
	 */
	public List<Table> createTables(int restId, int table_size, int no_Tables)
	{
		List<Table> result = new ArrayList<Table>();
		for(int i = 0; i < no_Tables; i++)
		{
			result.add(createTable(restId,table_size));
		}
		return result;
	}

	/**
	 * Create individual table
	 * @param restId
	 * @param table_size
	 * @param no_Tables
	 */
	private Table createTable(int restId, int table_size)
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Table result = null;
		try
		{
			ps = con.prepareStatement("INSERT INTO restaurant_tables "
					+ "(table_size, rest_id) VALUES (?, ?)", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, table_size);
			ps.setInt(2, restId);
			ps.execute();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{
				result = new Table();
				result.setRest_id(restId);
				result.setTable_id(rs.getInt(1));
				result.setTable_size(table_size);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		return result;
	}

	/**
	 * Update table in database
	 * @param table
	 */
	public void updateTable(Table table)
	{
		//TODO : to be implemented
	}
	
	/**
	 * Delete table in database
	 */
	public void deleteTable(int tableId)
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("DELETE FROM restaurant_tables "
					+ "WHERE table_id = ?", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tableId);
			ps.execute();
			rs = ps.getGeneratedKeys();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
	}
}
