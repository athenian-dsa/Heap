package test.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyTests {
    public static boolean arrayEqualsUpTo(int lastIndexExclusive, int[] arr1, int[] arr2) {
        for (int i = 0; i < lastIndexExclusive; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void insertionAndTrickeUp() {
        // --------------------------
        // Test 1: Insertion and Trickle Up
        // --------------------------
        Heap test = new Heap();

        // These nodes are actually added in a way that doesn't
        // mess up the binary heap property
        test.insert(7);
        test.insert(8);
        test.insert(9);

        int[] EXPECTED = new int[] {7, 8, 9};
        assertTrue(arrayEqualsUpTo(EXPECTED.length, EXPECTED, test.arr));

        // Adding these next few nodes requires some trickle-ups!
        test.insert(1);
        test.insert(2);
        test.insert(3);

        EXPECTED = new int[] {1, 2, 3, 8, 7, 9};
        assertTrue(arrayEqualsUpTo(EXPECTED.length, EXPECTED, test.arr));
    }

    @Test
    public void deletionAndTrickleDown() {
        Heap test = new Heap();
        test.insert(7);
        test.insert(8);
        test.insert(9);
        test.insert(1);
        test.insert(2);
        test.insert(3);
        // --------------------------
        // Test 2: Delete Min and Trickle Down
        // --------------------------
        test.deleteMin();
        int[] EXPECTED = new int[] {2, 7, 3, 8, 9};
        assertTrue(arrayEqualsUpTo(EXPECTED.length, EXPECTED, test.arr));
        
        // Delete some more minimum values
        test.deleteMin();
        test.deleteMin();
        EXPECTED = new int[] {7, 8, 9};
        assertTrue(arrayEqualsUpTo(EXPECTED.length, EXPECTED, test.arr));
    }

    @Test
    public void changeTrickleUp() {
        Heap test = new Heap();
        test.insert(7);
        test.insert(8);
        test.insert(9);
        test.insert(1);
        test.insert(2);
        test.insert(3);
        test.deleteMin();
        test.deleteMin();
        test.deleteMin();
        // --------------------------
        // Test 3: Change (trickle up)
        // --------------------------
        // Then change values of other nodes that trickle up
        test.change(5, 2);
        int[] EXPECTED = new int[] {2, 4, 3, 8, 7, 9, 6};
        assertTrue(arrayEqualsUpTo(EXPECTED.length, EXPECTED, test.arr));

        test.change(7, 1);
        EXPECTED = new int[] {1, 2, 3, 8, 4, 9, 6};
        assertTrue(arrayEqualsUpTo(EXPECTED.length, EXPECTED, test.arr));
    }

    @Test
    public void changeTrickleDown() {
        Heap test = new Heap();
        test.insert(7);
        test.insert(8);
        test.insert(9);
        test.insert(1);
        test.insert(2);
        test.insert(3);
        test.deleteMin();
        test.deleteMin();
        test.deleteMin();
        test.change(5, 2);
        test.change(7, 1);
        // --------------------------
        // Test 4: Change (trickle down)
        // --------------------------
        // Change values of nodes that trickle down
        test.change(3, 10);
        int[] EXPECTED = new int[] {1, 2, 6, 8, 4, 9, 10};
        assertTrue(arrayEqualsUpTo(EXPECTED.length, EXPECTED, test.arr));

        test.change(1, 20);
        EXPECTED = new int[] {2, 4, 6, 8, 20, 9, 10};
        assertTrue(arrayEqualsUpTo(EXPECTED.length, EXPECTED, test.arr));
    }
}