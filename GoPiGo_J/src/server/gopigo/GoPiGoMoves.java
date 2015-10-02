package server.gopigo;

import java.io.IOException;

/**
 *	Ensemble des mouvements réalisables par le GoPiGo.<br>
 *	Une fois appelés, ces fonctions animeront le robot du mouvement décrit<br>
 *	jusqu'à un appel à la fonction {@link #stop()}.<br>
 */
public interface GoPiGoMoves {
	

	/**
	 * Tourne le robot vers la droite.<br>
	 * Une roue active, meilleur controle.<br>
	 * @throws IOException
	 */
	public String turnRight() throws IOException;
	
	/**
	 * 	Tourne le robot vers la gauche.<br>
	 * 	Une roue active, meilleur controle.<br>
	 * @throws IOException
	 */
	public String turnLeft() throws IOException;
	
	/**
	 * Avance tout droit.
	 * @throws IOException
	 */
	public String forward() throws IOException;
	
	/**
	 * Recule.
	 * @throws IOException
	 */
	public String backward() throws IOException;
	
	/**
	 * Rotation sur place anti-horaire.<br>
	 * @throws IOException
	 */
	public String turnLeftRot() throws IOException ;
	
	/**
	 * Rotation sur place horaire.<br>
	 * @throws IOException
	 */
	public String turnRightRot() throws IOException ;
	
	/**
	 * Augmente la vitesse de 10.<br>
	 * @throws IOException
	 */
	public String increaseSpeed() throws IOException ; 
	
	/**
	 * Diminue la vitesse de 10.<br>
	 * @throws IOException
	 */
	public String decreaseSpeed() throws IOException ;
	
	/**
	 * Fixe la vitesse à un seuil donné, entre 0 et 255.<br>
	 * @param speed
	 * @throws IOException
	 */
	public String setSpeed(int speed) throws IOException ;
	
	/**
	 * Stoppe le mouvement courant.<br>
	 * @throws IOException
	 */
	public String stop() throws IOException ;
}
