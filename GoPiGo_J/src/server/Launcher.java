package server;

import server.gopigo.GoPiGo;

/**
 * 	Lanceur de l'application.<br>
 *	Se charge de traiter les �ventuels arguments, et de cr�er les instances de classes n�c�ssaires.<br>
 */
public class Launcher {

	public static void main(String[] args){
		try{
			Globals.verbose = true ;
			
			GoPiGo gopigo = new GoPiGo("JAVA") ;
			Globals.gopigo = gopigo ;
			@SuppressWarnings("unused")
			HttpServer http = new HttpServer(8080) ;
        
		}catch(Exception e){ e.printStackTrace(); }
	}
}
