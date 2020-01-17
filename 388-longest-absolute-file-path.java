/*
1. split the string using "\n", so the example input: 
"dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
becomes [dir, \tsubdir1, \tsubdir2, \t\tfile.txt]

2. offer 0 into the stack, means the current part starts from index 0

3. then we start dealing with each part of the path. Take s = "dir" as an example.
Last index of "\t" is -1, so number of tabs is 0, level 1. level == stack.size() 
means that this is the first time we reach this level, so we just need to append 
to the previous result.starting point is stack.peek(): 0, length of the current 
part consists of three parts, subtract the leading "\t"s, add path string characters, 
and a tailing "\".that is the curLen expression, and we offer it to the stack.if 
s.contains("."), this is the end of a file path. we record the result by 
comparing with the maxLen. 
    
*/


class Solution {
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        
        String[] paths = input.split("\n");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(0); // dummy null length
        int maxLen = 0;
        for (String s : paths) {
            int numOfTabs = s.lastIndexOf("\t") + 1;
            int level = numOfTabs + 1;
            while (level < stack.size()) {
                stack.pollLast();
            }
            int curLen = stack.peekLast() + s.length() - numOfTabs + 1;
            stack.offerLast(curLen);
            if (s.contains(".")) {
                maxLen = Math.max(maxLen, curLen - 1);
            }
        }
        return maxLen;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/

