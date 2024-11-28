package com.project.linkedin.user_service.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hashPasswor(String plainTextPassword){
       return BCrypt.hashpw(plainTextPassword,BCrypt.gensalt());
    }
    public static boolean checkPassword(String plainTextPassword,String hashedPassword){
        return BCrypt.checkpw(plainTextPassword,hashedPassword);
    }
}
