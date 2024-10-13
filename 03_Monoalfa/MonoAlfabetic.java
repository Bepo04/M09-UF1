/**
 * MonoAlfabetic
 */

import java.util.List;
import java.util.ArrayList;
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
        String prova1 = "Hola que tal";
        String prova2 = "provem --- amb &&& caracters !!!!! especials           .";
        String prova3 = "1234072079402834092835";
        String prova4 = "pròvém cáràctèrs àccéntüats í úuuu";
        String[] proves = {prova1, prova2, prova3, prova4};
        String[] provesXifrades =  new String[4];
        for (int i = 0; i < proves.length; i++) {
            provesXifrades[i] = xifraMonoAlfa(proves[i]);
            System.out.println("====================================================================");
            System.out.println("Prova xifrada " + (i+1) + ": " + provesXifrades[i]);
            System.out.println("Prova desxifrada " + (i+1) + ": " + desxifraMonoAlfa(provesXifrades[i]));
            System.out.println("====================================================================");
            System.out.println();
        }
    }
}