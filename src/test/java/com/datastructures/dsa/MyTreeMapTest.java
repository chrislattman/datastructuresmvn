package com.datastructures.dsa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyTreeMapTest {
    MyTreeMap<Integer, Integer> myTreeMap;

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void init() {
        myTreeMap = new MyTreeMap<>();
    }

    @Test
    void test() {
        assertTrue(true, "Not true");
    }

    @AfterEach
    void tearDown() {

    }

    @AfterAll
    static void tearDownAll() {

    }
}
