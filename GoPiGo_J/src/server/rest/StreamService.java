package server.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import server.Globals;

/**
 *		********	STREAM SERVICE	********
 *	<br>
 * 	Service REST permettant d'utiliser le module caméra du GoPiGo.<br>
 * 	Compte tenu de la puissance limitée du pi, et de la consommation électrique<br>
 * 	relativement élevée d'un tel dispositif, le choix a été fait d'utiliser<br>
 * 	des photos prises à intervalles réguliers au lieu d'un flux vidéo.<br>
 * 	Ainsi, la bande passante est économisée, et le pi n'a pas à faire d'encodage vidéo (exercice ou il est mauvais).<br>
 * <br>
 * 	Les fonctions disponibles permettent de :<br> 
 * 		- démarrer le streaming {@link #start}<br>
 * 		- le stopper {@link #stop}<br>
 * 		- récupérer la frame actuelle {@link #getStream}<br>
 * <br>
 *	Chaque méthode est documentée avec un exemple de cas d'utilisation.<br>
 */
@Path("/video")
public class StreamService {

	/**
	 * 	Récupère la dernière image enregistrée.<br>
	 * 	Attention : Impact important sur la bande passante.<br>
	 * <br>
	 * 		usage:	http://adressePi:8080/video/get<br>
	 * <br>
	 * @return	[BitMap File] - la dernière frame enregistrée
	 * @throws IOException
	 */
    @GET
    @Path("/get")
    @Produces("image/bmp")
    public Response getStream() throws IOException {

    		final String file = "/tmp/stream/pic.bmp";
    		
    		BufferedImage image = ImageIO.read(new File(file));
    		if (image == null ) return getLogo() ;
    	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	    ImageIO.write(image, "bmp", baos);
    	    byte[] imageData = baos.toByteArray();
    	    
    	    System.out.println("[StreamService] : getFrame");
    	    return Response.ok(new ByteArrayInputStream(imageData)).build();
    }
    
    /**
     * Sert une image d'acceuil fixe
     * @return
     * @throws IOException
     */
    @GET
    @Path("/getLogo")
    @Produces("image/bmp")
    public Response getLogo() throws IOException {

    		final String file = "/home/pi/gopigo.bmp";

    	    BufferedImage image = ImageIO.read(new File(file));

    	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	    ImageIO.write(image, "bmp", baos);
    	    byte[] imageData = baos.toByteArray();

    	    System.out.println("[StreamService] : getLogo");
    	    return Response.ok(new ByteArrayInputStream(imageData)).build();
    }
    
    /**
     * 	Démarre le processus d'acquisition vidéo.<br>
     * 	Une fois ce processus activé, la frame rendue par {@link #getStream()} changera à intervalle régulier.<br>
     * 	Cet intervalle est modifiable dans {@link server.rest.SettingsService#setFPS(String)}<br>
     * <br>
     * 	Attention : Impact important sur l'utilisation du CPU<br>
     * 	Bonne pratique : Stopper le streaming lorsqu'il n'est plus utilisé. {@link #stop()}<br>
     * <br>
	 * 		usage:	http://adressePi:8080/video/start<br>
     * <br>
     * @return	[String] -	"Streaming started !"
     * @throws IOException
     * @see server.rest.SettingsService#setFPS(String)
     */
    @GET
    @Path("/start")
    @Produces(MediaType.TEXT_PLAIN)
    public String start() throws IOException {

    	Globals.gopigo.startStreaming();
    	System.out.println("[StreamService] : started.");
    	return "Streaming started !\n" ;
    }
    
    /**
     * 	Stoppe le processus d'acquisition vidéo.<br>
     * 	La dernière image enregistrée reste accessible.<br>
     * <br>
	 * 		usage:	http://adressePi:8080/video/stop<br>
	 * <br>
     * @return
     * @throws IOException
     */
    @GET
    @Path("/stop")
    @Produces(MediaType.TEXT_PLAIN)
    public String stop() throws IOException {

    	Globals.gopigo.stopStreaming();
    	System.out.println("[StreamService] : stopped.");
    	return "Streaming stopped.\n" ;
    }
}