package com.ihr360.applet.customization.qys.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AesUtil {
    private static final Charset CHARSET = Charset.forName("utf-8");
    private byte[] aesKey;
    /**
     * ask getPaddingBytes key固定长度
     **/
    private static final Integer AES_ENCODE_KEY_LENGTH = 24;

    public AesUtil(String encodingAesKey) throws CustomException {
        if (null == encodingAesKey || encodingAesKey.length() != AES_ENCODE_KEY_LENGTH) {
            throw new CustomException("密钥错误");
        }
        aesKey = Base64.decodeBase64(encodingAesKey);
    }

    /**
     * 对密文进行解密.
     */
    public String decrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 设置解密模式为AES的CBC模式
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
        IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        // 使用BASE64对密文进行解码
        byte[] encrypted = Base64.decodeBase64(text);
        // 解密
        byte[] originalArr = cipher.doFinal(encrypted);
        byte[] bytes = PKCS7Padding.removePaddingBytes(originalArr);
        return new String(bytes, CHARSET);
    }

    public static class PKCS7Padding {
        private static final Charset CHARSET = Charset.forName("utf-8");
        private static final int BLOCK_SIZE = 32;

        public static byte[] getPaddingBytes(int count) {
            int amountToPad = BLOCK_SIZE - count % BLOCK_SIZE;
            if (amountToPad == 0) {
                amountToPad = BLOCK_SIZE;
            }
            char padChr = chr(amountToPad);
            String tmp = new String();
            for (int index = 0; index < amountToPad; ++index) {
                tmp = tmp + padChr;
            }
            return tmp.getBytes(CHARSET);
        }

        public static byte[] removePaddingBytes(byte[] decrypted) {
            int pad = decrypted[decrypted.length - 1];
            if (pad < 1 || pad > BLOCK_SIZE) {
                pad = 0;
            }
            return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
        }

        private static char chr(int a) {
            byte target = (byte) (a & 255);
            return (char) target;
        }
    }

    public static class CustomException extends Exception {

        public CustomException(String message) {
            super(message);
        }
    }
}
