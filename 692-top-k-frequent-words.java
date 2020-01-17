class Solution {
    class Pair implements Comparable<Pair> {
        int n = 0;
        String s = "";
        
        Pair(String s, int n) {
            this.s = s;
            this.n = n;
        }
        
        @Override
        public int compareTo(Pair p) {
            if (this.n == p.n) {
                return p.s.compareTo(this.s); 
            } else {
                return this.n - p.n;
            }
        }
        // when freq same, the latter one is smaller means we want the
        // lower alphabetical order one
    }
    
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new LinkedList<>();
        
        if (words == null || words.length == 0 || k <= 0) {
            return res;
        }
        
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().s);
        }
        
        return res;
    }
    
    
}

/*another way to deal with the <String, Integer> pair is to add the entry:
Map.Entry<> as the element in the PriorityQueue, that is:*/

		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
		             (a,b) -> a.getValue() == b.getValue() ? 
		                      b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
		);
		        
		for (Map.Entry<String, Integer> entry : map.entrySet()){
			pq.offer(entry);
		    if(pq.size()>k) {
		    	pq.poll();
		    }
		}

		while(!pq.isEmpty()) {
			result.add(0, pq.poll().getKey());
		}
            

/*
Time complexity: O(n + nlogn)
Space complexity: O(nlogk)
*/