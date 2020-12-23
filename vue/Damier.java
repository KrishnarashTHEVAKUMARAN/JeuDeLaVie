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
		///Pour obtenir un nombre aléatoire
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
	 * La méthode est_vivante nous permet de déterminer selon les règles du jeu si une cellule sera en vie ou pas à
	 * la génération suivante. Si elle a deux ou 3 cellules voisines vivantes alors elle sera en vie, sinon elle meurt.
	 * La méthode renvoie un booléen, true si la cellule sera vivante, false si la cellule sera morte à la génération suivante.
	 * La méthode va regarder chaque voisin de la cellule aux coordonnées suivantes:a+1/b, a+1/b+1, a/b+1, a-1/b+1, a-1/b, a-1/b-1, 
	 * a/b-1, a+1/b-1 avec a correspondant à la ligne et b correspondant à la colonne. Elle instancie un compteur qui va compter les cellules voisines vivantes.
	 * Ensuite elle regardera le nombre de voisines vivantes et déterminera si la cellule sera en vie ou non
	 * @param int a, la coordonnée correspondant aux lignes
	 * @param int b, la coordonnée correspondant aux colonnes
	 * @return boolean true ou false correspondant à l'état de la cellule à la génération suivante
	 */
	public boolean est_vivante (int a, int b){
		int compteur_voisines_vivantes=0;
		
		///Les cellules voisines d'une celulle sont situées en haut à gauche,
		///à gauche, en bas à gauche, en dessous, en bas à droite, à droite et en haut à gauche 
		/// de la cellule, donc noux fixons deux "délimiteurs" qui nous permettront de donner les valeurs que l'ont veut 
		///à a et b qui correspondent aux coordonnées de la cellule, ainsi avec une double boucle les coordonnées seront tour à tour:
		///a+1/b,a+1/b+1,a/b+1,a-1/b+1,a-1/b,a-1/b-1,a/b-1,a+1/b-1
		
		for(int delimiteur=-1;delimiteur<=1;delimiteur++){
			int ligne=a+delimiteur;
			for(int delimiteur2=-1;delimiteur2<=1;delimiteur2++){

				int colonne=b+delimiteur2;
				
				if(delimiteur!=0 || delimiteur2 != 0){ //dans le cas où c'est la cellule elle-même
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
	 * La méthode devient_vivante est à peu près similaire à la méthode est_vivante, sauf qu'ici elle va 
	 * être utilisée pour les celulles qui sont en état de mort, ainsi elle instancie un compteur de cellules voisines vivantes, 
	 * si la cellule en question est entourée de 3 cellules voisines vivantes alors à la génération suivante elle vivra.
	 * La méthode regarde les cellules voisines aux coordonnées: a+1/b, a+1/b+1, a/b+1, a-1/b+1, a-1/b, a-1/b-1, 
	 * a/b-1, a+1/b-1. Et va déterminer à la fin si la cellule deviendra vivante ou non en renvoyant un booléen true si la cellule devient vivante, false si la cellule reste morte.
	 * @param int a, correspondant aux coordonnées pour la ligne
	 * @param int b, correspondant aux coordonnées pour la colonne
	 * @return boolean true ou false correspondant à l'état de la cellule à la génération suivante
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
	 * la méthode génération_suivante est une méthode qui ne renvoie rien, qui permet à l'automate de 
	 * passer d'une génération à une suivante. Cette méthode appelle les deux méthode est_vivante et devient_vivante.
	 * Elle instancie au début un tableau 2D de booléen qui aura les mêmes dimensions que le tableau de boutonCellule instanciée dans le constructeur de la classe Damier.
	 * Cette méthode va parcourir le tableau de BoutonCellule et vérifier grâce à l'accesseur getEtatCellule l'état de la cellule(true ou false). Elle va ensuite regarder l'état
	 * de la cellule, si elle est vivante ou non, ensuite elle regarde en fonction de l'état de la cellule si elle : va mourir ou rester en vie à la génération suivante si la cellule est vivante grâce à 
	 * la méthode est_vivante, si elle va mourir elle mettra un booléen false aux mêmes coordonnées que la cellule dans le tableau de booléens et inversement elle mettra un booléen true
	 * si la cellule restera en vie à la génération suivante. Si la cellule est morte alors la méthode generation_suivante va appeler la méthode devient_vivante, et ainsi déterminer si elle met un booléen
	 * true ou false si la cellule va devenir vivante ou rester morte. Après ce traitement fini, la méthode parcourt le tableau de booléens et va mettre à jour le tableau de BoutonCellule de la classe Damier
	 * en modifiant l'état de la cellule. Puis si l'état de la cellule est vrai alors elle va "peindre" le devant du BoutonCellule en vert pour signifier que la cellule est vivante ou en blanc si l'état de la cellule est mort.
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
	 * Cette méthode getNbreCelluleVivante permet de retourner le nombre de cellules vivantes
	 * dans le damier à n'importe quel moment. Elle nous est donc utile pour afficher la population du damier.
	 * Elle parcourt le damier et elle incrémente un compteur à chaque cellule vivante ( grâce à la méthode getEtatCellule).
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
	 * Méthode qui permet d'initialiser toutes les cellules du tableau à false, c'est à dire de construire un damier vide
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
		
	













