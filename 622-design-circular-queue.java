class MyCircularQueue {
    final int[] arr;
    int front = 0;
    int end = -1;
    int size = 0;
    int cap;
    
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        arr = new int[k];
        cap = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (!isFull()) {
            end = (end + 1) % cap;
            arr[end] = value;
            size++;
            return true;
        }
        return false;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (!isEmpty()) {
            front = (front + 1) % cap;
            size--;
            return true;
        }
        return false;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : arr[front];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : arr[end];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == cap;
    }
}