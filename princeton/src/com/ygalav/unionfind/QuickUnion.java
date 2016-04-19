package com.ygalav.unionfind;

public class QuickUnion {

    private int[] id;

    public QuickUnion(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    public void union(int q, int p) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    public boolean connected(int q, int p) {
        return root(q) == root(p);
    }


    /**
     *
     *  0  1--2  3
     *  | /  /  /\
     *  |/  /  / \
     *  4  5--6   7--8  9
     *
     */
    public static void main(String[] args) {
        final QuickUnion quickUnion = new QuickUnion(10);
        quickUnion.union(0, 4);
        quickUnion.union(4, 1);
        quickUnion.union(1, 2);
        quickUnion.union(2, 5);
        quickUnion.union(5, 6);
        quickUnion.union(6, 3);
        quickUnion.union(3, 7);
        quickUnion.union(7, 8);

        System.out.println("0 and 8 are connected: [" + quickUnion.connected(0, 8) + "]");
        System.out.println("0 and 9 are connected: [" + quickUnion.connected(0, 9) + "]");
    }
}
