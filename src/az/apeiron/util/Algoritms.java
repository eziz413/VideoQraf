package az.apeiron.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Algoritms {
    public static String encodeSha256(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        System.out.println(encodeSha256("eziz413"));
//    }
}
