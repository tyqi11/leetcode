# :100: 953. (74) verifying an alien dictionary

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];        
        for (int i = 0; i < 26; i++) {
            map[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            if (!isSorted(first, second, map)) {
                return false;
            }
        } 
        return true;
    }
    
    private boolean isSorted(String first, String second, int[] map) {
        int cur = 0;
        int len = Math.min(first.length(), second.length());
        while (cur < len) {
            char c1 = first.charAt(cur);
            char c2 = second.charAt(cur);
            if (map[c1 - 'a'] < map[c2 - 'a']) {
                return true;
            } else if (map[c1 - 'a'] > map[c2 - 'a']) {
                return false;
            }
            cur++;
        } // exit when finish searching the shorter string 
        if (first.length() == len) {
            return true;
        } else {
            return false;
        }
    }
}
```





# # 301. (61) Remove Invalid Parentheses

# :100: 238. (59) product of array except self

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        
        int p = 1;
        for (int j = n - 1; j >= 0; j--) {
            res[j] *= p;
            p = p * nums[j];
        }
        
        return res;
    }
}

```



# :100: ​973. (47) k closest points to origin

```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]));
        
        if (points == null || points.length <= K) {
            return points;
        }
        
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        
        int[][] res = new int[pq.size()][2];
        int idx = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            res[idx][0] = cur[0];
            res[idx][1] = cur[1];
            idx++;
        }
        
        return res;
    }
}
```



# :100: 67. (45) add binary

```java
class Solution {
    public String addBinary(String a, String b) {
        if (a == null && b == null) {
            return "0";
        } else if (a == null) {
            return b.length() == 0 ? "0" : b;
        } else if (b == null) {
            return a.length() == 0 ? "0" : a;
        } else if (a.length() == 0) {
            return b;
        } else if (b.length() == 0) {
            return a;
        }
        
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int ia = 0;
            if (i >= 0) {
                ia = a.charAt(i--) - '0';
            }
            int ib = 0;
            if (j >= 0) {
                ib = b.charAt(j--) - '0';
            }
            int sum = ia + ib + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
}

// 11111111
//        1
```



# :exclamation: 560. (44) subarray sum equals k

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}


```

## 523. (18) continuous subarray sum

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // <sum, index>
        map.put(0, -1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            Integer prev = map.get(sum);
            if (prev != null) {       // IMP logic
                if (i - prev >= 2) {
                    return true;
                }
            } else { // as long as prev == null, put, no matter distance
                map.put(sum, i);
            }
        }
        return false;
    }
}
```

# 

# :heart:125. (41) valid palindrome

```java
public boolean isPalindrome(String s) {
    if (s == null || s.length() == 0) {
        return true;
    }
    s = s.toLowerCase();
    for(int i = 0, j = s.length()-1;i<j;i++,j--){
        while(i<j && !Character.isLetterOrDigit(s.charAt(i))) {
            i++
        }
        while(i<j && !Character.isLetterOrDigit(s.charAt(j))) {
            j--;
        }
        if(s.charAt(i) != s.charAt(j)) {
        	return false;    
        }
    }
    return true;
} 
```



# :100: ​680. (32) valid palindrome ii

```java
class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() <= 2) {
            return true;
        }
        
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isValid(s.substring(i + 1, j + 1)) || 
                       isValid(s.substring(i, j));
            }
            i++;
            j--;
        }
        return true;
    }
    
    private boolean isValid(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
```



# 124. (30) binary tree maximum path sum

```java
class Solution {
    private int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        helper(root);        
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftSum = Math.max(0, helper(root.left));
        int rightSum = Math.max(0, helper(root.right));
        
        int sum = root.val + leftSum + rightSum;
        max = Math.max(max, sum);
        
        return root.val + Math.max(leftSum, rightSum);
    }
}
```



# # 273. (29) integer to english words



