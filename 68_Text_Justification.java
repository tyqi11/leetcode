/*

1. We call the construction of one line: one round. In each round, there are two steps:
1.1 We get the index of the start and end word, and the length of all these words, plus 
one empty space between each two words. 
1.2 There are two circumstances: 1) last line/only one word, 2) others. We add spaces
differently.

2.1 If it is 1.1, we append one space after each word, and fill the rest of the line with
empty spaces.
2.1 If it is 1.2, we do append spaces in three steps. First, there is a space between 
every two words. We append it first. Secondly, we append the spaces that are distributed
evenly to each interval. Thirdly, if the spaces cannot be evenly distributed, we distribut
the extra spaces evenly to the left slots.

3. After each round, add the newly build String to the list, and reset the start point
for the next round. 

*/

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int start = 0;
        while (start < words.length) {
            int width = words[start].length();
            int end = start + 1;
            while (end < words.length && width + 1 + words[end].length() <= maxWidth) {
                width += words[end++].length() + 1; // one space between two words in width
            } // exit when reaches the last word or exceed maxWidth
            StringBuilder sb = new StringBuilder();
            sb.append(words[start]); // later in loops, we can add empty spaces first
            // 1) if last line or only one word, left-justified
            if (end == words.length || end == start + 1) {
                for (int i = start + 1; i < end; i++) {
                    sb.append(" ").append(words[i]);
                }
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else { // 2. other lines
                int slots = end - start - 1;
                int spaces = (maxWidth - width) / slots; 
                int leftSpaces = (maxWidth - width) % slots;
                for (int i = start + 1; i < end; i++) {
                    sb.append(" "); // already counted in width
                    for (int j = 0; j < spaces; j++) {
                        sb.append(" ");
                    } // spaces assigned for each slot
                    if (leftSpaces > 0) {
                        sb.append(" ");
                        leftSpaces--;
                    } // spaces for only left slots
                    sb.append(words[i]);
                }
            }
            ans.add(sb.toString());
            start = end;
        }
        return ans;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/