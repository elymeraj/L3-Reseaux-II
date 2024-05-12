package symbQR;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import symbQR.Cellule.Pixel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;



public class LecteurQR {
    public Boolean detaille = false;

    Cellule qr[][];
    Protocole protocole;
    int dimP;
    int dimC;
    int nb_bit_controle;
    int val_controle;
    String messageAControler = "";
    int tronc;

    public LecteurQR(){

    }

    public void activeDetaille(){
        this.detaille = true;
    }

    public String LireQR(String chemin){
        try {
            File file = new File(chemin);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList metadataList = doc.getElementsByTagName("metadata");
            Element metadata = (Element) metadataList.item(0); // Supposant qu'il y ait une seule balise metadata
            this.dimC = Integer.parseInt(metadata.getElementsByTagName("dimension_matrice_cellule").item(0).getTextContent());
            int numProtocole = Integer.parseInt(metadata.getElementsByTagName("protocole").item(0).getTextContent());
            this.protocole = ProtocoleFactory.createProtocole(numProtocole);
            this.dimP = protocole.getDimMatPixel();
            int dimPcarre = dimP*dimP;
            String[] couleur = new String[dimPcarre];
            this.qr = new Cellule[dimC][dimC];
            NodeList rectList = null;

            rectList = doc.getElementsByTagName(protocole.getTagName());

            for (int i = 0; i < rectList.getLength(); i++) {
                
                Element rect = (Element) rectList.item(i);
                int dI = Integer.parseInt(rect.getAttribute("data-i"));
                int dJ = Integer.parseInt(rect.getAttribute("data-j"));

                //int dM = Integer.parseInt(rect.getAttribute("data-m"));
                //int dN = Integer.parseInt(rect.getAttribute("data-n"));
                String dC = rect.getAttribute("fill");
                couleur[i%dimPcarre] = dC;
                if ((i+1) % dimPcarre == 0){
                    if (protocole.getProtocole()!=0 && noirComplet(couleur)){
                        qr[dI][dJ] = new Cellule(protocole);
                    }else{
                        qr[dI][dJ] = new Cellule(protocole, couleur);
                        //couleur = new String[dimPcarre];
                    }
                    qr[dI][dJ].toString();
                }

                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        orienter();
        //rotate90(qr);

        //getCrc
        //getProtocole
        //String protocole = "0";
        String messageBinaire = "";
        
        for (int i=0; i<dimC; i++){
            for (int j=0; j<dimC; j++){
                if (!qr[i][j].estIndex()){
                    messageBinaire += qr[i][j].getBinaire(protocole);
                }else{
                    //System.out.println("je suis index " + i + " " + j);

                }
            }
        }
        String message = messageBinaire;
        if (detaille){
            System.out.println("lecture");
            System.out.println("message initial: " + message);
        }
        message = messageFinal(message);

        if (detaille){
            System.out.println("message reconstituer: " + messageAControler + message);
        }

        messageAControler = messageAControler + message;// reconstitution du message qui a fait l'objet du codage de hamming
        
        message = hammingDecode(messageAControler);
        
        message = BToA(message);
        if (detaille){
            System.out.println("message avant depermutation: " + message);
        }
        message = depermuter(message);

        return message;
    }

    public static String AToB(String message){  

        byte[] bytesMessage = message.getBytes();  
        StringBuilder binMsg = new StringBuilder();  
        for (byte b : bytesMessage)  
        {  
           int val = b;  
           for (int i = 0; i < 8; i++)  
           {  
              binMsg.append((val & 128) == 0 ? 0 : 1);  
              val <<= 1;  
           }  
          // binary.append(' ');  
        }  
        return binMsg.toString();  
  }

    public String messageFinal(String message){
        String msg = "";
        String proto = message.substring(0, 8);
        String nb_cel_init = message.substring(8, 16);
        int p = Integer.parseInt(proto, 2);
        int nb_cel_ini = Integer.parseInt(nb_cel_init, 2);
        int k = 16;
        String t = "";
        for (int i=0; i<nb_cel_ini; i++){
            t += message.substring(k, k+8);
            k += 8;
        }
        messageAControler = proto + nb_cel_init + t;
        int taille = Integer.parseInt(t, 2);
        msg = message.substring(k, k+taille);
        tronc = k;
        nb_bit_controle = (int) Math.ceil(Math.log(msg.length()+1) / Math.log(2));
        if (detaille){
            System.out.println("taille message: " + message.length());
            System.out.println("nombre de bit de controle: " + nb_bit_controle);
        }
        while (nb_bit_controle%8 != 0){
            nb_bit_controle++;
        }
        if (detaille){
            System.out.println("nombre de bit de controle: " + nb_bit_controle);
        }

        //nb_bit_controle = (nb_bit_controle + 7) & ~7; // arrondi au multiple de 8 superieur
        String vc = message.substring(k + taille, k + taille + nb_bit_controle);
        val_controle = Integer.parseInt(vc, 2);

        if (detaille){
            System.out.println("recuperation procole: " + p + ", en binaire: " + proto);
            System.out.println("recuperation nombre cellule pour initialiser: " + nb_cel_ini + ", en binaire: " + nb_cel_init);
            System.out.println("recuperation taille du message: " + taille + ", en binaire: " + t);
            System.out.println("recuperation message: " + msg);
            System.out.println("recuperation bit de controle: " + val_controle + ", en binaire: " + vc);
        }
        
        return msg;
    }

    public static String depermuter(String message) {
        int taille = message.length();
        int mid = taille / 2;
        int j = mid;
        if (taille%2 == 1){
            j++;
        }
        int k = 0;
        String res = "";
    
        for (int i = 0; i < taille; i++) {
            if (i % 2 == 0) {
                res += message.charAt(k);
                k++;
            } else {
                res += message.charAt(j);
                j++;
            }
        }
        return res;
    }
    
    private void orienter() {
        if (!qr[0][0].estIndex() || !qr[0][dimC-1].estIndex() || !qr[dimC-1][dimC-1].estIndex()){
            rotate90(qr);
            orienter();
        }
    }

    public static void rotate90(Cellule[][] matrix) {
        transpose(matrix);
        for (Cellule[] row : matrix) {
            reverse(row);
        }
    }

    private static void transpose(Cellule[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                Cellule temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private static void reverse(Cellule[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            Cellule temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    private boolean noirComplet(String[] couleur) {
        boolean noir = true;
        Pixel pixel;
        for (String s : couleur){
            pixel = new Pixel(s);
            if (!pixel.estNoir()){
                noir = false;
            }
        }
        return noir;
    }

    public static String BToA(String messageBinaire) {
        StringBuilder asciiString = new StringBuilder();
        int charCode;
        for (int i = 0; i < messageBinaire.length(); i += 8) {
            String binaryChar = messageBinaire.substring(i, Math.min(i + 8, messageBinaire.length()));
            charCode = Integer.parseInt(binaryChar, 2);
            asciiString.append((char) charCode);
        }
        return asciiString.toString();
    }

    public void fichierSVG(String svg, String nomFichier) {//utilise pour le serveur
        try {
            FileWriter fileWriter = new FileWriter(nomFichier);
            fileWriter.write(svg);
            fileWriter.close();
            System.out.println("Fichier SVG cree: " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la creation du fichier SVG : " + e.getMessage());
        }
    }

    public String hammingDecode(String message) {
        int taille_total = message.length() + nb_bit_controle;
        ArrayList<Integer> liste = new ArrayList<>();
        int p = 0;
        for (int i = 1; i <= taille_total; i++) {
            if ((i & (i - 1)) != 0) {
                if (p < message.length() && message.charAt(p) == '1') {
                    liste.add(i);
                }
                p++;
            }
        }
        int pariter = val_controle;
        for (int e : liste) {
            pariter = pariter ^ e;
        }

        if (detaille){
            if (pariter == 0){
                System.out.println("pas d'erreur detecter grace au codage de hamming");
            }else{
                System.out.println("erreur detecter, bit de controle: " + pariter + ", en binaire: " + Integer.toBinaryString(pariter));
            }
            
        }

        if (pariter != 0){
            pariter--; // pour decaler car calculer effectuer  pour un message de 1 a n +1 pour  0 a n

            p = ajusteIndex(pariter);

            char bit_inverser = message.charAt(p);
            bit_inverser = (bit_inverser == '0') ? '1' : '0';
            StringBuilder msg_corriger = new StringBuilder(message);
            msg_corriger.setCharAt(p, bit_inverser);

            message = msg_corriger.toString();
        }

        return message.substring(tronc, message.length());
    }

    public int ajusteIndex(int pariter) {
        int index = pariter;
        int count = 0;
        for (int i = 1; i <= index; i *= 2) {
            if ((index & i) != 0) {
                count++;  
            }
        }
        return index - count; 
    }
    
}
