package com.iticbcn;

public class TextXifrat {
    
    private byte[] cadena;

    public TextXifrat(byte[] cadena) {
        this.cadena = cadena;
    }

    @Override
    public String toString() {
        return new String(cadena);
    }

    public byte[] getBytes() {
        return cadena;
    }
}
