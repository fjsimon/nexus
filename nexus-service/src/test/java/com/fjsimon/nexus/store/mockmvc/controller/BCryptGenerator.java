package com.fjsimon.nexus.store.mockmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptGenerator {

    @Test
    public void bcrypt_generate() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("s3cr3tpassw0rd"));
        System.out.println(passwordEncoder.encode("s4p3rs3c4r3ly"));
    }
}
