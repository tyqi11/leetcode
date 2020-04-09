/*
Use a deque to mock the snake, with head and tail. Use a hashset to quickly know if the snake
hit itself, which leads to a failure. 
Grow the length of the snake if it bits food, or remove the tail while add a new head.

Also, there is the chance that no food left, but the snake is still moving around. So check the
score to make sure it is within the range.
*/

class SnakeGame {
    int[][] food;
    int width; 
    int height;
    int score;
    Deque<Integer> body;
    Set<Integer> set;

    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        this.width = width;
        this.height = height;
        this.score = 0;
        set = new HashSet<>();
        set.add(0);
        body = new LinkedList<>();
        body.offerLast(0);
    }
    
    public int move(String direction) {
        int row = body.peekFirst() / width;
        int col = body.peekFirst() % width;
        
        if (direction.equals("U")) {
            row--;
        } else if (direction.equals("D")) {
            row++;
        } else if (direction.equals("L")) {
            col--;
        } else if (direction.equals("R")) {
            col++;
        }
        
        int head = row * width + col;
        set.remove(body.peekLast()); // when move one forward, remove the tail
        
        // if out of range, or head hit body, fail
        if (row < 0 || row == height || col < 0 || col == width || set.contains(head)) {
            return -1;
        }
        // else, add the head
        set.add(head);
        body.offerFirst(head);
        
        // if this spot has food
        if (score < food.length && row == food[score][0] && col == food[score][1]) {
            set.add(body.peekLast()); // eat food, length grow, tail back
            score++;
        } else { // if no food
            body.pollLast(); // really delete the tail completely (both set and body)
        }
        
        return score;
    }
}

/*
Time complexity: O()
Space complexity: O()
*/