package tinkoff_2020_08_23;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by asavan on 24.08.2020.
 */
class T7Test {
    List<Integer> arr;

    @BeforeEach
    public void init() {
        arr = T7.getPrimesAndBounds();
    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(9594, arr.size());
    }


    @org.junit.jupiter.api.Test
    void processRequest() {
        assertEquals(2, T7.processRequest(arr, new T7.Request(4, 3)));
    }

    @org.junit.jupiter.api.Test
    void processRequestC2() {
        assertEquals(2, T7.processRequest(arr, new T7.Request(2, 2)));
        assertEquals(1, T7.processRequest(arr, new T7.Request(3, 2)));
        assertEquals(-1, T7.processRequest(arr, new T7.Request(1, 2)));
        assertEquals(-1, T7.processRequest(arr, new T7.Request(112, 2)));
        assertEquals(35618, T7.processRequest(arr, new T7.Request(111, 2)));
    }

    @org.junit.jupiter.api.Test
    void processRequestC9591() {
        assertEquals(-1, T7.processRequest(arr, new T7.Request(4, 9591)));
        assertEquals(-1, T7.processRequest(arr, new T7.Request(100_000, 9591)));
        assertEquals(-1, T7.processRequest(arr, new T7.Request(100_000 - 1, 9591)));
        assertEquals(3, T7.processRequest(arr, new T7.Request(100_000 - 2, 9591)));
        assertEquals(3, T7.processRequest(arr, new T7.Request(100_000 - 3, 9591)));
        assertEquals(3, T7.processRequest(arr, new T7.Request(100_000 - 4, 9591)));
        assertEquals(3, T7.processRequest(arr, new T7.Request(100_000 - 5, 9591)));
    }


    @org.junit.jupiter.api.Test
    void processRequestC0() {
        assertEquals(-1, T7.processRequest(arr, new T7.Request(72, 0)));
        assertEquals(31398, T7.processRequest(arr, new T7.Request(71, 0)));
        assertEquals(31398, T7.processRequest(arr, new T7.Request(68, 0)));
    }

    @org.junit.jupiter.api.Test
    void processRequestC9592() {
        assertEquals(-1, T7.processRequest(arr, new T7.Request(100_001, 9592)));
        assertEquals(1, T7.processRequest(arr, new T7.Request(100_000, 9592)));
        assertEquals(1, T7.processRequest(arr, new T7.Request(100_000 - 1, 9592)));
    }

    @org.junit.jupiter.api.Test
    void processRequestC9592Speculative() {
        assertEquals(1, T7.processRequest(arr, new T7.Request(100_000 - 1, 9592)));
        assertEquals(1, T7.processRequest(arr, new T7.Request(100_000 - 6, 9592)));
    }

    @org.junit.jupiter.api.Test
    void processRequestC1() {
        assertEquals(58790, T7.processRequest(arr, new T7.Request(99, 1)));
        assertEquals(-1, T7.processRequest(arr, new T7.Request(100, 1)));
        assertEquals(-1, T7.processRequest(arr, new T7.Request(101, 1)));
        assertEquals(58790, T7.processRequest(arr, new T7.Request(90, 1)));
    }


    @org.junit.jupiter.api.Test
    void processRequestC9593() {
        assertEquals(-1, T7.processRequest(arr, new T7.Request(100_000, 9593)));
    }
}
