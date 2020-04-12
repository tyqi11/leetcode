/*

*/

class Solution {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        
        List<Integer> balance = new ArrayList<>(map.values()); 
        // map.values() return a Collection view, not a set view!
        // iterate and add only non-zero values may save time
        // save as an array may save time during iterations (exponential)
        return settle(0, balance);
    }
    
    private int settle(int cur, List<Integer> balance) {
        while (cur < balance.size() && balance.get(cur) == 0) {
            cur++;
        } // exit when cur == balance.size() or balance.get(cur) != 0
        
        if (cur == balance.size()) {
            return 0;
        }
        
        int pay = Integer.MAX_VALUE;
        for (int i = cur + 1; i < balance.size(); i++) {
            if (balance.get(i) * balance.get(cur) < 0) {
                balance.set(i, balance.get(i) + balance.get(cur));
                pay = Math.min(pay, 1 + settle(cur + 1, balance));
                balance.set(i, balance.get(i) - balance.get(cur));
            }
        }
        return pay;
    }
}

/*
Time complexity: O(n!), n is the number of overall non-zero balance
Space complexity: O(n)
*/