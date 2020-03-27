/*
# Djikstra's Algorithm

(my intuitive implementation is similar and faster. LOL)
*/

// Solution 1: my intuitive way
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // <start_node, list of end_nodes>
        Map<Integer, Integer> time = new HashMap<>(); // <route, time>
        // two maps can be combined with one, which, for me, is not better
        for (int[] edge: times) {
            int s = edge[0], e = edge[1], t = edge[2];
            map.putIfAbsent(s, new LinkedList<>());
            map.get(s).add(e);
            time.put(s * 101 + e, t);
        }
        
        if (map.get(K) == null) {
            return -1;
        }
        
        int[] receive = new int[N + 1];
        Arrays.fill(receive, Integer.MAX_VALUE);
        receive[K] = 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(K);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int end : map.get(cur)) {
                int temp = receive[end];
                receive[end] = Math.min(receive[end], receive[cur] + time.get(cur * 101 + end));
                if (receive[end] < temp && map.get(end) != null) {
                    q.offer(end);
                }
            }   
        }
        
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, receive[i]);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;                                        
    }
}


/*
Time complexity: O()
Space complexity: O()
*/

/******************************************************************/
// Solution 2: Dijkstra's Algorithm
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        // <start_node, <end_node, travel_time>>
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>(); 
        for (int[] edge: times) {
            map.putIfAbsent(edge[0], new HashMap<>());
            map.get(edge[0]).put(edge[1], edge[2]);
        }
        
        if (map.get(K) == null) {
            return -1;
        }
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        // [time_from_prev_node, curNode]
        pq.offer(new int[]{0, K});
        
        boolean[] visited = new boolean[N + 1];
        int ans = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curTime = cur[0], curNode = cur[1];
            if (visited[curNode]) {
                continue;
            }
            visited[curNode] = true;
            N--; // how many nodes left to visit
            ans = curTime;
            
            if (map.containsKey(curNode)) {
                for (Map.Entry<Integer, Integer> entry : map.get(curNode).entrySet()) {
                    int nextNode = entry.getKey();
                    int nextTime = entry.getValue(); 
                    pq.offer(new int[]{curTime + nextTime, nextNode});
                }
            }
        }
        
        return N == 0 ? ans : -1;                                               
    }
}