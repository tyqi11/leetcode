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

## Series

Series of problems with similar way of thinking and implementing.

### Substring - Sliding Window

| No.  | Title                                                  | Method         |
| ---- | ------------------------------------------------------ | -------------- |
| 76   | Minimum Window Substring                               | Sliding Window |
| 30   | Substring with Concatenation of All Words              | Sliding Window |
| 3    | Longest Substring Without Repeating Characters         | Sliding Window |
| 340  | Longest Substring with At Most K Distinct Characters   | Sliding Window |
| 395  | Longest Substring with At Least K Repeating Characters | Sliding Window |
| 159  | Longest Substring with At Most Two Distinct Characters | Sliding Window |

Solution reference​:​ [@ndec09](<https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems>)

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
|      |                 |
|      |                 |



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

* 

  

# end

