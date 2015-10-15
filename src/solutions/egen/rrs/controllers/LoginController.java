/**
 * 
 */
package solutions.egen.rrs.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import solutions.egen.rrs.dao.OwnerDao;
import solutions.egen.rrs.exceptions.RRSException;
import solutions.egen.rrs.model.Owner;
import solutions.egen.rrs.utils.ERROR_CODES;
import solutions.egen.rrs.utils.ERROR_MESSSAGES;

/**
 * @author kesava
 *
 */
@Path("/login")
@Api(tags = {"/login"})
public class LoginController
{
	/**
	 * Get all reservations in the database
	 */
	@GET
	@Path("/user{email}&password{pass}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Check login",
			notes = "Checks if username and password matches")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public boolean checkLogin(@PathParam("email") String email,
								@PathParam("pass") String pass)
	{
		try
		{
			OwnerDao oDao = new OwnerDao();
			return oDao.checkLogin(email,pass);
		}
		catch (RRSException e)
		{
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * Create a new owner
	 * Returns owner
	 */
	@POST
	@Path("/addowner")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Create a new owner",
			notes = "Create a new owner in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Owner addOwner(Owner owner)
	{
		try
		{
			OwnerDao oDao = new OwnerDao();
			return oDao.addOwner(owner);
		}
		catch (RRSException e)
		{
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Change owner password in database
	 */
	@PUT
	@Path("/editowner")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Change owner password",
			notes = "Change owner password in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Owner editOwner(Owner owner)
	{
		try
		{
			OwnerDao oDao = new OwnerDao();
			return oDao.editOwner(owner);
		}
		catch (RRSException e)
		{
			if(ERROR_MESSSAGES.lastKnownError == ERROR_CODES.INVALID_OWNER)
			{
				throw new WebApplicationException(e.getMessage(), Status.NOT_FOUND);
			}
			else
			{
				throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	
	/**
	 * Delete owner in database
	 * @param email
	 */
	@DELETE
	@Path("/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Delete owner",
			notes = "Delete owner in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public void deleteReservation(@PathParam("email") String email)
	{
		try
		{
			OwnerDao oDao = new OwnerDao();
			oDao.deleteOwner(email);
		}
		catch (RRSException e)
		{
			if(ERROR_MESSSAGES.lastKnownError == ERROR_CODES.INVALID_OWNER)
			{
				throw new WebApplicationException(e.getMessage(), Status.NOT_FOUND);
			}
			else
			{
				throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
