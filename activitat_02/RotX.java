

public class RotX {
    private static final char[] minuscules = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 
    'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};

    private static final char[] majuscules = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 
    'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    private static String prova = "hola que tal";
    private static String prova2 = "Próvèm cáràctérs èspecïals úÜ";
    private static String prova3 = "ArA BarrejAnt MajúScules i MinUSCUles";
    private static String prova4 = "Prova       d'altres ----- carácters?!";
    private static String prova5 = "'¡'34567{]   ---";
    private static String[] proves = {prova, prova2, prova3, prova4, prova5};


    /*
     * Funció per a xifrar una cadena de text amb un desplaçament de l'abecedari
     * segons l'enter indicat. 
     */
    public static String xifraRotX(String cadena, int desp) {
        if (desp >= majuscules.length) return "ERROR: S'accepten valors fins al 37";
        StringBuilder xifrat = new StringBuilder(cadena.length());
        //Troba la lletra
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            if (!Character.isLetter(caracter) ) {
                xifrat.append(caracter);
            } else if (Character.isUpperCase(caracter)) {
                xifrat.append(xifraLletra(caracter, majuscules, desp));
            } else  xifrat.append(xifraLletra(caracter, minuscules, desp));
        }
        return xifrat.toString();
    }

    /*
     * Funció que utilitza xifraRotX() per a calcular el recorregut de cada caracter
     */
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

    /*
     * Funció per a desxifrar una cadena de text amb un desplaçament de l'abecedari
     * segons l'enter indicat. 
     */
    public static String desxifraRotX(String cadena, int desp) {
        if (desp >= majuscules.length) return "ERROR: S'accepten valors fins al 37";
        StringBuilder xifrat = new StringBuilder(cadena.length());
        //Troba la lletra
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            
            if (!Character.isLetter(caracter) ) {
                xifrat.append(caracter);
            } else if (Character.isUpperCase(caracter)) {
                xifrat.append(desxifraLletra(caracter, majuscules, desp));
            } else  xifrat.append(desxifraLletra(caracter, minuscules, desp));
        }
        return xifrat.toString();
    }

    /*
     * Funció que utilitza desxifraRotX() per a calcular el recorregut de cada caracter
     */
    public static char desxifraLletra(char caracter, char[] abc, int desp) {
        int posicioInicial = 0;
        char aRetornar = 'e';
        for (int i = 0; i < abc.length; i++) {
            if (abc[i] == caracter) {
                posicioInicial = i;
                break;
            }
        }
        if (posicioInicial - desp >= 0) {
            aRetornar = abc[posicioInicial-desp];
        } else {
            aRetornar = abc[abc.length - Math.abs(posicioInicial - desp)];
        }
        return aRetornar;
    }

    /*
     * Funció que comprovarà totes les possibilitats de desxifrat per a la cadena
     * rebuda.
     */
    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int i = 1; i < majuscules.length; i++) {
            String cadenaDesxifrada = desxifraRotX(cadenaXifrada, i);
            boolean trobada = false;
            for (String actual: proves) {
                if (actual.equals(cadenaDesxifrada)) {
                    trobada = true;
                    break;
                }
            }
            System.out.println("    Desxifrat amb desplaçament " + i + ": " + cadenaDesxifrada + (trobada ? " - CORRECTE! :)" : " - INCORRECTE :("));
        }
    }

    public static void comprovaResultats(int desp) {
        String[] provesXifrades = new String[5];
        System.out.println("    Texts de prova xifrats:");
        for (int i = 0; i < proves.length; i++) {
            provesXifrades[i] = xifraRotX(proves[i], desp);
            System.out.println("        Prova " + (i+1) + ": " + provesXifrades[i]);
        }

        System.out.println("    Text de prova desxifrats");
        for (int i = 0; i < provesXifrades.length; i++) {
            System.out.println("        Prova " + (i+1) + ": " + desxifraRotX(provesXifrades[i], desp));
        }
    }

    public static void main(String[] args) {
        System.out.println();
        int[] desplasaments = {3, 11, 31};
        
        System.out.println("PROVES AMB DIFERENTS DESPLAÇAMENTS (PREDETERMINATS: 3, 11 i 31):");
        for (int actual: desplasaments) {
            System.out.println("===============================================================================");
            System.out.println("Proves amb desplaçament " + actual + ":");
            comprovaResultats(actual);
            System.out.println();
        }
        
        System.out.println("===============================================================================");
        System.out.println("PROVA DEL DESXIFRAT PER FORÇA BRUTA: ");
        forcaBrutaRotX(xifraRotX(prova, 24));
    }
}