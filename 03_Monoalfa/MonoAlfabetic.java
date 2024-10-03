/**
 * MonoAlfabetic
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class MonoAlfabetic {

    private static final char[] abc = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 
            'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    private static char[] abcPermutat = new char[abc.length];

    public static void permutaAlfabet() {
        
        List<Character> permutador = new ArrayList<Character>();
        
        for(int i = 0; i < abc.length; i++) {
            Character tmpLletra = abc[i];
            permutador.add(tmpLletra);
        }
        Collections.shuffle(permutador);
        for(int i = 0; i < abcPermutat.length; i++) {
            abcPermutat[i] = permutador.get(i);
        }
    }

    public static String xifraMonoAlfa(String cadena) {
        return canviaString(cadena, true);
    }

    public static String desxifraMonoAlfa(String cadena) {
        return canviaString(cadena, false);
    }

    public static String canviaString(String cadena, boolean esXifrar) {
        StringBuilder xifrat = new StringBuilder(cadena.length());
        for (int i = 0; i < cadena.length(); i++) {
            Character aSumar = canviaLletra(cadena.charAt(i), esXifrar);
            xifrat.append(aSumar);
        }
        return xifrat.toString();
    } 

    public static Character canviaLletra(Character caracter, boolean esXifrar) {
        int index = 0;
        boolean esMinuscula = false;
        for(int i = 0; i < abc.length; i++) {
            if (Character.isLowerCase(caracter)) {
                caracter = Character.toUpperCase(caracter);
                esMinuscula = true;
            }
            if (caracter == (esXifrar?abc[i]:abcPermutat[i])) index = i;
        }
        if (!Character.isLetter(caracter)) {
            return caracter;
        } else if (esXifrar) {
            caracter = esMinuscula ? Character.toLowerCase(abcPermutat[index]) : abcPermutat[index];
        } else {
            caracter = esMinuscula ? Character.toLowerCase(abc[index]) : abc[index];
        }
        return caracter;
    }
    public static void main(String[] args) {
        //Primer cal cridar al mètode permutar() per a generar l'array permutat
        permutaAlfabet();
        String prova = "Hola me llamo Pablo";
        String provaXifrada = xifraMonoAlfa(prova);
        System.out.println("Text xifrat: " + provaXifrada);

        String provaDesxifrada = desxifraMonoAlfa(provaXifrada);
        System.out.println("Text desxifrat: " + provaDesxifrada );
    }
}