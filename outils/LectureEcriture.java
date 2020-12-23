package outils;
import java.io.*;

public class LectureEcriture {
	
	/**
	 * La m�thode Lecture permet la lecture d'un fichier donn� en param�tre
	 * renvoie une erreur si il y a une exception de type IOException lors de la lecture du fichier
	 * La m�thode va utiliser une autre m�thode : readObject pour lire le fichier 
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
	 * La m�thode ne renvoie rien, elle permet l'�criture de l'objet Object 
	 * dans un fichier donn� en param�tre
	 * Elle renvoie une exception de type IOException si il y a une erreur lors de l'�criture dans le fichier
	 * @param Fichier parFichier
	 * @param Object parObjet
	 */
	public static void ecriture ( File parFichier, Object parObjet){
		ObjectOutputStream flux = null;
		
		//Ouverture du fichier en mode �criture
		try {
			flux = new ObjectOutputStream (new FileOutputStream(parFichier));
			flux.writeObject (parObjet);
			flux.flush();
			flux.close();
		}
		
		catch (IOException parException) {
			System.err.println( " Probl�me � l'��criture \n" + parException.toString());
			System.exit(1);
		}
	}  //ecriture
}