# 23. (29) merge k sorted lists

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((a, b) -> (Integer.compare(a.val, b.val)));
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        
        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            cur.next = smallest;
            cur = cur.next;
            if (smallest.next != null) {
                pq.offer(smallest.next);
            }
        }
        cur.next = null;
        return dummy.next;
    }
}
```



# 76. (27) minimum window substring

```java
class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        } // save t in an array
        
        int count = 0;
        int i = 0;
        int minLen = Integer.MAX_VALUE;
        int minI = 0;
        for (int j = 0; j < s.length(); j++) {
            map[s.charAt(j)]--;
            if (map[s.charAt(j)] >= 0) {
                count++;
            }
            while (count == t.length()) {
                if (minLen > j - i + 1) {
                    minLen = j - i + 1;
                    minI = i;
                }
                map[s.charAt(i)]++;
                if (map[s.charAt(i)] > 0) {
                    count--;
                }
                i++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minI, minI + minLen);
    }
}
```



# 253. (26) meeting rooms II

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }
        int len = intervals.length;
        int[] start = new int[len];
        int[] end = new int[len];
        for (int i = 0; i < len; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        int rooms = 0;
        int endIndex = 0;
        for (int i = 0; i < len; i++) {
            if (start[i] < end[endIndex]) {
                rooms++;
            } else {
                endIndex++;
            }
        }
        return rooms;
    }
}
```

# :heart: ​621. (26) task scheduler

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        int max = 0;
        int maxCount = 0;
        for (int t : tasks) {
            count[t - 'A']++;
            if (count[t - 'A'] == max) {
                maxCount++;
            } else if (count[t - 'A'] > max) {
                max = count[t - 'A'];
                maxCount = 1;
            }
        }
        
        int part = max - 1;
        int partSlots = n - (maxCount - 1);
        int totalSlots = part * partSlots;
        int tasksLeft = tasks.length - max * maxCount;
        int idles = Math.max(0, totalSlots - tasksLeft);
        return tasks.length + idles;
    }
}
```

## 767. reorganizing string

```java
class Solution {
    public String reorganizeString(String S) {
        int[] count = new int[26];
        int[] validPos = new int[26];
        for (char c : S.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            int letter = getNextLetter(count, validPos, i);
            if (letter == -1) {
                return "";
            }
            sb.append((char)('a' + letter));
            count[letter]--;
            validPos[letter] = i + 2;
        }
        return sb.toString();
    }
    
    private int getNextLetter(int[] count, int[] pos, int index) {
        int max = 0;
        int res = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] > max && pos[i] <= index) {
                max = count[i];
                res = i;
            }
        }
        return res;
    }
}
```

# 98. (24) validate binary search tree

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}
```



# 56. (23) merge intervals

````java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return intervals;
        }
        
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        
        List<int[]> list = new ArrayList<>();
        int[] pre = intervals[0];
        for (int[] interval : intervals) {
            if (interval[0] <= pre[1]) {
                pre[1] = Math.max(pre[1], interval[1]); 
            } else {
                list.add(new int[]{pre[0], pre[1]});
                pre = interval;
            }
        }
        list.add(pre);
        
        return list.toArray(new int[list.size()][2]);
    }
}
````



# 199. (22) binary tree right side view

```java

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}
```



# 278. (21) first bad version

```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int lo = 1;
        int hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion(mid)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
```

# 426. (21) convert BST to sorted doubly linked list

```java
class Solution {
    public Node head = null;
    public Node prev = null;
    
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        helper(root);
        prev.right = head;
        head.left = prev;
        return head;        
    }
    
    private void helper(Node root) {
        if (root == null) {
            return;
        }
        
        helper(root.left);
        if (head == null) {
            head = root;
        } else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        helper(root.right);
    }
}
```

# 34. (20) find first and last position of element in sorted array

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1}; 
        if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return res;
        }
        
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        if (nums[lo] != target) {
            return res;
        }
        
        res[0] = lo;
        
        hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid;
            }
        }
        
        if (nums[hi] == target){
            res[1] = hi;
        } else {
            res[1] = lo;
        }
                
        return res;
    }
}
```



# :exclamation:158. (20) read n characters given read4 

```java
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf); 
 */
