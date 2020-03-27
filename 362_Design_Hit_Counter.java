/*
This solution is perfect even when the number of hits per second is quite large.
*/

class HitCounter {
    Queue<Integer> q;
    Map<Integer, Integer> map;
    int sum;
        
    /** Initialize your data structure here. */
    public HitCounter() {
        q = new LinkedList<>(); // previous hit timestamp
        map = new HashMap<>(); // how many hits happen at this same time
        sum = 0; // sum for the last 300 seconds
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        while (!q.isEmpty() && q.peek() <= timestamp - 300) {
            sum -= map.get(q.poll());
        }
        if (!map.containsKey(timestamp)) {
            q.offer(timestamp);
        }
        map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
        sum++;
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!q.isEmpty() && q.peek() <= timestamp - 300) {
            sum -= map.get(q.poll());
        }
        
        return sum;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */


/*
Time complexity: O()
Space complexity: O()
*/
