package com.ygalav.unionfind;

public class QuickFind {

    private int[] id;

    public QuickFind(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int q, int p) {
        return id[q] == id[p];
    }

    public void union(int q, int p) {
        int qid = id[q];
        int pid = id[p];

        for (int i = 0; i< id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