public class Solution extends Reader4 {
    char[] temp = new char[4]; 
    int bufSize = 0; // bufSize may not equal to temp.length
    int bufIndex = 0;   
    
    public int read(char[] buf, int n) {
        int count = 0;
        while (count < n) {
            if (bufIndex < bufSize) {
                buf[count++] = temp[bufIndex++];
            } else {
                bufSize = read4(temp);
                bufIndex = 0;
                if (bufSize == 0) {
                    break;
                }
            }
            
        }
        return count;
    }
}
```





# 269. (19) alien dictionary

```java
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        } else if (words.length == 1) {
            return words[0];
        }
        
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
            }
        }
        
        for (int i = 0; i + 1 < words.length; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());
            
            for (int j = 0; j < minLen; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = map.getOrDefault(c1, new HashSet<Character>());
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            } // finish comparing letters
        } // finish comparing words
        
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        
        for (Character c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                q.offer(c);
            }
        }
        
        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            
            if (map.containsKey(c)) {
                for (Character next : map.get(c)) {
                    inDegree.put(next, inDegree.get(next) - 1);
                    if (inDegree.get(next) == 0) {
                        q.offer(next);
                    }
                }
            }
            
        }
        
        if (sb.length() != inDegree.size()) {
            return "";
        }
        
        return sb.toString();
        
    }
}
```

## 210. (5) course schedule ii

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Set<Integer>[] map = new Set[numCourses];
        int[] inDegree = new int[numCourses]; // default indegree: 0
        
        for (int[] pre : prerequisites) { // [cur, pre]
            Set<Integer> set = new HashSet<>();
            if (map[pre[1]] == null) {
                map[pre[1]] = set;
            } else {
                set = map[pre[1]];
            }
            set.add(pre[0]);
            
            inDegree[pre[0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        int[] res = new int[numCourses];
        int index = 0;
        while (!q.isEmpty()) {
            Integer course = q.poll();
            res[index++] = course;
            
            if (map[course] != null) {
                for (Integer d : map[course]) {
                    inDegree[d]--;
                    if (inDegree[d] == 0) {
                        q.offer(d);
                    }
                }
            }
        }
        
        if (numCourses == index) {
            return res;
        } else {
            return new int[0];
        }
        
    }
}
```



# 438. 1(19) Find All Anagrams in a String

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int pLen = p.length();
        int[] pCount = new int[26];
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        List<Integer> res = new LinkedList<>();
        int[] sCount = new int[26];
        for (int j = 0; j < s.length(); j++) {
            sCount[s.charAt(j) - 'a']++;
            if (j < pLen - 1) {
                continue;
            }
            int i = j - (pLen - 1);
            if (Arrays.equals(sCount, pCount)) {
                res.add(i);
            }
            sCount[s.charAt(i) - 'a']--;
        }
        return res;
    }
}
```

# 88. (18) merge sorted array

 ```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || nums2.length == 0) {
            return;
        }
        
        int index = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (index >= 0) {
            if (j < 0 || i >= 0 && nums1[i] >= nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--]; 
            }
        }
    }
}
 ```

# 543. (18) diameter of binary tree

```java
class Solution {
    private int maxNodes = 0; // number of nodes, return max - 1;
    
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return Math.max(0, maxNodes - 1);
    }
    
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = dfs(root.left);
        int right = dfs(root.right);
        maxNodes = Math.max(maxNodes, left + right + 1);
        
        return 1 + Math.max(left, right);
    }
}
```

# 349. (17) Intersection of two arrays

Then they ask you to solve it under these constraints:
O(n) time and O(1) space (the resulting array of intersections is not taken into consideration).
You are told the lists are sorted

Cases to take into consideration include:
duplicates, negative values, single value lists, 0's, and empty list arguments.
Other considerations might include sparse arrays.

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        Set<Integer> set = new HashSet<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        
        int[] ans = new int[set.size()];
        int idx = 0;
        for (Integer n : set) {
            ans[idx++] = n;
        }
        
        return ans;
    }
}
```

