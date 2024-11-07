package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.PublicKey;

import javax.crypto.Cipher;

import java.security.PrivateKey;
import java.security.KeyPairGenerator;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] msgBytes = msg.getBytes();
        byte[] msgXifrat = cipher.doFinal(msgBytes);
        return msgXifrat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
        return new String(msgDesxifrat);
    }
}
