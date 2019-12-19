# Frequently Used APIs

## Queue

`Queue<Integer> q = new LinkedList<>();`

(All the time complexity is O(1).)

| Operation   | Throws exception | Returns special value |
| ----------- | ---------------- | --------------------- |
| **Insert**  | add(e)           | offer(e)              |
| **Remove**  | remove()         | poll()                |
| **Examine** | element()        | peek()                |

### PriorityQueue

`Queue<Integer> pq = new PriorityQueue<>();`

```java
PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer i1, Integer i2) {
        return i2.compareTo(i1);
    }
}); // construct a heap in non-natural order with a new conparator
```

The elements of the priority queue are ordered according to their natural ordering, or by a Comparator provided at queue construction time.

**Note that this implementation is not synchronized.** 

This implementation provides $$O(logn)$$ time for the enqueuing and dequeuing methods (`offer`, `poll`, `add` and `remove()`); linear time($$O(n)$$) for the `remove(Object)` and `contains(Object)` methods; and constant time($$O(1)$$) for the retrieval methods (`peek`, `element`, and `size`).

==Note: remove() = poll() is O(logn), but  remove Object is O(n)==

### Stack (Deque)

`Deque<Integer> stack = new ArrayDeque<>();`

When a deque is used as a LIFO (Last-In-First-Out) stack, elements are pushed and popped from the beginning of the deque. Stack methods are precisely equivalent to Deque methods as indicated in the table below:
(All the time complexity is O(1).)

| Stack Method | Equivalent Deque Method |
| ------------ | ----------------------- |
| push(e)      | offerFirst(e)           |
| pop()        | pollFirst()             |
| peek()       | peekFirst()             |

When a deque is used as a FIFO (First-In-First-Out) queue:

| Queue Method | Equivalent Deque Method                         |
| ------------ | ----------------------------------------------- |
| offer(e)     | offer<span style="color:red">**Last**</span>(e) |
| poll()       | pollFirst()                                     |
| peek()       | peekFirst()                                     |



## List

* Constructor

  - `ArrayList()` , `LinkedList()`

  - :star:`ArrayList(Collection<? extends E> c)` 

    :star:`LinkedList(Collection<? extends E> c)`

    Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator

  - `ArrayList(int initialCapacity)`

* Method

  `List<Integer> list = new ArrayList<>();`

  | Method                                             | Discription                                                  |
  | -------------------------------------------------- | ------------------------------------------------------------ |
  | `list.add(E e)` / `list.add(int index, E e)`       |                                                              |
  | `list.set(int index, E e)`                         | replace the element at *index* with *e*                      |
  | `list.get(int index)` (ArrayList only)             | return the element at *index*                                |
  | `list.contains(Object o)`                          | return *true* if it contains *o*                             |
  | `list.remove(int index)` / `list.remove(Object o)` | remove the element at *index* / remove the first occurrence of *o* |
  | `list.size()`                                      | !!! a method, with ()                                        |
  | `list.isEmpty()`                                   | return *true* if contains no elements                        |
  | `list.toArray()`                                   | return an array containing all the elements in the list from first to last |

### ArrayList vs. LinkedList :star:

| Interfaces                                            | ArrayList                          | LinkedList                         |
| ----------------------------------------------------- | ---------------------------------- | ---------------------------------- |
| Serializable, Cloneable, Iterable\<E>, Collection\<E> | Yes                                | Yes                                |
| List\<E>                                              | Yes                                | Yes                                |
| Deque\<E>                                             | <span style="color:blue">No</span> | <span style="color:red">Yes</span> |
| Queue\<E>                                             | <span style="color:blue">No</span> | <span style="color:red">Yes</span> |
| RandomAccess                                          | <span style="color:red">Yes</span> | <span style="color:blue">No</span> |

So, LinkedList can use all the APIs in **List**, **Deque** and **Queue**.



## Set

A collection that contains no duplicate elements, and at most one null element. (Some implementations prohibit null elements.) 

| Method                 | Description                                   |
| ---------------------- | --------------------------------------------- |
| `s.add(E e)`           | add, return *false* if element already exists |
| `s.contains(Object o)` | return *true* if the set contains *o*         |
| `s.remove(Object o)`   | remove if it is present, return boolean       |
| `s.size()`             | return the number of elelments                |
| `s.toArray()`          | return an array containing all the elements   |

### TreeSet

A `NavigableSet` implementation based on a `TreeMap`. 

