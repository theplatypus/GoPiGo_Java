package server.daemons;

import java.io.IOException;

import server.Globals;

/**
 * 	Processus de type Démon, faisant tourner en tache de fond un serveur python sur un socket.<br>
 * 	Ce Démon est utilisé dans le cas ou la communication interne du programme (entre l'API et le matériel)<br>
 * 	se fait par socket plutot que par appels à des macros python.<br>
 * 	Ce mode est préférable si les mouvements sont courts et fréquents.<br>
 * 	Il est possible de changer le mode de communication en couirs d'utilisation, en invoquant le service adéquat.<br>
 * 
 * 	@see server.rest.SettingsService#toggleMode()
 *
 */
public class DaemonPython extends Thread{
	

	/**
	 * 	Execution du démon lors de son appel.
	 */
	private void startDameon(){
		
		System.out.println("[PythonDameon] Starting...");
		try {
			String[] cmd = {
			        "python",
			        "/home/pi/scripts/server.py"
			    };
			Process p = Runtime.getRuntime().exec(cmd);
			System.out.println("[PythonDameon] Running... "+p.toString());
			Globals.pythond = p ;
		} catch (IOException e) {
			System.out.println("[PythonDameon] Fichier server.py non trouvé");
			try {
				String[] cmd = {
				        "python",
				        "/home/pi/scripts/server.py"
				    };
				Process p = Runtime.getRuntime().exec(cmd);
				System.out.println("[PythonDameon] Running /home/pi/scripts/server.py... "+p.toString());
				
			} catch (IOException e1) {
				System.out.println("[PythonDameon] Scripts par defaut absents...");
				if (Globals.verbose) e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 	Appel du Thread
	 * @Override java.lang.Thread#run()
	 */
	public void run(){
		this.startDameon();
	}
}
