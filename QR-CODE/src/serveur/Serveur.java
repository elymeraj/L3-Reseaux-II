package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    private static final int PORT = 8081; // port par defaut

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur demarre sur le port " + PORT);

            while (true) {
                System.out.println("En attente de connexion...");
                Socket clientSocket = serverSocket.accept(); // attend co
                System.out.println("Connexion accepter : " + clientSocket.getInetAddress().getHostAddress());

                Thread clientThread = new Thread(new ConnectionHandlerServeur(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
