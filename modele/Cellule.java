package modele;

import java.io.Serializable;

public class Cellule  implements Serializable {

	boolean etat;
	
	
	public Cellule (boolean parEtat){
		etat=parEtat;
	}
	
	/**
	 * La méthode getEtat est un accesseur qui permet de savoir l'état de la cellule, elle renvoie donc le champ etat de la classe Cellule
	 * @return true si la cellule est vivante ou false si elle est morte 
	 */
	public boolean getEtat() {
		
		return etat;
	}
	
	/**
	 * La méthode setEtat est un modifieur qui permet de modifier l'état de la cellule, elle va donc changer le champ etat de la classe Cellule avec le booléen donnée en paramètre de 
	 * la méthode.
	 * @param parEtat un booléen qui correspond au nouvel état de la cellule. 
	 */
	public void setEtat( boolean parEtat ) {
		
		etat=parEtat;
		
	}
}
