package com.ihr360.applet.customization.qys.utils;


import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;

public final class EncryptUtil {

    /**
     * content: company_id=客户标识&mobile_no=手机号×tamp=时间戳
     **/
    public static String encryptByPrivateKey(String content, String privateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] resultBytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64Util.encode(resultBytes);
    }
}