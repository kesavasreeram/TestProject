/**
 * 
 */
package solutions.egen.rrs.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import solutions.egen.rrs.dao.RestaurantTablesDao;
import solutions.egen.rrs.model.Reservation;
import solutions.egen.rrs.model.TableDetails;

/**
 * @author Kesava
 *
 */
public class TableHelper
{
	//TODO : these values need to be set when restaurant profile is created
	private static int TABLES_SIZE_1 = 0;
	private static int TABLES_SIZE_2 = 0;
	private static int TABLES_SIZE_4 = 0;
	private static int TABLES_SIZE_6 = 0;
	private static int TABLES_SIZE_8 = 0;
	
	/**
	 * @return the tABLES_SIZE_1
	 */
	public static int getTABLES_SIZE_1() {
		return TABLES_SIZE_1;
	}
	/**
	 * @param tABLES_SIZE_1 the tABLES_SIZE_1 to set
	 */
	public static void setTABLES_SIZE_1(int tABLES_SIZE_1) {
		TABLES_SIZE_1 = tABLES_SIZE_1;
	}
	/**
	 * @return the tABLES_SIZE_2
	 */
	public static int getTABLES_SIZE_2() {
		return TABLES_SIZE_2;
	}
	/**
	 * @param tABLES_SIZE_2 the tABLES_SIZE_2 to set
	 */
	public static void setTABLES_SIZE_2(int tABLES_SIZE_2) {
		TABLES_SIZE_2 = tABLES_SIZE_2;
	}
	/**
	 * @return the tABLES_SIZE_4
	 */
	public static int getTABLES_SIZE_4() {
		return TABLES_SIZE_4;
	}
	/**
	 * @param tABLES_SIZE_4 the tABLES_SIZE_4 to set
	 */
	public static void setTABLES_SIZE_4(int tABLES_SIZE_4) {
		TABLES_SIZE_4 = tABLES_SIZE_4;
	}
	/**
	 * @return the tABLES_SIZE_6
	 */
	public static int getTABLES_SIZE_6() {
		return TABLES_SIZE_6;
	}
	/**
	 * @param tABLES_SIZE_6 the tABLES_SIZE_6 to set
	 */
	public static void setTABLES_SIZE_6(int tABLES_SIZE_6) {
		TABLES_SIZE_6 = tABLES_SIZE_6;
	}
	/**
	 * @return the tABLES_SIZE_8
	 */
	public static int getTABLES_SIZE_8() {
		return TABLES_SIZE_8;
	}
	/**
	 * @param tABLES_SIZE_8 the tABLES_SIZE_8 to set
	 */
	public static void setTABLES_SIZE_8(int tABLES_SIZE_8) {
		TABLES_SIZE_8 = tABLES_SIZE_8;
	}
	
	public static int getMaxTablesForSize(int size)
	{
		switch(size)
		{
			case 1:
				return TABLES_SIZE_1;
			case 2:
				return TABLES_SIZE_2;
			case 4:
				return TABLES_SIZE_4;
			case 6:
				return TABLES_SIZE_6;
			case 8:
				return TABLES_SIZE_8;
			default:
				return -1;
		}
	}
	
