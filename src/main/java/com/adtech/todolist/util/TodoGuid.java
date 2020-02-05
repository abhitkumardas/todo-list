package com.adtech.todolist.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class TodoGuid {


    public String getUniqueHashId(String input_string){
        MessageDigest messageDigest;
        try {
            messageDigest=MessageDigest.getInstance("SHA-256");
            byte[] guidBytes = messageDigest.digest(input_string.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < guidBytes.length; i++) {
                stringBuilder.append(Integer.toString((guidBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Exception :"+e.getMessage();
        }
    }
}
