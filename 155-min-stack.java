class MinStack {
    Deque<Integer> stack;
    int minVal = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<>();
    }
    
    public void push(int x) {
        if (x <= minVal) {
            stack.offerLast(minVal);
            minVal = x;
        }
        stack.offerLast(x);
    }
    
    public void pop() {
        if (stack.pollLast() == minVal) {
            minVal = stack.pollLast();
        }
    }
    
    public int top() {
        return stack.peekLast();
    }
    
    public int getMin() {
        return minVal;
    }
}