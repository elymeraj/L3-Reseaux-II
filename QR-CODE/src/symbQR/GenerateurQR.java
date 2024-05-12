package symbQR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



public class GenerateurQR {
    int nb_bit_controle = 0;

    public Boolean detaille = false;
    

    Cellule qr[][];
    int taille;
    int tailleAscii;
    int tailleBin;
    int tailleCarre;
    Protocole protocole;
    int dimP;
    int nb_cellule_initialisation;

    public GenerateurQR(){
    }

    public void activeDetaille(){
        this.detaille = true;
    }

    public void genQR(String message, int p){
        this.protocole = ProtocoleFactory.createProtocole(p);
        this.dimP = protocole.getDimMatPixel();
        this.tailleAscii = message.length();

        if (detaille){
            System.out.println("generation");
            System.out.println("message initial: " + message);
        }

        message = permuter(message);

        if (detaille){
            System.out.println("message permuter: " + message);
        }
        
        message = AToB(message);
        this.tailleBin = message.length();

        if (detaille){
            System.out.println("message initial en binaire: " + message);
        }

        

        String msgFinal = messageFinal(message);

        if (detaille){
            System.out.println("message initial avec toutes les information: " + msgFinal);
            System.out.println("taille du message final: " + msgFinal.length());
        }

        this.tailleCarre = (int) Math.sqrt(tailleAscii+this.nb_cellule_initialisation)+1; // probleme a gere taille crc et protocole
        this.qr = new Cellule[tailleCarre][tailleCarre];

        this.taille = message.length();
		String sousString;
	    int k = 0;
        //int celluleVide = tailleCarre*tailleCarre - tailleAscii;


    	for (int i=0; i<tailleCarre; i++){
			for (int j=0; j<tailleCarre; j++){ // 001010010110101010
                if (i + j == 0){
                    qr[i][j] = new Cellule(protocole);
                }else if (i == 0 && j+1 == tailleCarre){
                    qr[i][j] = new Cellule(protocole);
                }else if (i+j+2 == 2*tailleCarre){
                    qr[i][j] = new Cellule(protocole);
                }else{
                    if (k < taille+(nb_cellule_initialisation*8)) {
                        sousString = msgFinal.substring(k, Math.min(k + 8, msgFinal.length()));
                        qr[i][j] = new Cellule(protocole, sousString);
                        k += 8;
                    }else{
                        qr[i][j] = new Cellule(protocole, "00000000");
                    }
                    qr[i][j].toString();
                }
			}
        }
        //rotate90(qr);
        //rotate90(qr);
        //rotate90(qr);

        String svg = protocole.toSVG(qr, 20, tailleCarre, dimP);
        
        fichierSVG(svg);
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

  public static String permuter(String message) {
    String pair = "";
    String impair = "";

    int taille = message.length();
    for (int i=0; i<taille; i++){
        if (i%2==0){
            pair += message.charAt(i);
        }else{
            impair += message.charAt(i);
        }
    }
    return pair + impair;
 }

 public static String depermuter(String message) {
    int taille = message.length();
    int mid = taille/2;
    int j = 0;
    int k = 0;
    System.out.println(mid);
    String res = "";

    for (int i=0; i<taille; i++){
        if (i%2==0){
            res += message.charAt(k);
            k++;
        }else{
            if (taille % 2 != 0 && j + mid >= taille) {
                break; 
            }
            res += message.charAt(j+mid);
            j++;
        }
    }

    return res;
  }

  private void fichierSVG(String svg) {
    String nomDossier = "QRcode";
    File dir = new File(nomDossier);
    if (!dir.exists()) {
        dir.mkdirs();
    }

    int maxNum = 0;
    Pattern pattern = Pattern.compile("QRcode_(\\d+)\\.svg");
    File[] files = dir.listFiles();
    if (files != null) {
        for (File file : files) {
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.matches()) {
                int num = Integer.parseInt(matcher.group(1));
                if (num > maxNum) {
                    maxNum = num;
                }
            }
        }
    }
    File svgFile = new File(dir, "QRcode_" + (maxNum + 1) + ".svg");

    try (FileWriter fileWriter = new FileWriter(svgFile)) {
        fileWriter.write(svg);
        System.out.println("Fichier SVG cree : " + svgFile.getPath());
    } catch (IOException e) {
        System.out.println("Erreur lors de la creation du fichier SVG : " + e.getMessage());
    }
 }

  public String messageFinal(String message){ //peut etre pas necessaire
    //int complementCRC = TAILLE_CRC - crc.length();

    /*String msgFinal = protocole + crc(message) + message;
    return msgFinal;*/
    String clef = "";
    int p = protocole.getProtocole();
    clef += Integer.toBinaryString(p);
    int i = clef.length();
    for (; i<8; i++){
        clef = "0" + clef;
    }
    int nb_cel_init = (int) Math.ceil((int) Math.ceil(Math.log(tailleBin) / Math.log(2)) / 8.0);
    String nb_cel_ini = Integer.toBinaryString(nb_cel_init); 
    while (nb_cel_ini.length()%8 != 0){
        nb_cel_ini = "0" + nb_cel_ini;
    }

    String tailleFull = Integer.toBinaryString(tailleBin);
    while (tailleFull.length()%8 != 0){
        tailleFull = "0" + tailleFull;
    }
    nb_bit_controle = (int) Math.ceil(Math.log(message.length()) / Math.log(2)); // a voir

    this.nb_cellule_initialisation = nb_cel_init + (int) Math.ceil(nb_bit_controle/8.0) + 2;

    message =  clef + nb_cel_ini + tailleFull + message;
    message = hammingCode(message);
    if (detaille){
        System.out.println("protocole: " + p + ", en binaire: " + clef);
        System.out.println("nombre de cellule pour calculer la taille du message: " + nb_cel_init + ", en binaire: " + nb_cel_ini);
        System.out.println("taille du message: " + tailleBin + ", en binaire: " + tailleFull);
    }

    return message;
  }

  public String hammingCode(String message) {
    nb_bit_controle = (int) Math.ceil(Math.log(message.length()+ 1) / Math.log(2));
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
    int pariter = 0;
    for (int e : liste) {
        pariter = pariter ^ e;
    }

    String bc = Integer.toBinaryString(pariter);

    while (bc.length()%8!=0){
        bc = "0" + bc;
    }

    if (detaille){
        System.out.println("bit de control du message (message = protocole + nombre de cellule pour calculer la taille du message + taille du message + message): " + bc);
        System.out.println("taille message: " + message.length());
        System.out.println("taille bit de controle: " + bc.length());
    }

    return message + bc;
  }
    
}