## 350. (16) intersection of two arrays ii

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        
        for (int j = 0; j < nums2.length; j++) {
            if (map.containsKey(nums2[j]) && map.get(nums2[j]) > 0) {
                list.add(nums2[j]);
                map.put(nums2[j], map.get(nums2[j]) - 1);
            }
        }
        
        int[] ans = new int[list.size()];
        int idx = 0;
        for (int i : list) {
            ans[idx++] = i;
        }
        
        return ans;
    }
}
```



# 215. (17) kth largest element in an array

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo <= hi) {
            int p = sort(nums, lo, hi);
            if (p == len - k) {
                return nums[p];
            } else if (p < len - k) {
                lo = p + 1;
            } else {
                hi = p - 1;
            }
        }
        
        return Integer.MAX_VALUE;
    }
    
    private int sort(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int i = lo;
        int j = hi - 1;
        while (i <= j) {
            if (nums[i] < pivot) {
                i++;
            } else {
                swap(nums, i, j);
                j--;
            }
        }
        swap(nums, i, hi);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// 0 1 2 3 4 5 6
// a b c d e f g len = 7, k = 2, len - k
// l   ^   p   h

	///////////////////////////////////////////////
    private void shuffle(int[] nums) {           //
    	Random random = new Random();            //
    	for (int i = 0; i < nums.length; i++) {  //
    		int j = random.nextInt(i + 1);       //
    		swap(nums, i, j);                    //
    	}                                        //
    }                                            //
    ///////////////////////////////////////////////
```



# :exclamation:282. (17) expression add operators

```java
class Solution {
    public List<String> res = new LinkedList<>();
    
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() < 1) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        dfs(sb, num, 0, target, 0, 0);
        return res;
    }
    
    private void dfs(StringBuilder sb, String num, int pos, int target, long prev, long multi) {
        if (pos == num.length()) {
            if (target == prev) {
                res.add(sb.toString());
            }
            return;
        }
        
        for (int i = pos; i < num.length(); i++) {
            if (num.charAt(pos) == '0' && i != pos) {
                break;
            } // avoid substring "00"
            long curr = Long.parseLong(num.substring(pos, i + 1));
            int len = sb.length();
            if(pos == 0) {
                dfs(sb.append(curr), num, i + 1, target, curr, curr);
                sb.setLength(len);
            } else {
                dfs(sb.append("+").append(curr), num, i + 1, target, prev + curr, curr);
                sb.setLength(len);
                dfs(sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr);
                sb.setLength(len);
                dfs(sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr);
                sb.setLength(len);
            }
        }
        
    }
}
```



