/*
Easy.
*/

class StringIterator {
    char cur;
    int cnt;
    String str;
    int p;

    public StringIterator(String compressedString) {
        cur = ' ';
        cnt = 0;
        str = compressedString;
        p = 0;
    }
    
    public char next() {
        if (cnt != 0) {
            cnt--;
            return cur;
        }
        
        if (p == str.length()) {
            return ' ';
        }
        
        cur = str.charAt(p++);
        while (p < str.length() && str.charAt(p) >= '0' && str.charAt(p) <= '9') {
            cnt = 10 * cnt + str.charAt(p++) - '0';
        }
        cnt--;
        return cur;
    }
    
    public boolean hasNext() {
        return cnt != 0 || p != str.length();
    }
}

/*
Time complexity: O()
Space complexity: O()
*/