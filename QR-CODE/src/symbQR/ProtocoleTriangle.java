package symbQR;

import java.util.Random;
import symbQR.Cellule.Pixel;

public class ProtocoleTriangle implements Protocole {

    @Override
    public int getProtocole(){
        return 2;
    }
    
    @Override
    public int getDimMatPixel() {
        return 2;
    }

    @Override
    public String getTagName(){
        return "polygon";
    }
    
    @Override
    public String toSVG(Cellule[][] qr, int taille, int tailleCarre, int dimP){
    int epaisseurContour = 1; 
    
    Random random = new Random();

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

                    int x1 = x + taille / 2;  
                    int y1 = y;               
                    int x2 = x;               
                    int y2 = y + taille;      
                    int x3 = x + taille;      
                    int y3 = y + taille;      
                    int randomAngle = random.nextInt(361);
                        
                    //String path = "M" + x1 + " " + y1 + " L" + x2 + " " + y2 + " L" + x3 + " " + y3 + " Z";
                    String rotatedPoints = rotateTriangle(x1, y1, x2, y2, x3, y3, randomAngle);

                    String polygon = "<polygon points=\"" + rotatedPoints + "\" fill=\"" + color + "\" stroke=\"black\" stroke-width=\"" + epaisseurContour + "\" data-i=\"" + i + "\" data-j=\"" + j + "\"/>\n";

                    svg.append(polygon);

                    String path = "M" + rotatedPoints + " Z";
                        
                    svg.append("<path d=\"").append(path).append("\" fill=\"").append(color)
                    .append("\" stroke=\"black\" stroke-width=\"").append(epaisseurContour)
                    .append("\" data-i=\"").append(i).append("\" data-j=\"").append(j).append("\"/>\n");
                                   
                }
            }
        }
    }

    svg.append("</svg>");
    return svg.toString();
    }

    private String rotateTriangle(int x1, int y1, int x2, int y2, int x3, int y3, double angle) {
        double cx = (x1 + x2 + x3) / 3.0; 
        double cy = (y1 + y2 + y3) / 3.0; 
    
        double rad = Math.toRadians(angle);
        int newX1 = (int) ((x1 - cx) * Math.cos(rad) - (y1 - cy) * Math.sin(rad) + cx);
        int newY1 = (int) ((x1 - cx) * Math.sin(rad) + (y1 - cy) * Math.cos(rad) + cy);
        int newX2 = (int) ((x2 - cx) * Math.cos(rad) - (y2 - cy) * Math.sin(rad) + cx);
        int newY2 = (int) ((x2 - cx) * Math.sin(rad) + (y2 - cy) * Math.cos(rad) + cy);
        int newX3 = (int) ((x3 - cx) * Math.cos(rad) - (y3 - cy) * Math.sin(rad) + cx);
        int newY3 = (int) ((x3 - cx) * Math.sin(rad) + (y3 - cy) * Math.cos(rad) + cy);
    
        return newX1 + "," + newY1 + " " + newX2 + "," + newY2 + " " + newX3 + "," + newY3;
    }
}