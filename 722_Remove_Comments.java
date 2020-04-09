/*

*/

class Solution {
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        if (source == null || source.length == 0) {
            return res;
        }
        
        boolean commentOn = false;
        StringBuilder sb = new StringBuilder();
        for (String str : source) {
            for (int i = 0; i < str.length(); i++) {
                if (commentOn) {
                    if (str.charAt(i) == '*' && i + 1 < str.length() && str.charAt(i + 1) == '/') {
                        commentOn = false;
                        i++; // to skip also the / after *
                    }
                    continue;
                }
                
                if (str.charAt(i) == '/' && i + 1 < str.length() && str.charAt(i + 1) == '/') {
                    break; // finished this line
                } else if (str.charAt(i) == '/' && i + 1 < str.length() && str.charAt(i + 1) == '*') {
                    commentOn = true;
                    i++; // to skil also the * after /
                } else {
                    sb.append(str.charAt(i)); // not a comment
                }
            }
            
            // after all the characters
            if (!commentOn && sb.length() > 0) {
                res.add(sb.toString());
                sb.setLength(0); // better than sb = new StringBuilder()
            }
        }
        
        return res;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/