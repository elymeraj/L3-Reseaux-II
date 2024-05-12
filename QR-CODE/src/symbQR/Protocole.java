package symbQR;

public interface Protocole {
    int getProtocole();
    int getDimMatPixel();
    String getTagName();
    String toSVG(Cellule[][] qr, int taille, int tailleCarre, int dimP);
    
    public default int getTailleMatPixel(){
        return getDimMatPixel() * getDimMatPixel();
    }


    
}
