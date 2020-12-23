package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import controleur.*;

import javax.swing.*;

import outils.LectureEcriture;
import modele.Cellule;

public class PanelJeuDeLaVie extends JPanel implements ActionListener{
	JPanel haut = new JPanel();
	
	int compteurGen = 0 ;
	int compteurPop = 0 ;
	JLabel generation = new JLabel ("  Génération :  " +compteurGen);
	JLabel population = new JLabel ("   Population :  " +compteurPop);
	
	JButton generationSuivante = new JButton("Génération Suivante");
	JButton avanceRapide = new JButton("Avance rapide");
	JButton pause = new JButton("Pause");
	JLabel nomDamier = new JLabel("    Nom du damier");
	JTextField textDamier = new JTextField(10);
	JButton enregistrer = new JButton("Enregistrer");
	public JPanel bas;
	public JPanel milieu;
	Damier centre;

	int i;
	
	int j;
	
	File fichierAgenda = new File ("damiers" + File.separator + "damiers_2019.ser");
	
	public PanelJeuDeLaVie() {
		this.setLayout(new BorderLayout());
		
		// Le haut
		haut.setLayout(new FlowLayout(FlowLayout.CENTER,6,6));
		generation.setFont(new Font("Times",Font.BOLD,12));
		population.setFont(new Font("Times",Font.BOLD,12));
		
		haut.add(generation);
		haut.add(population);
		this.add(haut,BorderLayout.NORTH);

		// Le centre
		centre =new Damier();
		this.add(centre,BorderLayout.CENTER);
	
		
		// Le bas
		JPanel bas = new JPanel();
		bas.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		generationSuivante.setActionCommand("generation");
		
		generationSuivante.setContentAreaFilled(false);
		generationSuivante.setBorderPainted(false);
		
		avanceRapide.setActionCommand("avanceRapide");
		
		avanceRapide.setContentAreaFilled(false);
		avanceRapide.setBorderPainted(false);
		
		pause.setActionCommand("pause");
		
		pause.setContentAreaFilled(false);
		pause.setBorderPainted(false);
		
		enregistrer.setActionCommand("enregistrer");
		enregistrer.addActionListener(this);
		enregistrer.setContentAreaFilled(false);
		enregistrer.setBorderPainted(false);
		
		bas.add(generationSuivante);
		bas.add(avanceRapide);
		bas.add(pause);
		bas.add(nomDamier);
		bas.add(textDamier);
		bas.add(enregistrer);
		this.add(bas,BorderLayout.SOUTH);
		
		///Le controleur
		new Controleur(centre,this);
		
	}



	
	public void enregistreEcouteur(Controleur parControleur) {
		generationSuivante.addActionListener(parControleur);
		avanceRapide.addActionListener(parControleur);
		pause.addActionListener(parControleur);
	}
	
	
	public int getNbreGen() {
		return compteurGen;
	}

	public void setNbreGen() {
		compteurGen++;
		generation.setText("  Génération :  " +compteurGen);
	}
	
	public void setNbrePop(){
		compteurPop = centre.getNbreCelluleVivante();
		population.setText(" Population : "+compteurPop);
	}
	
	
	public void actionPerformed (ActionEvent parEvt){
		
		if(parEvt.getActionCommand().equals("confirmation")){
			int saisi=0;
			saisi=JOptionPane.showConfirmDialog(this, "Etes-vous sûr de votre choix ?","Dialogue de confirmation",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			switch (saisi){
				case JOptionPane.OK_OPTION:
					System.exit(1);
					break;
				case JOptionPane.CANCEL_OPTION:
					break;
			}
		}
		
		if(parEvt.getActionCommand().equals(Constantes.MENU[2])){
			centre.effacerDamier();
			}
		
		if(parEvt.getActionCommand().equals("enregistrer")){
			LectureEcriture.ecriture(fichierAgenda,centre);
			
		}
	}
	
}
