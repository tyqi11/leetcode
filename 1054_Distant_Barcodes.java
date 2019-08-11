/*

Similar to 358. Rearrange String k Distance Apart or 621. Task Scheduler

*/

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>(); // <value, appearanceTimes>
        for (int b : barcodes) {
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                if (a.getValue() == b.getValue()) {
                    return Integer.compare(a.getKey(), b.getKey());
                } else {
                    return Integer.compare(b.getValue(), a.getValue());
                }
            }
        });
        pq.addAll(map.entrySet()); // put all entries into the pq
        
        int[] res = new int[barcodes.length];
        int i = 0;
        List<Map.Entry<Integer, Integer>> list;
        
        while (!pq.isEmpty()) {
            int k = 2;
            list = new LinkedList<>();
            while (k > 0 && !pq.isEmpty()) {
                Map.Entry<Integer, Integer> cur = pq.poll();
                res[i++] = cur.getKey();
                cur.setValue(cur.getValue() - 1);
                list.add(cur);
                k--;
            }
            
            for (Map.Entry<Integer, Integer> e : list) {
                if (e.getValue() > 0) {
                    pq.offer(e);
                }
            }
        }
        
        return res;
    }
}


/*
Time complexity: O(nlogn)
Space complexity: O(n)
*/