// package tests;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;

// import org.junit.jupiter.api.Test;

// import agglomeration.CA;
// import agglomeration.Ville;

// import java.util.ArrayList;

// public class UtilTestCA {
    
//     /**
//      * Test la méthode ajouteVille() de CA
//      * Le scénario est d'ajouter une ville à CA et de comparer la liste de villes de CA avec une liste de ville vide
//      */
//     @Test
//     public static void testAjouteVille() {
//         CA ca = new CA();
//         CA.ajouteVille("a");

//         ArrayList<Ville> listVilles = new ArrayList<Ville>();

//         assertNotEquals(ca.getListeVilles(), listVilles);
//     }

//     /**
//      * Test la méthode trouveVille() de CA
//      * Le scénario est d'ajouter une ville à CA et de voir si l'on obtient une ville ou un objet null
//      */
//     @Test
//     public static void testTrouveVille() {
//         CA ca = new CA();
//         assertNull(ca.trouveVille("nom")); //On test d'abord si la fonction renvoie bien null

//         CA.ajouteVille("nom");

//         assertNotNull(ca.trouveVille("nom")); //Si ce n'est pas null alors la ville a bien été trouvée
//     }

//     /**
//      * Test la méthode getListeEcole() de CA
//      * Le scénario est d'ajouter une ville à CA et de voir si l'on obtient une ville ou un objet null
//      */
//     @Test
//     public static void testGetListeEcole() {
//         ArrayList<Ville> listEcoles = new ArrayList<Ville>();
//         CA ca = new CA();

//         assertEquals(listEcoles, ca.getListeEcole()); //test école vide

//         CA.ajouteVille("a");

//         assertNotEquals(ca.getListeEcole(), listEcoles); //Si c'est pas égale alors on a bien une ville avec une école
//     }

//     /**
//      * Test la méthode getNbEcole() de CA
//      * Le scénario est d'ajouter 3 villes à CA et de voir si l'on obtient bien 3 à la sortie de la fonction
//      */
//     @Test
//     public static void testGetNbEcole() {
//         CA.ajouteVille("a");
//         CA.ajouteVille("b");
//         CA.ajouteVille("c");

//         assertEquals(3, CA.getNbEcole());
//     }

//     /**
//      * Test la méthode triRelation() de CA
//      * Le scénario est de voir si le tri renvoie bien une liste de ville triée
//      */
//     @Test
//     public static void testTriRelation() {
//         CA ca = new CA();
//         ArrayList<Ville> ls = new ArrayList<Ville>();

//         Ville v1 = new Ville("a");
//         Ville v2 = new Ville("b");
//         Ville v3 = new Ville("c");

//         //v1 aura 2 voisins
//         v1.ajouteRoutes(v2);
//         v1.ajouteRoutes(v3);

//         //v2 aura 1 voisin
//         v2.ajouteRoutes(v3);

//         //On insère les villes dans l'ordre croissant du nb de voisin
//         ls.add(v1);
//         ls.add(v2);
//         ls.add(v3);

//         //On ajoute des villes
//         CA.ajouteVille("a");
//         CA.ajouteVille("b");
//         CA.ajouteVille("c");

//         //On ajoute une route à b (2ème élément)
//         ca.getListeVilles().get(1).ajouteRoutes(v1); 

//         //On ajoute 2 route à c (3ème élément)
//         ca.getListeVilles().get(2).ajouteRoutes(v1);
//         ca.getListeVilles().get(2).ajouteRoutes(v3);

//         assertEquals(ls, ca.triRelation());
//     }
// }
