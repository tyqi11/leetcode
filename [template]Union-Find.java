/*

547. Friend Circles 
https://leetcode.com/problems/friend-circles/

There are N students in a class. Some of them are friends, while some are not. 
Their friendship is transitive in nature. For example, if A is a direct friend 
of B, and B is a direct friend of C, then A is an indirect friend of C. And we 
defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in 
the class. If M[i][j] = 1, then the ith and jth students are direct friends with 
each other, otherwise not. And you have to output the total number of friend 
circles among all the students.

*/

class Solution {
    class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ; // small tree as child
            } else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    public int findCircleNum(int[][] M) {
    	if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }
}
