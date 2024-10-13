import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Polialfabetic {

    private static final char[] ABC = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();
    private static char[] abcPermutat;
    private static final int CLAU_SECRETA = 17492040;
    private static Random random; 

    public static void permutaAlfabet() {
        //Creem una llista amb ABC
        ArrayList<Character> llista = new ArrayList<Character>();
        for (char caracter: ABC) {
            llista.add(caracter);
        }
        Collections.shuffle(llista, random); //Permutem l'alfabet.
        //Recuperem la permutació en un Array global
        abcPermutat = new char[ABC.length];
        for (int i = 0; i < llista.size(); i++) {
            abcPermutat[i] = llista.get(i);
        }
    }

    public static String modificaString(String text, boolean esXifrar) {
        iniciaRandom(CLAU_SECRETA);
        StringBuilder xifrat = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char afegir = text.charAt(i);
            //Si no és un caràcter l'afegim directe i passem a la següent iteració
            if (!Character.isLetter(afegir)) {
                xifrat.append(afegir);
                continue;
            }
            permutaAlfabet();
            afegir = canviaLletra(text.charAt(i), esXifrar);
            xifrat.append(afegir);
        }
        return xifrat.toString();
    }

    public static char canviaLletra(char caracter, boolean esXifrar) {
        int index = 0;
        boolean esMinuscula = false;
        for(int i = 0; i < ABC.length; i++) {
            if (Character.isLowerCase(caracter)) {
                caracter = Character.toUpperCase(caracter);
                esMinuscula = true;
            }
            if (caracter == (esXifrar?ABC[i]:abcPermutat[i])) {
                index = i;
                break;
            }
        }
        caracter = esXifrar? abcPermutat[index] : ABC[index];
        if (esMinuscula) caracter = Character.toLowerCase(caracter);
        return caracter;
    }

    public static String xifraPoliAlfa(String text) {
        return modificaString(text, true);
    }

    public static String desxifraPoliAlfa(String text) {
        return modificaString(text, false);
    }

    public static void iniciaRandom(int clau) {
        random = new Random(clau);
    }

    public static void main(String[] args) {
        String[] proves = {
            "Hola com estem?",
            "Bon dia a tothom",
            "CàRáctÉrs èspEcÏÁls Úü",
            "NO POT SER!!!!!!                   ...",
            "Una      última     Prova"
        };
        String[] provesXifrades = new String[proves.length];

        for (int i = 0; i < proves.length; i++) {
            provesXifrades[i] = xifraPoliAlfa(proves[i]);
            System.out.println("====================================================================");
            System.out.println("Prova xifrada " + (i+1) + ": " + provesXifrades[i]);
            System.out.println("Prova desxifrada " + (i+1) + ": " + desxifraPoliAlfa(provesXifrades[i]));
            System.out.println("====================================================================");
            System.out.println();
        }
    }
}

