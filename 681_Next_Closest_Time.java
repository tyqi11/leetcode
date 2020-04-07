/*
It's basically find the next valid number for each digit from right to left.

My intuive solution is easy to understand but so general. Solution 2 is recommended.
*/
// Solution 1：my straightforward and intuitive way
class Solution {
    public String nextClosestTime(String time) {
        char[] chs = time.toCharArray();
        char[] digits = new char[]{chs[0], chs[1], chs[3], chs[4]}; 
        Arrays.sort(digits); // digits from small to big
        
        // change HH:M_
        for (int i = 1; i <= 3; i++) {
            if (chs[4] < digits[i]) {
                return time.substring(0, 4) + digits[i];
            }
        } // the last is the biggest number
        
        // change HH:_M
        for (int i = 1; i <= 3; i++) {
            if (chs[3] < digits[i] && digits[i] < ((char) '0' + 6)) {
                return time.substring(0, 3) + digits[i] + digits[0];
            } 
        } // have to change the hour
        
        // set the minutes to the minimum first
        String minutes = digits[0] + "" + digits[0];
        
        // find the next smallest hour
        int h = Integer.valueOf(time.substring(0, 2));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int hour = (digits[i] - '0') * 10 + (digits[j] - '0');
                if (hour > 23) {
                    break;
                } else if (hour > h) {
                    return (hour < 10 ? "0" : "") + hour + ":" + minutes;
                }
            }
        }
        
        // no bigger one, we go to the next day, the smallest hour
        return minutes + ":" + minutes;
        
    }
}

/**************************************************************************/
// Solution 2: set limit and find next for each position
class Solution {
    public String nextClosestTime(String time) {
        char[] result = time.toCharArray();
        char[] digits = new char[] {result[0], result[1], result[3], result[4]};
        Arrays.sort(digits);
        
        // find next digit for HH:M_
        result[4] = findNext(result[4], '9', digits);
        if(result[4] > time.charAt(4)) {
            return String.valueOf(result);
        }
        
        // find next digit for HH:_M
        result[3] = findNext(result[3], '5', digits);
        if(result[3] > time.charAt(3)) {
            return String.valueOf(result);
        }
        
        // find next digit for H_:MM
        result[1] = result[0] == '2' ? findNext(result[1], '3', digits) : findNext(result[1], '9', digits);
        if(result[1] > time.charAt(1)) {
            return String.valueOf(result);
        }
        
        // find next digit for _H:MM
        result[0] = findNext(result[0], '2', digits);
        return String.valueOf(result);
    }
    
    private char findNext(char current, char upperLimit, char[] digits) {
        if(current == upperLimit) {
            return digits[0];
        }
        for (int i = 0; i < 4; i++) {
            if (digits[i] > current && digits[i] <= upperLimit) {
                return digits[i];
            }
        }
        return digits[0];
    }
}