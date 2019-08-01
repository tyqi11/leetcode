/*
# Two Pointers

1. The key is to understand the if-else if-else if loop. For any combinations of
i + j + k = target, it must fall into one of the three categories:
1) i = j = k 	three are the same
2) j < j < k 	three are all different
3) i = j != k 	two are the same, one is different
Any combination falls into only one category only once. So this loop makes sure
that we count each combination only once.

2. The res calculation is based on permutations. Like C_5_2 = (5 * 4) / (2 * 1).
The mathematical way to express is: C_n_k = n! / ((n - k)! * k!). In the code,
1) num[i] = num[j] = num[j] --> C_num[i]_3
2) num[i] = num[j] != num[k] --> C_num[i]_2 * C_num[k]_1
3) num[i] != num[j] != num[k]  --> C_num[i]_1 * C_num[j]_1 * C_num[k]_1

3. Confusion: When return, if it is (int) (res % (10^9 + 7)), the output is wrong. 
What is the difference between 1e9+7 and 10^9 + 7? Also, I think each number appears 
no more than 3000 times, so num[i] can be int[], but the answer is wrong again? 
why? 

One possible answer? 1e9 + 7 is double but 10^9 + 7 is int?

*/

class Solution {
    public int threeSumMulti(int[] A, int target) {
        long[] num = new long[101]; // number i appears num[i] times
        for (int a : A) {
            num[a]++;
        }
        
        long res = 0;
        for (int i = 0; i <= 100; i++) { // 0 <= A[i] <= 100
            for (int j = i; j <= 100; j++) {
                int k = target - i - j;
                if (k < 0 || k > 100) {
                    continue;
                }
                if (i == j && j == k) {
                    res += num[i] * (num[i] - 1) * (num[i] - 2) / 6;
                } else if (i == j && j != k) {
                    res += num[i] * (num[i] - 1) / 2 * num[k];
                } else if (j < k) {
                    res += num[i] * num[j] * num[k];
                }
            }
        }
        
        return (int) (res % (1e9 + 7));
    }
}

/*
Time complexity: O(n + 101^101)
Space complexity: O(101)
*/

