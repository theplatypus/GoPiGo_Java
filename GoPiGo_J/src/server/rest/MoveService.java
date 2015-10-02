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

/**
 *		********	MOVE SERVICE	********
 *	<br>
 * 	<br>Service REST permettant de contrôler le robot.
 * 	<br>Les fonctions disponibles permettent de : 
 * <ul>
 * 	<li>	- lui donner un mouvement {@link #move} </li>
 * 	<li>	- le stopper {@link #stop}</li>
 * 	<li>	- modifier sa vitesse {@link #setSpeed}</li>
 * </ul>
 * 	<br>
 *	<br>Chaque méthode est documentée avec un exemple de cas d'utilisation.
 */
@Path("/gopigo")
public class MoveService {

	/**
	 * 	<br>Fait éxécuter au GoPiGo le mouvement indiqué.
	 * 	<br>Le mouvement continuera jusqu'à un appel à la fonction {@link #stop}.
	 * 	<br>Les valeurs possibles de direction sont : <br>
	 * 	<ul>
	 * 	<li>	- FORWARD</li>
	 * 	<li>	- BACKWARD</li>
	 * 	<li>	- RIGHT</li>
	 * 	<li>	- LEFT</li>
	 * 	<li>	- ROTR</li>
	 * 	<li>	- ROTL</li>
	 * 	<li>	- STOP (préférer {@link #stop} )</li>
	 * 	</ul>
	 * 	<br>Toute autre direction donnée ne produira aucun effet.
	 * 	<br>
	 * 	<br>Attention : le robot commencera le mouvmeent instantanément. Vérifier son emplacement au préalable !
	 * 	<br>
	 * 	<br>usage:	http://adressePi:8080/gopigo/move/right
	 * 
	 * @param direction	- [String] direction 
	 * @return	Response : [200 - $statusCode] | [400 - "invalid"]
	 * @throws IOException
	 */
    @GET
    @Path("/move/{direction}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response move( @PathParam("direction") String direction ) throws IOException {

    	direction = direction.toUpperCase() ;	// ignoreCase
    	String result = "invalid" ;
		try
		{
			if(direction.equals("RIGHT"))		// Java 6 compliance : pas de switch...
			{
				result = Globals.gopigo.turnRight();
			}
			else if(direction.equals("LEFT"))
			{
				result = Globals.gopigo.turnLeft();
			}
			else if(direction.equals("FORWARD")) 
			{
				result = Globals.gopigo.forward();
			}
			else if(direction.equals("BACKWARD"))
			{
				result = Globals.gopigo.backward();
			}
			else if(direction.equals("ROTR"))
			{
				result = Globals.gopigo.turnRightRot();;
			}
			else if(direction.equals("ROTL"))
			{
				result = Globals.gopigo.turnLeftRot();
			}
			else if(direction.equals("STOP"))
			{
				result = Globals.gopigo.stop();
			}
			else{
				ResponseBuilder rb = Response.ok(result) ;
				rb.status(Status.BAD_REQUEST) ;
				return rb.build();
			}
			return Response.ok("Moving "+direction+" : "+result).build();
		}
		catch
		(NullPointerException npe){}
    	return Response.ok(result).build() ;
    }
    
    /**
     * 	<br>Stoppe le robot si celui-ci est en mouvement.
     * 	<br>Réflexe à developper en environnement hostile...
     * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/gopigo/stop
     * 	<br>
     * @return [200 - "Stopped"]
     * @throws IOException
     */
    @GET
    @Path("/stop")
    @Produces(MediaType.TEXT_PLAIN)
    public Response stop() throws IOException {

    	String ret = Globals.gopigo.stop();
    	return Response.ok("Stopped : " + ret).build() ;
    }
    
    @GET
    @Path("/off")
    @Produces(MediaType.TEXT_PLAIN)
    public Response off() throws IOException {

    	Runtime.getRuntime().exec("sudo shutdown -r now");
    	return Response.ok("Power OFF").build() ;
    }
    
    /**
     * 	<br>Fixe la vitesse du GoPiGo à une valeur donnée.
     * 	<br>Si la valeur n'est pas un entier, ne fait rien et le signale en retour.
     * 	<br>Valeur minimale : 0
     * 	<br>Valeur maximale : 255
     * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/gopigo/speed/set/85
     * 	<br>
     * @param speed - [String] Valeur entière et positive
     * @return	[ 200 - "Set speed to $speed" ] | [ 400 - "!range 0 -> 255 ]
     * @throws IOException
     */
    @GET
    @Path("/speed/set/{speed}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response setSpeed(@PathParam("speed") String speed) throws IOException {

    	int sp = Integer.parseInt(speed) ;
    	String ret = "" ;
    	ResponseBuilder rb ;
    	if (sp > 0 && sp <= 255){
    		
    		ret = Globals.gopigo.setSpeed(sp) ;
    		rb = Response.ok("Set speed to "+speed+" : "+ret) ;
    	}else{
        	ret = "! range 0 -> 255" ; 
        	rb = Response.ok(ret) ;
        	rb.status(Status.BAD_REQUEST) ;
    	}
    	
    	return rb.build() ;
    }
    
    /**
     * <br>	Augmente la vitesse du GoPiGo.
     * <br>
	 * 	<br>	usage:	http://adressePi:8080/gopigo/speed/increase
     * <br>
     * @return	[ 200 - "Speed ++" ]
     * @throws IOException
     */
    @GET
    @Path("/speed/increase")
    @Produces(MediaType.TEXT_PLAIN)
    public Response speedIncrease() throws IOException {

    	String ret = Globals.gopigo.increaseSpeed();
    	return Response.ok("Speed ++ : "+ret).build() ;
    }
    
    /**
     * 	<br>Diminue la vitesse du GiPiGo.
     * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/gopigo/speed/decrease
     * 	<br>
     * @return	[ 200 - "Speed --" ]
     * @throws IOException
     */
    @GET
    @Path("/speed/decrease")
    @Produces(MediaType.TEXT_PLAIN)
    public Response speedDecrease() throws IOException {

    	String ret = Globals.gopigo.decreaseSpeed();
    	return Response.ok("Speed -- : "+ret).build() ;
    }
    
    
}
