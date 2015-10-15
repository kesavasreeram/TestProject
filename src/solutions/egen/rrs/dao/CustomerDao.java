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
import solutions.egen.rrs.model.Customer;
import solutions.egen.rrs.model.Reservation;
import solutions.egen.rrs.utils.DBUtil;
import solutions.egen.rrs.utils.ERROR_CODES;
import solutions.egen.rrs.utils.ERROR_MESSSAGES;

/**
 * @author Kesava
 *
 */
public class CustomerDao
{
	/**
	 * Check if customer is already present in the database
	 * if he is not present in the database, add him to database
	 * @param reservation
	 * @throws RRSException 
	 */
	public void addCustomer(Reservation reservation) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		String customerEmail = reservation.getCustomerEmail();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean customerFound = false;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("SELECT * FROM customer_details WHERE email = ?");
			ps.setString(1, customerEmail);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				// Customer is already present so we dont have to do anything
				customerFound = true;
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
		
		if(!customerFound)
		{
			//Create new customer and add to database
			createNewCustomer(reservation);
		}
	}

	private void createNewCustomer(Reservation reservation) throws RRSException
	{
		String customerEmail = reservation.getCustomerEmail();
		String first_name = reservation.getFirst_name();
		String last_name = reservation.getLast_name();
		String phone = reservation.getPhone();
		
		Customer customer =  new Customer();
		customer.setEmail(customerEmail);
		customer.setFirstName(first_name);
		customer.setLastName(last_name);
		customer.setPhone(phone);
		
		//create customer in database
		createCustomer(customer);
	}

	/**
	 * Get all customer from database
	 * @return
	 * @throws RRSException 
	 */
	public List<Customer> getAllCustomers() throws RRSException
	{
		List<Customer> result = null;
		Connection con = DBUtil.getConnection();
		Customer customer = null;
		String customerEmail = "";
		String first_name = "";
		String last_name = "";
		String phone = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			result = new ArrayList<Customer>();
			ps = con.prepareStatement("Select * from customer_details");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				first_name = rs.getString("first_name");
				last_name = rs.getString("last_name");
				phone = rs.getString("phone");
				customerEmail = rs.getString("email");

				customer =  new Customer();
				customer.setEmail(customerEmail);
				customer.setFirstName(first_name);
				customer.setLastName(last_name);
				customer.setPhone(phone);
				
				result.add(customer);
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
	 * Find customer from database based on email
	 * @param email
	 * @return
	 * @throws RRSException 
	 */
	public Customer getCustomer(String email) throws RRSException
	{
		Customer result = null;
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String first_name = "";
		String last_name = "";
		String phone = "";
		try
		{
			
			ps = con.prepareStatement("Select * from customer_details where email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				first_name = rs.getString("first_name");
				last_name = rs.getString("last_name");
				phone = rs.getString("phone");


				result =  new Customer();
				result.setEmail(email);
				result.setFirstName(first_name);
				result.setLastName(last_name);
				result.setPhone(phone);
			}
			else
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(
						ERROR_CODES.INVALID_CUST_EMAIL));
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
	 * Create a new customer
	 * @param customer
	 * @throws RRSException 
	 */
	public Customer createCustomer(Customer customer) throws RRSException
	{		
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("INSERT INTO customer_details "
					+ "(first_name, last_name, email, phone) "
					+ "VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPhone());
			ps.execute();
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
		
		return customer;
	}

	/**
	 * Edit the customer in database
	 * @param customer
	 * @return
	 * @throws RRSException 
	 */
	public Customer editCustomer(Customer customer) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("UPDATE customer_details SET first_name = ?,"
					+ "last_name = ?, phone = ? WHERE email = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getPhone());
			ps.setString(4, customer.getEmail());
			int status = ps.executeUpdate();
			if(status != 1)
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(
						ERROR_CODES.INVALID_CUST_EMAIL));
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
		
		return customer;
	}

	/**
	 * Delete a customer from database
	 * @param customer
	 * @throws RRSException 
	 */
	public void deleteCustomer(String email) throws RRSException
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("DELETE FROM customer_details WHERE "
					+ "email = ?");
			ps.setString(1, email);
			int status = ps.executeUpdate();
			if(status != 1)
			{
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(
						ERROR_CODES.INVALID_CUST_EMAIL));
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
