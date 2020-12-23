package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import modele.Cellule;
import vue.BoutonCellule;

public class Damier extends JPanel{

	public BoutonCellule damierDuJeu[][];
	int x;
	int y;
	
	public Damier() {
		///Pour obtenir un nombre al�atoire
		x=(int)1+(int)(Math.random()*99);
		y=x;
		damierDuJeu =new BoutonCellule[x][y];
		this.setLayout(new GridLayout(x,y));
		
		for (int i=0;i<x;i++){
			for (int j=0;j<y;j++){
				int a = (int)1+(int)(Math.random()*10);
				if(a%2==0){
					damierDuJeu[i][j]=new BoutonCellule(new Cellule(true));
					damierDuJeu[i][j].setBackground(Color.green);
						}
				else{
					damierDuJeu[i][j]=new BoutonCellule(new Cellule(false));
					damierDuJeu[i][j].setBackground(Color.white);
						}

				
				
				this.add(damierDuJeu[i][j]);
			}
		}
		
	}
	


	/**
	 * La m�thode est_vivante nous permet de d�terminer selon les r�gles du jeu si une cellule sera en vie ou pas �
	 * la g�n�ration suivante. Si elle a deux ou 3 cellules voisines vivantes alors elle sera en vie, sinon elle meurt.
	 * La m�thode renvoie un bool�en, true si la cellule sera vivante, false si la cellule sera morte � la g�n�ration suivante.
	 * La m�thode va regarder chaque voisin de la cellule aux coordonn�es suivantes:a+1/b, a+1/b+1, a/b+1, a-1/b+1, a-1/b, a-1/b-1, 
	 * a/b-1, a+1/b-1 avec a correspondant � la ligne et b correspondant � la colonne. Elle instancie un compteur qui va compter les cellules voisines vivantes.
	 * Ensuite elle regardera le nombre de voisines vivantes et d�terminera si la cellule sera en vie ou non
	 * @param int a, la coordonn�e correspondant aux lignes
	 * @param int b, la coordonn�e correspondant aux colonnes
	 * @return boolean true ou false correspondant � l'�tat de la cellule � la g�n�ration suivante
	 */
	public boolean est_vivante (int a, int b){
		int compteur_voisines_vivantes=0;
		
		///Les cellules voisines d'une celulle sont situ�es en haut � gauche,
		///� gauche, en bas � gauche, en dessous, en bas � droite, � droite et en haut � gauche 
		/// de la cellule, donc noux fixons deux "d�limiteurs" qui nous permettront de donner les valeurs que l'ont veut 
		///� a et b qui correspondent aux coordonn�es de la cellule, ainsi avec une double boucle les coordonn�es seront tour � tour:
		///a+1/b,a+1/b+1,a/b+1,a-1/b+1,a-1/b,a-1/b-1,a/b-1,a+1/b-1
		
		for(int delimiteur=-1;delimiteur<=1;delimiteur++){
			int ligne=a+delimiteur;
			for(int delimiteur2=-1;delimiteur2<=1;delimiteur2++){

				int colonne=b+delimiteur2;
				
				if(delimiteur!=0 || delimiteur2 != 0){ //dans le cas o� c'est la cellule elle-m�me
					if(ligne>=0 && ligne<damierDuJeu.length && colonne>=0 && colonne<damierDuJeu.length){
						if(damierDuJeu[ligne][colonne].getEtatCellule()==true){
							compteur_voisines_vivantes++;
						}
					}
				}
			}
		}
		
		if(compteur_voisines_vivantes==2 || compteur_voisines_vivantes==3){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * La m�thode devient_vivante est � peu pr�s similaire � la m�thode est_vivante, sauf qu'ici elle va 
	 * �tre utilis�e pour les celulles qui sont en �tat de mort, ainsi elle instancie un compteur de cellules voisines vivantes, 
	 * si la cellule en question est entour�e de 3 cellules voisines vivantes alors � la g�n�ration suivante elle vivra.
	 * La m�thode regarde les cellules voisines aux coordonn�es: a+1/b, a+1/b+1, a/b+1, a-1/b+1, a-1/b, a-1/b-1, 
	 * a/b-1, a+1/b-1. Et va d�terminer � la fin si la cellule deviendra vivante ou non en renvoyant un bool�en true si la cellule devient vivante, false si la cellule reste morte.
	 * @param int a, correspondant aux coordonn�es pour la ligne
	 * @param int b, correspondant aux coordonn�es pour la colonne
	 * @return boolean true ou false correspondant � l'�tat de la cellule � la g�n�ration suivante
	 */
	public boolean devient_vivante (int a, int b){
		int compteur_voisines_vivantes=0;
	
		for(int delimiteur=-1;delimiteur<=1;delimiteur++){
			
			int ligne=a+delimiteur;
			
			for(int delimiteur2=-1;delimiteur2<=1;delimiteur2++){
				
				int colonne=b+delimiteur2;
				
				if(delimiteur!=0 || delimiteur2 != 0){
					
					if(ligne>=0 && ligne<damierDuJeu.length && colonne>=0 && colonne<damierDuJeu.length){
						if(damierDuJeu[ligne][colonne].getEtatCellule()==true){
							compteur_voisines_vivantes++;
						}
					}
				}
			}
		}
		
		if(compteur_voisines_vivantes==3){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * la m�thode g�n�ration_suivante est une m�thode qui ne renvoie rien, qui permet � l'automate de 
	 * passer d'une g�n�ration � une suivante. Cette m�thode appelle les deux m�thode est_vivante et devient_vivante.
	 * Elle instancie au d�but un tableau 2D de bool�en qui aura les m�mes dimensions que le tableau de boutonCellule instanci�e dans le constructeur de la classe Damier.
	 * Cette m�thode va parcourir le tableau de BoutonCellule et v�rifier gr�ce � l'accesseur getEtatCellule l'�tat de la cellule(true ou false). Elle va ensuite regarder l'�tat
	 * de la cellule, si elle est vivante ou non, ensuite elle regarde en fonction de l'�tat de la cellule si elle : va mourir ou rester en vie � la g�n�ration suivante si la cellule est vivante gr�ce � 
	 * la m�thode est_vivante, si elle va mourir elle mettra un bool�en false aux m�mes coordonn�es que la cellule dans le tableau de bool�ens et inversement elle mettra un bool�en true
	 * si la cellule restera en vie � la g�n�ration suivante. Si la cellule est morte alors la m�thode generation_suivante va appeler la m�thode devient_vivante, et ainsi d�terminer si elle met un bool�en
	 * true ou false si la cellule va devenir vivante ou rester morte. Apr�s ce traitement fini, la m�thode parcourt le tableau de bool�ens et va mettre � jour le tableau de BoutonCellule de la classe Damier
	 * en modifiant l'�tat de la cellule. Puis si l'�tat de la cellule est vrai alors elle va "peindre" le devant du BoutonCellule en vert pour signifier que la cellule est vivante ou en blanc si l'�tat de la cellule est mort.
	 */
	public void generation_suivante(){
		boolean tabSuivant[][];
		tabSuivant=new boolean[x][y];

		for (int i=0;i<x;i++){
			for (int j=0;j<y;j++){
				if(this.damierDuJeu[i][j].getEtatCellule()==true){
					

					if(this.est_vivante(i,j)==true){
						
						tabSuivant[i][j]=true;
					}
					else{
						tabSuivant[i][j]=false;
					}
				}
				else{
					if(this.devient_vivante(i,j)==true){
						
						tabSuivant[i][j]=true;
					}
					else{
						
						tabSuivant[i][j]=false;
					}
				}
			}
		}
		for(int f=0;f<tabSuivant.length;f++){
			for(int g=0;g<tabSuivant[f].length;g++){
				this.damierDuJeu[f][g].setEtatCellule(tabSuivant[f][g]);
				if(tabSuivant[f][g]){
				this.damierDuJeu[f][g].setBackground(Color.green);
				}
				else{
					this.damierDuJeu[f][g].setBackground(Color.white);
				}
			}
		}
	}
	
	/**
	 * Cette m�thode getNbreCelluleVivante permet de retourner le nombre de cellules vivantes
	 * dans le damier � n'importe quel moment. Elle nous est donc utile pour afficher la population du damier.
	 * Elle parcourt le damier et elle incr�mente un compteur � chaque cellule vivante ( gr�ce � la m�thode getEtatCellule).
	 * 
	 * @return int le nombre de cellule vivantes.
	 */
	public int getNbreCelluleVivante(){
		int compteur=0;
		
		for(int i =0; i<x;i++){
			for(int j= 0; j<y;j++){
				if(this.damierDuJeu[i][j].getEtatCellule()){
					compteur++;
				}
			}
		}
		return compteur;
		
	}
	
	/**
	 * Un accesseur qui nous permet de retourner le champ damierDuJeu de la classe
	 * Damier
	 * @return BoutonCellule[][], un tableau en 2D de bouton de type Cellule
	 */
	public BoutonCellule[][] getTabBoutonCellule(){
		return damierDuJeu;
	}

	/**
	 * M�thode qui permet d'initialiser toutes les cellules du tableau � false, c'est � dire de construire un damier vide
	 * en mettant toutes les couleurs des boutonsCellule en blanc. 
	 */
	public void effacerDamier(){
		for(int i =0; i<x;i++){
			for(int j= 0; j<y;j++){
				this.damierDuJeu[i][j].setEtatCellule(false);
				this.damierDuJeu[i][j].setBackground(Color.white);
			}
		}
	}
	
}
		
	













