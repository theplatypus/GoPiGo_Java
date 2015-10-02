package server.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import server.Globals;
import server.gopigo.GoPiGo;
import server.gopigo.GoPiGo.Mode;

/**
 *		********	Settings SERVICE	********
 *	<br>
 * 	<br>Service REST permettant de modifier quelques paramètres du GoPiGo
 * 	<br>
 *	<br>Chaque méthode est documentée avec un exemple de cas d'utilisation.
 */
@Path("/settings")
public class SettingsService {

	/**
	 * 	<br>Change le mode de communication.
	 * 	<br>Il est possible de communiquer soit par Socket avec un daemon Python,
	 * 	<br>soit de lancer un interpréteur à chaque nouvelle commande.
	 * 	<br>
	 * 	<br>Selon si le mouvement change souvent ou pas, l'un ou l'autre sera moins énergivore...
	 * 	<br>Si le robot doit souvent changer de direction, opter pour la communication par Socket.
	 * 	<br>
	 * 	<br>Cette fonction inverse le mode de communication, et renvoie la valeur actuelle.
	 * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/mode/toggle
	 * 	<br>
	 *  <br> NOTE : N'existe plus dans cette version du programme
	 *  
	 * @return [ 410 GONE  - "No longer available"]
	 * @throws IOException
	 */
    @GET
    @Path("/mode/toggle")
    @Produces(MediaType.TEXT_PLAIN)
    public Response toggleMode() throws IOException {
    	
    	ResponseBuilder rb = Response.ok("No longer available");
    	rb.status(Status.GONE) ;
    	return rb.build() ; 
    }
    
    /**
     * 	<br>Change la résolution des images (snapshots) générés par le GoPiGo.
     * 	<br>Le module caméra équipé permet de filmer à une résolution FullHD, c'est à dire 1920 * 1080 pixels.
     * 	<br>Ceci dit, le processeur ARM présent est très peu performant pour l'encodage.
     * <br>
     * 	<br>Il s'agira donc de faire un compromis entre la résolution et les fps (frames per second), fréquence de rafraichissement.
     * 	<br>Pour régler les fps, voir la fonction {@link #setFPS}
     * <br>
     * 	<br>Valeurs possibles : <br>
     * 	<ul>
     * 	<li>	- FULLHD 	| 	FHD 	| 	1080 	| 	1080p </li>
     * 	<li>	- HD 		|	720		| 	720p  </li>
     * 	<li>	- 480		| 	480p  </li>
     * 	<li>	- 360		|	360p  </li>
     * 	<li>	- 240		|	240p  </li>
     * 	</ul>
     * 	<br>Note : (insensible à la casse)
     *  <br>Il peut être nécéssaire de redémarrer le streaming après un appel à cette fonction.	{@link StreamService.start}}
     *  <br>
     *  <br>	usage:	http://adressePi:8080/settings/video/resolution/480p
     *  <br>
     *  <br>Valeurs conseillées : 360p@10fps
     *  <br>
     * @param resolution
     * @return 	[ 200 - "Resolution set to $newRes" ]
     * @throws IOException
     */
    @GET
    @Path("/video/resolution/{mode}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response setResolution( @PathParam("mode") String resolution) throws IOException {
    	
    	int height = 240, width = 320 ; // default
    	resolution = resolution.toLowerCase() ;
    	
    	if(resolution.equals("FULLHD") || resolution.equals("FHD") || resolution.equals("1080") || resolution.equals("1080p")){
    		height = 1080 ;
    		width = 1920 ;
    	}else if(resolution.equals("HD") || resolution.equals("720") || resolution.equals("720p")){
    		height = 720 ;
    		width = 1280 ;
    	}else if(resolution.equals("480p") || resolution.equals("480")){
    		height = 480 ;
    		width = 640 ;
    	}else if(resolution.equals("360p") || resolution.equals("360")){
    		height = 360 ;
    		width = 480 ;
    	}else if(resolution.equals("240p") || resolution.equals("240")){
    		height = 240 ;
    		width = 320 ;
    	}
    	Globals.gopigo.setResolution(height, width);
    	
    	return Response.ok("Resolution set to "+height).build() ;
    }
    
    /**
     * 	Change la fréquence de rafraichissement des images (snapshots) générés par le GoPiGo.<br>
     * 	Le module caméra équipé permet de filmer jusqu'à 30 fps.<br>
     * 	Néanmoins, chaque image générée donne beaucoup de travail d'encodage au processeur.<br>
     * 	Le nombre d'images par secondes (et donc la fluidité) se fait donc au détriment de la latence.<br>
     * <br>
     * 	Il s'agira donc de faire un compromis entre la résolution et les fps (frames per second), fréquence de rafraichissement.<br>
     * 	Pour régler la resoltuion, voir la fonction {@link #setResolution}<br>
     * <br>
     * 	Valeurs possibles : 0 < n < 30<br>
     * <br>
     *  Il peut être nécéssaire de redémarrer le streaming après un appel à cette fonction.	{@link StreamService.start}}<br>
     *  <br>
     *  	usage:	http://adressePi:8080/settings/video/fps/8<br>
     *  <br>
     *  Valeurs conseillées : 360p@10fps<br> 
     *  <br>
     * @param resolution
     * @return 	[String] - "FPS set to $newFPS"
     * @throws IOException
     */
    @GET
    @Path("/video/fps/{fps}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response setFPS( @PathParam("fps") String fps) throws IOException {
    	
    	ResponseBuilder rb ;
    	try{
    		
    	int FPS = 1000 / Math.round(Integer.parseInt(fps)) ;
    	Globals.gopigo.setFPS(FPS);
    	rb = Response.ok("FPS set to "+fps) ;
    	}catch(Exception e){
    		rb = Response.ok("Bad value") ;
    		rb.status(Status.BAD_REQUEST) ;
    		return rb.build() ;
    	}
    	
    	return rb.build() ;
    }
}
