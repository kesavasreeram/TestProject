/**
 * 
 */
package solutions.egen.rrs.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import solutions.egen.rrs.dao.RestaurantDao;
import solutions.egen.rrs.exceptions.RRSException;
import solutions.egen.rrs.model.Restaurant;

/**
 * @author kesava
 *
 */
public class ValidationUtils
{

	/**
	 * Validate open and close timings
	 * for simplicity we are only validating for timing on same day
	 * open time < close time and both should be on same day
	 * @param open_time
	 * @param close_time
	 * @return
	 */
	public static boolean validateOpenCloseTimes(String open_time, String close_time) throws RRSException
	{
		GregorianCalendar openTime = validateDatetime(open_time);
		GregorianCalendar closeTime = validateDatetime(close_time);
		
		if(openTime.before(closeTime) && 
			openTime.get(Calendar.YEAR) ==  closeTime.get(Calendar.YEAR) &&
			openTime.get(Calendar.MONTH) ==  closeTime.get(Calendar.MONTH) &&
			openTime.get(Calendar.DATE) ==  closeTime.get(Calendar.DATE) )
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Date time should be in format : YYYY-MM-DD-HH-MM
	 * @param datetime
	 * @return
	 * @throws RRSException 
	 */
	public static GregorianCalendar validateDatetime(String datetime) throws RRSException
	{
		String[] tokens = datetime.split("-");
		
		if(tokens.length == 5)
		{
			//Check if we can create a calendar day with these details
			try
			{
				
				int year = Integer.parseInt(tokens[0]);
				int month = Integer.parseInt(tokens[1]);
				int date = Integer.parseInt(tokens[2]);
				int hr = Integer.parseInt(tokens[3]);
				int min = Integer.parseInt(tokens[4]);
				
				GregorianCalendar cal = new  GregorianCalendar();
				cal.setLenient(false);
				cal.set(year, month - 1, date, hr, min);
				return cal;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_DATE_TIME) , e.getCause());
			}
		}
		else
		{
			System.out.println(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_DATE_TIME));
			throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_DATE_TIME));
		}
	}
	
	
	/**
	 * Validate the reservation time. Check if the time falls between restaurant open and close times as well
	 * @param datetime
	 * @throws RRSException 
	 */
	public static void validateReservationtime(String datetime) throws RRSException
	{
		if(!Restaurant.isValuesSet())
		{
			RestaurantDao rDao = new RestaurantDao();
			rDao.assignStaticValues();
		}
		
		GregorianCalendar now = new GregorianCalendar();
		
		GregorianCalendar openTime = validateDatetime(Restaurant.getOPEN_TIME());
		
		GregorianCalendar closeTime = validateDatetime(Restaurant.getCLOSE_TIME());
		
		GregorianCalendar reservationTime = validateDatetime(datetime);
		
		
		if(now.before(reservationTime))
		{
			int yr = reservationTime.get(Calendar.YEAR);
			int month = reservationTime.get(Calendar.MONTH);
			int date = reservationTime.get(Calendar.DATE);
			
			openTime.set(yr, month, date);
			closeTime.set(yr, month, date);
			
			if( ( openTime.before(reservationTime) || openTime.equals(reservationTime) )&& closeTime.after(reservationTime))
			{
				return; //Return if everything is OK;
			}
			
		}
		
		//else throw error message
		throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_RESERVATION_TIME));
	}

	/**
	 * Party size should be a positive number
	 * @param partySize
	 * @throws RRSException 
	 */
	public static void validatePartySize(int partySize) throws RRSException
	{
		if(partySize <= 0)
		{
			throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_PARTY_SIZE));
		}
	}
	
	
	/**
	 * Number of tables has to be either 0 or positive integer
	 * @param table_1
	 * @throws RRSException 
	 */
	public static void validateTableSize(int noOfTables) throws RRSException
	{
		if(noOfTables < 0)
		{
			throw new RRSException(ERROR_MESSSAGES.getErrorMessage(
					ERROR_CODES.INVALID_NO_OF_TABLES));
		}
		
	}

}
