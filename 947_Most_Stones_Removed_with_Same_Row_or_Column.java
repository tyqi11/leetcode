/*
# Union Find

1. If there is a point at (x, y), connect row x with column y as a union. When
there is a new coordinate appears, count++. If two coordinates are unioned,
count--. After iterating all the stones, count is the number of independent parts.
stones.length - count is the stones can be removed.

2. ~stones[i][1] functions the same as stones[i][0] + 1000. (1000 is because
stones.length <= 1000.) We want to connect rows and columns, but the row and the
column may have the same index. So we do a little trick to the column index, so
that coordinates can be distinguished.

*/

class Solution {
    int count = 0;
    Map<Integer, Integer> graph = new HashMap<>(); 
    
    public int removeStones(int[][] stones) {       
        for (int i = 0; i < stones.length; i++) {
            union(stones[i][0], ~stones[i][1]);
        }
        
        return stones.length - count;
    }
    
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            graph.put(rootX, rootY);
            count--;
        }
    }
    
    private int find(int x) {
        if (!graph.containsKey(x)) {
            graph.put(x, x);
            count++;
        }
        if (x != graph.get(x)) {
            graph.put(x, find(graph.get(x)));
        }
        return graph.get(x);
    }
}

/*
Time complexity: O(n), stones.length
Space complexity: O(n)
*/

/**************************************************************************/
// DFS
class Solution {
    public int removeStones(int[][] stones) {
        Set<int[]> visited = new HashSet();
        int numOfIslands = 0;
        for (int[] s : stones){
            if (!visited.contains(s)){
               dfs(s, visited, stones); 
               numOfIslands++;
            }
        }
        return stones.length - numOfIslands;
    }
    
    private void dfs(int[] s1, Set<int[]> visited, int[][] stones){
        visited.add(s1);
        for (int[] s2: stones){
            if (!visited.contains(s2)){
                if (s1[0] == s2[0] || s1[1] == s2[1]) {
                    dfs(s2, visited, stones);
                }       
            }
        }
    }
} 

/*
Time complexity: O(n^2), stones.length
Space complexity: O(n)
*/