package agglomeration;

import java.util.ArrayList;

public class Ville {

    private boolean ecole;
    private String nom;
    private ArrayList <Ville> routes;

    /**
     * Constructeur Ville, initialise ses attributs
     * 
     * @param nom Nom de la ville
     */
    public Ville(String nom) {
        this.nom = nom;
        ecole = true;
        routes = new ArrayList <Ville> ();
    }

    /**
     * Ajoute une route entre la ville et une ville voisine
     * 
     * @param v Objet ville voisine (reliee par une route)
     */
    public void ajouteRoutes(Ville v) {

        if (!routes.contains(v)) {
            routes.add(v);
        } 
        
        else {
            System.out.println("La ville "+nom+" a deja une route avec "+v.getNom());
        }
    }

    /**
     * Supprime une route avec la ville voisine
     * 
     * @param v Objet ville voisine (reliee par une route)
     */
    public void retireRoutes(Ville v) {

        if (routes.contains(v)) {
            routes.remove(v);
        } 
        
        else {
            System.out.println("La ville "+nom+" n'a pas de route avec "+v.getNom());
        }
    }

    /**
     * Ajoute une ecole a la ville
     */
    public void ajouteEcole() {

        if (!ecole) {
            ecole = true;
        } 
        
        else {
            System.out.println("Cette ville possede deja une ecole");
        }
    }

    /**
     * Retire une ecole a la ville
     */
    public void retireEcole() {

        if (ecole) {

            boolean testEcoleVoisine = false;

            //Verifie qu'au moins une ville voisine possede une ecole
            for (Ville villeVoisine : routes) {
                if (villeVoisine.getEcole()) {
                    testEcoleVoisine = true;
                }
            }

            /**
             * Si au moins une ville voisine possede une ecole, verifie pour chaques villes voisines ne possedant pas d'ecole, 
             * si elles possedent au moins dans leur voisinage une ville ayant une ecole (excepte l'objet ville courant)
             */
            if (testEcoleVoisine) {
                boolean testEcoleLointaine;
                ecole = false;
                for (Ville villeVoisine : routes) {
                    testEcoleLointaine = false;
                    if (!villeVoisine.getEcole()) {
                        for (Ville villeLointaine : villeVoisine.getRoutes()) {
                            if (villeLointaine.getEcole() && !villeLointaine.equals(this)){
                                testEcoleLointaine = true;
                            }
                        }
                        if (!testEcoleLointaine) {
                            System.out.println("Action impossible ! La ville voisine "+villeVoisine.getNom()+" n'aura plus acces a une ecole !");
                            ecole = true;
                        }
                    }
                }
            } else {
                System.out.println("Impossible de retirer cette ecole, car aucune ville voisine n'en possede");
            }

        } else {
            System.out.println("Cette ville ne possede pas d'ecole");
        }
    }

    /**
     * Getter du nom de la ville
     * 
     * @return Retourne le nom de la ville
     */
    public String getNom() {

        return nom;
    }

    /**
     * Getter de la liste de villes voisines (routes)
     * 
     * @return Retourne la liste de routes 
     */
    public ArrayList<Ville> getRoutes() {

        return routes;
    }

    /**
     * Getter de l'ecole de la ville
     * 
     * @return Retourne true s'il y a une ecole, false sinon
     */
    public boolean getEcole() {

        return ecole;
    }

    /**
     * Verifie si la ville a acces a une ecole 
     *
     * @return Retourne true si la ville a acces a une ecole, false sinon
     */
    public boolean accessibilite() {

        // Verifie si la ville possede une ecole
        if(ecole) return true; 

        else {

            //Verifie qu'au moins une ville voisine possede une ecole
            for (Ville villeVoisine : routes) {

                if (villeVoisine.getEcole()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  Verifie si on peut retirer l'ecole a la ville courante
     * 
     * @return true si on peut retirer l'ecole, false sinon
     */
    public boolean ifRetireEcole(){

        if(ecole){

            // On supprime l'ecole de la ville courante 
            ecole=false;

            // On verifie que pour chaque ville voisine celle ci ait acces a une ecole
            for (Ville villeVoisine : routes) {

                // Si une ville voisine n'a pas acces a une ecole alors on retourne false
                if (!villeVoisine.accessibilite()) {

                    // On remet une ecole dans la ville courante 
                    ecole=true;
                    return false;
                }
            }

            // On remet une ecole dans la ville courante
            ecole=true;
            return true;

        }

        return false;
    }

}