package com.iticbcn.xifratge;

import com.iticbcn.ClauNoSuportada;
import com.iticbcn.TextXifrat;

/**
 * MonoAlfabetic
 */

import com.iticbcn.Xifrador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {

    private static final char[] abc = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 
            'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    private static char[] abcPermutat = new char[abc.length];

    public XifradorMonoalfabetic() {
        permutaAlfabet();
    }

    public void permutaAlfabet() {
        
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

    public String xifraMonoalfa(String cadena) {
        return canviaString(cadena, true);
    }

    public String desxifraMonoalfa(String cadena) {
        return canviaString(cadena, false);
    }

    public String canviaString(String cadena, boolean esXifrar) {
        StringBuilder xifrat = new StringBuilder(cadena.length());
        for (int i = 0; i < cadena.length(); i++) {
            Character aSumar = canviaLletra(cadena.charAt(i), esXifrar);
            xifrat.append(aSumar);
        }
        return xifrat.toString();
    } 

    public Character canviaLletra(Character caracter, boolean esXifrar) {
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        return new TextXifrat(xifraMonoalfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String msgXifrat = xifrat.toString();
        return desxifraMonoalfa(msgXifrat);
    }
}