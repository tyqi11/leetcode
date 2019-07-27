/*
# Dynamic Programming

1. dp[i] is the min possible height after the i-th book, which is books[i - 1].

2. When we want to put this books[i - 1] onto the shelves. There are two choices:
2.1 Put it alone onto a new shelf, then dp[i] = dp[i - 1] + books[i - 1][1].
2.2 Put it with previous books. But the number of books to be put on the same 
shelf is restricted by the shelf_width. So, we update the height every time one
more book is with the current book, until it reaches the shelf_width limit.


*/

class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // either put this book onto a new shelf
            dp[i] = dp[i - 1] + books[i - 1][1];
            // or put it with previous books
            int sum = books[i - 1][0];
            int height = books[i - 1][1];
            for (int j = i - 1; j >= 1; j--) {
                if (sum + books[j - 1][0] <= shelf_width) {
                    sum += books[j - 1][0];
                    height = Math.max(height, books[j - 1][1]);
                    dp[i] = Math.min(dp[i], dp[j - 1] + height);
                } else {
                    break;
                }
            }
        }
        return dp[n];
    }
}

/*
Time complexity: O(n^2), n is number of books
Space complexity: O(n), for the dp array
*/