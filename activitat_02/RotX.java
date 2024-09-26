

public class RotX {
    private static final char[] minuscules = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 
    'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};

    private static final char[] majuscules = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 
    'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    public static String xifraRotX(String cadena, int desp) {
        if (desp < majuscules.length) return "ERROR: S'accepten valors fins al 38";
        String xifrat = "";
        //Troba la lletra
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            if (!Character.isLetter(caracter) ) {
                xifrat = xifrat + caracter;
            } else if (Character.isUpperCase(caracter)) {
                xifrat = xifrat + xifraLletra(caracter, majuscules, desp);
            } else  xifrat = xifrat + xifraLletra(caracter, minuscules, desp);
        }
        return xifrat;
    }

    public static char xifraLletra(char caracter, char[] abc, int desp) {
        int posicioInicial = 0;
        char aRetornar;
        //Trobem la posició del caracter al array
        for (int i = 0; i < abc.length; i++) {
            if (abc[i] == caracter) {
                posicioInicial = i;
                break; 
            }
        }
        if ((posicioInicial + desp) < abc.length) {
            aRetornar = abc[posicioInicial + desp];
        } else {
            aRetornar = abc[posicioInicial + desp - abc.length];
        }
        return aRetornar;
    }

    public static void main(String[] args) {
        
        String prova = "Hola que tal";
        
        System.out.println("String de prova xifrada: " + xifraRotX(prova, 13));
    }
}