# 139. (16) word break

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        
        Set<String> set = new HashSet<>();
        int maxLen = 0;
        for (String str : wordDict) {
            set.add(str);
            maxLen = Math.max(maxLen, str.length());
        }
        
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int j = 1; j <= s.length(); j++) {
            for (int len = 1; len <= j && len <= maxLen; len++) {
                dp[j] = dp[j - len] && set.contains(s.substring(j - len, j));
                if (dp[j]) {
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
```

## 140. (3) word break ii

time complexity?

```java
/*
# Backtracking (with memorization)

*/

class Solution {
    Map<String, List<String>> map = new HashMap<>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);        
        
        return dfs(s, set);
    }
   

    private List<String> dfs(String s, Set<String> set) {
        List<String> res = new ArrayList<>();
        
        if (map.containsKey(s)) {
            return map.get(s);
        }
        
        for (String word : set) {
            if (s.startsWith(word)) {
                String next = s.substring(word.length());
                if (next.length() == 0) {
                    res.add(word);
                } else {
                    List<String> subList = dfs(next, set);   
                    for (String sub : subList) {
                        res.add(word + " " + sub);
                    }
                }
            }
        }
        map.put(s, res);
        return res;
    }
}

```



# 896. (15) monotonic array

```
class Solution {
    public boolean isMonotonic(int[] A) {
        if (A.length == 1) {
            return true;
        }
        boolean isIncreasing = false;
        int len = A.length;
        if (A[len - 1] >= A[0]) {
            isIncreasing = true;
        }
        
        for (int i = 1; i < len; i++) {
            if ((isIncreasing && A[i - 1] > A[i]) || (!isIncreasing && A[i - 1] < A[i])) {
                return false;
            }
        }
        
        return true;
    }
}
```

# 415. (15) add strings :green_heart:

```java
class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null && num2 == null) {
            return null;
        } else if (num1 == null) {
            return num2;
        } else if (num2 == null) {
            return num1;
        }
        
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        
        while (i >= 0 || j >= 0) {
            int a = 0;
            int b = 0;
            if (i >= 0) {
                a = num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                b = num2.charAt(j--) - '0';
            }
            int sum = carry + a + b;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        
        if (carry != 0) {
            sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
}
```



# 986. (14) interval list intersections

```java
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return new int[0][0];
        }
        int i = 0;
        int j = 0;
        List<int[]> list = new ArrayList<>();
        while (i < A.length && j < B.length) {
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);
            if (start <= end) {
                list.add(new int[]{start, end});
            } 
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
            
        }
        
        return list.toArray(new int[list.size()][2]);
    }
}
```

# 211. (14) add and search word - data structure design

```java
class WordDictionary {
    public class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean isWord;
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word, 0, root);    
    }
    
    private boolean match(String word, int idx, TrieNode node) {
        if (idx == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(idx);
        if (c != '.') {
            return node.children[c - 'a'] != null && match(word, idx + 1, node.children[c - 'a']);
        } else {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && match(word, idx + 1, node.children[i])) {
                    return true;
                }
            }
            return false;
        }      
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
```



# 340. (13) longest substring with at most k distinct characters

```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int distinct = 0;
        int ans = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (count[c] == 0) {
                distinct++;
            }
            count[c]++;
            while (distinct > k && i <= j) {
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0) {
                    distinct--;
                }
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
```



# 31. (13) Next Permutation

```java
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        } // exit when i = -1, or nums[i] decreases
        // if not always increasing, swap with the smallest larger first
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // then no matter which situation, reverse i + 1 to end
        reverse(nums, i + 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    private void reverse(int[] nums, int start) {
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
}
```

# 785. (13) is graph bipartite

* DFS

```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] sets = new int[n];
        for (int i = 0; i < n; i++) {
            if (sets[i] == 0 && !dfs(graph, sets, i, 1)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean dfs(int[][] graph, int[] sets, int cur, int set) {
        if (sets[cur] != 0) {
            return sets[cur] == set;
        }
        sets[cur] = set;
        for (int node : graph[cur]) {
            if (!dfs(graph, sets, node, -set)) {
                return false;
            }
        }
        return true;
    }
}
```

* BFS

```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] sets = new int[n];
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (sets[i] != 0) {
                continue;
            }
            
            sets[i] = 1;
            q.offer(i);
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int node : graph[cur]) {
                    if (sets[node] == 0) {
                        sets[node] = -sets[cur];
                        q.offer(node);
                    } else {
                        if (sets[node] != -sets[cur]) {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
}
```



# 33. (12) search in rotated sorted array

```java
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > nums[right]) { // right not in order
                if (target > nums[mid] || target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else { // right in order
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        if (nums[left] == target) {
            return left;
        } else {
            return -1;
        }
    }
}
```

# 65. (12) valid number

```java
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numAfterE = false;
        boolean numSeen = false;
        
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numSeen = true;
                if (eSeen) {
                    numAfterE = true;
                }
            } else if (s.charAt(i) == '.') {
                if (pointSeen || eSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {
                if (eSeen || !numSeen) {
                    return false;
                }
                eSeen = true;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        return numSeen && (eSeen && numAfterE || !eSeen);
    }
}
```

# 71. (12) simplify path

```java
class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "/";
        }
        
        Deque<String> stack = new ArrayDeque<>();
        for (String s : path.split("/")) {
            if (s.equals("..")) {
                stack.pollLast();
            } else if (!s.equals(".") && !s.equals("")) {
                stack.offerLast(s);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if (stack.size() == 0) {
            return "/";
        }
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.pollFirst());
        }
        return sb.toString();
    }
}
```

# 987. (11) vertical order traversal of a binary tree

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Queue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) { // [x, y, value]
            if (a[0] == b[0]) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[2], b[2]); // smaller value first
                } else {
                    return Integer.compare(b[1], a[1]); // top y first
                }
            } else {
                return Integer.compare(a[0], b[0]); // smaller x first
            }
        }
    });
        
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        
        dfs(root, 0, 0);
        
        int prevX = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == prevX) {
                List<Integer> list = res.get(res.size() - 1);
                list.add(cur[2]);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(cur[2]);
                res.add(list);
                prevX = cur[0];
            }
        }
        
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y) {
        if (root == null) {
            return;
        }
        q.offer(new int[]{x, y, root.val});
        
        dfs(root.left, x - 1, y - 1);
        dfs(root.right, x + 1, y - 1);
        
    }
}
```

