package com.datastructures.dsa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStackTest {
    MyStack<Integer> myStack;

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void init() {
        myStack = new MyStack<>();
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
