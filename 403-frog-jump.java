class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        map.put(0, new HashSet<Integer>());
        map.get(0).add(1);
        
        for (int i = 1; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        
        for (int i = 0; i < stones.length; i++) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                int next = stone + step;
                if (next == stones[stones.length - 1]) {
                    return true;
                }
                if (map.containsKey(next)) {
                    Set<Integer> set = map.get(next);
                    set.add(step);
                    if (step - 1 > 0) {
                        set.add(step - 1);
                    }
                    set.add(step + 1);
                }
            }
        }
        return false;
    }
}