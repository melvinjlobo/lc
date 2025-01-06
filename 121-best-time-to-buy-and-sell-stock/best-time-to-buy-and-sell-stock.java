/**

Algo:
To maximize profit:
	1.	Buy Low: The buying price should be as low as possible.
	2.	Sell High: The selling price should be as high as possible, but it must come after the buying price.
Instead of tracking the maximum price (sell price), we track the minimum price (buy price) up to the current day because:
	•	The maximum profit achievable on any day is based on the difference between the current price (potential sell price) and the lowest price seen so far (buy price).
	•	Keeping track of the minimum price ensures that we are always using the best possible buy price.

Tracking the maximum price does not help because:
	•	You must buy before you sell. If you track the maximum price, it’s possible that the maximum occurs before the minimum price in the array, violating the problem’s constraints.
	•	For example, in prices = [7, 6, 4, 3, 1], the maximum price is 7, but you cannot sell at 7 after buying at 1.

	1.	Time Complexity:
	•	O(n): We traverse the array once.
	2.	Space Complexity:
	•	O(1): Only a few variables are used.
 */

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for(int currentPrice : prices) {
            minPrice = Math.min(currentPrice, minPrice);
            maxProfit = Math.max(maxProfit, currentPrice - minPrice);
        }

        return maxProfit;
    }
}