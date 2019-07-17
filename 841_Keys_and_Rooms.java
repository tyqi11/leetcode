/*
# DFS
# BFS

0. This is similar to 547. Friend circles. 

1. We write a more concise code of DFS for this problem, which uses a HashSet
to store all the entered rooms, instead of a boolean to track if one room is visited.

*/

// Solution 1: DFS
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return true;
        }
        Set<Integer> entered = new HashSet<>();
        entered.add(0);
        dfs(rooms, entered, 0);
        return entered.size() == rooms.size();
    }
    
    private void dfs(List<List<Integer>> rooms, Set<Integer> entered, int cur) {
        List<Integer> keys = rooms.get(cur);
        for (int room : keys) {
            if (entered.add(room)) {
                dfs(rooms, entered, room);
            }
        }
    }
}



/*
Time complexity: O(n)
Space complexity: O(n)
*/

/**************************************************************/
// Solution 2: BFS
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return true;
        }
        int n = rooms.size();
        boolean[] entered = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        entered[0] = true;
        int count = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> keys = rooms.get(cur);
            for (int room : keys) {
                if (!entered[room]) {
                    entered[room] = true;
                    q.offer(room);
                    count++;
                }
            }
        }
        return count == n;        
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/
