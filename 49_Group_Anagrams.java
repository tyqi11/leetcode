/*

1. To group something is to find the same characteristic in them. In this problem,
strings in the same group has the same count array (shown in code). But int arrays
are the same only if they are identical (same memory address) in Java, so we hash 
it to get the same key.

2. return new ArrayList<>(map.values())
   is also a new learning point, for how to make a list of all the values in a map.

*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            int key = Arrays.hashCode(count); 
            // or String key = Arrays.toString(count)
            List<String> curList = map.getOrDefault(key, new ArrayList<String>());
            curList.add(str);
            map.put(key, curList);
        }
        return new ArrayList<>(map.values());
    }
}

/*
Time complexity: O()
*/