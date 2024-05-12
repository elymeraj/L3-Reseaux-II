package symbQR;

import symbQR.Cellule.Pixel;

public class ProtocoleBinaire implements Protocole {

    @Override
    public int getProtocole(){
        return 0;
    }

    @Override
    public int getDimMatPixel() {
        return 3;
    }

    @Override
    public String getTagName(){
        return "rect";
    }

/*      @Override
    public int getTailleMatPixel(){
        return getDimMatPixel() * getDimMatPixel();
    }*/

    @Override
    public String toSVG(Cellule[][] qr, int taille, int tailleCarre, int dimP){
    int epaisseurContour = 1; 
        
    
    int svgWidth = tailleCarre * dimP * taille + 20; 
    int svgHeight = tailleCarre * dimP * taille + 20;
    
    
    
    StringBuilder svg = new StringBuilder();
    svg.append("<svg width=\"").append(svgWidth).append("\" height=\"").append(svgHeight).append("\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    //svg.append("<svg width=\"").append(tailleCarre * dimP * taille).append("\" height=\"").append(tailleCarre * dimP * taille).append("\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    svg.append("<metadata>\n\t<dimension_matrice_cellule>").append(tailleCarre).append("</dimension_matrice_cellule>\n");
    //svg.append("\t<dimension_matrice_pixel>").append(3).append("</dimension_matrice_pixel>\n</metadata>\n");
    svg.append("\t<protocole>").append(getProtocole()).append("</protocole>\n</metadata>\n");
    
    
    for (int i = 0; i < tailleCarre; i++) {
        for (int j = 0; j < tailleCarre; j++) {
            for (int m = 0; m < dimP; m++) { 
                for (int n = 0; n < dimP; n++) { 
                    Pixel pixel = qr[i][j].getPixel(m * dimP + n); 
                    pixel.toString();
                    int x = j * dimP * taille + n * taille;
                    int y = i * dimP * taille + m * taille; 
    
                    String color = String.format("#%02x%02x%02x", pixel.getRouge(), pixel.getVert(), pixel.getBleu()); // Conversion du Pixel en couleur hexadécimale
                    //svg.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(taille).append("\" height=\"").append(taille).append("\" fill=\"").append(color).append("\" ").append("data-i=\"").append(i).append("\" data-j=\"").append(j).append("\"/>\n");
                    //svg.append("<circle cx=\"").append(x + taille / 2).append("\" cy=\"").append(y + taille / 2).append("\" r=\"").append(taille / 2).append("\" fill=\"").append(color).append("\" ").append("data-i=\"").append(i).append("\" data-j=\"").append(j).append("\"/>\n");
    
                    svg.append("<rect x=\"").append(x).append("\" y=\"").append(y)
                    .append("\" width=\"").append(taille).append("\" height=\"").append(taille)
                    .append("\" fill=\"").append(color)
                    .append("\" stroke=\"black\" stroke-width=\"").append(epaisseurContour).append("\" ")
                    .append("data-i=\"").append(i).append("\" data-j=\"").append(j).append("\"/>\n");
                        
                                       
                }
            }
        }
    }
    
    svg.append("</svg>");
    return svg.toString();
    }
   
}
