package serveur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import symbQR.LecteurQR;
import symbQR.GenerateurQR;

import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ConnectionHandlerServeur implements Runnable {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String adr = "./test.svg";

    public ConnectionHandlerServeur(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
/* 
    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String message;
            while ((message = reader.readLine()) != null) {

                /*if (estQRCode(message)) {
                    System.out.println("avant" + message);
                    System.out.println("apres");
                    int svgIndex = message.indexOf("<svg");
                    if (svgIndex != -1) {
                        adr = message.substring(2, svgIndex);
                        message = message.substring(svgIndex);
                        System.out.println(message);
                    } else {
                        System.out.println("erreur svg.");
                    }
                    System.out.println("QR code reçu");
                    LecteurQR lecteurQR = new LecteurQR();
                    lecteurQR.fichierSVG(message, adr);
                    String lec = lecteurQR.LireQR(adr);

                    System.out.println("Contenu du QR code : " + lec);
                    writer.println("Message reçu : " + lec);
                    writer.flush();
                }//remettre fin commentaire
                if (estQRCode(message)) {
                    System.out.println("Début de la réception du message SVG");
                    int svgIndex = message.indexOf("<svg");
                    if (svgIndex != -1) {
                        adr = message.substring(2, svgIndex);
                        message = message.substring(svgIndex);
                    } else {
                        System.out.println("erreur svg");
                    }
                    StringBuilder receivedContent = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {

                        receivedContent.append(line + "\n");
                    }
                    String svgContent = receivedContent.toString();
                    message = message + "\n" + svgContent;

                    LecteurQR lecteurQR = new LecteurQR();
                    lecteurQR.fichierSVG(message, adr);
                    String lec = lecteurQR.LireQR(adr);
                    System.out.println("Contenu du QR code : " + lec);
                    writer.println("Message reçu : " + lec);
                    writer.flush();
                }
                
                
                else {
                    System.out.println("Message texte reçu : " + message);
                    String protocole = message.substring(0, 8);
                    int p = Integer.parseInt(protocole);
                    message = message.substring(8, message.length());
                    GenerateurQR generateurQR = new GenerateurQR();
                    generateurQR.genQR(message, p);
                    adr = getQRcode("./QRcode");
                    try (BufferedReader fileReader = new BufferedReader(new FileReader(adr))) {
                        String line;
                        while ((line = fileReader.readLine()) != null) {
                            writer.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    @Override
    public void run() {

        try (
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
                String message;
                while ((message = reader.readLine()) != null) {

                    /*if (estQRCode(message)) {
                        System.out.println("avant" + message);
                        System.out.println("apres");
                        int svgIndex = message.indexOf("<svg");
                        if (svgIndex != -1) {
                            adr = message.substring(2, svgIndex);
                            message = message.substring(svgIndex);
                            System.out.println(message);
                        } else {
                            System.out.println("erreur svg.");
                        }
                        System.out.println("QR code reçu");
                        LecteurQR lecteurQR = new LecteurQR();
                        lecteurQR.fichierSVG(message, adr);
                        String lec = lecteurQR.LireQR(adr);

                        System.out.println("Contenu du QR code : " + lec);
                        writer.println("Message reçu : " + lec);
                        writer.flush();
                    }*/
                    if (estQRCode(message)) {
                        System.out.println("Début de la réception du message SVG");
                        int svgIndex = message.indexOf("<svg");
                        if (svgIndex != -1) {
                            adr = message.substring(2, svgIndex);
                            message = message.substring(svgIndex);
                        } else {
                            System.out.println("erreur svg");
                        }
                        StringBuilder receivedContent = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {

                            receivedContent.append(line + "\n");
                        }
                        String svgContent = receivedContent.toString();
                        message = message + "\n" + svgContent;

                        LecteurQR lecteurQR = new LecteurQR();
                        lecteurQR.fichierSVG(message, adr);
                        String lec = lecteurQR.LireQR(adr);
                        System.out.println("Contenu du QR code : " + lec);
                        writer.println("Message reçu : " + lec);
                        writer.flush();
                    }
                    
                    
                    else {
                        System.out.println("Message texte reçu : " + message);
                        String protocole = message.substring(0, 8);
                        int p = Integer.parseInt(protocole);
                        message = message.substring(8, message.length());
                        GenerateurQR generateurQR = new GenerateurQR();
                        generateurQR.genQR(message, p);
                        adr = getQRcode("./QRcode");
                        try (BufferedReader fileReader = new BufferedReader(new FileReader(adr))) {
                            String line;
                            while ((line = fileReader.readLine()) != null) {
                                writer.println(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if ("exit".equalsIgnoreCase(message)) {
                        break;
                    }
        } catch (IOException e) {
                System.err.println("Erreur IO: " + e.getMessage());
        } finally {
            try {
                clientSocket.close(); // Assurez-vous de fermer la socket
            } catch (IOException ex) {
                System.err.println("Erreur lors de la fermeture de la socket: " + ex.getMessage());
            }
        }

    }

    private boolean estQRCode(String message) {
        return message.trim().startsWith("QR");
    }

    private String getQRcode(String dossier) {
        File dir = new File(dossier);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null; 
        }
    
        Pattern pattern = Pattern.compile("QRcode_(\\d+)\\.svg");
        int maxNumber = -1;
        File latestFile = null;
    
        for (File file : files) {
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.find()) {
                int number = Integer.parseInt(matcher.group(1));
                if (number > maxNumber) {
                    maxNumber = number;
                    latestFile = file;
                }
            }
        }
    
        return (latestFile != null) ? latestFile.getAbsolutePath() : null;
    }
}
