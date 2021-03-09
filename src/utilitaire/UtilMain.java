package utilitaire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import agglomeration.CA;
import agglomeration.Ville;

public class UtilMain {

    /**
	 * Permet a l'utilisateur de creer des villes et de les ajouter a la CA
	 */
	public static void creerVille(Scanner sc) {
		System.out.println(" CREATION DES VILLES ");
		System.out.println();

		int cpt = 0;

		// Demande a l'utilisateur un nombre de villes a creer 
		while (cpt < 1 || cpt > 100) {

			try {
				System.out.printf("Nombre de villes à représenter > ");
				cpt = sc.nextInt();

				if (cpt < 1 || cpt > 100) {
					System.out.println("Veuillez choisir un nombre de villes compris entre 1 et 100 !");
				}
			}

			// Dans le cas ou l'utilisateur n'entre pas un nombre entier
			catch (InputMismatchException e) {
                System.out.println("Mauvaise entrée, ceci n'est pas un nombre entier ! Recommencez !");
				sc.nextLine();
			}
		}

		System.out.println();
		System.out.println(" (i) La saisie automatique est activé ! ");
		System.out.println();

        // Ajoute automatiquement le nombre de villes entre dans la CA
        int moduloAlphabet = 0; 
		int compteurNomVille = 0;
		String nom = "";
		
		for (int i = 0; i < cpt; i++) {

            // Nom de la ville (Lettre majuscule suivi d'un nombre )
			moduloAlphabet = i%26;
			
            if (moduloAlphabet == 0) {
                compteurNomVille++;
			}

			// Dans le cas ou plus de 26 villes sont entrees on ajoute un numero afin de pouvoir les identifier (Lettre + numero)
			if(cpt>26) {

				int letterASCII = 'A' + moduloAlphabet;
				nom = Character.toString((char) letterASCII)+compteurNomVille;
				System.out.println("Nom de la ville n° " + (i + 1) + " > " + nom);
			}

			// Dans le cas ou les lettres de l'alphabet suffisent a identifier toutes les villes 
			else {

				int letterASCII = 'A' + moduloAlphabet;
				nom = Character.toString((char) letterASCII);
				System.out.println("Nom de la ville n° " + (i + 1) + " > " + nom);
			}
			

			// Cree une nouvelle ville dans la CA
			CA.ajouteVille(nom);

			System.out.println(" La ville " + nom + " a bien été créée !");

		}
	} // fin creerVille

	/**
	 * Permet a l'utilisateur de creer des routes
	 */
	public static void gererRoutes(Scanner sc, CA ca) {

		System.out.println();
		System.out.println(" REPRESENTATION DES VILLES ");
		System.out.println();

		int choix = 0;

		while (choix != 2) {

			System.out.println("1) Ajouter route");
            System.out.println("2) Quitter");

			try {

				System.out.print("Votre choix > ");
				choix = sc.nextInt();
				sc.nextLine();
				System.out.println();

				switch(choix) {

				case 1:
					ajouterRoute(sc, ca);
					break;

				case 2:
					break;

				default:
                System.out.println("Votre choix ne correspond a aucune option ! Recommencez !");
					break;
				}

			}
			// Dans le cas ou l'utilisateur n'entre pas un nombre entier 
			catch (InputMismatchException e) {
                System.out.println("Mauvaise entree, ceci n'est pas un nombre entier ! Recommencez !");
				sc.nextLine();
			}
		}
	} // gererRoutes

