package com.iticbcn;
/**
 * Aquesta Exception manejarà els casos en que s'usi una clau no soportada
 * pel tipus de xifratge en us.
 */
public class ClauNoSuportada extends Exception {
    
    public ClauNoSuportada(String msg) {
        super(msg);
    }
}
