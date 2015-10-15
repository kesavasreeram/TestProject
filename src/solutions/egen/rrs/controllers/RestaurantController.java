/**
 * 
 */
package solutions.egen.rrs.controllers;

import java.util.List;

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
import solutions.egen.rrs.dao.RestaurantDao;
import solutions.egen.rrs.exceptions.RRSException;
import solutions.egen.rrs.model.Restaurant;
import solutions.egen.rrs.utils.ERROR_CODES;
import solutions.egen.rrs.utils.ERROR_MESSSAGES;

/**
 * @author Kesava
 *
 */
@Path("/restaurant")
@Api(tags = {"/restaurant"})
public class RestaurantController
{
	/**
	 * Get all restaurants in the database
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find all restaurants",
			notes = "Find all restaurants in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public List<Restaurant> getAllRestaurants()
	{
		try
		{
			RestaurantDao restDao = new RestaurantDao();
			return restDao.getAllRestaurants();
		}
		catch (RRSException e)
		{
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);		
		}
	}
	
	/**
	 * Get restaurant in the database
	 * based on confirmation Code
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find one restaurant",
			notes = "Find restaurant in the database for a specific id")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Restaurant getRestaurant(@PathParam("id") int id)
	{
		
		try
		{
			RestaurantDao restDao = new RestaurantDao();
			return restDao.getRestaurant(id);
		}
		catch (RRSException e)
		{
			if(ERROR_MESSSAGES.lastKnownError == ERROR_CODES.INVALID_RESTAURANT_ID)
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
	 * Create a new restaurant
	 * Returns restaurant with confirmation number and status
	 */
	@POST
	@Path("/newrestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Create a new restaurant",
			notes = "Create a new restaurant in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Restaurant createRestaurant(Restaurant restaurant)
	{
		try
		{
			//Add the restaurant into database
			RestaurantDao restDao = new RestaurantDao();
			return restDao.createRestaurant(restaurant);
		}
		catch (RRSException e)
		{
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Edit the restaurant by providing a combination of
	 * new date, new time, new party size
	 * @param restaurant
	 * @return restaurant
	 */
	@PUT
	@Path("/editrestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Edit the restaurant",
			notes = "Edit the restaurant in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Restaurant editRestaurant(Restaurant restaurant)
	{
		
		try
		{
			RestaurantDao restDao = new RestaurantDao();
			return restDao.editRestaurant(restaurant);
		}
		catch (RRSException e)
		{
			if(ERROR_MESSSAGES.lastKnownError == ERROR_CODES.INVALID_RESTAURANT_ID)
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
	 * Delete the restaurant in database
	 * @param restaurant
	 */
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Delete the restaurant",
			notes = "Delete the restaurant in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public void deleteRestaurant(@PathParam("id") int id)
	{
		
		try
		{
			RestaurantDao restDao = new RestaurantDao();
			restDao.deleteRestaurant(id);
		}
		catch (RRSException e)
		{
			if(ERROR_MESSSAGES.lastKnownError == ERROR_CODES.INVALID_RESTAURANT_ID)
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
