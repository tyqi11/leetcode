[TOC]

# LeetCodeExplanation

This repository shows how I think of the solutions to problems on LeetCode and how I want to explain them to the others(including interviewers). 

All the solutions are in Java.

## Must-Masters

Fundamental problems, whose solutions can be part of a bigger problem. You need to come up with the best and final result in one try in an interview.

### Linked List

| No.  | Title                     | Similar problems                                |
| ---- | ------------------------- | ----------------------------------------------- |
| 206  | Reverse Linked List       | Palindrome Linked List, Reverse Linked List II, |
| 876  | Middle of the Linked List |                                                 |
| 24   | Swap Nodes in Pairs       | Reverse Nodes in k-Group                        |
| 141  | Linked List Cycle         | Linked List Cycle II,                           |
|      |                           |                                                 |
|      |                           |                                                 |
|      |                           |                                                 |

### Sort

| Algorithm     | Example |
| ------------- | ------- |
| MergeSort     |         |
| InsertionSort |         |
| BucketSort    |         |
| RadixSort     |         |
|               |         |

### Greedy &DP

|No. |Title               | General Class of Problem                                     | Method |
| -- |: ----------------------------: | ------------------------------------------------------------ | ------ |
| 435 | Non-overlapping Intervals | [Interval Scheduling](<https://en.wikipedia.org/wiki/Interval_scheduling>) | Greedy |
| 253 | Meeting Rooms II          |                                                              | Greedy |
| 322 | Coin Change               |                                                              | DP     |
| 583 | Delete Operation for Two Strings | | DP |
| 72 | Edit Distance | | DP |
|  |  | |  |
|  |  | |  |

## Series

Series of problems with similar way of thinking and implementing.

### Sliding Window

* Substring

| No.  | Title                                                  |
| ---- | ------------------------------------------------------ |
| 76   | Minimum Window Substring                               |
| 30   | Substring with Concatenation of All Words              |
| 3    | Longest Substring Without Repeating Characters         |
| 340  | Longest Substring with At Most K Distinct Characters   |
| 395  | Longest Substring with At Least K Repeating Characters |
| 159  | Longest Substring with At Most Two Distinct Characters |

Solution reference​:​ [@ndec09](<https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems>)

* Subarray

| No.  | Title                              |
| ---- | ---------------------------------- |
| 992  | Subarray with K Different Integers |
|      |                                    |
|      |                                    |
|      |                                    |



### Tree Traversal

| No.  | Title    | Method  |
| ---- | --------------------------------- | --------- |
| 94   | Binary Tree Inorder Traversal     | Inorder   |
| 144  | Binary Tree Preorder Traversal    | preorder  |
| 145  | Binary Tree Postorder Traversal   | postorder |
| 102  | Binary Tree Level Order Traversal | DFS + BFS |
| 107 | Binary Tree Level Order Traversal II | BFS |
| 314 | Binary Tree Vertical Order Traversal |  |
| 103 | Binary Tree Zigzag Level Order Traversal | BFS |
| 429   | N-ary Tree Inorder Traversal     | Inorder   |
| 589  | N-ary Tree Preorder Traversal    | preorder  |
| 590  | N-ary Tree Postorder Traversal   | postorder |

#### Morris Traversal :exclamation:

using [Threaded Binary Tree](https://en.wikipedia.org/wiki/Threaded_binary_tree) （线索二叉树）

[Great illustration and explanation in Chinese by AnnieKim](<https://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html>)

Problems: 94, 144, 145, 99 (Recover Binary Search Tree)

### Topological Sort

Learn the algorithm at University of Washington [CSE326 Data Structure](<https://courses.cs.washington.edu/courses/cse326/03wi/lectures/RaoLect20.pdf>)

| No.  | Title           |
| ---- | --------------- |
| 207  | Course Schedule |
|      |                 |

### Two pointers

Problems involving 1 or 2 passes from left to right and/or right to left. by [@chrislzm](<https://leetcode.com/problems/longest-mountain-in-array/discuss/135593/C%2B%2BJavaPython-1-pass-and-O(1)-space>).

53 Maximum Subarray
121 Best Time to Buy and Sell Stock
152 Maximum Product Subarray
238 Product of Array Except Self
739 Daily Temperatures
769 Max Chunks to Make Sorted
770 Max Chunks to Make Sorted II
821 Shortest Distance to a Character
845 Longest Mountain in Array

### Monotonous increase  stack

Summary and explanation by [@wxd_sjtu](<https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step>).

[Next Greater Element II](https://leetcode.com/problems/Next-Greater-Element-II/description/) (a very basic one)
[Largest Rectangle in Histogram](https://leetcode.com/problems/Largest-Rectangle-in-Histogram/description/)(almost the same as this problem)
[Maximal Rectangle](https://leetcode.com/problems/Maximal-Rectangle/description/)(please do this problem after you solve the above one)
[Trapping Rain Water](https://leetcode.com/problems/Trapping-Rain-Water/description/) (challenge)
[Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/description/)(challenge)
[Remove K Digits](https://leetcode.com/problems/remove-k-digits/description/)
[Create Maximum Number](https://leetcode.com/problems/create-maximum-number/description/)
[132 Pattern](https://leetcode.com/problems/132-pattern/description/)(challenge, instead of focusing on the elements in the stack, this problem focuses on the elements poped from the monotone stack)
[sliding window maximum](https://leetcode.com/problems/sliding-window-maximum/description/)(challenge, monotone **queue**)
[Max Chunks To Make Sorted II](https://leetcode.com/problems/Max-Chunks-To-Make-Sorted-II/description/)


## Tiny-Tips

### Binary Search terminal condition

1. If the target you want surely exists and you want the index at the end, use `while (left < right)`. There will be only one element left and it is the target.
2. If you may return the value during the search, use `while (left <= right)`

Problems to learn Binary Search: 

1. 35
2. 33

### Bit manipulation

### Graph and Tree

[Tree in graph theory](<https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts>) by @dietpepsi



## Templates

* [Union-Find]([template]Union-Find.java), shown as an answer to Problem 547. Friend Circles

* [Topological Sort]([template][template]Topological-Sort)

## Return type conversion

| From - To                  | Implementation                                               |
| -------------------------- | ------------------------------------------------------------ |
| `List<int[]>` to `int[][]` | `list.toArray(new int[list.size()][2])`; <br />2 is number of elements in each `int[]` |
| `char[]` to `String`       | `new String(ch)`, `ch` is the char array                     |







# end

