package symbQR;


public class TestQR {
    public static void main(String[] args) {
        String message = "Hello, world!";
        //String message = "Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!Hello, world!";
        //String message = "hi";
        //String message = "un message qui pourrait etre de taille 'normale' si l'on voulait envoyer un message par QRcode";
        testGeneration(message);

        //String cheminFichierQRCode = "./test/test_hi.svg";
        String cheminFichierQRCode = "./test/HW_rond.svg";
        //String cheminFichierQRCode = "./test/HW_binaire.svg";
        //String cheminFichierQRCode = "./test/HW_triangle.svg";
        //String cheminFichierQRCode = "./test/test_Var_Couleur.svg";
        //String cheminFichierQRCode = "./test/test_Var_Couleur_max.svg";
        //String cheminFichierQRCode = "./test/test_moyen.svg";
        //String cheminFichierQRCode = "./test/test_long.svg";
        testDecodage(cheminFichierQRCode);



    }

    public static void testGeneration(String message) {
        
        //String message = "Hello";
        GenerateurQR generateurQR = new GenerateurQR();
        generateurQR.activeDetaille();
        //generateurQR.genQR(message);
        generateurQR.genQR(message, 2);

    }

    public static void testDecodage(String cheminFichierQRCode) {
        LecteurQR lecteurQR = new LecteurQR();
        lecteurQR.activeDetaille();
        String contenuDecoder = lecteurQR.LireQR(cheminFichierQRCode);

        System.out.println("Contenu du QR code : " + contenuDecoder);
    }

    
}
