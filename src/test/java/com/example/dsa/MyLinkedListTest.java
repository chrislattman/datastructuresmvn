package com.example.dsa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {
    MyLinkedList<Integer> myLinkedList;

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void init() {
        myLinkedList = new MyLinkedList<>();
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
