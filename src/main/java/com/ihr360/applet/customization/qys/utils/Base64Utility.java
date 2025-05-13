package com.ihr360.applet.customization.qys.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Utility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String AppID = "9cff72b6-bb79-4f07-9326-41a31684fccd";
		//String AppSecret = "7e68649e-4343-4838-862b-c69aea2fd636";
		String AppID = "c3a4c72f-dca0-4ec7-8b86-7bd869c875ce";
		String AppSecret = "0fcadde8-ae3f-4d97-86ed-e3457b441512";
		
		String Authorization = AppID+":"+AppSecret;
		System.out.println(Authorization);
		try {
			System.out.println(Base64Utility.encoder(Authorization));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
     * base64编码
     * @param string
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encoder(String string) throws UnsupportedEncodingException {
        Encoder encoder = Base64.getEncoder();
        byte[] textByte = string.getBytes("UTF-8");
        return encoder.encodeToString(textByte);
    }
    
    /**
     * base64解码
     * @param string
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decoder(String string) throws UnsupportedEncodingException {
        Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(string), "UTF-8");
    }

}
