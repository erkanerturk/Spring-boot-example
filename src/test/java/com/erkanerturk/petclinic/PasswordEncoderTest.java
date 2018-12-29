package com.erkanerturk.petclinic;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    public void generateEncodedPasswords() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
    }
}
