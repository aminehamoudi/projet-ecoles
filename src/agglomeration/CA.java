package agglomeration;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.*;

public class CA implements Comparable {

    private static ArrayList<Ville> listVilles;
    private HashMap <Ville, ArrayList<Ville>> listeAdjacent;

    // Regex
    private final String ville_Regex = "ville\\((\\w+)\\).*";
    private final String route_Regex = "route\\((\\w+),(\\w+)\\).*";
    private final String ecole_Regex = "ecole\\((\\w+)\\).*";

    // Pattern
    private final Pattern ville_Pattern = Pattern.compile(ville_Regex);
    private final Pattern ecole_Pattern = Pattern.compile(ecole_Regex);
    private final Pattern route_Pattern = Pattern.compile(route_Regex);

    /**
     * Constructeur CA (Communaute d'Agglomerations), initialise ses attributs
     */
    public CA () {

        listVilles = new ArrayList<Ville>();
        listeAdjacent = new HashMap<Ville, ArrayList<Ville>>();
    }

    /**
     * Modifie la liste d'adjacence de l'agglomeration (liste des routes par villes)
     * 
     * @param v Objet ville qui correspond a la "key" de la HashMap
     */
    public void modificationListeAdjacent(Ville v) {

        listeAdjacent.put(v, v.getRoutes());
    }

    /**
     * Ajoute une ville au sein de l'agglomeration
     * 
     * @param nom Correspond au nom de la ville
     */
    public static void ajouteVille(String nom) {

        Ville v = new Ville(nom);

        if (!listVilles.contains(v)) {
            listVilles.add(v);
        } 
        
        else {
            System.out.println("La ville "+nom+" existe deja dans l'agglomeration");
        }
    }

    /**
     * Retire une ville au sein de l'agglomeration
     * 
     * @param nom Correspond au nom de la ville
     */
    public void retireVille(String nom) {

        Ville ville = trouveVille(nom);

        // Dans le cas ou la ville n'existe pas
        if (ville != null) { 

            // Dans le cas ou la ville possede des villes voisines
            if (!ville.getRoutes().isEmpty()) {

                // Supprime la ville en question dans la liste des villes voisines des autres villes
                for (Ville villeVoisine : ville.getRoutes()) {
                    villeVoisine.getRoutes().remove(ville);

                    // Mise a jour du Hashmap de villes et de villes voisines
                    if (!villeVoisine.getRoutes().isEmpty()) {
                        modificationListeAdjacent(villeVoisine);
                    } else {
                        listeAdjacent.remove(villeVoisine);
                    }
                    
                }
    
                ville.getRoutes().clear();
                listeAdjacent.remove(ville);
            }
            
            listVilles.remove(ville);
            System.out.println("La ville "+nom+" a ete correctement supprimee");
        } 
        
        else {
            System.out.println("L'agglomeration ne contient pas la ville "+nom);
        }
    }

    /**
     * Affiche toutes les villes de l'agglomeration
     * 
     */
    public void afficheVilles() {

        if (!listVilles.isEmpty()) {
            for (Ville ville : listVilles) {
                System.out.println(ville.getNom());
            }
        } 

        else {
            System.out.println("Pas de villes dans cette agglomeration !");
        }
    }

    /**
     * Retrouve une ville a partir de son nom dans l'agglomeration
     * 
     * @param nom Nom de la ville
     * @return Retourne la ville trouvee, null sinon
     */
    public Ville trouveVille(String nom) {

        Ville ville = null;

        for (Ville v : listVilles) {
            if (v.getNom().equals(nom)) {
                ville = v;
            }
        }

        return ville;
    }

    /**
     * Getter de la liste de villes de l'agglomeration
     * 
     * @return Retourne la liste des villes de l'agglomeration
     */
    public ArrayList<Ville> getListeVilles() {

        return listVilles;
    }

    /**
     * Getter de la liste de villes de l'agglomeration possedant une ecole
     * 
     * @return Retourne la liste des villes de l'agglomeration possedant une ecole
     */
    public ArrayList<Ville> getListeEcole() {

        ArrayList<Ville> listEcoles = new ArrayList<Ville>();

        for(Ville ville : listVilles){
            if(ville.getEcole()){
                listEcoles.add(ville);
            }
        }

        return listEcoles;
    }
    
    /**
     * Affiche la liste des noms de villes de l'agglomeration possedant une ecole
     * 
     * @return Retourne la liste de noms des villes de l'agglomeration possedant une ecole
     */
    public ArrayList<String> afficheEcole() {

        ArrayList<String> listEcoles = new ArrayList<String>();

        for(Ville ville : listVilles){
            if(ville.getEcole()){
                listEcoles.add(ville.getNom());
            }
        }

        return listEcoles;
    }

    /**
     * Getter de la liste d'adjacence des villes voisines de chaques villes de l'agglomeration
     * 
     * @return Retourne la liste des villes voisine (routes)
     */
    public HashMap <Ville, ArrayList<Ville>> getListeAdjacent() {

        return listeAdjacent;
    }

