package com.farm_management.fms.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashUtil  {
    // Initialize the BCrypt encoder
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String hashPassword(String rowPassword){
        return passwordEncoder.encode(rowPassword);
    }
    public static boolean verifyPassword(String hashedPassword,String password){
        return passwordEncoder.matches(password,hashedPassword);
    }
}
