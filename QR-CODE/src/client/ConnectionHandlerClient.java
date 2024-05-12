package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;



public class ConnectionHandlerClient {
    private static final String SERVEUR_ADR = "localhost"; 
    private static final int SERVEUR_PORT = 8081; 

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public void connectToServer() {
        try {
            socket = new Socket(SERVEUR_ADR, SERVEUR_PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connecter au serveur sur le port " + SERVEUR_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToServer(String serveurAdr, int serveurPort) {
        try {
            socket = new Socket(serveurAdr, serveurPort);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connecter au serveur sur le port " + serveurPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void envoyerMessageAuServeur(String message) {
        writer.println(message);
        writer.flush();
    }

    public void lireMessage() throws IOException{
        String response = reader.readLine();
        System.out.println(response);
    }

    public void recevoirFichierSVG(String cheminDestination) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(cheminDestination));
            String line;
            while (!(line = reader.readLine()).equals("</svg>")) {
                writer.write(line);
                writer.newLine();
            }
            writer.write("</svg>");
            writer.newLine();
            writer.close();
            System.out.println("Fichier SVG recu et enregistre a l'emplacement : " + cheminDestination);
        } catch (SocketTimeoutException e) {
            System.err.println("Le delai d'attente pour la reception du fichier SVG a ete depasser.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la reception ou de l'ecriture du fichier SVG: " + e.getMessage());
        }
    }
    
}
