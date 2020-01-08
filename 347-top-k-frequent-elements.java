/*

*/

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }
        
        List<Integer> res = new LinkedList<>();
        while (res.size() < k) {
            res.add(pq.poll().getKey());
        }
        
        return res;
    }
}

/*
Time complexity: O(m * n)
Space complexity: O(1)
*/