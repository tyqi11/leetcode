/*



*/

class TimeMap {

    Map<String, TreeMap<Integer, String>> map;
        
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
        
    }
    
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> tree = map.getOrDefault(key, new TreeMap<Integer, String>());
        tree.put(timestamp, value);
        map.put(key, tree);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> tree = map.get(key);
        Integer k = tree.floorKey(timestamp);
        if (k == null) {
            return "";
        } else {
            return tree.get(k);
        }
    }
}

/*
Time complexity: O(1) for set operation, and O(nlogn) for get operation, n is 
				 the number of entries in the TimeMap
Space complexity: O(n)
*/