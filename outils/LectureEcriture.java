package outils;
import java.io.*;

public class LectureEcriture {
	
	/**
	 * La méthode Lecture permet la lecture d'un fichier donné en paramètre
	 * renvoie une erreur si il y a une exception de type IOException lors de la lecture du fichier
	 * La méthode va utiliser une autre méthode : readObject pour lire le fichier 
	 * @param Fichier parFichier
	 * @return Objet de Type Agenda pour ce projet, sinon un objet de type Object
	 */
	public static Object lecture (File parFichier){
		ObjectInputStream flux;
		Object objetLu = null;
		
		//Ouverture du fichier en mode lecture
		try{
			flux = new ObjectInputStream ( new FileInputStream ( parFichier ));
			objetLu= ( Object )flux.readObject();
			flux.close();
		}
		
		catch ( ClassNotFoundException parException) {
			System.err.println(parException.toString ());
			System.exit(1);
		}
		
		catch (IOException parException){
			System.err.println ( "Erreur lecture du fichier " + parException.toString());
			System.exit(1);
		}
		
		return objetLu;
		} //lecture ()
	
	/**
	 * La méthode ne renvoie rien, elle permet l'écriture de l'objet Object 
	 * dans un fichier donné en paramètre
	 * Elle renvoie une exception de type IOException si il y a une erreur lors de l'écriture dans le fichier
	 * @param Fichier parFichier
	 * @param Object parObjet
	 */
	public static void ecriture ( File parFichier, Object parObjet){
		ObjectOutputStream flux = null;
		
		//Ouverture du fichier en mode écriture
		try {
			flux = new ObjectOutputStream (new FileOutputStream(parFichier));
			flux.writeObject (parObjet);
			flux.flush();
			flux.close();
		}
		
		catch (IOException parException) {
			System.err.println( " Problème à l'éécriture \n" + parException.toString());
			System.exit(1);
		}
	}  //ecriture
}