## 314. (7) binary tree vertical order traversal

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        
        if (root == null) {
            return res;
        }
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        
        q.offer(root);
        cols.offer(0);
        int min = 0;
        int max = 0;
        
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            int col = cols.poll();
            
            if (!map.containsKey(col)) {
                map.put(col, new LinkedList<>());
            }
            map.get(col).add(cur.val);
            
            if (cur.left != null) {
                q.offer(cur.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }
            
            if (cur.right != null) {
                q.offer(cur.right);
                cols.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        
        return res;
    }
}
```



# 126. (9) word ladder ii :heart:

```java
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return res;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        HashMap<String, List<String>> map = new HashMap<>(); // <word, nextWords>
        bfs(wordSet, beginSet, endSet, map);
        
    //    List<String> temp = new ArrayList<>(beginSet); // why same as next lines
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        dfs(res, temp, beginWord, endWord, map);
        
        return res;
    }

    private void bfs(Set<String> wordSet, Set<String> beginSet, Set<String> endSet, Map<String, List<String>> map) {
        boolean flip = false;
        boolean terminate = false;
        
        while (!beginSet.isEmpty()) {
            wordSet.removeAll(beginSet);
            wordSet.removeAll(endSet);
            
            if (beginSet.size() > endSet.size()) {
                flip = !flip;
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            
            Set<String> nextSet = new HashSet<>();
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {                  
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String str = String.valueOf(chars);
                        
                        String key = flip ? str : word; // if not flipped, word as key, str as val
                        String val = flip ? word : str;
                            
                        if (endSet.contains(str)) {
                            map.putIfAbsent(key, new ArrayList<String>());
                            map.get(key).add(val);
                            terminate = true;
                        } else if (wordSet.contains(str)) {
                            map.putIfAbsent(key, new ArrayList<String>());
                            map.get(key).add(val);
                            nextSet.add(str);
                        }
                    }
                    chars[i] = old;
                }
            }
            beginSet = nextSet;
            if (terminate == true) {
                return;
            }
        }
    }
    
    private void dfs(List<List<String>> res, List<String> temp, String beginWord, String endWord, Map<String, List<String>> map) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String word : map.get(beginWord)) {
            temp.add(word);
            dfs(res, temp, word, endWord, map);
            temp.remove(temp.size() - 1);
        }
    }
}

/*
Time complexity: O()
Space complexity: O()
*/

```

## 127. (5) word ladder

```java
class Solution {    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        
        if (!dict.contains(endWord)) {
            return 0;
        }
        