    /**
     * Getter du nombre d'ecoles presents dans la communaute d'agglomeration
     * 
     * @return Retourne le nombre d'ecoles 
     */
    public static int getNbEcole() { 

        int nb=0;

        for(Ville ville : listVilles){
            if(ville.getEcole()){
                nb++;
            }
        }

        return nb;
    }

    /**
     * Affiche les villes voisines de chaques villes (correspond aux routes)
     */
    public void afficheRoutes() {

        // Si les villes possedent des villes voisines
        if (!listeAdjacent.isEmpty()) {

            listeAdjacent.entrySet().forEach(ville -> {

                System.out.print("Ville "+ville.getKey().getNom()+" ==> ");

                for (Ville villeFor : ville.getValue()) {
                    System.out.print(villeFor.getNom()+" ");
                }

                System.out.println("");
            });

        } 
        
        else {
            System.out.println("Il n'existe aucunes routes entre les villes");
        }
        
    }

    /**
     * Trie la communaute d'agglomeration en fonction du nombre de relations qu'elle possede
     * 
     * @return Une liste de villes triees en fonction de leurs nombres de relations avec les autres villes
     */
        public ArrayList<Ville> triRelation() {

        int taille;
        int i;
        Ville buffer;
        HashMap<Ville, Integer> pile = new HashMap<Ville, Integer>();
        ArrayList<Ville> ls = new ArrayList<Ville>();

        // Cree un nouveau HashMap contenant les villes associees au nombre de relations qu'elle possede
        for(Ville ville : listeAdjacent.keySet()){
            taille = 0;
            taille = (listeAdjacent.get(ville)).size();
            pile.put(ville, taille);
        }

        // Cree un tableau trie des villes en fonction du nombre de leurs relations
        for(Ville ville : pile.keySet()) { 
        
            // Placer la premiere ville dans le tableau 
            if(ls.isEmpty()) {
                ls.add(ville);
            }

            // Compare la ville avec celles presentes dans le tableau 
            else {

                i=0;

                for(Ville v : ls){
                    if((pile.get(v)).compareTo(pile.get(ville)) < 0){
                        buffer = ls.get(i);
                        ls.set(i, ville);
                        ls.add(buffer);
                    }
                    else{
                        ls.add(ville);         
                    }
                    i++;
                }

            }

        }

        return ls;
    }

    /**Trie la communauté d'agglomération en fonction du nombre d'écoles à sa dispostion (strictement superieur a 1)
     * 
     * @return Une liste de villes triees en fonction de leurs nombres d'écoles à proximité
     */
    public ArrayList<Ville> triEcole() {

        int nb;
        int i;
        Ville buffer;
        HashMap<Ville, Integer> pile = new HashMap<Ville, Integer>();
        ArrayList<Ville> ls = new ArrayList<Ville>();

        // Cree un nouveau HashMap contenant les villes associees au nombre d'ecoles a sa disposition
        for(Ville ville : listeAdjacent.keySet()){

            nb=0; 

            if(ville.getEcole()){
                nb++;
            }

            for (Ville villeProche : ville.getRoutes()) {
                if(villeProche.getEcole()){
                    nb++;
                }
            }

            if(nb>1){
            pile.put(ville, nb);
            }

        }

        // Cree un tableau trie des villes en fonction du nombre d'ecoles mis a sa disposition
        for (Ville ville : pile.keySet()) { 

            // Place la premiere ville dans le tableau
            if(ls.isEmpty()) { 
                ls.add(ville);
            }

            // Compare la ville avec celles presentes dans le tableau 
            else {

                i=0;

                for(Ville v : ls){

                    if((pile.get(v)).compareTo(pile.get(ville)) < 0){
                        buffer = ls.get(i);
                        ls.set(i, ville);
                        ls.add(buffer);
                    }

                    else{
                        ls.add(ville);         
                    }

                    i++;

                }
            }
        }

        return ls;
    }

    /**
     * Getter du pattern ecole_Pattern
     * 
     * @return Retourne le ecole_Pattern
     */
    public Pattern getEcole_Pattern() {
        return ecole_Pattern;
    }

    /**
     * Getter du regex ecole_Regex
     * 
     * @return Retourne ecole_Regex de type String
     */
    public String getEcole_Regex() {
        return ecole_Regex;
    }

    /**
     * Getter du pattern route_Pattern
     * 
     * @return Retourne le route_Pattern
     */  
    public Pattern getRoute_Pattern() {
        return route_Pattern;
    }

    /**
     * Getter du regex route_Regex
     * 
     * @return Retourne route_Regex de type String
     */
    public String getRoute_Regex() {
        return route_Regex;
    }

    /**
     * Getter du pattern ville_Pattern
     * 
     * @return Retourne le ville_Pattern
     */
    public Pattern getVille_Pattern() {
        return ville_Pattern;
    }

    /**
     * Getter du regex ville_Regex
     * 
     * @return Retourne ville_Regex de type String
     */
    public String getVille_Regex() {
        return ville_Regex;
    }

    /**
     * Compare deux valeurs de meme types
     * 
     * @return un entier (positif ou negatif ou nul) correspondant a la comparaison avec la deuxieme valeur
     */
	@Override
	public int compareTo(Object o) {
		
		return 0;
	}
}
