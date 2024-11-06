package com.iticbcn.xifratge;

import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.iticbcn.ClauNoSuportada;
import com.iticbcn.TextXifrat;
import com.iticbcn.Xifrador;

import java.security.MessageDigest;
import javax.crypto.Cipher;

/**
 * AES
 */
public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding"; 
    private static final int MIDA_IV = 16;
    
    private byte[] iv = new byte[MIDA_IV];
    //private final String CLAU = "NachoYAndresTienenQueLimpiarse";


    public byte[] xifraAES(String msg, String clau) throws Exception {
        //Obtenim el missatge a xifrar en bytes
        byte[] msgBytes = msg.getBytes();

        //Generem IvParameterSpec
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        //Generem el hash
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        md.update(clau.getBytes("UTF-8"));
        byte[] clauBytes = new byte[MIDA_IV];
        System.arraycopy(md.digest(), 0, clauBytes, 0, clauBytes.length);
        SecretKeySpec keySpec = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        //Encriptem
        Cipher cipherXifra = Cipher.getInstance(FORMAT_AES);
        cipherXifra.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] msgEncriptat = cipherXifra.doFinal(msgBytes);

        //Combinem IV i part encriptada
        byte[] iViTextEncriptat = new byte[MIDA_IV + msgEncriptat.length];
        System.arraycopy(iv, 0, iViTextEncriptat, 0, MIDA_IV);
        System.arraycopy(msgEncriptat, 0, iViTextEncriptat, MIDA_IV, msgEncriptat.length);

        return iViTextEncriptat;

    }

    public String desxifraAES(byte[] ivIMsgXifrat, String clau) throws Exception {
        //Extreure IV
        byte[] iv = new byte[MIDA_IV];
        System.arraycopy(ivIMsgXifrat, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        //Extreure la part encriptada
        int tamanyEncriptat = ivIMsgXifrat.length - MIDA_IV;
        byte[] bytesEncriptats = new byte[tamanyEncriptat];
        System.arraycopy(ivIMsgXifrat, MIDA_IV, bytesEncriptats, 0, tamanyEncriptat);

        //Fer Hash de la clau
        byte[] clauBytes = new byte[MIDA_IV];
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        md.update(clau.getBytes());
        System.arraycopy(md.digest(), 0, clauBytes, 0, clauBytes.length);
        SecretKeySpec keySpec = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        //Desencripta
        Cipher cipherDesxifra = Cipher.getInstance(FORMAT_AES);
        cipherDesxifra.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
        byte[] bytesDesxifrats = cipherDesxifra.doFinal(bytesEncriptats);

        return new String(bytesDesxifrats);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        byte[] xifrat;
        try {
            xifrat = xifraAES(msg, clau);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new TextXifrat(xifrat);
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        String desxifrat;
        try {
            desxifrat = desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return desxifrat;
    }
}