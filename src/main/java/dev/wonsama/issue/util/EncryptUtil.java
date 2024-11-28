package dev.wonsama.issue.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {

  private Encoder encoder = Base64.getEncoder();
  private Decoder decoder = Base64.getDecoder();

  public String encryptString(String encryptString, String secretKey) throws Exception {
    Cipher cipher = cipherPkcs5(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = cipher.doFinal(encryptString.getBytes("UTF-8"));
    return encoder.encodeToString(encryptedBytes);
  }

  public String decryptString(String decryptString, String secretKey) throws Exception {
    byte[] decodedBytes = decoder.decode(decryptString.getBytes("UTF-8"));
    Cipher cipher = cipherPkcs5(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedBytes = cipher.doFinal(decodedBytes);
    return new String(decryptedBytes, "UTF-8");
  }

  private Cipher cipherPkcs5(int opMode, String secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    SecretKeySpec sk = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
    IvParameterSpec iv = new IvParameterSpec(secretKey.substring(0, 16).getBytes("UTF-8"));
    cipher.init(opMode, sk, iv);
    return cipher;
  }
}
