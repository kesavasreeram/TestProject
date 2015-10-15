/**
 * 
 */
package solutions.egen.rrs.providers;

import java.io.IOException;
import java.util.Arrays;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author Kesava
 *
 */
public class CORSFilter implements ContainerResponseFilter
{

	@Override
	public void filter(ContainerRequestContext requestContext, 
			ContainerResponseContext responseContext) throws IOException {
		
		String [] originsAllowed = {"http://localhost:63342",
				"http://run.plnkr.co", "http://petstore.swagger.io"};
		
		String origin = requestContext.getHeaders().getFirst("Origin");
		
		if(Arrays.asList(originsAllowed).contains(origin)) {
			MultivaluedMap<String, Object> headers = responseContext.getHeaders();
			headers.add("Access-Control-Allow-Origin", origin);
			headers.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
			headers.add("Access-Control-Allow-Headers", "Content-Type, Origin");
			headers.add("X-Powered-By", "https://egen.solutions");			
		}
	}
}
