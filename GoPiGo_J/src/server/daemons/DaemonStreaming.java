package server.daemons;

import java.io.IOException;

import server.Globals;

/**
 * 	Processus d'arrière plan se chargeant de l'acquisition vidéo.<br>
 * 	<br>
 * 	La capture vidéo consiste en fait à  réaliser un appel à raspistill, <br>
 * 	pour ainsi prendre une photo à intervalle régulier et économiser les ressources du pi.<br>
 * 	Selon les besoins de l'utilisateur, il est possible de modifier les paramètres de cette acquisition<br>
 * 	en cours d'utilisation, il est par contre impossible de gérer plusieurs paramètre en meme temps,<br>
 * 	dans le cadre de clients multiples.<br>
 * 	<br>
 *	@see server.rest.SettingsService#setResolution(String) 
 *	@see server.rest.SettingsService#setFPS(String)
 */
public class DaemonStreaming{
	
	Process streamingProcess ;
	
	public void run(){
		
		startDaemon() ;
	}
	
	/**
	 * 	Mise en marche du Démon, avec des paramètres variables
	 * <br>
	 * @see server.Globals#fps
	 * @see server.Globals#StreamingHeight
	 * @see server.Globals#StreamingWidth
	 */
	private void startDaemon(){
		
		System.out.println("[StreamingDaemon] Starting...");
		try{
			String[] mkdir = {
			        "mkdir",
			        "/tmp/stream"
			    };
			Runtime.getRuntime().exec(mkdir);
			String[] raspistill = {
			        "raspistill",
			        "--nopreview",
			        "-tl",
			        Globals.fps,
			        "-w",
			        Globals.StreamingWidth,
			        "-h",
			        Globals.StreamingHeight,
			        "-e",
			        "bmp",
			        "-o",
			        "/tmp/stream/pic.bmp",
			        "-t",
			        "9999999",
			    };
			streamingProcess = Runtime.getRuntime().exec(raspistill);
			System.out.println("[StreamingDameon] Running... "+streamingProcess.toString());
		}catch(IOException e){
			System.out.println("[StreamingDaemon] : Failed to start.");
			if (Globals.verbose) e.printStackTrace();
		}
	}
	
	/**
	 * 	Met l'acquisition en pause.<br>
	 * 	Utile pour changer les paramètres, ou tout simplement économiser de l'énergie.<br>
	 * 	La charge CPU descend lorsque l'acquision est sur pause, ne pas hésiter.<br>
	 */
	public void stopDaemon(){
		
		String[] kill = {
		        "killall",
		        "raspistill"
		    };
		try {
			Runtime.getRuntime().exec(kill);
			System.out.println("[StreamingDaemon] : Stopped.");
		} catch (IOException e) {
			System.out.println("[StreamingDaemon] : Failed to stop.");
			e.printStackTrace();
		}
	}
}
