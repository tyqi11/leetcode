/*

*/

class Solution {
    
    Map<Integer, Integer> map;
    int res = 0;

    public int confusingNumberII(int N) {
        map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);

        if (N == 1000000000) {
            res++;
            N--;
        } // manual check if 10^9 is confusing, a trick here
        
        search(0, 0, N);
        return res;
    }
    
    private void search(int p, int cur, int N) {
        if (p > 9 || cur > N) {
            return;
        } // too many digits or too big number
        if (isConfusing(cur)) {
            res++;
        }
        
        for (int i : map.keySet()) {
            if (p == 0 && i == 0) {
                continue;
            }
            search(p + 1, cur * 10 + i, N);
        }
    }
    
    private boolean isConfusing(int n) {
        long rot = 0;
        int tmp = n;
        while (n > 0) {
            rot = rot * 10 + map.get(n % 10);
            n /= 10;
        }
        return rot != tmp;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/
