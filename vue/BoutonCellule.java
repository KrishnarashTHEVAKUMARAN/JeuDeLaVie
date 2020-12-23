package vue;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Cellule;

public class BoutonCellule extends JButton implements ActionListener{

	private Cellule cellule;
	
	public BoutonCellule(Cellule parCellule){
		super();
		cellule=parCellule;
		this.addActionListener((ActionListener) this);

	}
	
	/**
	 * La m�thode getEtatCellule est un accesseur qui appelle l'acccesseur de la classe Cellule getEtat, ainsi cette m�thode nous permet de renvoyer 
	 * l'�tat de la Cellule contenue dans le bouton.
	 * @return bool�en correspond � l'�tat de la cellule
	 */
	public boolean getEtatCellule(){
		return cellule.getEtat();
	}
	
	/**
	 * La m�thode setEtatCellule est un modifieur qui permet la modification de l'�tat de la cellule donc en 
	 * appelant la m�thode setEtat de la classe cellule.
	 * @param parEtat
	 */
	public void setEtatCellule(boolean parEtat){
		cellule.setEtat(parEtat);
	}
	
	public void actionPerformed(ActionEvent parEvt){

		if(this.getEtatCellule()==true){
			
			this.setBackground(Color.white);
			this.setEtatCellule(false);
		}
		
		else{
			
			this.setBackground(Color.green);
			this.setEtatCellule(true);
		}
	}

}