This implementation provides ==guaranteed O(logn) time cost== for the basic operations (`add`, `remove` and `contains`).

**Note that this implementation is not synchronized.**

| Method                            | Description                                                  | IMP  |
| --------------------------------- | ------------------------------------------------------------ | ---- |
| `s.ceiling(E e)` / `s.floor(E e)` | return the least element >= / <= *e*, return null if no such element |      |
| `s.first()` / `s.last()`          | return the first(lowest)/last(highest) element in this set   |      |
| `s.higher()` / `s.lower()`        | return the least element > / < *e*, return null if no such element |      |
| `s.pollFirst()` / `s.pollLast()`  | retrieve and remove the first(lowest)/ last(highest) element in this set |      |
|                                   |                                                              |      |



## Map

Implementation: HashMap, Hashtable, LinkedHashMap, TreeMap

| Method                          | Description                                          |
| ------------------------------- | ---------------------------------------------------- |
| `m.get(Object key)` |return *null* if no mapping for *key*|
| `m.containsKey(Object key)`     | return *true* if contains a mapping for *key*        |
| `m.containsValue(Object value)` | return *true* if maps to one or more keys to *value* |
| `m.remove(Object key)`          | return *value* if the key is present                 |
| `m.entrySet()`                  | return a **Set** view of the mappings                |
| `m.keySet()`                    | return a **Set** view of the keys                    |
| `m.values()`                    | return a **Collection** view of the values           |

* Difference between a **Set** view and a **Collection** view?

  * **Set** is a collection that contains ==no duplicate== elements.

