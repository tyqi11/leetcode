/*
# Sliding Window/Two Pointers

1. We use two pointers to mark a sliding window, in which is the array we are
working on. j is always increasing, marking the end of the array. i is the largest
index that keeps the array with k distinct elements.
         0, 1, 2, 3, 4
2. Take [1, 2, 1, 2, 3] and 2 as an example.
         i  j               map:{<1, 1>, <2, 1>}, dist = 2, res = 1
         i     j            map:{<1, 2>, <2, 1>}, dist = 2, res = 1 + 2 = 3
         i        j         map:{<1, 2>, <2, 2>}, dist = 2, res = 3 + 3 = 6
         i           j      map:{<1, 2>, <2, 2>, <3, 1>}, dist = 3, res = 6
            i        j      map:{<1, 1>, <2, 2>, <3, 1>}
               i     j      map:{<1, 1>, <2, 1>, <3, 1>}
                  i  j      map:{<1, 0>, <2, 1>, <3, 1>}, dist = 2, res = 6 + 1 = 7
result: 
{[1, 2], [2, 1], [1, 2, 1], [2, 1, 2], [1, 2], [1, 2, 1, 2], [1, 2, 1, 2, 3]}

3. The key point is that when we have an array, and include a new element but
not changing *distinct*, there are j - i more arrays added to the final result.

*/

class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }
    
    private int atMostK(int[] A, int k) {
        int i = 0;
        int j = 0;
        int res = 0;
        int distinct = 0;
        Map<Integer, Integer> count = new HashMap<>(); // <value, times>
        
        while (j < A.length) {
            if (count.getOrDefault(A[j], 0) == 0) {
                distinct++;
            }
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            j++;
            while (i < j && distinct > k) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0) {
                    distinct--;
                }
                i++;
            }
            res += j - i;
        }
        
        return res;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/