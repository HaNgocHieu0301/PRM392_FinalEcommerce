package com.example.prm392_finalecommerce;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    String EmailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    String PhoneRegex = "^((\\+84)|0)(1[2689]|9)\\d{8}$";
    String UnameRegex = "^[a-zA-Z0-9._-]+$";
//    String PassRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,20}$";
    public boolean checkEmail (String Email){
        Pattern pattern = Pattern.compile(EmailRegex);
        Matcher matcher = pattern.matcher(Email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkPhone (String Phone){
        Pattern pattern = Pattern.compile(PhoneRegex);
        Matcher matcher = pattern.matcher(Phone);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkName (String str){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]+$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkPass (String str){
//        Pattern pattern = Pattern.compile();
//        Matcher matcher = pattern.matcher(str);
//        if (matcher.matches()) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }
    public static String doHashing (String Password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Password.getBytes());
            byte[] resultByteArray = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
