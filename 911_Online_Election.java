/*

1. When we want the greatest key less than or equal to the one provided, we 
think of TreeMap.

2. If we use binary search, we do not need TreeMap anymore, but HashMap, which 
reduces the time cost for construction from O(nlogn) to O(n).

3. In the method Arrays.binarySearch(), it returns the index of the `key` if 
`key` is in the array; otherwise, return i = -(insertion point) - 1. So 
insertionPoint = - i - 1. Then, the floor key is (- i - 2).

*/

// Solution 1: TreeMap
class TopVotedCandidate {
    TreeMap<Integer, Integer> curLead = new TreeMap<>(); // <time, leader>
    
    public TopVotedCandidate(int[] persons, int[] times) {
        int leader = -1;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < persons.length; i++) {
            int p = persons[i];
            int t = times[i];
            counter.put(p, counter.getOrDefault(p, 0) + 1);
            if (leader == - 1 || counter.get(p) >= counter.get(leader)) {
                leader = p; // when equal, change the leader to the most recent 
            }
            curLead.put(t, leader); // O(nlogn)
        }
    }
    
    public int q(int t) {
        return curLead.floorEntry(t).getValue();
    }
}

/*
Time complexity: O(nlogn) for construction, O(logn) for query, n is times.length
Space complexity: O(n)
*/

/***************************************************************/
// Solution 2: binary search
class TopVotedCandidate {
    Map<Integer, Integer> curLead = new HashMap<>(); // <time, leader>
    int[] time;
    
    public TopVotedCandidate(int[] persons, int[] times) {
        this.time = times;
        int leader = -1;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < persons.length; i++) {
            int p = persons[i];
            int t = times[i];
            counter.put(p, counter.getOrDefault(p, 0) + 1);
            if (leader == - 1 || counter.get(p) >= counter.get(leader)) {
                leader = p;
            }
            curLead.put(t, leader);
        }
    }
    
    public int q(int t) {
        int i = Arrays.binarySearch(time, t);
        int floorIndex = i < 0 ? (- i - 2) : i;
        return curLead.get(time[floorIndex]);
    }
}


/*
Time complexity: O(n) for construction, O(logn) for query, n is times.length
Space complexity: O(n)
*/