        begin.add(beginWord);
        end.add(endWord);
        
        int step = 1; // beginWord is counted
        while (!begin.isEmpty()) { 
            if (begin.size() > end.size()) {
                Set<String> temp = begin;
                begin = end;
                end = temp;
            }
            
            step++;
            Set<String> oneLetterDifferent = new HashSet<>();
            for (String word : begin) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char c = chs[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        chs[i] = j;
                        String nextWord = String.valueOf(chs);
                        if (end.contains(nextWord)) {
                            return step;
                        }
                        if (dict.contains(nextWord)) {
                            oneLetterDifferent.add(nextWord);
                            dict.remove(nextWord); // avoid future visit
                        }
                    }
                    chs[i] = c;
                }
            }
            begin = oneLetterDifferent;
        }
        
        return 0;
    }
}
```



# 636. (9) exclusive time of functions

```java
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // store id
        int prev = 0; // store timestamp
        
        for (String s : logs) {
            String[] parts = s.split(":");
            int index = Integer.parseInt(parts[0]);
            int curr = Integer.parseInt(parts[2]);
            if (parts[1].equals("start")) {
                if (!stack.isEmpty()) {
                    ans[stack.peekLast()] += curr - prev;
                }
                stack.offerLast(index);
                prev = curr;
            } else { // end
                ans[stack.pollLast()] += curr - prev + 1; 
                prev = curr + 1;
            }
        }
        return ans;
    }
}
```

# 304. (9) range sum query 2dimmutable

```java
class NumMatrix {
    int[][] dp;
    
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }    
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row2 + 1][col1] - dp[row1][col2 + 1] + dp[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
```

![304](C:\Users\Tianyu\Documents\LeetCodeExplanation\Facebook\304.jpg)

​																	illustrated by @[vincent_chan](https://leetcode.com/vincent_chan)

# 43. (9) multiply strings

```java
class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        
        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + pos[p2]; // pos[p2] is from previous multiply
                
                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (p == 0 && sb.length() == 0) {
                continue;
            }
            sb.append(p);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
```

# 958. (9) check completeness of a binary tree

```java
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n.left == null && n.right != null) {
                return false;
            } else if (n.left == null) {
                break;
            }
            
            q.offer(n.left);
            
            if (n.right == null) {
                break;
            } else {
                q.offer(n.right);
            }
        }
        
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n.left != null || n.right != null) {
                return false;
            }
        }
        
        return true;
    }
}
```

# 1027. (8) longest arithmetic sequence

```java
class Solution {
    public int longestArithSeqLength(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        
        int longest = 2;
        int n = A.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n]; // <diff, length>
        
        for (int j = 0; j < n; j++) {
            dp[j] = new HashMap<Integer, Integer>();
            for (int i = 0; i < j; i++) {
                int diff = A[j] - A[i];
                int len = 2; // a chain of (A[j], A[i])
                
                dp[j].put(diff, dp[i].getOrDefault(diff, 1) + 1);
                
                longest = Math.max(longest, dp[j].get(diff));
            }
        }
        return longest;
    }
}
```

# 825. (8) friends of appropriate ages

```java
class Solution {
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 2) {
            return 0;
        }
        
        int res = 0;
        int[] ageCount = new int[121];
        int[] ageSum = new int[121];
        
        for (int i : ages) {
            ageCount[i]++;
        }
        
        for (int i = 15; i <= 120; i++) {
            ageSum[i] = ageSum[i - 1] + ageCount[i];
            if (ageCount[i] == 0) {
                continue;
            }
            int friends = ageSum[i] - ageSum[i / 2 + 7];
            res += ageCount[i] * friends - ageCount[i]; // people don't friend themselves
        }
        
        return res;
    }
}

// age[B] > 0.5 * age[A] + 7
// age[B] <= age[A]
// (0.5 * age[A] + 7, A]

