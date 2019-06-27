/*

1. To save space, we can do a length check first so that we can put strings in
the shorter array into the hashmap.

2. res.toArray(new String[res.size()])
   This is the way to get an array of items w/out specific size. Create a List 
   to store the values first, and then use list.toArray().

*/

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length > list2.length) {
            return findRestaurant(list2, list1);
        } // to save space
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int min = Integer.MAX_VALUE;
        List<String> res = null;
        for (int j = 0; j < list2.length; j++) {
            if (map.containsKey(list2[j])) {
                int sum = j + map.get(list2[j]);
                if (sum < min) {
                    res = new ArrayList<>();
                    res.add(list2[j]);
                    min = sum;
                } else if (sum == min) {
                    res.add(list2[j]);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }
}

/*
Time complexity: O(m + n), m and n are the length of two lists
Space complexity: O(min(m, n)), save strings of the shorter array into a hashmap
*/