	/**
	 * 
	 * @param result
	 * @param partySize
	 * @param tablesAssigned
	 * @param restaurantTablesDao 
	 */
	public TableDetails getAvailableTable(Reservation reservation, 
			HashMap<Integer, Integer> tablesAssigned, Connection con,
			PreparedStatement ps, ResultSet rs)
	{
		TableDetails result = null;
		//TODO: re - implement the class with more dynamic methods and parameters
		int partySize = reservation.getPartySize();
		int tableSize = 0;
		
		switch(partySize)
		{
			case 1:
				
				if( (tablesAssigned.get(1) == null && TABLES_SIZE_1 > 0) ||
					(tablesAssigned.get(1) != null && tablesAssigned.get(1) < TABLES_SIZE_1))
				{
					tableSize = 1;
				}
				else if( (tablesAssigned.get(2) == null && TABLES_SIZE_2 > 0 )||
						(tablesAssigned.get(2) != null && tablesAssigned.get(2) < TABLES_SIZE_2))
				{
					tableSize = 2;
				}
				else if( (tablesAssigned.get(4) == null && TABLES_SIZE_4 > 0) ||
						(tablesAssigned.get(4) != null && tablesAssigned.get(4) < TABLES_SIZE_4))
				{
					tableSize = 4;
				}
				else if( (tablesAssigned.get(6) == null && TABLES_SIZE_6 > 0) ||
						(tablesAssigned.get(6) != null && tablesAssigned.get(6) < TABLES_SIZE_6))
				{
					tableSize = 6;
				}
				else if( (tablesAssigned.get(8) == null && TABLES_SIZE_8 > 0)||
						(tablesAssigned.get(8) != null && tablesAssigned.get(8) < TABLES_SIZE_8))
				{
					tableSize = 8;
				}
				break;
			case 2:
				if( (tablesAssigned.get(2) == null && TABLES_SIZE_2 > 0 )||
						(tablesAssigned.get(2) != null && tablesAssigned.get(2) < TABLES_SIZE_2))
				{
					tableSize = 2;
				}
				else if( (tablesAssigned.get(4) == null && TABLES_SIZE_4 > 0) ||
						(tablesAssigned.get(4) != null && tablesAssigned.get(4) < TABLES_SIZE_4))
				{
					tableSize = 4;
				}
				else if( (tablesAssigned.get(6) == null && TABLES_SIZE_6 > 0) ||
						(tablesAssigned.get(6) != null && tablesAssigned.get(6) < TABLES_SIZE_6))
				{
					tableSize = 6;
				}
				else if( (tablesAssigned.get(8) == null && TABLES_SIZE_8 > 0)||
						(tablesAssigned.get(8) != null && tablesAssigned.get(8) < TABLES_SIZE_8))
				{
					tableSize = 8;
				}
				break;
			case 4:
				if( (tablesAssigned.get(4) == null && TABLES_SIZE_4 > 0) ||
						(tablesAssigned.get(4) != null && tablesAssigned.get(4) < TABLES_SIZE_4))
				{
					tableSize = 4;
				}
				else if( (tablesAssigned.get(6) == null && TABLES_SIZE_6 > 0) ||
						(tablesAssigned.get(6) != null && tablesAssigned.get(6) < TABLES_SIZE_6))
				{
					tableSize = 6;
				}
				else if( (tablesAssigned.get(8) == null && TABLES_SIZE_8 > 0)||
						(tablesAssigned.get(8) != null && tablesAssigned.get(8) < TABLES_SIZE_8))
				{
					tableSize = 8;
				}
				break;
			case 6:
				if( (tablesAssigned.get(6) == null && TABLES_SIZE_6 > 0) ||
						(tablesAssigned.get(6) != null && tablesAssigned.get(6) < TABLES_SIZE_6))
				{
					tableSize = 6;
				}
				else if( (tablesAssigned.get(8) == null && TABLES_SIZE_8 > 0)||
						(tablesAssigned.get(8) != null && tablesAssigned.get(8) < TABLES_SIZE_8))
				{
					tableSize = 8;
				}
				break;
			case 8:
				if( (tablesAssigned.get(8) == null && TABLES_SIZE_8 > 0)||
						(tablesAssigned.get(8) != null && tablesAssigned.get(8) < TABLES_SIZE_8))
				{
					tableSize = 8;
				}
				break;
			default:
				break;
		}
		
		if(tableSize != 0) //table is available
		{
			RestaurantTablesDao restaurantTablesDao = new RestaurantTablesDao();
			result = restaurantTablesDao.assignTable(reservation,tableSize,con,ps,rs);
		}
		else
		{
			result = new TableDetails();
		}
		
		return result;
	}
}
