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

import solutions.egen.rrs.exceptions.RRSException;
import solutions.egen.rrs.model.Restaurant;
import solutions.egen.rrs.utils.DBUtil;
import solutions.egen.rrs.utils.ERROR_CODES;
import solutions.egen.rrs.utils.ERROR_MESSSAGES;
import solutions.egen.rrs.utils.TableHelper;
import solutions.egen.rrs.utils.ValidationUtils;

/**
 * @author Kesava
 *
 */
public class RestaurantDao
{

	/**
	 * Return all the restaurants in the database
	 * @return
	 * @throws RRSException 
	 */
	public List<Restaurant> getAllRestaurants() throws RRSException
	{
		List<Restaurant> result = null;
		Connection con = DBUtil.getConnection();
		Restaurant restaurant = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			result = new ArrayList<Restaurant>();
			ps = con.prepareStatement("SELECT * FROM restaurant_details");
			rs = ps.executeQuery();
			while(rs.next())
			{
				restaurant = new Restaurant();
				restaurant.setName(rs.getString("name"));
				restaurant.setOpen_Close_Time( rs.getString("open_time") , rs.getString("close_time"));
				restaurant.setAddress1(rs.getString("address1"));
				restaurant.setAddress2(rs.getString("address2"));
				restaurant.setCity(rs.getString("city"));
				restaurant.setState(rs.getString("state"));
				restaurant.setZip(rs.getInt("zip"));
				restaurant.setEmail(rs.getString("email"));
				restaurant.setPhone(rs.getString("phone"));
				restaurant.setTable_1(rs.getInt("table_1"));
				restaurant.setTable_2(rs.getInt("table_2"));
				restaurant.setTable_4(rs.getInt("table_4"));
				restaurant.setTable_6(rs.getInt("table_6"));
				restaurant.setTable_8(rs.getInt("table_8"));
				restaurant.setId(rs.getInt("id"));
				result.add(restaurant);
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
		return result;
	}

	/**
	 * Find restaurant in the database based on phone number
	 * @param phone
	 * @return
	 * @throws RRSException 
	 */
	public Restaurant getRestaurant(int id) throws RRSException
	{
		Restaurant result = null;
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			
			ps = con.prepareStatement("SELECT * FROM restaurant_details WHERE "
					+ "id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				result = new Restaurant();
				result.setName(rs.getString("name"));
				result.setOpen_Close_Time(rs.getString("open_time") , rs.getString("close_time"));
				result.setAddress1(rs.getString("address1"));
				result.setAddress2(rs.getString("address2"));
				result.setCity(rs.getString("city"));
				result.setState(rs.getString("state"));
				result.setZip(rs.getInt("zip"));
				result.setEmail(rs.getString("email"));
				result.setPhone(rs.getString("phone"));
				result.setTable_1(rs.getInt("table_1"));
				result.setTable_2(rs.getInt("table_2"));
				result.setTable_4(rs.getInt("table_4"));
				result.setTable_6(rs.getInt("table_6"));
				result.setTable_8(rs.getInt("table_8"));
				result.setId(rs.getInt("id"));
			}
			else
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_RESTAURANT_ID));
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
		return result;
	}

	/**
	 * Create a new restaurant in the database
	 * @param restaurant
	 * @return
	 * @throws RRSException 
	 */
	public Restaurant createRestaurant(Restaurant restaurant) throws RRSException
	{
		Restaurant result = addRestaurant(restaurant);
		
		if(result != null)
		{
			//Now since a new restaurant is added, add tables too
			TableDao tableDao = new TableDao();
			tableDao.addTables(result);
		}
		
		return result;
	}

	/**
	 * @param restaurant
	 * @return
	 * @throws RRSException 
	 */
	private Restaurant addRestaurant(Restaurant restaurant) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		restaurant.validate();
		
		try
		{
			ps = con.prepareStatement("INSERT INTO restaurant_details ("
					+ "name,open_time,close_time,address1,address2,city,"
					+ "state,zip,email,phone,table_1,table_2,table_4,table_6,"
					+ "table_8,auto_assign,id) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
					, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getOpen_time());
			ps.setString(3, restaurant.getClose_time());
			ps.setString(4, restaurant.getAddress1());
			ps.setString(5, restaurant.getAddress2());
			ps.setString(6, restaurant.getCity());
			ps.setString(7, restaurant.getState());
			ps.setInt(8, restaurant.getZip());
			ps.setString(9, restaurant.getEmail());
			ps.setString(10, restaurant.getPhone());
			ps.setInt(11, restaurant.getTable_1());
			ps.setInt(12, restaurant.getTable_2());
			ps.setInt(13, restaurant.getTable_4());
			ps.setInt(14, restaurant.getTable_6());
			ps.setInt(15, restaurant.getTable_8());
			ps.setInt(16, restaurant.getAuto_assign());
			//TODO: just a hack to prevent entering new restaurants into database
			ps.setInt(17, restaurant.getId());
			ps.execute();
			rs = ps.getGeneratedKeys();
			if(rs.next())
			{
				restaurant.setId(rs.getInt(1));
				
				TableHelper.setTABLES_SIZE_1(restaurant.getTable_1());
				TableHelper.setTABLES_SIZE_2(restaurant.getTable_2());
				TableHelper.setTABLES_SIZE_4(restaurant.getTable_4());
				TableHelper.setTABLES_SIZE_6(restaurant.getTable_6());
				TableHelper.setTABLES_SIZE_8(restaurant.getTable_8());
			}
		}
		catch (SQLException e)
		{
			restaurant = null;
			e.printStackTrace();
			throw new RRSException(e.getMessage(), e.getCause());
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}		
		
		
		Restaurant.setAUTO_ASSIGN(restaurant.getAuto_assign());
		Restaurant.setOPEN_CLOSE_TIME(restaurant.getOpen_time(), restaurant.getClose_time());
		
		return restaurant;
	}

	/**
	 * Edit restaurant details based on the id
	 * @param restaurant
	 * @return
	 * @throws RRSException 
	 */
	public Restaurant editRestaurant(Restaurant restaurant) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		restaurant.validate();
			
		try
		{
			//First create a reservation with default status and table ids
			//Then update based on the table availability.
			ps = con.prepareStatement("UPDATE restaurant_details "
					+ "SET name = ?,"
					+ "open_time = ?,"
					+ "close_time = ?,"
					+ "address1 = ?,"
					+ "address2 = ?,"
					+ "city = ?,"
					+ "state = ?,"
					+ "zip = ?,"
					+ "email = ?,"
					+ "phone = ?,"
					+ "table_1 = ?,"
					+ "table_2 = ?,"
					+ "table_4 = ?,"
					+ "table_6 = ?,"
					+ "table_8 = ?,"
					+ "auto_assign = ? "
					+ "WHERE id = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, restaurant.getName());
			ValidationUtils.validateOpenCloseTimes(restaurant.getOpen_time(), restaurant.getClose_time());
			ps.setString(2, restaurant.getOpen_time());
			ps.setString(3, restaurant.getClose_time());
			ps.setString(4, restaurant.getAddress1());
			ps.setString(5, restaurant.getAddress2());
			ps.setString(6, restaurant.getCity());
			ps.setString(7, restaurant.getState());
			ps.setInt(8, restaurant.getZip());
			ps.setString(9, restaurant.getEmail());
			ps.setString(10, restaurant.getPhone());
			ps.setInt(11, restaurant.getTable_1());
			ps.setInt(12, restaurant.getTable_2());
			ps.setInt(13, restaurant.getTable_4());
			ps.setInt(14, restaurant.getTable_6());
			ps.setInt(15, restaurant.getTable_8());
			ps.setInt(16, restaurant.getAuto_assign());
			ps.setInt(17, restaurant.getId());
			int status = ps.executeUpdate();
			
			//If row doesn't exist for the specified constraint
			if(status == 0)
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_RESTAURANT_ID));
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
		
		
		
		checkAndUpdateTables(restaurant);
		
		Restaurant.setAUTO_ASSIGN(restaurant.getAuto_assign());
		Restaurant.setOPEN_CLOSE_TIME(restaurant.getOpen_time(), restaurant.getClose_time());
		return restaurant;
	}

	/**
	 * Delete a restaurant in database based on its id
	 * @param id
	 * @throws RRSException 
	 */
	public void deleteRestaurant(int id) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("DELETE FROM restaurant_details WHERE "
					+ "id = ?");
			ps.setInt(1, id);
			int status = ps.executeUpdate();
			
			//If row doesnt exist for the specified constraint
			if(status == 0)
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_RESTAURANT_ID));
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
	 * If the number of tables are changed in restaurant
	 * add or delete the tables as needed.
	 * @param restaurant
	 */
	private void checkAndUpdateTables(Restaurant restaurant)
	{
		//This is not requested so not implementing it
//		int table_1 = restaurant.getTable_1();
//		int table_2 = restaurant.getTable_1();
//		int table_4 = restaurant.getTable_1();
//		int table_6 = restaurant.getTable_1();
//		int table_8 = restaurant.getTable_1();
	}

	/**
	 * Assign values to AUTO_ASSIGN and Restaurant OPEN CLOSE times
	 * @throws RRSException 
	 */
	public void assignStaticValues() throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("SELECT * FROM restaurant_details  WHERE "
					+ "id = ?");
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				Restaurant.setOPEN_CLOSE_TIME( rs.getString("open_time") , rs.getString("close_time"));
				Restaurant.setAUTO_ASSIGN(rs.getInt("auto_assign"));
			}
			else
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_RESTAURANT_ID));
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

}
