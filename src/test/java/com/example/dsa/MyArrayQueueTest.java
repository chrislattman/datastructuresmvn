package com.example.dsa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyArrayQueueTest {
    MyArrayQueue<Integer> myArrayQueue;

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void init() {
        myArrayQueue = new MyArrayQueue<>();
        myArrayQueue.clear();
    }

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new MyArrayQueue<Integer>(-1));
    }

    @Test
    void testEquals() {
        assertTrue(myArrayQueue.equals(myArrayQueue));
        assertFalse(myArrayQueue.equals(null));
        MyArrayQueue<Integer> myArrayQueue2 = new MyArrayQueue<>();
        myArrayQueue2.offer(5);
        assertFalse(myArrayQueue.equals(myArrayQueue2));
        myArrayQueue.offer(5);
        assertTrue(myArrayQueue.equals(myArrayQueue2));
    }

    @Test
    void testIsEmpty() {
        assertTrue(myArrayQueue.isEmpty());
    }

    @Test
    void testOffer() {
        myArrayQueue.offer(5);
        assertEquals(5, myArrayQueue.peek());
    }

    @Test
    void testPeek() {
        assertNull(myArrayQueue.peek());
        myArrayQueue.offer(5);
        assertEquals(5, myArrayQueue.peek());
    }

    @Test
    void testPoll() {
        assertNull(myArrayQueue.poll());
        myArrayQueue.offer(5);
        assertEquals(5, myArrayQueue.poll());
        assertTrue(myArrayQueue.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, myArrayQueue.size());
    }

    @Test
    void testToString() {
        assertEquals("[]", myArrayQueue.toString());
        myArrayQueue.offer(5);
        assertEquals("[5]", myArrayQueue.toString());
        myArrayQueue.offer(6);
        assertEquals("[5, 6]", myArrayQueue.toString());
    }

    @AfterEach
    void tearDown() {

    }

    @AfterAll
    static void tearDownAll() {

    }
}
