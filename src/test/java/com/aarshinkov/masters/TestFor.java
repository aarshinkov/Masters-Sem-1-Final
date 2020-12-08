package com.aarshinkov.masters;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestFor {

    @Test
    public void testEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        System.out.println(passwordEncoder.encode("Test-1234"));
    }

    @Test
    public void testFor() {
        int elements = 20;
        int cols = 2;

//        1    4   7

        for (int i = 1; i <= elements; i += cols) {
            for (int j = i; j <= i + cols - 1; j++) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
}
