/**
 This problem is of the category, "Binary search on answer space". The pattern is where you have an answer space and you have to reduce the answer space to get the final result
 let's try to find the answer space first.
 E.g: [3,6,7,11]
 If koko eats one banana per hour, then the hours required to finish the banana piles above will be [3, 6, 7, 11]-> 3 + 6 + 7 + 11 = 27 hours
 If koko eats 14 bananas per hour, then the hours required to eat the banana piles will be [1, 1, 1, 1] -> 1 + 1 + 1 + 1 = 4 hours
  Observations:
    - The min of the answer space will be 1 since that's the slowest koko can eat per hour. ANything lower will not be possible
    - For max, eating 14 bananas will take 4 hours and so will 13, 12 or 11. This means that eating 11 bananas (which is the max bananas in a pile i.e. the biggest pile) can be the fastest that coco has to eat. Anything above will not change the best outcome
    So, the answer space is between 1 and 11

So, the general logic will be:
1. Pick a number between 1 and max pile in the array
2. From the pile, start at the eating rate (bananas per hour) and note how many hours it took to eat the pile
3. Do a binary search on the search space (slowest and fastest). Find mid
4. Calculate the hours required to eat at the rate of `mid` bananas per hour
5. If the result in 4 was more than `h`, we need to go high and start again (low = mid + 1). this just means we need to eat more bananas per hour
6. if the result in 4 was less than `h`, we need to go lower and start again (high = mid - 1). this just means we need to eat less bananas per hour
 */


class Solution {

    // Go through all the piles and try eating them in the rate per hour provided in this function.
    // Note: We have to take the ceiling in case the ans for a pile is in decimals as we cannot start eating the next pile until the end of that hour. 
    // NOTE: we need housRequired to be of type `long`, else it fails for a use case where the piles are almost the MAX_INT. The use case is:
    // piles = [805306368,805306368,805306368], h = 000000000
    // Given pile = 805306368 and a very small rate (ratePerHour = 1 early in binary search), each pile takes 805,306,368 hours, and summing three of them overflows int (which maxes at 2,147,483,647).
    public long calculateRate(int piles[], int ratePerHour) {
        long hoursRequired = 0;
        for(int pile : piles) {
            int timeToEatThisPile = (int) Math.ceil((double) pile / ratePerHour);
            hoursRequired += timeToEatThisPile;
        }
        return hoursRequired;
    }


    public int minEatingSpeed(int[] piles, int h) {
        int ans = 0;
        int maxRange = piles[0];
        // Find max so that we can get the range. Min will be 1
        for(int pile : piles) {
            maxRange = Math.max(maxRange, pile);
        }

        int low = 1, high = maxRange;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            //Get the number of hours to eat all piles
            long hours = calculateRate(piles, mid);

            if(hours <= h) { // Koko can finish in time or faster — try to find a smaller speed (more relaxed pace)
                ans = mid;
                high = mid - 1; 
            } else {
                // This is ok since koko can eat all piles, but we need to see if we can decrease the rate further so that it is 
                // not too crazy on koko and she can take it easy by eating fewer bananas
                low = mid + 1;
            }

        }

        return ans;

    }
}