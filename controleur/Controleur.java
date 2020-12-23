package controleur;
import modele.*;
import vue.*;

import javax.swing.Timer;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import outils.*;
import javax.swing.*;

public class Controleur implements ActionListener{

	Damier chDamier;
	PanelJeuDeLaVie panelCentral;
	Timer timer;
	
	public Controleur(Damier parDamier, PanelJeuDeLaVie parPanel){
		timer=new Timer(150,this);
		chDamier=parDamier;
		panelCentral=parPanel;
		panelCentral.enregistreEcouteur(this);
	}
	
	public void actionPerformed(ActionEvent evt){
		
		if(evt.getActionCommand()!=null){
			switch(evt.getActionCommand()){
		
			case "generation":
				
				panelCentral.setNbreGen();
				
				panelCentral.setNbrePop();
				
				chDamier.generation_suivante();
				
				panelCentral.updateUI();

				break;
				
			case "avanceRapide":
				timer.start();
				
				break;
				
			case "pause":	
				timer.stop();
				
				break;
			}
		}
		
		else{
			
			panelCentral.setNbreGen();
			chDamier.generation_suivante();
			panelCentral.setNbrePop();
			panelCentral.updateUI();
		}
	
		
	}
}