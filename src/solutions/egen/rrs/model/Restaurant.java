/**
 * 
 */
package solutions.egen.rrs.model;

import solutions.egen.rrs.exceptions.RRSException;
import solutions.egen.rrs.utils.ERROR_CODES;
import solutions.egen.rrs.utils.ERROR_MESSSAGES;
import solutions.egen.rrs.utils.ValidationUtils;

/**
 * @author Kesava
 *
 */
public class Restaurant
{
	private String name = "";
	private String open_time = null;
	private String close_time = null;
	private String address1 = "";
	private String address2 = "";
	private String city = "";
	private String state = "";
	private int zip = 0;
	private String email = "";
	private String phone = "";
	private int table_1 = 0;
	private int table_2 = 0;
	private int table_4 = 0;
	private int table_6 = 0;
	private int table_8 = 0;
	
	//TODO : For present scenario we are hard coding this value to 1, setter is hardcoded
	private int id = 1;
	
	private int auto_assign = 0;
	
	//TODO make sure that this is set when auto assign is changes in restaurant Dao
	private static int AUTO_ASSIGN = 1; //1 is for true , 0 is for false
	
	//Static variables
	private static String OPEN_TIME = "2015-10-10-10-00";
	
	private static String CLOSE_TIME = "2015-10-10-20-00";
	
	private static boolean valuesSet = false;

	/**
	 * @return the valuesSet
	 */
	public static boolean isValuesSet() {
		return valuesSet;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * @return the open_time
	 */
	public String getOpen_time() {
		return open_time;
	}

	/**
	 * @return the close_time
	 */
	public String getClose_time() {
		return close_time;
	}
	
	public void setOpen_Close_Time(String open_time, String close_time) throws RRSException
	{
		if(ValidationUtils.validateOpenCloseTimes(open_time,close_time))
		{
			this.open_time = open_time;
			this.close_time = close_time;
		}
		else
		{
			throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_OPEN_CLOSE_TIMES));
		}
		
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the table_1
	 */
	public int getTable_1() {
		return table_1;
	}

	/**
	 * @param table_1 the table_1 to set
	 */
	public void setTable_1(int table_1) {
		this.table_1 = table_1;
	}

	/**
	 * @return the table_2
	 */
	public int getTable_2() {
		return table_2;
	}

	/**
	 * @param table_2 the table_2 to set
	 */
	public void setTable_2(int table_2) {
		this.table_2 = table_2;
	}

	/**
	 * @return the table_4
	 */
	public int getTable_4() {
		return table_4;
	}

	/**
	 * @param table_4 the table_4 to set
	 */
	public void setTable_4(int table_4) {
		this.table_4 = table_4;
	}

	/**
	 * @return the table_6
	 */
	public int getTable_6() {
		return table_6;
	}

	/**
	 * @param table_6 the table_6 to set
	 */
	public void setTable_6(int table_6) {
		this.table_6 = table_6;
	}

	/**
	 * @return the table_8
	 */
	public int getTable_8() {
		return table_8;
	}

	/**
	 * @param table_8 the table_8 to set
	 */
	public void setTable_8(int table_8) {
		this.table_8 = table_8;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = 1;
	}

	/**
	 * @return the auto_assign
	 */
	public int getAuto_assign() {
		return auto_assign;
	}

	/**
	 * @param auto_assign the auto_assign to set
	 */
	public void setAuto_assign(int auto_assign)
	{
		this.auto_assign = auto_assign;
	}

	/**
	 * @return the aUTO_ASSIGN
	 */
	public static int getAUTO_ASSIGN() {
		return AUTO_ASSIGN;
	}

	/**
	 * @param aUTO_ASSIGN the aUTO_ASSIGN to set
	 */
	public static void setAUTO_ASSIGN(int aUTO_ASSIGN) {
		AUTO_ASSIGN = aUTO_ASSIGN;
		valuesSet = true;
	}

	/**
	 * @return the oPEN_TIME
	 */
	public static String getOPEN_TIME() {
		return OPEN_TIME;
	}

	/**
	 * @return the cLOSE_TIME
	 */
	public static String getCLOSE_TIME() {
		return CLOSE_TIME;
	}

	/**
	 * @param cLOSE_TIME the cLOSE_TIME to set
	 * @throws RRSException 
	 */
	public static void setOPEN_CLOSE_TIME(String open_time, String close_time) throws RRSException
	{
		if(ValidationUtils.validateOpenCloseTimes(open_time,close_time))
		{
			OPEN_TIME = open_time;
			CLOSE_TIME = close_time;
		}
		else
		{
			throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_DATE_TIME));
		}
	}

	public void validate() throws RRSException
	{
		ValidationUtils.validateOpenCloseTimes(this.open_time, this.close_time);
		ValidationUtils.validateTableSize(table_1);
		ValidationUtils.validateTableSize(table_2);
		ValidationUtils.validateTableSize(table_4);
		ValidationUtils.validateTableSize(table_6);
		ValidationUtils.validateTableSize(table_8);
	}
	
	
}
