package client;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ConnectionHandlerClient connectionHandlerClient = new ConnectionHandlerClient();
        boolean communiquer = true;
        boolean newCon = true;
        boolean valide;
        int choix;
    
        do {
            if (newCon) {
                con(connectionHandlerClient);
            }
    
            message(connectionHandlerClient);
            do {
                valide = true;
                communiquer = true;
                System.out.println("1. Envoyer un autre message a la meme machine");
                System.out.println("2. Envoyer un autre message a une autre machine");
                System.out.println("3. quitter");
                System.out.print("Entrez votre choix (1 ou 2 ou 3) : ");
    
                if (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    
                    // verfi si entier
                    if (input.matches("\\d+")) {
                        choix = Integer.parseInt(input);
    
                        switch (choix) {
                            case 1:
                                newCon = false;
                                break;
                            case 2:
                                newCon = true;
                                break;
                            case 3:
                                communiquer = false;
                                break;
                            default:
                                System.out.println("Choix non valide.");
                                valide = false;
                                break;
                        }
                    } else {
                        valide = false;
                        System.out.println("Entree non valide. Entrez un nombre entier.");
                    }
                }
            } while (!valide);
    
        } while (communiquer);
    
        scanner.close(); 
    }
    
    public static String lireQR(String chemin){
        String qrcode = "";
         try {
            qrcode = new String(Files.readAllBytes(Paths.get(chemin)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrcode;

    }

    private static void con(ConnectionHandlerClient connectionHandler) {
        Scanner scanner = new Scanner(System.in);
            boolean valide;
            int choix = 0;
            String input;
            do {
                valide = true;
                System.out.println("Choisir une machine.");
                System.out.println("1. Se connecter au serveur local");
                System.out.println("2. Se connecter a une autre machine");
                System.out.print("Entrez votre choix (1 ou 2) : ");
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();
   
                    //input est bien de type int
                    if (input.matches("\\d+")) {
                        choix = Integer.parseInt(input);
                
                        switch (choix) {
                            case 1:
                                connectionHandler.connectToServer();
                                break;
                            case 2:
                                System.out.print("Entrez l'adresse IP du serveur : ");
                                String serveurAdr = scanner.nextLine();
                                System.out.print("Entrez le port du serveur : ");
                                int serveurPort = scanner.nextInt();
                                scanner.nextLine();
                            
                                connectionHandler.connectToServer(serveurAdr, serveurPort);
                                break;
                            default:
                                valide = false;
                                System.out.println("Choix non valide.");
                                break;
                        }
                    } else {
                        valide = false;
                        System.out.println("Entree non valide. Entrez un nombre entier.");
                    }
                }   
                
            } while (!valide);
    
    }
    

    private static void message(ConnectionHandlerClient connectionHandler) throws IOException {
        Scanner scanner = new Scanner(System.in);
            int choixProtocole; 
            boolean valide = false;
            int choix = 0;
            String input;
            do {
                valide = true;
                System.out.println("1. Envoyer un message textuel et recevoir un QR code");
                System.out.println("2. Envoyer un QR code et recevoir un message textuel");
                System.out.print("Entrez votre choix (1 ou 2) : ");
   
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();
   
                    //input est bien de type int
                    if (input.matches("\\d+")) {
                        choix = Integer.parseInt(input);
   
                        switch (choix) {
                            case 1:
                                choixProtocole = choixProtocole(); 
                                String protocole = Integer.toBinaryString(choixProtocole);

                                while (protocole.length()%8!=0){    
                                    protocole = "0" + protocole;
                                }
                                
                                System.out.print("Entrez votre message textuel : ");
                                String messageTextuel = scanner.nextLine();
                                connectionHandler.envoyerMessageAuServeur(protocole + messageTextuel);
                                connectionHandler.recevoirFichierSVG("./ok.svg");
                                break;
                            case 2:
                                System.out.print("Entrez le chemin vers votre fichier QR code SVG : ");
                                String chemin = scanner.nextLine();
   
                                String contenu = lireQR(chemin);
                                connectionHandler.envoyerMessageAuServeur("QR" + chemin + contenu);
                                connectionHandler.lireMessage();
                                break;
                            default:
                                valide = false;
                                System.out.println("Choix non valide.");
                                break;
                        }
                    } else {
                        valide = false;
                        System.out.println("Entree non valide. Entrez un nombre entier.");
                    }
                }
            } while (!valide);
    }
    
    private static int choixProtocole(){
        Scanner scanner = new Scanner(System.in);
            int choix = 0;
            boolean valide = false; // Initialiser valide à false
            String input;
            do {
                System.out.println("choix du protocole : ");
                System.out.println("0. bianire  ");
                System.out.println("1. rond  ");
                System.out.println("2. triangle ");
                System.out.print("Entrez votre choix (0 ou 1 ou 2) : ");
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();
                    //input est bien de type int
                    if (input.matches("\\d+")) {
                        choix = Integer.parseInt(input);
                        if (choix == 0 || choix == 1 || choix == 2){
                            valide = true; 
                        }
                    } else {
                        System.out.println("Entree non valide. Entrez un nombre entier.");
                    }
                }
                
            } while (!valide); 
                return choix;

    }
    
    
    
}
