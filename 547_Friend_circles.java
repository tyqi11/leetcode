/*
# DFS
# BFS
# Union-Find

0. There are three ways to solve these connected-elements-problems: DFS, BFS 
and Union-Find.

1. DFS: we iterate over each student and mark all his friends (and friends of 
his friends) as visited using DFS. So next time we when start visiting the next 
student, as long as there is an unvisited student, we know that there is a new 
friend zone.

2. BFS: same idea as DFS. But we use a q to store all friends of one student.

3. To use Union-Find, the general way is to create a UnionFind class. This is
a template for many future problems. 

*/


// Solution 1: DFS
class Solution {
    public int findCircleNum(int[][] M) {
    	if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int n = M.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) { // iterate over each student
            if (!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
    
    private void dfs(int[][] M, boolean[] visited, int cur) {
        for (int i = 0; i < M.length; i++) {
            if (!visited[i] && M[cur][i] == 1) {
                visited[i] = true;
                dfs(M, visited, i);
            }
        }
    }
}

/*
Time complexity: O(n^2), one-pass
Space complexity: O(n), for the visited array and call stack
*/

/*******************************************************/
// Solution 2: BFS
class Solution {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int n = M.length;
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            count++;
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            visited[i] = true;
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int j = 0; j < n; j++) {
                    if (!visited[j] && M[cur][j] == 1) {
                        visited[j] = true;
                        q.offer(j);
                    }
                }
            }
        }
        return count;
    }
}

/*
Time complexity: O(n^2), one pass
Space complexity: O(n), for visited array and queue
*/

/*******************************************************/
// Solution 3: Union-Find
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

/*
Time complexity: O(n^2*lgn)
Space complexity: O(n), for parent and rank array
*/











