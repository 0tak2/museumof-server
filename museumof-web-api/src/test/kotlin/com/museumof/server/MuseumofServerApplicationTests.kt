package com.museumof.server

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles(value = ["test"])
class MuseumofServerApplicationTests {

    @Test
    fun contextLoads() {
    }

}
