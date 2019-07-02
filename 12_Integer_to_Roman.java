/*

1. There are certain amount of situations which may appear in a roman string. So
we can do a similar check in a HashMap, but we use two strings here, which are 
similar.

*/

class Solution {
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        if (num < 1 || num > 3999) {
            return "";
        }
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        
	    int i = 0;
        while (num > 0) {
            while (num >= values[i]) {
                num -= values[i];
                res.append(roman[i]);
            }
            i++;
        }
        return res.toString();
    }
}

/*
Time complexity: O(n) ?
Space complexity: O(1), set length of String array and int array
*/