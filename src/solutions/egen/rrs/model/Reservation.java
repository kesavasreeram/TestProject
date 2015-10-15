/**
 * 
 */
package solutions.egen.rrs.model;

import solutions.egen.rrs.exceptions.RRSException;
import solutions.egen.rrs.utils.ValidationUtils;

/**
 * @author Kesava
 *
 */
public class Reservation
{
	// Details provided by customer
	private String customerEmail = "";
	private String first_name = "";
	private String last_name = "";
	private String phone = "";
	private String datetime = "";
	private int partySize = 0;
	private int rest_id = 1;
	
	//Details created in server
	private int confNo = -1;
	private int status = 0;
	private int tableID = -1;
	
	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}


	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}



	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}


	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}


	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	 * @return the confNo
	 */
	public int getConfNo() {
		return confNo;
	}



	/**
	 * @param confNo the confNo to set
	 */
	public void setConfNo(int confNo) {
		this.confNo = confNo;
	}

	/**
	 * @return the time
	 */
	public String getDatetime() {
		return datetime;
	}

	/**
	 * @param time the time to set
	 * @throws RRSException 
	 */
	public void setDatetime(String time) throws RRSException
	{
		ValidationUtils.validateReservationtime(time);
		this.datetime = time;
	}



	/**
	 * @return the partySize
	 */
	public int getPartySize() {
		return partySize;
	}



	/**
	 * @param partySize the partySize to set
	 * @throws RRSException 
	 */
	public void setPartySize(int partySize) throws RRSException {
		ValidationUtils.validatePartySize(partySize);
		this.partySize = partySize;
	}



	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}



	/**
	 * @return the tableID
	 */
	public int getTableID() {
		return tableID;
	}



	/**
	 * @param tableID the tableID to set
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}


	public int getRest_id() {
		return rest_id;
	}


	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
		
		//TODO : hardcoded 
		this.rest_id = 1;

	}


	/**
	 * Validates if the object has valid date time values
	 * and party size
	 * @throws RRSException 
	 */
	public void validate() throws RRSException
	{
		ValidationUtils.validateReservationtime(datetime);
		ValidationUtils.validatePartySize(partySize);
	}
	
	
	
}
