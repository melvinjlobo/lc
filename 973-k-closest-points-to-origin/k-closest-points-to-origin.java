class Solution {
    public class Point{
        int x, y, distance;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = x*x + y*y;
        }

    }

    public int[][] kClosest(int[][] points, int k) {
        
        int[][] result = new int[k][2];
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((p1, p2) -> (-1) * Integer.compare(p1.distance, p2.distance));

        //Easy Algo
        for(int ctr = 0; ctr < points.length; ctr++) {
            Point p = new Point(points[ctr][0], points[ctr][1]);
            maxHeap.add(p);
            if(maxHeap.size() > k)
                maxHeap.poll();
        }

        //alternative algo
        // for(int i = 0; i < k; i++) {
        //     Point p = new Point(points[i][0], points[i][1]);
        //     maxHeap.add(p);
        // }

        // for (int i = k; i < points.length; i++) {
        //     Point curr = new Point(points[i][0], points[i][1]);
        //     if (curr.distance < maxHeap.peek().distance) {
        //         maxHeap.poll();
        //         maxHeap.add(curr);  
        //     }
        // }

        int ctr = 0;
        while(!maxHeap.isEmpty()) {
            Point p = maxHeap.poll();
            result[ctr][0] = p.x;
            result[ctr][1] = p.y;
            ctr++;
        }

        return result;
        
    }
}