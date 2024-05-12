package symbQR;

public class ProtocoleFactory {
    public static Protocole createProtocole(int protocole) {
        switch(protocole) {
            case 0:
                return new ProtocoleBinaire();
            case 1:
                return new ProtocoleCercle();
            case 2:
                return new ProtocoleTriangle();
            default:
                throw new IllegalArgumentException("Protocole invalide : " + protocole);
        }
    }
}
