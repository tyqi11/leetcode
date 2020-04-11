/*
# BFS
# DFS (up-to-bottom and bottom-up)
*/
// BFS
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] graph = new List[n]; // manager and its subordinations
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                graph[manager[i]].add(i);
            }
        }
        
        Queue<int[]> q = new LinkedList<>(); // until [0], spent [1] time
        q.offer(new int[]{headID, 0});
        int ans = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int m = cur[0], t = cur[1];
            for (int sub : graph[m]) {
                int time = t + informTime[m];
                q.offer(new int[]{sub, time});
                ans = Math.max(ans, time);
            }
        } 
        
        return ans;
    }
}

/*
Time complexity: O(n), for adding ArrayList, for forming the list, for iterating
Space complexity: O(n)
*/

/***************************************************************************/
// DFS, up-to-bottom
class Solution {
    int max = 0;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                graph[manager[i]].add(i);
            }
        }
        
        helper(headID, 0, graph, informTime);
        return max;
    }
    
    private void helper(int curMan, int totalTime, List<Integer>[] graph, int[] informTime) {
        for (int sub : graph[curMan]) {
            int time = totalTime + informTime[curMan];
            max = Math.max(max, time);
            helper(sub, time, graph, informTime);
        }
    }
}

// DFS, bottom-up
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                graph[manager[i]].add(i);
            }
        }
        
        return helper(headID, graph, informTime);
    }
    
    private int helper(int curMan, List<Integer>[] graph, int[] informTime) {
        int max = 0;
        for (int sub : graph[curMan]) {
            max = Math.max(max, helper(sub, graph, informTime));
        }
        return informTime[curMan] + max;
    }
}

/*
Time complexity: O(n), iterating over all members
Space complexity: O(n), for the call stack, also the graph
*/
