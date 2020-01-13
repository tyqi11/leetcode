/*
similar idea to arranging meeting rooms.
*/

class Solution {
    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n + 1];
        for(String d : dict) {
            int i = -1;
            while((i = s.indexOf(d, i+1)) >= 0) {
                mark[i]++;
                mark[i + d.length()]--;
            }
        }

        StringBuilder sb = new StringBuilder();
        int pre = 0;
        int cur = 0;
        for(int i = 0; i <= n; i++) {
            cur = pre + mark[i];
            if (pre == 0 && cur > 0) {
                sb.append("<b>");
            } else if (pre > 0 && cur == 0) {
                sb.append("</b>");
            }
            if (i == n) break;
            sb.append(s.charAt(i));
            pre = cur;
        }
        return sb.toString();
    }
}

/*
Time complexity: O()
Space complexity: O()
*/
