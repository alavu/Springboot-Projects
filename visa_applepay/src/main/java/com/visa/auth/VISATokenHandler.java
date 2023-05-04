package com.visa.auth;

import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

import com.visa.config.VISAConfig;

import java.nio.charset.StandardCharsets;

public class VISATokenHandler {

    private static String getTimestamp() {
        long timeStamp = (long) (new Date().getTime() / 1000);
        return Long.toString(timeStamp);
    }

    private static String getHash(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(VISAConfig.SHARED_SECRET.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] hmacData = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hmacData);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String getXPayToken(String resourcePath, String queryString, String requestBody) throws Exception {
        String timestamp = getTimestamp();
        String sourceString = timestamp + resourcePath + queryString + requestBody;
        String hash = getHash(sourceString);
        String token = "xv2:" + timestamp + ":" + hash;
        return token;
    }
}
