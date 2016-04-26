package com.ygalav.unionfind.practice.percolation;

import java.util.Random;

public final class Utils {
    private Utils() {
    }

    public static boolean isOnDifferentLines(int squareSize, int currentPosition, int testPosition) {
        int lineSize = (int) Math.sqrt(squareSize);
        if ((currentPosition + 1) % lineSize == 0 && testPosition > currentPosition) return true; //Is most right element
        if ((currentPosition) % lineSize == 0 && testPosition < currentPosition) return true; //Is most left element

        final int delta = currentPosition - testPosition;
        return  (delta >= lineSize || delta <= -lineSize);
    }

    public static int[] getPaintedSquare(final int arryaSize, final double coef) {
        int[] blackWriteSquare = new int[arryaSize];
        int whiteNodesCount = 0;
        while (((double)whiteNodesCount / (double)arryaSize) < coef) {
            int currentIndex = getRandomNumberInRange(0, arryaSize);
            int currentValue = blackWriteSquare[currentIndex];
            if (currentValue == 0) {
                blackWriteSquare[currentIndex] = 1;
                whiteNodesCount ++;
            }
        }
        return blackWriteSquare;
    }

    private static int getRandomNumberInRange(int from, int to) {
        Random r = new Random();
        return r.nextInt((to - from)) + from;
    }
}
