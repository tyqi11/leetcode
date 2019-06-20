/*

1. We use two ways to solve this problem. First is to use O(n) time and O(n)
space, while the second use O(n) time and only O(1) space.

2. With O(n) space, we can create a new array to store the candies assigned
to each child. First we fill the array with 1's as each child will get at 
least one candy. Then we go from left to right, pretend the rules to be:
if current child get higher rating than his left neighbor, give more candies.
Next we go from right to left and follow the same logit. Before assign candies,
we need to follow both cur = right + 1, also cur = left + 1, so cur should
be the max of the two. Finally go through the array to add all the candies.

3. (credit to: @meng789987, @JobQ: https://leetcode.com/problems/candy/discuss/135698/Simple-solution-with-one-pass-using-O(1)-space) 
In the O(1) space solution, we care about the trend of the ratings going up
or down. One thing to care more about is the (peak >= down ? -1 : 0). If 
peak >= down, it means that we do not arrive at 1 yet and we rise more times
than going down. We do not need to change anything. But if peak < down, it
means that we are going to give the current child 0. 
For example, 
 0  1  2  3  4  5    
[1, 2, 8, 6, 4, 2].  
at index 2: peak = 2, up = 2, down = 0; we give the child 1 + up = 3
at index 3: peak = 2, up = 0, down = 1; we give the child 1 + down + (-1) = 1 
at index 4: peak = 2, up = 0, down = 2; we give the child 1 + down + (-2) = 2
at index 5: peak = 2, up = 0, down = 3; we give the chiil 1 + down + (0) = 4
the result is [1, 2, 3, 1, 2, 4], sum = 13
in fact, when going down, we are recording the candies we should give the previous 
child to the current child. When we find 4 < 6, we should give 6 one more as it is
higher than a neighbor. We give 6, but record it as if we give it to 4.
So in fact, we should give [1, 2, 3, 2, 1, ?].
When it comes to index 5, when down = 3 and peak = 2. It means that from left to 
right 8 is higher than 2 but from right to left, 8 is higher than 3. (Just like how
we compute from right to left in the first solution.) So we need to add 1 to each 
one during the whole going down process as [1, 2, 3 + 1, 2 + 1, 1 + 1, 1]. But we
count it as [1, 2, 3, 2, 1, 1 + 3]. The 3 is from the previous 3 children.
So the total should be [1, 2, 4, 3, 2, 1] but we record it as [1, 2, 3, 1, 2, 4].
They are the same in total.

*/

// Solution 1
class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int sum = candies[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            if (ratings[j] > ratings[j + 1]) {
                candies[j] = Math.max(candies[j], candies[j + 1] + 1);
            }
            sum += candies[j];
        }
        return sum;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/

/****************************************************************/

// Solution 2
class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int up = 0, down = 0;
        int sum = 1; // for the first child
        int peak = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                up++;
                down = 0;
                peak = up;
                sum += 1 + up;
            } else if (ratings[i - 1] == ratings[i]) {
                peak = up = down = 0;
                sum += 1;
            } else { // ratings[i - 1] > ratings[i]
                up = 0;
                down++;
                sum += 1 + down + (peak >= down ? -1 : 0);
            }
        }
        return sum;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/