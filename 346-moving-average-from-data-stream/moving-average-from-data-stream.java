/**
The solution is to keep a queue that is constrained to the window size. Whenever an element is added to the queue, check
the capacity and if it's larger than the size, remove the oldest entry. A Deque is the right data structure for this.

Also, keep a running count and a running sum so that we don't have to iterate over the deque always when calculating the moving average
 */

class MovingAverage {

    Deque<Integer> queue = new ArrayDeque<>();
    int size = 0;
    double movingSum = 0;

    public MovingAverage(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        // Add the value to our queue.
        queue.add(val);
        movingSum += val;

        // Now check if the addtion has gone beyond the window `size`
        if(queue.size() > size) {
            //remove the oldest value
            int removeVal = queue.poll();
            movingSum -= removeVal;
        }

        return movingSum / queue.size();

    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */