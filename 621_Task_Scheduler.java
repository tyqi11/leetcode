/*

Similar to 358. Rearrange String k Distance Apart or 1054. Distant Barcodes.

*/

class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char t : tasks) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            (a, b) -> a.getValue() == b.getValue() ? 
                     a.getKey() - b.getKey() : b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        
        int count = 0;
        List<Map.Entry<Character, Integer>> wait;
        while (!pq.isEmpty()) {
            int k = n + 1;
            wait = new LinkedList<>();
            while (k > 0 && !pq.isEmpty()) {
                Map.Entry<Character, Integer> cur = pq.poll();
                cur.setValue(cur.getValue() - 1);
                wait.add(cur);
                k--;
                count++;
            }           
            
            for (Map.Entry<Character, Integer> w : wait) {
                if (w.getValue() > 0) {
                    pq.offer(w);
                }
            }
            
            if (pq.isEmpty()) {
                break;
            }
            count += k;
        }
        
        return count;
    }
}




/*
Time complexity: O(n)
Space complexity: O(n)
*/