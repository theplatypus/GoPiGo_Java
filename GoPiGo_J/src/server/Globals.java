package server;

import server.gopigo.GoPiGo;

/**
 * 	Variables globales, étant partagées par plusieurs packages
 *
 */
public class Globals {

	/**
	 * 	Mode debug ou silencieux
	 */
	public static boolean verbose = true ;
	
	/**
	 * 	Interface entre le programme (API) et le matériel (GoPiGo)
	 */
	public static GoPiGo gopigo ;
	
	/**
	 * 	Démon python recevant les requetes par socket, si le programme est configuré en mode SOCKET
	 */
	public static Process pythond ;
	
	/**
	 * 	Paramètres vidéo
	 */
	public static String StreamingWidth = "480" , StreamingHeight = "360", fps = "100", SnapshotEncoding = "bmp" ;
}
