package server.gopigo;

import java.io.IOException;

import server.Globals;
import server.daemons.DaemonStreaming;

import com.dexterind.gopigo.Gopigo;
/**
 * 	Interface entre l'API (appels utilisateurs), les démons et le matériel.<br>
 * 	L'instance de cette classe, selon le {@link #mode} actuel,<br>
 * 	va communiquer avec les PIN de la carte GoPiGo.<br>
 * 	<br>
 * 	Dans le cas du mode SOCKET, un packet contenant l'action à effectuer est envoyée<br>
 * 	au DaemonPython {@link #pythond}.<br>
 *  Sinon, un interpréteur est éxécuté avec le code idoine.<br>
 *
 */
public class GoPiGo implements GoPiGoMoves, GoPiGoStreaming{

	private static Gopigo gopigo = Gopigo.getInstance();
	
	private String outputValue ;

	/**
	 *	Modes de communication interne possibles
	 */
	public enum Mode{
		
		JAVA,
		DEBUG ;
	}
	
	/**
	 * Mode de communication interne
	 */
	public Mode mode ;
	
	/**
	 * Processus d'acquisition vidéo
	 */
	private DaemonStreaming stream ;
	
	/**
	 * Constructeur debug
	 * @throws IOException
	 */
	public GoPiGo() throws IOException{

		this.mode = Mode.JAVA ;
		System.out.println("Gopigo started...");
		
		this.stream = new DaemonStreaming() ;

		// gopigo.addListener(this);

    	//gopigo.ultraSonicSensor.setPin(ultrasonicPin);
    	//gopigo.setMinVoltage(5.5);
    	gopigo.init();
		
		System.out.println("GoPiGo started in mode "+mode+"...");
	}
	
	/**
	 * Initialise une instance dans un mode de communication concret
	 * @param mode
	 * @throws IOException
	 */
	public GoPiGo(String mode) throws IOException{

		this.mode = Mode.valueOf(mode);
		this.stream = new DaemonStreaming() ;

		// gopigo.addListener(this);

    	//gopigo.ultraSonicSensor.setPin(ultrasonicPin);
    	//gopigo.setMinVoltage(5.5);
    	gopigo.init();
		
		System.out.println("GoPiGo started in mode "+mode+"...");
	}

	@Override
	public String turnRight() throws IOException {

		outputValue =  Integer.toString(gopigo.motion.right());
		System.out.println("[Gopigo] : Turning right...");
		
		return outputValue ;
	}
	
	@Override
	public String turnRightRot() throws IOException {
		outputValue =  Integer.toString(gopigo.motion.rightWithRotation());
		System.out.println("[Gopigo] : Turning right with rotation...");
		
		return outputValue ;
	}

	@Override
	public String turnLeft() throws IOException {

		outputValue =  Integer.toString(gopigo.motion.left());
		System.out.println("[Gopigo] : Turning left...");
		
		return outputValue ;
	}
	
	@Override
	public String turnLeftRot() throws IOException {

		outputValue =  Integer.toString(gopigo.motion.leftWithRotation());
		System.out.println("[Gopigo] : Turning left with rotation...");
		
		return outputValue ;
	}
	
	@Override
	public String forward() throws IOException {

		outputValue =  Integer.toString(gopigo.motion.forward(false));
		System.out.println("[Gopigo] : Movinf forward...");
		
		return outputValue ;
	}
	
	@Override
	public String backward() throws IOException {

		outputValue =  Integer.toString(gopigo.motion.backward(false));
		System.out.println("[Gopigo] : Moving backward...");
		
		return outputValue ;
	}

	@Override
	public String stop() throws IOException {

		outputValue =  Integer.toString(gopigo.motion.stop());
		System.out.println("[Gopigo] : Stopped !");
		
		return outputValue ;
	}
	
	@Override
	public String increaseSpeed() throws IOException {
		
		outputValue =  Integer.toString(gopigo.motion.increaseSpeed());
		System.out.println("[Gopigo] : Speed ++ !");	
		
		return outputValue ;
	}

	@Override
	public String decreaseSpeed() throws IOException {
		
		outputValue =  Integer.toString(gopigo.motion.decreaseSpeed());
		System.out.println("[Gopigo] : Speed -- !");	
		
		return outputValue ;
	}

	@Override
	public String setSpeed(int speed) throws IOException {

		outputValue =  Integer.toString(gopigo.motion.setSpeed(speed));
		System.out.println("[Gopigo] : Set speed to "+speed+" !");		
		return outputValue ;
	}

	@Override
	public void startStreaming() {

		stream.run();
		System.out.println("[Gopigo] : Start Streaming...");
	}

	@Override
	public void stopStreaming() throws IOException {
		
		stream.stopDaemon();
		System.out.println("[Gopigo] : Streaming stopped !");
	}

	@Override
	public void setResolution(int h, int w) {
		
		stream.stopDaemon();
		Globals.StreamingHeight = String.valueOf(h) ;
		Globals.StreamingWidth = String.valueOf(w) ;
		stream = new DaemonStreaming() ;
		startStreaming() ;
	}

	@Override
	public void setFPS(int fps) {

		stream.stopDaemon();
		Globals.fps = String.valueOf(fps) ;
		stream = new DaemonStreaming() ;
		startStreaming() ;
	}
}