	/**
	 * Ajout de routes entre une ville et d'autres
	 */
	public static void ajouterRoute(Scanner sc, CA ca) {

		System.out.println("1. Ajouter une route");
		System.out.println("--------------------");
		System.out.println();

		System.out.printf("Ville de depart > ");
		String VilleA = sc.nextLine();

		// Si la premiere ville n'existe pas
		if (ca.trouveVille(VilleA) == null) {
			System.out.println(" Ville inconnue ! ");

			return;
		}

		System.out.printf("Villes voisines : (ex: B, C) > ");
		String VilleB = sc.nextLine();

		// Formats acceptes grace a regex (virgules entre les noms de villes avec espaces ou sans )
		final String regex = ",[ ]*";
		String[] listVilleVoisines = VilleB.split(regex);
		int nbRoutes = listVilleVoisines.length;

		System.out.println(" Creation de " + nbRoutes + (nbRoutes == 1 ? " route" : " routes") + " :");

		for (String Voisin : listVilleVoisines) {

			// Verifie si VilleA et Voisin existent
            Ville ville1 = ca.trouveVille(VilleA);
            Ville ville2 = ca.trouveVille(Voisin);

            if ((ville1 == null) || (ville2 == null)) {
                System.out.println("L'une des 2 villes n'existe pas !");
            }

            else {

                ville1.ajouteRoutes(ville2);
                ville2.ajouteRoutes(ville1);

                ca.modificationListeAdjacent(ville1);
                ca.modificationListeAdjacent(ville2);
                
                System.out.println("\t- " + VilleA + " <-> " + Voisin);
            }
		}
	} // ajouterRoute

	/**
	 * Menu de gestion des ecoles
	 */
	public static void gererEcoles(Scanner sc, CA ca) {
		System.out.println();
		System.out.println("| GESTION DES ECOLES                             |");
		System.out.println();

		int choix = 0;

		while (choix != 4) {
			System.out.println("1) Ajouter une ecole");
            System.out.println("2) Retirer une ecole");
            System.out.println("3) Voir la liste des villes ayant une ecole");
            System.out.println("4) Fin du programme");


			try {
				System.out.print("Votre choix > ");
				choix = sc.nextInt();
				sc.nextLine();
				System.out.println();

				switch (choix) {
				case 1:
					ajouterEcole(sc, ca);
					break;

				case 2:
					supprimerEcole(sc, ca);
					break;

				case 3:
					afficheEcoles(ca);
					break;

				case 4:
					break;

				default:
                System.out.println("Votre choix ne correspond a aucune option ! Recommencez !");
                break;
				}
			}

			catch (InputMismatchException e) {
                System.out.println("Mauvaise entree, ceci n'est pas un nombre entier ! Recommencez !");
				sc.nextLine();
			}
		} // while

		// Presente le nb d'ecoles construite et leur localisation
		conclusion(ca);
	} // gererEcoles

	/**
	 * Permet d'ajouter une ecole dans une ville entree par l'utilisateur
	 */
	public static void ajouterEcole(Scanner sc, CA ca) {
		System.out.println("1. Ajouter une ecole");
		System.out.println("--------------------");

		System.out.print("Ville concernee > ");
		String nomVille = sc.nextLine();
		System.out.println();

		// Si la ville n'existe pas
		if (ca.trouveVille(nomVille) == null) {
			System.out.println(" Ville inconnue !  ");

			return;
		}

		Ville v = ca.trouveVille(nomVille);
		v.ajouteEcole();

		System.out.println(" L'ecole dans la ville " + nomVille + " a bien ete cree.\n");
	} // ajouterEcole

	/**
	 * Permet de supprimer une ecole dans une ville entree par l'utilisateur
	 */
	public static void supprimerEcole(Scanner sc, CA ca) {
		System.out.println("2. Supprimer une ecole");
		System.out.println("----------------------");

		System.out.printf("Ville concernee > ");
		String nomVille = sc.nextLine();
		System.out.println();

		// Si la ville n'existe pas
		if (ca.trouveVille(nomVille) == null) {
			System.out.println(" Ville inconnue ! ");

			return;
		}

		Ville v = ca.trouveVille(nomVille);

		v.retireEcole();

	} // supprimerEcole

	/**
	 * Affiche le noms des villes possedant une ecole
	 */
	public static void afficheEcoles(CA ca) {
		System.out.println("3. Afficher ecoles");
		System.out.println("------------------");

        System.out.println("Voici la liste des villes possedant une ecole :");
        for (Ville ville : ca.getListeVilles()) {
            if (ville.getEcole()) {
                System.out.println(ville.getNom());
            }
        }
	} // afficheEcoles

