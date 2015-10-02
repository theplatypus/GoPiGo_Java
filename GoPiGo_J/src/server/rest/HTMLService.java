package server.rest;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *		********	HTML SERVICE	********
 *	<br>
 * 	<br>Service REST permettant de récupérer l'interface embarquée par le GoPiGo.
 * 	<br>
 */
@Path("/")
public class HTMLService {
	
	/**
	 * 	<br>Récupère l'interface embarquée du GoPiGo.
	 * 	<br>Cette interface est un fichier html, réalisant des appels à cette API.
	 * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/interface
	 * 	<br>
	 * @return - [HTML] interface embarquée
	 * @throws IOException
	 */
    @GET
    @Path("/interface")
    @Produces(MediaType.TEXT_HTML)
    public Response getInterface() throws IOException {

    		InputStream is = getClass().getClassLoader().getResourceAsStream("client/index.html");
    		
    		return Response.ok(is).build();
    }

    @GET
    @Path("/misc/jquery")
    @Produces("text/javascript")
    public Response getJQuery() throws IOException {

    		InputStream is = getClass().getClassLoader().getResourceAsStream("client/jquery.js");
    		
    		return Response.ok(is).build();
    }
}
