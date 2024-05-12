package symbQR;

import java.awt.Color;

public class Cellule {

    static class Pixel {
        private static final int TOLERANCE = 10;
        int rouge;   
        int vert;  
        int bleu;   

        public Pixel(int rouge, int vert, int bleu) {
            
            if (valide(rouge)){
                this.rouge = rouge;
            } else {
                throw new IllegalArgumentException("La valeur de rouge n'est pas valide : " + rouge);
            }
            if (valide(vert)){
                this.vert = vert;
            } else {
                throw new IllegalArgumentException("La valeur de vert n'est pas valide : " + vert);
            }
            if (valide(bleu)){
                this.bleu = bleu;
            } else {
                throw new IllegalArgumentException("La valeur de bleu n'est pas valide : " + bleu);
            }
        }

        public Pixel(String couleurHexa){
            this.rouge = Integer.parseInt(couleurHexa.substring(1, 3), 16);
            this.vert = Integer.parseInt(couleurHexa.substring(3, 5), 16);
            this.bleu = Integer.parseInt(couleurHexa.substring(5, 7), 16);
        }

        public int getRouge() {
            return rouge;
        }
    
        public int getVert() {
            return vert;
        }
    
        public int getBleu() {
            return bleu;
        }

        private boolean valide(int couleur) {
            return 0 <= couleur && couleur <= 255;
        }

        public boolean estBlanc() {
            return estDansTolerance(rouge, 255) && estDansTolerance(vert, 255) && estDansTolerance(bleu, 255);
        }

        public boolean estNoir() {
            return estDansTolerance(rouge, 0) && estDansTolerance(vert, 0) && estDansTolerance(bleu, 0);
        }
    
        public boolean estRouge() {
            return estDansTolerance(rouge, 255) && estDansTolerance(vert, 0) && estDansTolerance(bleu, 0);
        }
    
        public boolean estVert() {
            return estDansTolerance(rouge, 0) && estDansTolerance(vert, 255) && estDansTolerance(bleu, 0);
        }
    
        public boolean estBleu() {
            return estDansTolerance(rouge, 0) && estDansTolerance(vert, 0) && estDansTolerance(bleu, 255);
        }
    
        public boolean estJaune() {
            return estDansTolerance(rouge, 255) && estDansTolerance(vert, 255) && estDansTolerance(bleu, 0);
        }
    
        private boolean estDansTolerance(int valeurSVG, int valeurRef) {
            int tolerance = (int) (valeurRef * (TOLERANCE / 100.0)); 
            return valeurSVG >= valeurRef - tolerance && valeurSVG <= valeurRef + tolerance;
        }

        @Override
        public String toString(){
            return "" + getRouge() + ", " + getVert() + ", " + getBleu();
        }

    }

    Pixel[][] pixels;
    int dimP; 
   // int taille;
    int tailleP;
    Protocole protocole;
    String erreur = "00111111";

    @Override
    public String toString(){
        String s = "";
        for (int i=0; i<dimP; i++){
            for (int j=0; j<dimP; j++){
                s += pixels[i][j].toString() + "; ";
            }
            s += "\n";
        }
        return s;
    }

/*     public Cellule(int taille, String message) {
        this.taille = taille;
        this.tailleCarre = (int) Math.ceil(Math.sqrt(taille)) + 1;
        this.pixels = new Pixel[tailleCarre][tailleCarre];
        this.setCellule(message);

    }*/

    public Cellule(Protocole protocole, String message) {
        this.protocole = protocole;
        this.dimP = protocole.getDimMatPixel();
        this.tailleP =protocole.getTailleMatPixel();
        this.pixels = new Pixel[dimP][dimP];
        this.setCellule(message);

    }

    public Cellule(Protocole protocole, String[] couleur) {
        this.protocole = protocole;
        this.dimP = protocole.getDimMatPixel();
        this.tailleP =protocole.getTailleMatPixel();
        this.pixels = new Pixel[dimP][dimP];
        this.setCellule(couleur);

    }

    public Cellule(Protocole protocole){
        this.protocole = protocole;
        this.dimP = protocole.getDimMatPixel();
        this.tailleP =protocole.getTailleMatPixel();
        this.pixels = new Pixel[dimP][dimP];
        this.setCellule();
    }

    public void setCellule(){
        for (int i=0; i<dimP; i++){
            for (int j=0; j<dimP; j++){
                this.pixels[i][j] = new  Pixel(0, 0, 0);
            } 
        }
    }

    public boolean estIndex(){
        boolean index = true;
        for (int i=0; i<dimP; i++){
            for (int j=0; j<dimP; j++){
                if(!this.pixels[i][j].estNoir()){
                    index = false;
                }
            } 
        }
        return index;
    }



    public Pixel getPixel(int k){
        int i = k/dimP;
        int j = k%dimP;

        return pixels[i][j];
    }

    public void setCellule(String message){
        char c;
        char d;
        int p = 0;
        for (int i=0; i<dimP; i++){
            for (int j=0; j<dimP; j++){
                if (protocole.getProtocole() == 0){
                    if (p < message.length()){
                        c = message.charAt(p);
                    }else{
                        c = '0';
                    }
                    if (c == '0'){
                        this.pixels[i][j] = new  Pixel(255, 255, 255);//blanc
                    }else{
                        this.pixels[i][j] = new  Pixel(0, 0, 0);//noir
                    }
                    p++;
                }else if (protocole.getProtocole() == 1 || protocole.getProtocole() == 2){
                    c = message.charAt(p);
                    p++;
                    d = message.charAt(p);
                    p++;
                    if (c == '0' && d == '0') {
                        this.pixels[i][j] = new Pixel(255, 0, 0); // rouge
                    } else if (c == '0' && d == '1') {
                        this.pixels[i][j] = new Pixel(0, 255, 0); // vert
                    } else if (c == '1' && d == '0') {
                        this.pixels[i][j] = new Pixel(0, 0, 255); // bleu
                    } else if (c == '1' && d == '1') {
                        this.pixels[i][j] = new Pixel(255, 255, 0); // jaune
                    }
                }
            }
            
        }

    }

    public void setCellule(String[] couleur){
        Color c;
        int p = 0;
        for (int i=0; i<dimP; i++){
            for (int j=0; j<dimP; j++){
                c = Color.decode(couleur[p]);
                this.pixels[i][j] = new  Pixel(c.getRed(), c.getGreen(), c.getBlue());
                p++;
            }
            
        }

    }

    public String getBinaire(Protocole protocole){
        String message = "";
        Boolean alterer = false;
        Pixel pixel;
        for (int i=0; i<dimP; i++){
            for (int j=0; j<dimP; j++){
                pixel = pixels[i][j];
                if (!estIndex()){
                    if (protocole.getProtocole() == 0){
                        if (i+1!=dimP || j+1!=dimP){
                            
                            if (pixel.estBlanc()){
                                message += "0";
                            }else if(pixel.estNoir()){
                                message += "1";
                            }else{
                                alterer = true;
                            }
                        }
                    }else if (protocole.getProtocole() == 1 || protocole.getProtocole() == 2){
                        pixel = pixels[i][j];
                        if (pixel.estRouge()){
                            message += "00";
                        }else if(pixel.estVert()){
                            message += "01";
                        }else if(pixel.estBleu()){
                            message += "10";
                        }else if(pixel.estJaune()){
                            message += "11";
                        }else{
                            alterer = true;
                        }
                    }

                }
            }
        }
        if (alterer){
            message = this.erreur;
        }
        return message;
    }
}

