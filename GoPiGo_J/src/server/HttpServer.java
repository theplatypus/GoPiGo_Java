package server;
 
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
 
import com.sun.jersey.spi.container.servlet.ServletContainer;

/**
 * 	<br> HTTP SERVER
 *	<br>
 *	Servlet dont la racine se situe à la racine du jar.<br>
 *	Charge les services REST lorsqu'ils sont appelés une fois.<br>
 *	Par défaut, écoute sur le port 8080.<br>
 */
public class HttpServer {
 
	/**
	 * Constructeur du Servlet
	 * @param port - port à écouter
	 * @throws Exception - erreur inconnue
	 */
     public HttpServer( int port ) throws Exception {
         ServletHolder sh = new ServletHolder(ServletContainer.class);    
         sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
         sh.setInitParameter("com.sun.jersey.config.property.packages", "server.rest");
         sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
       
         Server server = new Server(port);
         ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
         context.addServlet(sh, "/*");
         server.start();
         System.out.println("[HTTP Server] : Ready.");
         server.join();      
      }
}
