/*
# Topological Sort
# DFS

1. This is a typical topological sort problem. 
1.1 We create a indegree array and an adjacency list to represent the graph. 
For example:
                        indegree adjacency list
   A --> B --> C     		0    A --> B --> D
    \     	 /	\			1    B --> C
     \   	/	 \	  -->	1 	 C --> D --> E
      V    V	  V			2    D --> E
        D -------->E    	2    E
1.2 We maintain a queue to store all vertices with indegree 0. Poll this 
element (take this course). Then iterate over all the ones who take this 
vertex as a prerequisite, decrease their indegree (one prerequisite course 
finished). If the indegree is 0, put into the queue. Count the number of courses
whose indegree reached 0 at the end. If the number is less than required
courses, there must be some course who still have prerequisite not finished.
So we cannot take all the courses.

2. DFS: for each course, go through all the courses depending on this course,
return false until one dependency is a prerequisite of a visited course.

*/

// Solution 1: Topological Sort
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // an indegree array of vertices indegrees
        int[] indegree = new int[numCourses];
        // an adjacency list of vertices current vertex connects
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) { // O(V)
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {     // O(E), [cur, pre]
            adjList.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }
        
        // maintain a queue of vertices with indegree 0
        int count = 0;  // number of indegree = 0 vertices
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {  // O(V)
            if (indegree[i] == 0) {
                q.offer(i);
                count++;
            }
        }
        
        while (!q.isEmpty()) {                  // O(V)
            int cur = q.poll(); // this course finished
            for (int adj : adjList.get(cur)) { // for courses depend on it
                indegree[adj]--;
                if (indegree[adj] == 0) {
                    count++;
                    q.offer(adj);
                }
            }
        }
        return count == numCourses;
    } 
}

/*
Time complexity: O(V + E), V is the number of vertices (courses here), 
						   E is the number of edges (prerequisites.length here) 
Space complexity: O(V), V for the indegree array, and the list
*/

/*************************************************************/
// Solution 2: DFS
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] dp = new boolean[numCourses];
        for (int[] edge : prerequisites) {   // O(E)
            graph.get(edge[0]).add(edge[1]);
        }
        for (int i = 0; i < numCourses; i++) {  // O(V)
            if (!dfs(graph, visited, dp, i)) {
                return false;
            }
        }
        return true;
    } 
    
    // with visited array, each vertex visit only once
    private boolean dfs(List<List<Integer>> graph, boolean[] visited, boolean[] dp, int cur) {
        if (visited[cur]) {
            return dp[cur];
        } else {
            visited[cur] = true;
        }
        
        for (int course : graph.get(cur)) {
            if (!dfs(graph, visited, dp, course)) {
                dp[course] = false;
                return false;
            }
        }
        dp[cur] = true;
        return true;
    }
}     

/*
Time complexity: O(V + E)
Space complexity: O(V)
*/