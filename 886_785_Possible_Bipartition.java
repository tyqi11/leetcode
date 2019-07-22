/*
# DFS
# BFS

1. This is similar to 785.Is Graph Bipartite. We need to construct an adjacency
list due to the reason that we are given an array depicting "edges between vertices".

*/

// Solution 1: DFS
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) { 
            graph.add(new ArrayList<>());
        }
        for (int[] pair : dislikes) {
            graph.get(pair[0] - 1).add(pair[1] - 1);
            graph.get(pair[1] - 1).add(pair[0] - 1);
        }       
        int[] sets = new int[N]; // 1 for set A, -1 for set B
// ====above==== DFS and BFS are the same         
        for (int i = 0; i < N; i++) {
            if (sets[i] == 0 && !dfs(graph, sets, i, 1)) {
                return false;
            } 
        }
        return true;
    }
    
    private boolean dfs(List<List<Integer>> graph, int[] sets, int i, int set) {
        if (sets[i] != 0) {
            return sets[i] == set;
        }
        sets[i] = set;
        for (int j : graph.get(i)) {
            if (!dfs(graph, sets, j, -set)) {
                return false;
            }
        }
        return true;
    }
}


/*
Time complexity: O(V + E), vertices and edges
Space complexity: O(V + E)
*/

/************************************************************/
// Solution 2: BFS
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) { 
            graph.add(new ArrayList<>());
        }
        for (int[] pair : dislikes) {
            graph.get(pair[0] - 1).add(pair[1] - 1);
            graph.get(pair[1] - 1).add(pair[0] - 1);
        }
        int[] sets = new int[N]; // 1 for set A, -1 for set B
// ====above==== DFS and BFS are the same         
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (sets[i] != 0) {
                continue;
            }
            sets[i] = 1;
            q.offer(i);
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int j : graph.get(cur)) {
                    if (sets[j] == 0) {
                        sets[j] = -sets[cur];
                        q.offer(j);
                    } else if (sets[j] != 0 && sets[j] != -sets[cur]) {
                        return false;
                    }  
                }
            }
        }
        return true;
    }
}

/*
Time complexity: O(V + E), vertices and edges
Space complexity: O(V + E)
*/