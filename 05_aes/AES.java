import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

/**
 * AES
 */
public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding"; 

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "NachoYAndresTienenQueLimpiarse";


    public static byte[] xifraAES(String msg, String clau) throws Exception {
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

    public static String desxifraAES(byte[] ivIMsgXifrat, String clau) throws Exception {
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

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                         "Hola Andrés cómo está tu cuñado",
                         "Àgora ïlla Ôtto"
                    };
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES (bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("--------------------" );
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}