	/**
	 * Open a file and load it's CA informations <br/>
	 * Extension: .ca <br/>
	 * Format: multiple lines ending with '.' <br/>
	 * Line format: <br/>
	 * 
	 * <pre>
	 * ville(A).
	 * route(A,B).
	 * ecole(A).
	 * </pre>
	 * 
	 * @param chemin Relative or absolute file path
	 * @throws FileNotFoundException
	 */
	public static void chargerFichier(String chemin, CA ca) throws FileNotFoundException {
		System.out.println();
		System.out.println(" (i) Chargement de la CA depuis un fichier... ");
		System.out.println("Ouverture du fichier : " + chemin);
		System.out.println();

		int nbVilles = 0;
		int nbRoutes = 0;
		int nbEcoles = 0;

		try {
			File file = new File(chemin);
			Scanner reader = new Scanner(file);
			String line;
            Matcher m;

			while (reader.hasNextLine()) {
				line = reader.nextLine();

				// exemple: ville(A)
				if (line.matches(ca.getVille_Regex())) {
					m = ca.getVille_Pattern().matcher(line);

					if (m.find()) {
						String nomVille = m.group(1);

						CA.ajouteVille(nomVille);

						nbVilles++;
					}
				}

				// exemple: route(A,B)
				if (line.matches(ca.getRoute_Regex())) {
					m = ca.getRoute_Pattern().matcher(line);

					if (m.find()) {
						String villeA = m.group(1);
                        String villeB = m.group(2);
                        
                        Ville ville1 = ca.trouveVille(villeA);
                        Ville ville2 = ca.trouveVille(villeB);

						ville1.ajouteRoutes(ville2);
                        ville2.ajouteRoutes(ville1);

                        ca.modificationListeAdjacent(ville1);
                        ca.modificationListeAdjacent(ville2);

						nbRoutes++;
					}
				}

				// exemple: ecole(A)
				if (line.matches(ca.getEcole_Regex())) {
					m = ca.getEcole_Pattern().matcher(line);

					if (m.find()) {
                        String nomVille = m.group(1);

                        Ville v = ca.trouveVille(nomVille);
		                v.ajouteEcole();

						nbEcoles++;
					}
				}
			} // while

			reader.close();

			System.out.println(" (i) Chargement termine ! ");
			System.out.println("------------------------------------------------");
			System.out.println("==> Villes creees : " + nbVilles);
			System.out.println("==> Routes creees : " + nbRoutes + " (x2)");
			System.out.println("==> Ecoles creees : " + nbEcoles);

			// if no city was loaded
			if (ca.getListeVilles().size() == 0) {
				System.out.println(" (i) Aucun ville cree !  ");
			}

			System.out.println();
		}

		// Si le chemin ne correspond a aucun fichier
		catch (FileNotFoundException e) {
            System.out.println("Nom du ficher ou chemin incorrect ! Impossible de charger le fichier demande !"); //Ancienne phrase "Veuillez entrer un chemin d'acces au fichier valide..."
            
            throw new FileNotFoundException();
		}

    } // chargerFichier
    
    public static void solution(CA ca){

        ArrayList<Ville> tabRelation = new ArrayList<Ville>();
        ArrayList<Ville> tabEcole = new ArrayList<Ville>();
        ArrayList<Integer> confirmation = new ArrayList<Integer>();
        int i;
        int size=0;
        int nb_ville=0;
        int nb_ecoles=0;
        confirmation.add(0); //Ajout de la premiére valeur du tableau a 0
        confirmation.add(0); //Ajout de la seconde valeur du tableau a 0
		
		/** Stocke dans le tableau tabRelation la communaute d'agglomeration 
        *  trie en fonction du nombre de relations qu'elle possede
        */
        tabRelation = ca.getListeVilles();
        for (i=0; i<nb_ville; i++) {
            for (Ville ville : tabRelation) {
                if(!ville.accessibilite()) {
                    ville.ajouteEcole();
                } 
            } 
        } 

        // Condition d'arret / L'algorithme devra s'arreter si le nombre d'ecoles de la CA ne change plus 3 iterations consecutives
        do {
            tabEcole=ca.getListeVilles();
            for(Ville ville : tabEcole){
                ville.ajouteEcole(); 
                for(Ville villeadjacente : ville.getRoutes()){
                    villeadjacente.retireEcole();
                } //
                ville.retireEcole();
            } //
            nb_ecoles = CA.getNbEcole();
            confirmation.add(nb_ecoles); //Ajout du nombre d'ecoles actuel dans la liste confirmation
            size = confirmation.size();

         
        } while( (confirmation.get(size-1)!=confirmation.get(size-2)) || (confirmation.get(size-2)!=confirmation.get(size-3)) );

        System.out.println("Voici la liste des villes :");
        ca.afficheVilles();
		
		System.out.println("Voici les routes associées aux villes :");
        ca.afficheRoutes();
		
		System.out.println("Voici la liste des villes ayant une école :");
		for (Ville ville : ca.getListeVilles()) {
            if (ville.getEcole()) {
                System.out.println(ville.getNom());
            } 
        } 
        
    }

