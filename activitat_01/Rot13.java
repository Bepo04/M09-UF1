/*
 * Classe que implementa el xifrat ROT13, per a fer xifrats senzills de text
 */

public class Rot13 {

    private static final char[] minuscules = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 
            'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};
     
    private static final char[] majuscules = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 
            'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        String prova = "hOlA qUe tAl";

        String xifrat = xifraRot13(prova);

        System.out.println("String de prova: " + xifrat);


    }


    /*
     * Mètode principal que s'encarrega de xifrar una cadena de text
     */
    public static String xifraRot13(String cadena) {
        String xifrat = "";
        //Troba la lletra
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            
            if (!Character.isLetter(caracter) ) {
                xifrat = xifrat + caracter;
            } else if (Character.isUpperCase(caracter)) {
                xifrat = xifrat + xifraMajuscula(caracter);
            } else  xifrat = xifrat + xifraMinuscula(caracter);
        }
        return xifrat;
    } 

    /*
     * Mètode que utilitzarà "xifraRot13(String)" per xifrar minuscules
     */
    public static char xifraMinuscula(char caracter) {
        int desplasament = 13;
        int posicioInicial = 0;
        char aRetornar;
        //Trobem la posició del caracter al arrat+y
        for (int i = 0; i < minuscules.length; i++) {
            if (minuscules[i] == caracter) {
                posicioInicial = i;
            }
        }
        if ((posicioInicial + desplasament) < minuscules.length) {
            aRetornar = minuscules[posicioInicial + desplasament];
        } else {
            int restant = posicioInicial + desplasament - minuscules.length;
            aRetornar = minuscules[restant];
        }
        return aRetornar;
    }
    
    /*
     * Mètode que utilitzarà "xifraRot13(String)" per xifrar minuscules
     */
    public static char xifraMajuscula(char caracter) {
        int desplasament = 13;
        int posicioInicial = 0;
        char aRetornar;
        //Trobem la posició del caracter al arrat+y
        for (int i = 0; i < majuscules.length; i++) {
            if (majuscules[i] == caracter) {
                posicioInicial = i;
            }
        }
        if ((posicioInicial + desplasament) < majuscules.length) {
            aRetornar = majuscules[posicioInicial + desplasament];
        } else {
            int restant = posicioInicial + desplasament - majuscules.length;
            aRetornar = majuscules[restant];
        }
        return aRetornar;
    }

    /*
     * Mètode principal que s'encarrega de desxifrar una cadena de text ja xifrada
     */
    public static String desxifraRot13(String cadena) {
        return "a";
    }
 }

/*
* pxúi àçn ciú
* PXÚI ÀÇN CIÚ
*/