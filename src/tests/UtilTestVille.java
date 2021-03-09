// package tests;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import org.junit.jupiter.api.Test;

// import agglomeration.Ville;

// import java.util.ArrayList;


// public class UtilTestVille {
    
//     /**
//      * Test la méthode accessibilite() de Ville
//      * Le scénario est de tester si la méthode renvoie bien "true"
//      */
//     @Test
//     public static void testBooleanAccessibilite() {
//         Ville v = new Ville("a");

//         assertTrue(v.accessibilite());
//     }

//     /**
//      * Test la méthode ajouteRoute() de Ville
//      * Le scénario est de comparer une liste de ville avec la liste de ville d'un objet Ville qui a utilisé la méthode ajouteRoute()
//      */
//     @Test
//     public static void testAjouteRoute() {
//         Ville v1 = new Ville("a");
//         Ville v2 = new Ville("b");
//         Ville v3 = new Ville("c");
//         ArrayList<Ville> route = new ArrayList<Ville>();

//         v1.ajouteRoutes(v2);
//         v1.ajouteRoutes(v3);

//         route.add(v2);
//         route.add(v3);

//         assertEquals(route, v1.getRoutes());
//     }

//     /**
//      * Test la méthode retireRoute() de Ville
//      * Le scénario est de comparer une liste vide de ville avec la liste de ville voisine d'un objet Ville après utilisation du retireRoute()
//      */
//     @Test
//     public static void testRetireRoute() {
//         Ville v1 = new Ville("a");
//         Ville v2 = new Ville("b");
//         Ville v3 = new Ville("c");
//         ArrayList<Ville> route = new ArrayList<Ville>();

//         v1.ajouteRoutes(v2);
//         v1.ajouteRoutes(v3);

//         v1.retireRoutes(v2);
//         v1.retireRoutes(v3);
//         assertEquals(route, v1.getRoutes());
//     }

//     /**
//      * Test la méthode retireEcole() de Ville
//      * Le scénario est d'enlever les écoles des villes voisines et voir si le retireEcole laisse l'école sur true
//      */
//     @Test
//     public static void testRetireEcole() {
//         Ville v1 = new Ville("a");
//         Ville v2 = new Ville("b");
//         Ville v3 = new Ville("c");

//         v1.ajouteRoutes(v2);
//         v1.ajouteRoutes(v3);

//         v2.ajouteRoutes(v1);
//         v3.ajouteRoutes(v1);

//         v1.retireEcole();
//         assertFalse(v1.getEcole()); //On veut que l'école soit bien sur false, car les villes voisine ont des écoles

//         v1.ajouteEcole(); //On remet école sur true

//         v2.retireEcole();
//         v3.retireEcole();

//         v1.retireEcole();
//         assertTrue(v1.getEcole()); //On veut que l'école soit resté true, malgré le retireEcole exécuté précédemment, car les villes voisine n'ont pas d'école

//     }

//     /**
//      * Test la méthode ajouteEcole() de Ville
//      * Le scénario est de retirer une école, puis de rajouter une école avec la méthode ajouteEcole() et vérifier si école est bien true
//      */
//     @Test
//     public static void testAjouteEcole() {
//         Ville v1 = new Ville("a");

//         v1.retireEcole();

//         v1.ajouteEcole();

//         assertTrue(v1.getEcole()); 
//     }
// }
