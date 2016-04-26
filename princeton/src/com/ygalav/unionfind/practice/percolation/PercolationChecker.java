package com.ygalav.unionfind.practice.percolation;

import com.ygalav.unionfind.QuickUnion;

import java.util.Arrays;

public class PercolationChecker {


    private QuickUnion quickUnion;
    private int square;
    private int unionArraySize;

    public PercolationChecker(int square) {
        this.square = square;
        unionArraySize = (int) Math.pow(this.square, 2);
        quickUnion = new QuickUnion(unionArraySize);
    }

    public void union(int q, int p) {
        if (isAbleToConnect(q, p)) {
            quickUnion.union(q, p);
            return;
        }
        throw new IllegalArgumentException("Cant make union");
    }

    public boolean connected(int q, int p) {
        return quickUnion.connected(q, p);
    }

    private boolean isAbleToConnect(int q, int p) {
        assert q >= 0 && q < unionArraySize;
        assert p >= 0 && p < unionArraySize;

        int qpDelta = q - p;
        return qpDelta == 1 || qpDelta == -1 ||
                qpDelta == square || qpDelta == -square;
    }

    int[] getNeighborsAvailableForConnection(int index) {
        final int topNeighbor = index - square;
        final int leftNeighbor = Utils.isOnDifferentLines(unionArraySize, index, index - 1) ? -1 : index - 1;
        final int rightNeighbor = Utils.isOnDifferentLines(unionArraySize, index, index + 1) ? -1 : index + 1;
        final int bottomNeighbor = index + square;
        final int[] intermediateArray = new int[] {topNeighbor, leftNeighbor, rightNeighbor, bottomNeighbor};

        return Arrays.stream(intermediateArray).filter(item -> (item > 0) && item < unionArraySize).toArray();
    }



    public boolean isPercolated() {
        int unionArraySize = (int) Math.pow(this.square, 2);
        int unionArrayLastIndex = unionArraySize - 1;
        int unionArrayBottomRowStartIndex = unionArraySize - square;

        for (int i = 0; i < square; i++) {
            for (int j = unionArrayLastIndex; j >= unionArrayBottomRowStartIndex; j--) {
                if (connected(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void percolate(final int quadrat, final double coef) {
        assert coef > 0 && coef < 1;
        final PercolationChecker percolationChecker = new PercolationChecker(quadrat);
        int[] blackWriteSquare = Utils.getPaintedSquare(percolationChecker.unionArraySize, coef);
        for (int i =0; i < blackWriteSquare.length; i++) {
            if (blackWriteSquare[i] == 1) {
                for (int j : percolationChecker.getNeighborsAvailableForConnection(i)) {
                    if (blackWriteSquare[j] == 1) {
                        percolationChecker.union(i, j);
                    }
                }
            }
        }

        drawSquare(percolationChecker.quickUnion.getArray());
        System.out.println("");
        drawSquare(blackWriteSquare);
        System.out.println("");
        System.out.println(percolationChecker.isPercolated());
        System.out.println("");

    }

    private static void drawSquare(int[] array) {
        int size = (int) Math.sqrt(array.length);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (array[i] < 10) System.out.print(" ");
            System.out.print(";");
            if (i != 0 && (i + 1) % size == 0) {
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++ ) {
            System.out.println("Percolating:");
            PercolationChecker.percolate(4, 0.6);
        }
    }
}
