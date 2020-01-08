/*
find a way to represent each pattern
*/

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new LinkedList<>();
        if (strings == null || strings.length == 0) {
            return res;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            StringBuilder sb = new StringBuilder();
            char c = s.charAt(0);
            for (int i = 0; i < s.length(); i++) {
                int diff = (s.charAt(i) - c + 26) % 26;
                sb.append(diff).append(',');
            }
            String comb = sb.toString();
            List<String> list = map.getOrDefault(comb, new LinkedList<String>());
            list.add(s);
            map.put(comb, list);
        }
        
        for (String key : map.keySet()) {
            List<String> list = new LinkedList<>();
            for (String val : map.get(key)) {
                list.add(val);
            }
            res.add(list);
        }
        
        return res;
    }
}


/*
Time complexity: O(m), m is the sum of characters in all strings
Space complexity: O(n), n is the number of strings
*/