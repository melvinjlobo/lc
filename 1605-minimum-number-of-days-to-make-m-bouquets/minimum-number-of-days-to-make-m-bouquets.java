/**
 Logic:
    If we had to go through this sequentially, we have to start from Day 1 and `might` have to wait until the day when `all` of the flowers will bloom, if we cannot make the bouquets... So essentially, we would have to do the following:
    - On day 1, check how many flowers have bloomed and check if we can make the bouquets that we want, given the constraint of adjacent flowers
    - Repeat the same on day 2 and so on
    - Example: For the array: [1,10,3,10,2], m = 3, k = 1. Let's assume `B` means bloomed, `X` means not bloomed
        - For day 1, the bloom array will be: [B,X,X,X,X] -> Flower at position 0 has bloomed by day 1. Since k = 1, we can make one bouquet with 1 adjacent flower
        - For day 2, the bloom array will be: [B,X,X,X,B] -> Flowers at position 0 and 4 have bloomed by day 2. Since k = 1, we can make two bouquets with 1 adjacent flower
        - For day 3, the bloom array will be: [B,X,B,X,B] -> Flowers at position 0, 2 and 4 have bloomed by day 3. Since k = 1, we can make three bouquets with 1 adjacent flower
    
    - We can write a function (lets say areMBouquetsPossible) that gives us an idea given the number of days, how many bouquets can we form
    - Now, while the original idea was to run a loop from 1 to Max in the array and then run areMBouquetsPossible to get it right, we can see that the problem is a binary search over the answer space. So, we can run a Binary Search (BS) loop and then run the function areMBouquetsPossible for every `mid`, If it is possible, we note that as an ans, but will have to go lower in the number of days to check if the bouquets are possible in lesser number of days and all our conditions for `m`and `k` are met...
    - We can optimize this slightly as we don't need to start from 1, we can start from the min days required to bloom any one flower in the array. This will kind of shorted the loop for us by a bit.

 */

class Solution {

    public boolean areMBouquetsPossible(int[] bloomDay, int daysPassed, int m, int k) {

        // Run a counter. Whenever we encounter a number that is less than or equal to `daysPassed`, we increment the counter. This means that this particular flower has bloomed. Now, when we encounter the opposite (the flower has not bloomed as the days have not met it's minimum blooming time), we divide the `counter` by `k`, so that we can get the number of bouquets for this section and reset the counter to zero and continue our loop. The reason why counter/k works is the below:
        //Imagine that k = 3, if counter = 2, this means that we have two adjacent flowers that have bloomed (since we reset the counter when we find that a flower has not bloomed). when we divide the two numbers, 2 / 3, we get 0 (since we take only the int value), so no bouquets can be formed. For counter = 3, it is exactly 1 (3/3) and from 4 to 5, it will continue to be 1 (as we take only integer and the decimal part is ignored; 4/3, 5/3). Now if the counter = 6, then 6/3 = 2, so we found 2 bouquets. This is how we aggregate the bouquets.
        int counter = 0, numBouquets = 0;
        for(int day : bloomDay) {
            if(day <= daysPassed) {     // Note if the flower has bloomed
                counter++;
            } 
            else {  // We're done counting. Now calculate bouquets based on above logic
                numBouquets += (counter / k);   // Pick the int value of the division to get the right bouquet
                counter = 0;   // Reset this number so that we can start counting the next span of adjacent blooms
            }
        }

        // Calcualte numBouquets again since the counter may be equal to k
        numBouquets += (counter / k);

        return numBouquets >= m;


    }


    public int minDays(int[] bloomDay, int m, int k) {

        // Find Max in array
        int maxRange = Integer.MIN_VALUE, minRange = Integer.MAX_VALUE;
        for(int bloomDaysForFlower : bloomDay) {
            maxRange = Math.max(maxRange, bloomDaysForFlower);
            minRange = Math.min(minRange, bloomDaysForFlower);
        }

        int low = minRange, high = maxRange, ans = -1;

        while(low <= high) {
            int mid = low + (high - low ) / 2;

            // Bouquets are possible, go lower with the number of days as that may be possible too
            if(areMBouquetsPossible(bloomDay, mid, m, k)) {
                ans = mid;
                high = mid - 1;
            }
            else
            // Bouquets not possible, go higher with the number of days, so that we give a chance to more flowers to bloom
                low = mid + 1;
        }
        
        return ans;
    }
}