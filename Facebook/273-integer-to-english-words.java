class Solution {
    private final String[] LESSTHANTWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        String ans = "";
        
        if (num == 0) {
            return "Zero";
        }
        
        int index = 0;
        while (num > 0) {
            if (num % 1000 != 0) { // IMP, to deal with 12,000,345
                ans = helper(num % 1000) + THOUSANDS[index] + " " + ans; // " " IMP
            }
            num /= 1000;
            index++;
        }
        
        return ans.trim(); // trim()
    }
    
    private String helper(int num) { // 0 ~ 999
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return LESSTHANTWENTY[num] + " "; // " " IMP
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);
        } else {
            return LESSTHANTWENTY[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}