* `map.getOrDefault(Object key, V defaultValue)` vs. `map.putIfAbsent(K key, V value)`

  (Used in LeetCode 815. Bus Routes)

  [Which is better?](<https://stackoverflow.com/questions/29539336/which-one-is-better-getordefault-or-putifabsent-of-hashmap-in-java>)

  Simple answer: the first is a getter and the second is a setter.

### HashMap vs. Hashtable

### TreeMap

| Method                                                       | Description                                                  | IMP  |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| `m.ceilingKey(K key)` (`m.ceilingEntry(K key)`)              | return the least **key** greater than or equal to the given key, or *null* if no such key |      |
| `m.firstKey()` (`m.firstEntry()`)                            | return he first/lowest key currently in this map             |      |
| `m.floorKey(K key)` (`m.floorEntry(K key)`)                  | return the greatest **key** less than or equal to the given key, or *null* if no such key |      |
| `m.higherKey(K key)` / `m.lowerKey(K key)`                   | return **the least key strictly greater than**/**the greatest key strictly less than** then given key |      |
| `m.headMap(K toKey, boolean inclusive)` / `m.tailMap(K fromKey)` | return a view of the portion of this map whose keys are strictly less than (or equal to, if *inclusive* is true, default false) *toKey*. (similar logic to tailMap) |      |
|                                                              |                                                              |      |

This implementation provides guaranteed log(n) time cost for the `containsKey`, `get`, `put` and `remove` operations. 

## Tree

### Trie

Trie (pronounce "try") or Prefix Tree is a tree data structure, which is used for retrieval of a key in a dataset of strings. 

[Detailed explanation](https://leetcode.com/articles/implement-trie-prefix-tree/)

Related problems:

[208. Implement Trie (Prefix Tree)](<https://leetcode.com/problems/implement-trie-prefix-tree/>)

[211. Add and Search Word - Data structure design](<https://leetcode.com/problems/add-and-search-word-data-structure-design/>)

[212. Word Search II](<https://leetcode.com/problems/add-and-search-word-data-structure-design/>)

## String
`String str = "hello";`

| Method                                                       | Description                                                  | IMP    |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------ |
| `str.charAt(int index)`                                      | return the *char* at *index*                                 |        |
| `str1.compareTo(str2)`                                       | compare two strings lexicographically (negative if str1 precedes, positive if str1 follows) |        |
| `str.toLowerCase()` / `str.toUpperCase()`                    |                                                              |        |
| `str.replace(char old, char new)`                            | e.g. `str.replace(".", "");` it is "", not ''.               |        |
| `str1.concat(str2)`                                          | return "str1" + "str2", time complexity O(n), try to use StringBuilder instead |        |
| `str.substring(int start, int end)`                          | [start, end)                                                 |        |
| `str.split(String regex)`                                    | return `String[]` , split the string around matches of *regex* (regular expression)<br />`str.split("\\.")` | :star: |
| `String.valueOf(E e)` (E can be boolean, char[], int, Object...  ) | return the string representation of the *E* argument         | :star: |



### StringBuilder

[Java7 Doc](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html)

A **mutable** sequence of characters, with no guarantee of synchronization. This class is designed for use as a drop-in replacement for StringBuffer in places where the string buffer was being used by **a single thread** (as is generally the case). Where possible, it is **recommended** that this class be used **in preference to StringBuffer** as it will be faster under most implementations.

Every string builder has a capacity. As long as the length of the character sequence contained in the string builder does not exceed the capacity, it is not necessary to allocate a new internal buffer. If the internal buffer overflows, it is automatically made larger.

* Constructor
  * `StringBuiler sb = new StringBuilder()`
  * `StringBuilder(CharSequence seq)`
  * `StringBuilder(int capacity)`
  * `StringBuilder(String str)`

| Method                                     | Description                                             | IMP |
| ------------------------------------------ | ------------------------------------------------------- | ---- |
| `sb.append(E e)` (E: any type)             | append the string representation of *e* to the sequence | :star: |
| `sb.delete(int start, int end)`          | remove the characters in a substring |      |
| `sb.replace(int start, int end, String str)` |                                                         |      |
| `sb.insert(int offset, E e)` (E: any type) | insert the string representation of *e* at *index*      |      |
| `sb.charAt(int index)`                     | return the *char* value at *index*                      |      |
| `sb.deleteCharAt(int index)`               | remove the *char* at the specified position |      |
| `sb.setCharAt(int index, char ch)`         | the char at the specified index is set to *ch* |      |
| `sb.substring(int start, int end)`         | return a new String from *start* to *end*               |      |
|`sb.reverse()`|return StringBuilder|:star:|

==NO ITERABLE! NO `for (char c : sb)`!==

### StringBuffer

A **thread-safe**, **mutable** sequence of characters. A string buffer is like a String, but can be modified. At any point in time it contains some particular sequence of characters, but the length and content of the sequence can be changed through certain method calls.

Whenever an operation occurs involving a source sequence (such as appending or inserting from a source sequence) this class synchronizes only on the string buffer performing the operation, not on the source.

## Arrays

| Method                                                       | Description                                                  | IMP  |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| `Arrays.sort(int[] a)`                                       | sort into ascending order, using Dual-Pivot Quicksort, with O(nlogn) performance |      |
| `Arrays.asList(E a)` (or `Arrays.asList("Larry", "Moe", "Curly"))` | return a fixed-size list backed by *a*, the returned list implements RandomAccess. |      |
| `Arrays.binarySearch(int[] a, int key)`                      | search *key* in *a* using binary search algorithm            |      |
| `Arrays.copyOf(int[] a, int len)`                            | copy, truncating or padding with zeros (if necessary) so the copy has the length *len* |      |
| `Arrays.copyOfLength(int[] a, int from, int to)`             | copy specified range of the *a* into a new array             |      |
| `Arrays.toString(int[] a)`                                   | return a string representation of contents in *a*: `"[1, 2, 3]"` |      |

* `Arrays.asList()`

  Not recommend to use when the array is of primitive data type .

  The array and the list is connected by `asList()`, one is updated when the other is updated.

  The List returned from `asList()` has no `add` or `remove` method, because the List is referencing to the array, so the list implements RandomAccess.

* `Arrays.binarySearch(int[] a, int key)`

  return the index of the `key` if `key` is in the array; otherwise, return *-(insertion point) - 1*. 

## Character

`char c = 'a';`

| Method                         | Description |
| ------------------------------ | ----------- |
| `Character.isLetterOrDigit(c)` |             |
| `Character.getNumericValue(c)` |             |

## Return type conversion

| From - To                  | Implementation                                               |
| -------------------------- | ------------------------------------------------------------ |
| `List<int[]>` to `int[][]` | `list.toArray(new int[list.size()][2])`; <br />2 is number of elements in each `int[]` |
| `char[]` to `String`       | `new String(ch)`, `ch` is the char array                     |
| `char` to `int`            | `int a = c - '0';` or `int a = Character.getNumericValue(c);` |
| `int` to `char`            | `char c = (char)(a);`                                        |
| `String` to `int`          | `int a = Integer.parseInt(s);`  and `double b = Double.parseDouble(s)` |
| `int` to `String`          | `String s = String.valueOf(a);`                              |
|                            |                                                              |



by Tina