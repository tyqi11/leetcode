/*

1. The natural way that comes to our mind is to add all divisors of num, and check
the difference of sum and num. It takes O(n^(1/2)) time complexity.

2. One advanced method is using the Euclid-Euler Theorem. As shown in https://leetcode.com/problems/perfect-number/solution/

3. And also in solution 1, we can avoid the if (i * i != m) condition, as a square
can never be a perfect number according to Euclid-Euler Theorem. Check:
https://math.stackexchange.com/questions/1701915/could-a-square-be-a-perfect-number/1702054

*/

class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num <= 1) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) { // avoid Math.sqrt(), much faster
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        return sum - num == num;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/

/***********************************************************/
// Euclid-Euler Theorem
class Solution {
    public int pn(int p) {
        return (1 << (p - 1)) * ((1 << p) - 1);
    }

    public boolean checkPerfectNumber(int num) {
        int[] primes=new int[]{2,3,5,7,13,17,19,31};
        for (int prime: primes) {
            if (pn(prime) == num)
                return true;
        }
        return false;
    }
}