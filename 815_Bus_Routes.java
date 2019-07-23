/*
# BFS

0. This problem is difficult but very much worth practicing. We use BFS to
go to as many stops from current stop as possible in one single trip.

1. We create a HashMap with the stop index as the key, and a list of routes
that cover this stop as the value by iterating the routes array.

2. We use a queue to store all the stops we can reach at the current level.
To be specific: 
current stop -> 
for all bus routes passing current stop -> take it (if taken before, continue)
for each stop this bus can take us to: if this is the destination, return
 									   if not, this is one stop we need to 
 									   consider in the next level

3. For each level, all the bus stops we visit is one bus away from the 
previous stop.

*/

class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        int count = 0;
        Set<Integer> took = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>(); // stop and routes pass it
        for (int i = 0; i < routes.length; i++) { // number of routes
            for (int j = 0; j < routes[i].length; j++) { // number of stops
                graph.putIfAbsent(routes[i][j], new LinkedList<>());
                graph.get(routes[i][j]).add(i);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);
        while (!q.isEmpty()) {
            count++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curStop = q.poll();
                for (int bus : graph.get(curStop)) { // buses pass current stop
                    if (took.add(bus)) { 
                        for (int stop : routes[bus]) { // stops of current bus
                            if (stop == T) {
                                return count;
                            } else {
                                q.offer(stop);
                            }
                        }
                    }
                }
            } // end each trip
        } // go as much as possible
        return -1;
    }
}


/*
Time complexity: O(V + E)
Space complexity: O(V + E)
*/