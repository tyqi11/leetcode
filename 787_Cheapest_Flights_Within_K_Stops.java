/*
# Dijkstra Algorithm


*/

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] f : flights) { // f = [src, dst, price]，O(m), flights.length
            if (!graph.containsKey(f[0])) {
                graph.put(f[0], new HashMap<>());
            }
            graph.get(f[0]).put(f[1], f[2]);
        } // <currentCity, <nextCity, price> paris>
        
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { 
            @Override
            public int compare(int[] a, int[] b) { 
                return Integer.compare(a[0], b[0]);
            }
        });
        pq.add(new int[]{0, src, K + 1});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); // [totalCost, src, stopsLeft], O(logn)
            int cost = cur[0];
            int city = cur[1];
            int stops = cur[2];
            if (city == dst) {
                return cost; // always pop out the cheapest, when arrive, 
            }
            if (stops > 0) {
                Map<Integer, Integer> nextCities = graph.getOrDefault(cur[1], new HashMap<>()); 
                for (int next : nextCities.keySet()) { // visit each route once, sum O(m)
                    pq.add(new int[] {cost + nextCities.get(next), next, stops - 1});
                }
            }
        }
        return -1;
    }
}

/*
Time complexity: O(m + nlogn)
Space complexity: O(m + n), O(m) for graph map, O(n) for the PriorityQueue
*/