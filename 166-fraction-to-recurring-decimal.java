/*

1. numerator == 0
2. deal with the sign
3. make num and den long to avoid overflow

4. recursively get the remainder, and record the starting position in a HashMap.
The appearance of previous remainder is the beginning of repeating fractionl part.

*/

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        res.append((numerator > 0) ^ (denominator > 0) ? "-" : ""); // not same, -
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        
        // fractional part
        res.append('.');
        Map<Long, Integer> map = new HashMap<>();
        while (num != 0) {
            map.put(num, res.length()); // starting position of the remainder
            num *= 10;
            res.append(num / den);
            num %= den;
            Integer remainderIndex = map.get(num);
            if (remainderIndex != null) {
                res.insert(remainderIndex, "(");
                res.append(")");
                break;
            }
        }
        
        return res.toString();
    }
}


/*
Time complexity: O(m * n)
Space complexity: O(1)
*/