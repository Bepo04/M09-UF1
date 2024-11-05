package com.iticbcn.xifratge;

import java.util.Random;

import com.iticbcn.ClauNoSuportada;
import com.iticbcn.TextXifrat;
import com.iticbcn.Xifrador;

import java.util.ArrayList;
import java.util.Collections;

public class XifratgePolialfabetic implements Xifrador {

    private final char[] ABC = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();
    private char[] abcPermutat;
    private final int CLAU_SECRETA = 17492040;
    private Random random; 

    public void permutaAlfabet() {
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

    public String modificaString(String text, boolean esXifrar) {
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

    public char canviaLletra(char caracter, boolean esXifrar) {
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

    public String xifraPolialfa(String text) {
        return modificaString(text, true);
    }

    public String desxifraPolialfa(String text) {
        return modificaString(text, false);
    }

    public void iniciaRandom(int clau) {
        random = new Random(clau);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'xifra'");
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desxifra'");
    }
}