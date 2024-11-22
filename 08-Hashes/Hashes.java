import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    private int npass;

    public Hashes() {}

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.reset();
        md.update(passwordAndSaltToBytes(pw, salt));

        byte[] hash = md.digest();
        
        HexFormat hex = HexFormat.of();
        String strHash = hex.formatHex(hash);

        return strHash;
    }

    public byte[] passwordAndSaltToBytes(String pw, String salt) {
        byte[] pswdBytes = pw.getBytes();
        byte[] saltBytes = salt.getBytes();
        byte[] concatened = new byte[pswdBytes.length + saltBytes.length];

        System.arraycopy(pswdBytes, 0, concatened, 0, pswdBytes.length);
        System.arraycopy(saltBytes, 0, concatened, pswdBytes.length, saltBytes.length);

        return concatened;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        
        String hash = null;
        try {
            
            byte[] aSalt = salt.getBytes(StandardCharsets.UTF_8);
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), aSalt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] aHash = factory.generateSecret(spec).getEncoded();

            HexFormat hex = HexFormat.of();
            hash = hex.formatHex(aHash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return hash;
    }

    public String forcaBruta(String alg, String hash, String salt) throws NoSuchAlgorithmException {
        //A CADA PROVA HA D'INCREMENTAR npass
        this.npass = 0;
        char[] charset = "abcdefABCDEF0123456789!".toCharArray();
        char[] pswd = new char[6];
        for(int i = 0; i < charset.length; i++) {
            pswd[0] = charset[i]; 
            if (hash.equals(executaAlgorisme(alg,  creaPassword(pswd, 1), salt))) return creaPassword(pswd, 1);

            for (int j = 0; j < charset.length; j++) {
                pswd[1] = charset[j]; 
                if (hash.equals(executaAlgorisme(alg,  creaPassword(pswd, 2), salt))) return creaPassword(pswd, 2);

                for (int k = 0; k < charset.length; k++) {
                    pswd[2] = charset[k];
                    if (hash.equals(executaAlgorisme(alg,  creaPassword(pswd, 3), salt))) return creaPassword(pswd, 3);

                    for (int l = 0; l < charset.length; l++) {
                        pswd[3] = charset[l];
                        if (hash.equals(executaAlgorisme(alg,  creaPassword(pswd, 4), salt))) return creaPassword(pswd, 4);

                        for (int m = 0; m < charset.length; m++) {
                            pswd[4] = charset[m];
                            if (hash.equals(executaAlgorisme(alg,  creaPassword(pswd, 5), salt))) return creaPassword(pswd, 5);

                            for (int n = 0; n < charset.length; n++) {
                                pswd[5] = charset[n];
                                if (hash.equals(executaAlgorisme(alg,  creaPassword(pswd, 6), salt))) return creaPassword(pswd, 6);
                            }
                        }
                    }
                }
            }
        }
        /**
         * Llençar el métode que prova contrassenya en un if, si retorna null seguim, si troba
         * la contrassenya retorna la contrasssenya i surt. CAL FER LA COMPROVACIO A CADA BUCLE
         */
        return "FALLO LOCO";

    }

    public String creaPassword(char[] caracters, int length) {
        return new String(caracters).substring(0,length);
    }

    public String executaAlgorisme(String alg, String hash, String salt) throws NoSuchAlgorithmException {
        this.npass++;
        if (alg.equals("SHA-512")) return getSHA512AmbSalt(hash, salt);
        else return getPBKDF2AmbSalt(hash, salt);

    }

    /**
     * 
     * @param t1 temps en milisegons abans de forcaBruta()
     * @param t2 temps en milisegons després de forcaBruta()
     * @return Diferència entre t1 i t2
     */
    public String getInterval(long t1, long t2) {
        
        long milisPerDia = 24*60*60*1000;
        long milisPerHora = 60*60*1000;
        long milisPerMinut = 60*1000;
        long milisPerSegon = 1000;

        long diferencia = t2 - t1;
        
        long dies = diferencia / milisPerDia; 
        diferencia %= milisPerDia;

        long hores = diferencia / milisPerHora;
        diferencia %= milisPerHora;

        long minuts = diferencia / milisPerMinut;
        diferencia %= milisPerMinut;

        long segons = diferencia / milisPerSegon;

        long milisegons = diferencia / milisPerSegon;
        
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d milisegons", dies, hores, minuts, segons, milisegons);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aAf!81";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            
            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}