/*

1. It is easy to think of 'A' + x and n /= 26. But what is x? x is not n % 26,
because when n = 27, n % 26 = 1, (char)('A' + 1) is 'B' while we should get 'A'.
so x should be n % 26 - 1 ? No. When n = 26, n % 26 = 0, we cannot do - 1. So
we should do n-- before n % 26. And this is how it works.


*/

class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        return sb.toString();
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/