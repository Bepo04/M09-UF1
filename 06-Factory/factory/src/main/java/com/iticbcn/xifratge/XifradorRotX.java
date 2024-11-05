package com.iticbcn.xifratge;

import com.iticbcn.ClauNoSuportada;
import com.iticbcn.TextXifrat;
import com.iticbcn.Xifrador;

public class XifradorRotX implements Xifrador {
    private final char[] minuscules = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 
    'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};

    private final char[] majuscules = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 
    'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    /*
     * Funció per a xifrar una cadena de text amb un desplaçament de l'abecedari
     * segons l'enter indicat. 
     */
    public String xifraRotX(String cadena, int desp) {
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
    public char xifraLletra(char caracter, char[] abc, int desp) {
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
    public String desxifraRotX(String cadena, int desp) {
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
    public char desxifraLletra(char caracter, char[] abc, int desp) {
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        return null;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        return null;
    }
}