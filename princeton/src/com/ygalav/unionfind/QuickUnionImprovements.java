package com.ygalav.unionfind;

public class QuickUnionImprovements {

    private int[] id;
    private int[] size;

    public QuickUnionImprovements(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    public void union(int _Q, int _P) {
        int _Qroot = root(_Q);
        int _Proot = root(_P);

        if (size[_Proot] > size[_Qroot]) {
            id[_Qroot] = _Proot;
            size[_Proot] = size[_Qroot] + size[_Proot];
        }
        else {
            id[_Proot] = _Qroot;
            size[_Qroot] = size[_Proot] + size[_Qroot];
        }
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
        final QuickUnionImprovements quickUnion = new QuickUnionImprovements(10);
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
