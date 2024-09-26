/*
 * Classe que implementa el xifrat ROT13, per a fer xifrats senzills de text
 */

public class Rot13 {

    private static final char[] minuscules = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 
            'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};
     
    private static final char[] majuscules = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 
            'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    
    /*
     * Mètode principal que s'encarrega de xifrar una cadena de text.
     * Recorre la cadena i xifra cada caràcter
     */
    public static String xifraRot13(String cadena) {
        String xifrat = "";
        //Troba la lletra
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            if (!Character.isLetter(caracter) ) {
                xifrat = xifrat + caracter;
            } else if (Character.isUpperCase(caracter)) {
                xifrat = xifrat + xifraLletra(caracter, majuscules);
            } else  xifrat = xifrat + xifraLletra(caracter, minuscules);
        }
        return xifrat;
    }

    public static char xifraLletra(char caracter, char[] abc) {
        int desplasament = 13;
        int posicioInicial = 0;
        char aRetornar;
        //Trobem la posició del caracter al array
        for (int i = 0; i < abc.length; i++) {
            if (abc[i] == caracter) {
                posicioInicial = i;
                break; 
            }
        }
        if ((posicioInicial + desplasament) < abc.length) {
            aRetornar = abc[posicioInicial + desplasament];
        } else {
            int restant = posicioInicial + desplasament - abc.length;
            aRetornar = abc[restant];
        }
        return aRetornar;
    }

    /*
     * Mètode principal que s'encarrega de desxifrar una cadena de text ja xifrada
     */
    public static String desxifraRot13(String cadena) {
        String xifrat = "";
        //Troba la lletra
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            
            if (!Character.isLetter(caracter) ) {
                xifrat = xifrat + caracter;
            } else if (Character.isUpperCase(caracter)) {
                xifrat = xifrat + desxifraLletra(caracter, majuscules);
            } else  xifrat = xifrat + desxifraLletra(caracter, minuscules);
        }
        return xifrat;
    }
    
    public static char desxifraLletra(char caracter, char[] abc) {
        int desplasament = 13;
        int posicioInicial = 0;
        char aRetornar = 'e';
        for (int i = 0; i < abc.length; i++) {
            if (abc[i] == caracter) {
                posicioInicial = i;
                break;
            }
        }
        if (posicioInicial - desplasament >= 0) {
            aRetornar = abc[posicioInicial-desplasament];
        } else {
            aRetornar = abc[abc.length - Math.abs(posicioInicial - desplasament)];
        }
        return aRetornar;
    }

    public static void main(String[] args) {
        String prova = "hola que tal";
        String prova2 = "Próvèm cáràctérs èspecïals úÜ";
        String prova3 = "ArA BarrejAnt MajúScules i MinUSCUles";
        String prova4 = "Prova       d'altres ----- carácters?!";
        String prova5 = "'¡'34567{]   ---";

        String[] proves = {prova, prova2, prova3, prova4, prova5};
        int i = 1;
        for (String actual: proves) {
            System.out.println("==============");
            System.out.println("PROVA NÚMERO " + i);
            System.out.println("==============");
            System.out.println("String de prova xifrada: " + xifraRot13(actual));
            System.out.println("String de prova desxifrada: " + desxifraRot13(xifraRot13(actual)));
            i++;
        }
    }
 }

