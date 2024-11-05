package com.iticbcn;

/**
 * Interfície que implementaran els diferents xifradors
 */
public interface Xifrador {
    
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada;
}
