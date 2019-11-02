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



# 426. (19) convert BST to sorted doubly linked list

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



# 621. task scheduler

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

# 560. subarray sum equals k

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

## 523. continuous subarray sum

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

# 438. (19) Find All Anagrams in a String

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



# 341. flatten nested list iterator

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



# # 31. (13) Next Permutation



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

# #1094. (5) car pooling



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



# #977. (4) squares of a sorted array



# end