/*

207. Course Schedule
https://leetcode.com/problems/course-schedule/

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to 
first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it 
possible for you to finish all courses?

*/

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
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {  // O(V)
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        
        int count = q.size(); // number of indegree = 0 vertices
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