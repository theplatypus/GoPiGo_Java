package server.gopigo;

import java.io.IOException;

/**
 * 	Ensemble des fonctions utiles pour controler le streaming vidéo.<br>
 * 	Ce processus de streaming génère des bitmaps (pas d'encodage donc plus rapide) d'une dimension<br>
 * 	donnée et à intervalle régulier.
 *
 */
public interface GoPiGoStreaming {

	/**
	 * Démarre le processus d'acquisition.
	 * @throws IOException
	 */
	public void startStreaming() throws IOException;
	
	/**
	 * Met le processus d'acquisition en pause.
	 * @throws IOException
	 */
	public void stopStreaming() throws IOException;
		
	/**
	 * Modifie la résolution des images générées
	 * @param h - hauteur en pixels
	 * @param w	- largeur en pixels
	 */
	public void setResolution(int h, int w) ;
	
	/**
	 * Modifie la fréquence à laquelle les images sont générées
	 * @param fps - nombre d'images générées en une seconde
	 */
	public void setFPS(int fps) ;
}
