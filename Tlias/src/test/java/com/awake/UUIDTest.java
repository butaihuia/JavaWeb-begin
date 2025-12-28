package com.awake;

import org.junit.jupiter.api.Test;

public class UUIDTest {

    @Test
    public void testUUID() {
        for (int i = 0; i < 10000; i++) {
            String uuid = java.util.UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

}
