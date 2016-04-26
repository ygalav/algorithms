package com.ygalav.unionfind.practice.percolation;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PercolationCheckerTest {

    private PercolationChecker testInstance = new PercolationChecker(4);

    @Test(expected = IllegalArgumentException.class)
    public void testUnion_shouldNotAllowToConnectNotStraightNodes() throws Exception {
        testInstance.union(6, 1);
    }

    @Test()
    public void testUnion_shouldAllowToConnectNeighborStraightNodes() throws Exception {
        testInstance.union(6, 10);
        assertTrue(testInstance.connected(6, 10));
    }

    @Test
    public void testIsPercolated() {
        testInstance.union(0, 4);
        testInstance.union(4, 5);
        testInstance.union(5, 9);
        testInstance.union(9, 8);
        testInstance.union(8, 12);

        assertTrue(testInstance.isPercolated());
    }

    @Test
    public void testIsPercolated_negative() {
        testInstance.union(0, 4);
        testInstance.union(4, 5);
        testInstance.union(5, 9);
        testInstance.union(9, 8);

        assertFalse(testInstance.isPercolated());
    }

    @Test
    public void testGetNeighborsAvailableForConnection_testLeftTopCornerIndex() {
        int leftTopCornerIndex = 0;
        final int[] neighborsForLeftCornerIndex = testInstance.getNeighborsAvailableForConnection(leftTopCornerIndex);
        final int[] expectedNeighborsForLeftCornerIndex = new int[]{1, 4};

        verifyArrays(neighborsForLeftCornerIndex, expectedNeighborsForLeftCornerIndex);
    }

    @Test
    public void testGetNeighborsAvailableForConnection_testRightTopCornerIndex() {
        int leftTopCornerIndex = 3;
        final int[] neighborsForRightTopCornerIndex = testInstance.getNeighborsAvailableForConnection(leftTopCornerIndex);
        final int[] expectedNeighborsForRightTopCornerIndex = new int[]{7, 2};

        verifyArrays(neighborsForRightTopCornerIndex, expectedNeighborsForRightTopCornerIndex);
    }

    @Test
    public void testGetNeighborsAvailableForConnection_testRightBottomCornerIndex() {
        int index = 15;
        final int[] neighbors = testInstance.getNeighborsAvailableForConnection(index);
        final int[] expectedNeighbors = new int[]{14, 11};

        verifyArrays(neighbors, expectedNeighbors);
    }

    @Test
    public void testGetNeighborsAvailableForConnection_testLeftBottomCornerIndex() {
        int index = 12;
        final int[] neighbors = testInstance.getNeighborsAvailableForConnection(index);
        final int[] expectedNeighbors = new int[]{8, 13};

        verifyArrays(neighbors, expectedNeighbors);
    }

    @Test
    public void testGetNeighborsAvailableForConnection_testMiddlePosition() {
        int index = 6;
        final int[] neighbors = testInstance.getNeighborsAvailableForConnection(index);
        final int[] expectedNeighbors = new int[]{2, 7, 5, 10};

        verifyArrays(neighbors, expectedNeighbors);
    }

    private void verifyArrays(int[] neighbors, int[] expectedNeighbors) {
        Arrays.sort(neighbors);
        Arrays.sort(expectedNeighbors);

        assertTrue(
                Arrays.equals(neighbors, expectedNeighbors)
        );
    }


}