```



# 721. (7) accounts merge

```java
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        // group emails by parent
        Map<String, Integer> owners = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (owners.containsKey(email)) { // email appears before
                    int preOwner = owners.get(email);
                    int p1 = findParent(parents, i);
                    int p2 = findParent(parents, preOwner);
                    if (p1 != p2) {
                        parents[p2] = p1;
                    }
                } else { // new email
                    owners.put(email, i); // the i-th person
                }
            }
        }
        
        // reorganize emails to their user
        Map<Integer, TreeSet<String>> users = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> emails = accounts.get(i);
            int parent = findParent(parents, i);
            users.putIfAbsent(parent, new TreeSet<String>());
            users.get(parent).addAll(emails.subList(1, emails.size()));
        }   
     
        // generate result
        List<List<String>> res = new LinkedList<>();
        for (Integer idx : users.keySet()) {
            String name = accounts.get(idx).get(0);
            LinkedList<String> emails = new LinkedList<>(users.get(idx));
            emails.add(0, name);
            res.add(emails);
        }
        
        return res;
    }
    
    private int findParent(int[] parents, int idx) {
        while (idx != parents[idx]) {
            idx = findParent(parents, parents[idx]);
        }
        return idx;
    }
}
```



# 161. (7) one edit distance

```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            return false;
        } else if (s.length() == 0 && t.length() == 0) {
            return false;
        }
        
        
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        
        boolean isSameLength = (s.length() == t.length());
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (isSameLength) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        
        return s.length() + 1 == t.length();
    }
}

// aabbecdd
// aabbccdd
```



# 270. (7) closest binary search tree value

```java
class Solution {
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(res - target)) {
                res = root.val;
            }
            
            root = (root.val > target) ? root.left : root.right;
        }
        return res;
    }
}
```



# 1004. (6) max consecutive ones III

```java
class Solution {
    public int longestOnes(int[] A, int K) {
        int count = 0;
        int i = 0;
        int maxLen = 0;
        
        for (int j = 0; j < A.length; j++) {
            if (A[j] == 0) {
                count++;
            }
            while (count > K) {
                if (A[i] == 0) {
                    count--;
                }
                i++;
            }
            
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }
}
```



# 1094. (5) car pooling

* TreeMap

```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] t : trips) {
            map.put(t[1], map.getOrDefault(t[1], 0) + t[0]);
            map.put(t[2], map.getOrDefault(t[2], 0) - t[0]);
        }
        
        for (int v : map.values()) {
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }
}
```

* Array

```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] stops = new int[1001];
        
        for (int[] t : trips) {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }
        
        for (int s : stops) {
            capacity -= s;
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }
}
```



# 153. (4) find minimum in rotated sorted array

```java
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) { // right not in order, has min
                left = mid + 1;
            } else { // right in order
                right = mid;
            }
        }
        
        return nums[left];
    }
}
```

# 977. (4) squares of a sorted array

```java
class Solution {
    public int[] sortedSquares(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int[] res = new int[A.length];
        for (int p = A.length - 1; p >= 0; p--) {
            if (A[left] * A[left] > A[right] * A[right]) {
                res[p] = A[left] * A[left];
                left++;
            } else {
                res[p] = A[right] * A[right];
                right--;
            }
        }
        
        return res;
    }
}
```

# 341. (3) flatten nested list iterator

```java
public class NestedIterator implements Iterator<Integer> {
    private final Deque<NestedInteger> stack = new ArrayDeque<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.offerLast(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pollLast().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger n = stack.peekLast();
            if (n.isInteger()) {
                return true;
            } else {
                n = stack.pollLast();
                List<NestedInteger> list = n.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.offerLast(list.get(i));
                }
            }
        }
        return false;
    }
}
```

# 62. (2) unique paths

```java
class Solution {    
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 0, j = 0; j < n; j++) {
            dp[i][j] = 1;
        }
        
        for (int i = 0, j = 0; i < m; i++) {
            dp[i][j] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }   
        return dp[m - 1][n - 1];
    }  
}
```





# end