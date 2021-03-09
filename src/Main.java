import java.io.FileNotFoundException;
import java.util.Scanner;

import agglomeration.CA;
import utilitaire.UtilMain;

public class Main {

	public static void main(String[] args) {
        System.out.println("***** Bienvenue ******");

		// intialisation de la CA et du scanner
		CA ca = new CA();
		Scanner sc = new Scanner(System.in);
		
		// verifie qu'un fichier a ete passe en argument 
		boolean hasInputFile = args.length == 1;

		// Si presence d'un fichier
		if (hasInputFile) {

			try {
				// Recuperer les informations du fichier
				UtilMain.chargerFichier(args[0], ca);

			}

			// Si le fichier n'a pas ete trouve
			catch (FileNotFoundException e) {
				hasInputFile = false;
			}
		} 
		
		else {
			System.out.println("Vous n'avez pas rentre de fichier en parametre :( ");
		}

		// Si absence de fichier 
		if (!hasInputFile) {

			// Cree une CA 
			UtilMain.creerVille(sc);

			// Cree des routes entre les villes
			UtilMain.gererRoutes(sc, ca);

		}

		// Menu de resolution et de sauvegarde
		UtilMain.resolution(sc, ca);

		sc.close();
		
	}
}
	