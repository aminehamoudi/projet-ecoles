// package tests;

// import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import org.junit.jupiter.api.Test;

// import java.io.FileNotFoundException;
// import java.io.IOException;

// import agglomeration.CA;
// import utilitaire.UtilMain;
// import java.util.Scanner;


// public class UtilTestUtilMain {

//     /**
//      * Test la méthode createCities() de UtilMain.
//      * Le scénario est de passer un scanner contenant autre chose qu'un int.
//      */
//     @Test
//     public static void testThrowCreateCities() {
//         Scanner sc = new Scanner(System.in);
//         sc.nextLine();

//         /**
//          * On ne veut pas renvoyer d'erreur, on la catch mais les System.out.println avertisse l'utilisateur d'un problème
//          */
//         assertDoesNotThrow( 
//         () -> {
//             UtilMain.createCities(sc);
//         });
//     }

//     /**
//      * Test la méthode manageRoutes() de UtilMain.
//      * Le scénario est de passer un scanner contenant autre chose qu'un int.
//      */
//     @Test
//     public static void testThrowManageRoutes() {
//         Scanner sc = new Scanner(System.in);
//         CA ca = new CA();
//         sc.nextLine();

//         /**
//          * On ne veut pas renvoyer d'erreur, on la catch mais les System.out.println avertisse l'utilisateur d'un problème
//          */
//         assertDoesNotThrow( 
//         () -> {
//             UtilMain.manageRoutes(sc, ca);
//         });
//     }

//     /**
//      * Test la méthode loadCAFromFile() de UtilMain.
//      * Le scénario est de passer un chemin bidon et de récupérer un throw FileNotFoundException
//      */
//     @Test
//     public static void testThrowLoadCAFromFile() {
//         assertThrows(FileNotFoundException.class, 
//         () -> {
//             UtilMain.loadCAFromFile("cheminbidon", new CA());
//         });
//     }

//     /**
//      * Test la méthode saveCAToFile() de UtilMain.
//      * Le scénario est d'utiliser un chemin bidon contenu dans le scanner et de récupérer un throw IOException
//      */
//     @Test
//     public static void testThrowSaveCAToFile() {
//         Scanner sc = new Scanner(System.in);
//         CA ca = new CA();
//         sc.nextLine();

        
//         assertThrows(IOException.class, 
//         () -> {
//             UtilMain.saveCAToFile(sc, ca);
//         });
//     }
// }
