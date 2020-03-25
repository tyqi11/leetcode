/*
Similar to 659 Split Array into Consequtive Subsequences. But 659 requires 
len >= 3, while 846 requires len == W. So we need totally different solutions.

# 
*/
// Solution 1: PriorityQueue
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
	if (hand.length % W != 0) {
	    return false;
	}

        PriorityQueue<Integer> pq =  new PriorityQueue<>();
        for (int n : hand) {
            pq.offer(n);
        } // O(nlogn) for offering, n is the length of hand array
        
        while (!pq.isEmpty()) {
            int start = pq.peek();
            for (int i = 0; i < W; i++) {
                if (!pq.contains(start + i)) {
                    return false;
                }
                pq.remove(start + i); // O(n) for removing object
            }
        } // iterating for all the n elements
        
        return true;
    }
}

/*
Time complexity: O(nlogn + n^2) = O(n^2)
Space complexity: O(n) for the pq
*/

/******************************************************************/
// Solution 2: TreeMap for removing efficiency
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        } 
        
        for (int num : map.keySet()) { // iterate in natural order
            int cur = map.get(num); // treeMap.get() is O(logn), so save as new variables
            if (cur > 0) { // there IS num
                for (int i = W - 1; i >= 0; i--) {
                    int nxt = map.getOrDefault(num + i, 0); // same here because of O(logn)
                    if (nxt < cur) {
                        return false;
                    }
                    map.put(num + i, nxt - cur); // O(logn)
                } // from 0 to (W-1) causes ConcurrentModificationException
            }
        }
        
        return true;     
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(n) for the pq
*/