	/**
	 * Menu de resolution 
	 */
	public static void resolution(Scanner sc, CA ca) {
		System.out.println(" RESOLUTION ");
		System.out.println();

		int choix = 0;

		while (choix != 4) {
			System.out.println("1) Resoudre manuellement");
            System.out.println("2) Resoudre automatiquement");
            System.out.println("3) Sauvegarder");
            System.out.println("4) Quitter");

			try {
				System.out.print("Votre choix > ");
				choix = sc.nextInt();
				sc.nextLine();
				System.out.println();

				switch (choix) {
				case 1:
					gererEcoles(sc, ca);
					break;

				case 2:
					solution(ca);
					conclusion(ca);
					break;

				case 3:
					sauvegarderFichier(sc, ca);
					break;

				case 4:
					break;

				default:
                System.out.println("Votre choix ne correspond a aucune option ! Recommencez !");
                break;
				}
			}

			// Dans le cas ou l'utilisateur n'entre pas un nombre entier
			catch (InputMismatchException e) {
                System.out.println("Mauvaise entree, ceci n'est pas un nombre entier ! Recommencez !");
				sc.nextLine();
			}
		}
	} // resolution

	/**
	 * Permet de sauvegarder la solution dans un fichier
	 */
	public static void sauvegarderFichier(Scanner sc, CA ca) {
		System.out.println("3. Sauvegarder");
		System.out.println("--------------");

		System.out.print("Nom du fichier > ");
		String chemin = sc.nextLine();
		System.out.println();

		try {
			File fichier = new File(chemin);

			System.out.println(" (i) Si le fichier existe, il sera remplace.   ");

			fichier.createNewFile();

			FileWriter ecrire = new FileWriter(fichier);

			HashMap<String, Boolean> visite = new HashMap<String, Boolean>();

			// ecrire chaque ville
			for (Ville v : ca.getListeVilles()) {
                String nomVille = v.getNom();
				String ligne = "ville(" + nomVille + ").\n";
				ecrire.write(ligne);

				// une fois la ville visite lui associer false
				visite.put(nomVille, false);
			}

			// ecrire les routes
			for (Ville v : ca.getListeVilles()) {
				for (Ville villeVoisine : v.getRoutes()) {
					if (!visite.get(villeVoisine.getNom())) {
						String line = "route(" + v.getNom() + "," + villeVoisine.getNom() + ").\n";
						ecrire.write(line);
					}
				}

				// placer la valeur de la ville a true pour ne pas dupliquer
				visite.put(v.getNom(), true);
			}

			// ecrire les ecoles
			for (Ville v : ca.getListeEcole()) {
				String ligne = "ecole(" + v.getNom() + ").\n";
				ecrire.write(ligne);
			}

			ecrire.close();

			System.out.println("==> Sauvegarde termine !");
			System.out.println();

		}

		// Dans le cas ou la sauvegarde n'a pas ete effectue
		catch (IOException e) {
			System.out.println("Desole ! Nous n'avons pas pu trouver le fichier :( ");
		}
	} // sauvegarderFichier

	/**
	 * Bilan du projet
	 */
	public static void conclusion(CA ca) {
		ArrayList<String> ecoles = ca.afficheEcole();
		int nbEcoles = ecoles.size();

		System.out.println(" BILAN SOLUTION !!!!!                ");
		System.out.println("==> Nombre ecoles construites : " + nbEcoles);
		System.out.println("==> Ecoles construites : " + ecoles);
		System.out.println();
	}
}

