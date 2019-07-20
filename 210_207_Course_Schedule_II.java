/*
# Topological Sort

1. This problem is only one way further than 207. Course Schedule. We need
to keep array to store all the indegree = 0 vertices when we meet them.

2. DFS still works. But be clear of the order of taking courses.

*/

// Solution 1: topological sort
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        int[] ans = new int[numCourses];
        int idx = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                ans[idx++] = i;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                    ans[idx++] = next;
                }
            }
        }
        
        return idx == numCourses ? ans : new int[0]; // empty array: new int[0]
    }
}

/*
Time complexity: O(V + E)
Space complexity: O(V)
*/

/************************************************************/
// Solution 2: DFS
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) { // O(V)
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {   // O(E)
            graph.get(edge[0]).add(edge[1]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] inUse = new boolean[numCourses];
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < numCourses; i++) {  // O(V)
            if (!dfs(graph, visited, inUse, i, stack)) {
                return new int[0];
            }
        }
        
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = stack.poll();
        }
        return ans;
    } 
    
    // with visited array, each vertex visit only once
    private boolean dfs(List<List<Integer>> graph, boolean[] visited, boolean[] inUse, int cur, Deque stack) {
        if (visited[cur]) {
            return true;
        } 
        if (inUse[cur]) {
            return false;
        }
        
        inUse[cur] = true;
        for (int next : graph.get(cur)) {
            if (!dfs(graph, visited, inUse, next, stack)) {
                return false;
            }
        }
        visited[cur] = true;
        stack.offer(cur);
        return true;
    }
}

/*
Time complexity: O(V + E)
Space complexity: O(V)
*/