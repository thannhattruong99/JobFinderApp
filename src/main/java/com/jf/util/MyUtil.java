/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.util;

/**
 *
 * @author truongtn
 */
public class MyUtil {
    private static final String DUPLICATE_ERROR_KEY = "Duplicate entry";
    public static String generateUserNameFromFullName(String fullName){
        String[] parts = fullName.trim().split(" ");
        String username = "";
        username += parts[parts.length - 1];
        for(int i = 0; i < parts.length - 1; i++){
            if(!parts[i].isEmpty()){
                char[] chars = parts[i].toLowerCase().toCharArray();
                chars[0] = Character.toLowerCase(chars[0]);
                username += ((String.valueOf(chars[0]).trim()));
            }
        }
        return username.toLowerCase().trim();
    }
    
    public static String catchSQLException(String errorMsg){
        String[] parts = errorMsg.trim().split("'");
        if(parts[0].contains(DUPLICATE_ERROR_KEY)){
            String[] subParts = parts[3].split("_");
            //subParts[1] is error field name
            return subParts[1] + "is duplicated!";
        }
        return "Occured error. Please try again!!";
    }
}
