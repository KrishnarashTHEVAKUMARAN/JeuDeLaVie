package modele;

import java.io.Serializable;

public class Cellule  implements Serializable {

	boolean etat;
	
	
	public Cellule (boolean parEtat){
		etat=parEtat;
	}
	
	/**
	 * La m�thode getEtat est un accesseur qui permet de savoir l'�tat de la cellule, elle renvoie donc le champ etat de la classe Cellule
	 * @return true si la cellule est vivante ou false si elle est morte 
	 */
	public boolean getEtat() {
		
		return etat;
	}
	
	/**
	 * La m�thode setEtat est un modifieur qui permet de modifier l'�tat de la cellule, elle va donc changer le champ etat de la classe Cellule avec le bool�en donn�e en param�tre de 
	 * la m�thode.
	 * @param parEtat un bool�en qui correspond au nouvel �tat de la cellule. 
	 */
	public void setEtat( boolean parEtat ) {
		
		etat=parEtat;
		
	}
}
