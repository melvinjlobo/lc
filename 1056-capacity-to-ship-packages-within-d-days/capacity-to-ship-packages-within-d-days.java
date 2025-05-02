/**
    LOGIC:
        This is an example of BS in the answer space
        Example: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
        If the ships capacity was 1, then we cannot ship the other packages with weights 2, 3, 4..etc. as we cannot split the packages up. So, this means that the min capacity of the ship should be the max weight in the array. What about the maxRange? If we choose to ship ALL packages in one day, then the min capacity of the ship should be the sum of all elements in the array i.e. 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 = 55. We don't need a capacity greater than 55 since it's pointless as we can ship only 55 in this example (or sum of all elements in all examples).
        When we do a BS, we use `mid` as a weight to figure out in how many days we can ship all the elements. 
        If this is <= `days` (5 in this example), then we try to go lower in weight capacity and higher and closer to `days` so that we get the `least` weight capacity
        If this is > `days` (5 in this example), then we try to go higher in weight capacity so that we go lower and closer to `days`

 */


class Solution {


    /**
        Given a capacity, return the number of days it will take to ship all the packages
     */
    public int daysToShip(int[] weights, int capacity) {
        int days = 1, currentWeight = 0;
        for(int weight: weights) {
            currentWeight += weight;
            // We have hit the capacity of the ship for the day. Need to add this weight for the next day.
            if(currentWeight > capacity) {
                days += 1;
                currentWeight = weight;
            }
        }
        return days;
    }


    public int shipWithinDays(int[] weights, int days) {

        int minWeight = Integer.MIN_VALUE, allWeight = 0;

        // Bounds
        // Lower bound = max weight in the array of weights, else we wont be able to ship the maxWeight
        // Higher bound = Sum of all weights if we want to ship everything in one day
        for(int weight: weights) {
            allWeight += weight;
            minWeight = Math.max(minWeight, weight);
        }


        int low = minWeight, high = allWeight, ans = 0;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            int daysWithCapacity = daysToShip(weights, mid);

            if(daysWithCapacity <= days) {
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}