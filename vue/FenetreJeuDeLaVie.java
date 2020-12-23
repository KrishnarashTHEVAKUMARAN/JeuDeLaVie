package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreJeuDeLaVie extends JFrame implements ActionListener{
	// Champs pour le menu
	JMenuBar menuBar = new JMenuBar();

	private JMenu quitter = new JMenu("Quitter");
	private JMenuItem confirmation = new JMenuItem("Je veux quitter");
	private JMenuItem annulation = new JMenuItem("Je ne veux pas quitter");
	
	public FenetreJeuDeLaVie (String parTitre){
		super (parTitre);
		PanelJeuDeLaVie contentPane = new PanelJeuDeLaVie(); 
		setContentPane(contentPane);
		contentPane.setBackground(new Color(255,255,255));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,800);
		setVisible(true);
		setLocation(200,100);
		
		// ajout pour les menus
			this.setJMenuBar(menuBar);
			
			for (String nomItem : Constantes.MENU){
				if(nomItem!=Constantes.MENU[1]){
				JMenuItem menu = new JMenuItem(nomItem);
				menu.addActionListener(contentPane);
				menu.setActionCommand(nomItem);
				menuBar.add(menu);
				}
			}
			
			JMenu damierExemples = new JMenu(Constantes.MENU[1]);
				for(int i=0;i<3;i++){
					JMenuItem menuItem = new JMenuItem(Constantes.MENU_ITEM[i]);
					menuItem.addActionListener(contentPane);
					menuItem.setActionCommand(Constantes.MENU_ITEM[i]);
					damierExemples.add(menuItem);
					}
				
		menuBar.add(damierExemples);
			
			
		// ajout pour les menus
		this.quitter.add(annulation);
		this.quitter.addSeparator();
		confirmation.addActionListener(contentPane);
		confirmation.setActionCommand("confirmation");
		
		this.quitter.add(confirmation);   
		this.menuBar.add(Box.createHorizontalGlue());
		this.menuBar.add(quitter);
		

	}
	
	public static void main (String [] args){
		new FenetreJeuDeLaVie ("Jeu De La Vie");
	} //main()

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
		

