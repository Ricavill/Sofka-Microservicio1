package com.sofka.sofkatest.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {


    public static String hash(String password) {
        return hash(password, 10);
    }

    public static String hash(String password, int strength) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
        return passwordEncoder.encode(password);
    }


}
