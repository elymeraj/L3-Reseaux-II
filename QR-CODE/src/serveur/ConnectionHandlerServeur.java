package serveur;

import symbQR.LecteurQR;
import symbQR.GenerateurQR;

import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionHandlerServeur implements Runnable {
    private Socket clientSocket;
    private String adr = "./test.svg";

    public ConnectionHandlerServeur(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.length() >= 8) {
                    if (estQRCode(message)) {
                        System.out.println("Debut de la reception du message SVG");
                        int svgIndex = message.indexOf("<svg");
                        if (svgIndex != -1) {
                            adr = message.substring(2, svgIndex);
                            message = message.substring(svgIndex);
                        } else {
                            System.out.println("Erreur SVG : balise <svg> non trouvee");
                        }
                        StringBuilder receivedContent = new StringBuilder();
                        receivedContent.append(message);
                        String line;
                        while ((line = reader.readLine()) != null && !line.equals("</svg>")) {
                            receivedContent.append(line).append("\n");
                        }
                        receivedContent.append("</svg>");
                        message = receivedContent.toString();

                        LecteurQR lecteurQR = new LecteurQR();
                        lecteurQR.activeDetaille();
                        lecteurQR.fichierSVG(message, adr);
                        String lec = lecteurQR.LireQR(adr);
                        System.out.println("Contenu du QR code : " + lec);
                        writer.println("Message recu : " + lec);
                    } else {
                        System.out.println("Message texte recu : " + message);
                        String protocole = message.substring(0, 8);
                        int p = Integer.parseInt(protocole, 2);
                        message = message.substring(8);
                        GenerateurQR generateurQR = new GenerateurQR();
                        generateurQR.genQR(message, p);
                        String svgPath = getQRcode("./QRcode");
                        if (svgPath != null) {
                            try (BufferedReader fileReader = new BufferedReader(new FileReader(svgPath))) {
                                String line;
                                while ((line = fileReader.readLine()) != null) {
                                    writer.println(line);
                                }
                            } catch (IOException e) {
                                System.err.println("Erreur de lecture du fichier SVG : " + e.getMessage());
                            }
                        } else {
                            writer.println("Erreur : fichier SVG non trouver.");
                        }
                    }
                } else {
                    System.err.println("Message recu trop court pour etre traiter.");
                }
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur IO: " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture de la socket: " + e.getMessage());
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
        int max = -1;
        File fichier = null;

        for (File file : files) {
            Matcher match = pattern.matcher(file.getName());
            if (match.find()) {
                int number = Integer.parseInt(match.group(1));
                if (number > max) {
                    max = number;
                    fichier = file;
                }
            }
        }

        return (fichier != null) ? fichier.getAbsolutePath() : null;
    }
}
