/**
 * 
 */
package solutions.egen.rrs.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * @author Kesava
 * Jersey configuration and url mapping
 */
@ApplicationPath("/api")
public class RouteConfig extends ResourceConfig
{

	/**
	 * Constructor defines where rest of the implementation 
	 * for server calls are located
	 */
	public RouteConfig()
	{
		packages("solutions.egen.rrs.controllers");
		
		//Bean config for swagger		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		
		BeanConfig config = new BeanConfig();
		config.setBasePath("/RRSRestApi/api");
		config.setDescription("Restaurant Reservation System");
		config.setVersion("1.0");
		config.setSchemes(new String [] {"http"});
		config.setResourcePackage("solutions.egen.rrs");
		config.setTitle("Restaurant Reservation System API");
		config.setScan(true);
	}
	
}
