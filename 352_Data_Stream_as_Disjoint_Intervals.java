/*
# TreeMap
# BST

1. TreeMap solution: https://leetcode.com/problems/data-stream-as-disjoint-intervals/discuss/82553/Java-solution-using-TreeMap-real-O(logN)-per-adding.

2. BST solution: https://leetcode.com/problems/data-stream-as-disjoint-intervals/discuss/82610/Java-fast-log-(N)-solution-(186ms)-without-using-the-TreeMap-but-a-customized-BST

*/

class SummaryRanges {
    TreeMap<Integer, int[]> map;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }
    
    public void addNum(int val) { // O(logn)
        if (map.containsKey(val)) {
            return;
        }
        Integer lowerKey = map.lowerKey(val);
        Integer higherKey = map.higherKey(val);
        
        if (lowerKey!= null && higherKey != null 
            && val == map.get(lowerKey)[1] + 1 
            && val == map.get(higherKey)[0] - 1) {
            map.get(lowerKey)[1] = map.get(higherKey)[1];
            map.remove(higherKey);
        } else if (lowerKey != null && val <= map.get(lowerKey)[1] + 1) {
            map.get(lowerKey)[1] = Math.max(map.get(lowerKey)[1], val);
        } else if (higherKey != null && val == map.get(higherKey)[0] - 1) {
            map.put(val, new int[]{val, map.get(higherKey)[1]});
            map.remove(higherKey);
        } else { // new key is not connected to previous intervals
            map.put(val, new int[]{val, val});
        }
    }
    // lowerKey(), higherKey(), put() and remove() are all O(logN)
    
    public int[][] getIntervals() {
        int[][] res = new int[map.size()][2];
        int i = 0;
        for (int[] interval : map.values()) {
            res[i++] = interval;
        }
        return res;
    }
}

/*
Time complexity: O(logn) for each adding
*/