/*
# DFS
# BFS

*/

// Solution 1: DFS
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length; // n nodes
        int[] sets = new int[n]; // 1 for subset A and -1 for subsetB
        for (int i = 0; i < n; i++) {
            if (sets[i] == 0 && !dfs(graph, sets, i, 1)) {
                return false;
            } // if sets[i] != 0, it was assigned a set, we do not rewrite
        }     // if sets[i] == 0, we start a new traverse, and assign it to set A
        return true;
    }
    
    private boolean dfs(int[][] graph, int[] sets, int cur, int set) { 
        if (sets[cur] != 0) {
            return sets[cur] == set;
        }
        sets[cur] = set;
        for (int node : graph[cur]) {
            if (!dfs(graph, sets, node, -set)) {
                return false;
            }
        }
        return true;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/

/*******************************************************/
// Solution 2: BFS
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length; // n nodes
        int[] sets = new int[n]; // 1 for subset A and -1 for subsetB
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (sets[i] != 0) {
                continue;
            }
            q.offer(i);
            sets[i] = 1;
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int node : graph[cur]) {
                    if (sets[node] == 0) {
                        sets[node] = -sets[cur];
                        q.offer(node);
                    } else {
                        if (sets[node] != -sets[cur]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}

/*
Time complexity: O(n), visie each node once
Space complexity: O(n), for the sets array and the queue
*/
