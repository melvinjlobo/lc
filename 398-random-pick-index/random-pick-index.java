/**
Approach 1: Preprocessing with a Map (Efficient for Frequent Calls)
	1.	Preprocess the Array:
	•	Create a map where the keys are the unique elements of  \text{nums} , and the values are lists of their indices.
	•	For example:
            nums = [1, 2, 3, 3, 3]
            Preprocessed map: {1: [0], 2: [1], 3: [2, 3, 4]}
    2.	Randomly Pick an Index:
	•	When pick(target) is called, fetch the list of indices for  \text{target}  and randomly select one using Random.
    Time Complexity: O(n)

Approach 2 Reservoir Sampling  (Efficient for Large Arrays, but slow for avg case)

If  nums is too large or the preprocessing space is prohibitive, you can use reservoir sampling. This approach dynamically decides which index to return during a single traversal of  nums

Key Idea

Every time we see the target element in the array:
	1.	We decide randomly whether to select its index.
	2.	The probability of selecting each occurrence decreases as we find more occurrences, ensuring fairness.

By the end, each target index has an equal chance of being picked.
Key Decisions (Step-by-Step)
	1.	Index 2:
	•	First occurrence of  \text{target} = 3 .
	•	We always pick it because it’s the only target so far.
	•	Result index:  2 .
	2.	Index 3:
	•	Second occurrence of  \text{target} = 3 .
	•	Now there are 2 targets seen so far.
	•	We randomly decide whether to replace the current result:
	•	50% chance to pick this index.
	•	If picked, result index becomes  3 .
	3.	Index 4:
	•	Third occurrence of  \text{target} = 3 .
	•	Now there are 3 targets seen so far.
	•	We randomly decide whether to replace the current result:
	•	33.3% chance to pick this index.
	•	If picked, result index becomes  4 .

Imagine you have a bag of colorful balls:
	•	The bag contains lots of balls, but we are only interested in the red balls (these are the targets).
	•	The balls are arranged in a straight line, one after the other, and you can only look at one ball at a time as you walk through the line.

Your job is to pick one random red ball from the line, but you can’t see all the balls at once. You must decide on the spot whether to pick a red ball when you see one.

Here’s the rule:
	1.	When you see the first red ball, you have no other choice, so you pick it. (It’s the only red ball you’ve seen so far.)
	2.	When you see the next red ball, flip a coin:
	•	Heads: Keep your current choice.
	•	Tails: Switch to the new red ball.
	3.	When you see another red ball, roll a die (with 3 sides only):
	•	If the die shows a 1, switch to the new red ball.
	•	Otherwise, keep your current choice.

Why It’s Fair

Every red ball has the same chance of being picked because:
	•	The first red ball stays if the coin/die favors it every time.
	•	The second red ball has a fair shot (coin flip or die roll).
	•	The third red ball has a fair shot too, and so on.

In Code Terms

Here’s how the computer does it:
	1.	Start with no choice (result = -1).
	2.	Walk through the array.
	3.	Every time it sees a red ball (target):
	•	Flip a coin or roll a die.
	•	Replace your choice if the random number says so.
	4.	At the end, return the lucky red ball’s index.

For the Third Red Ball

In the example:
	•	By the time we encounter the third red ball (index 4),  c = 3  (we’ve seen three red balls).
	•	The algorithm generates a random number  r  in the range  [0, 2]  (3 possible outcomes).

Random Number Outcomes:
	•	If  r = 0 : Replace the current result with the third red ball (index 4).
	•	If  r = 1  or  r = 2 : Keep the current result (index 2 or 3).

This ensures the probability of picking the third red ball is  \frac{1}{3} .

Here’s how the probabilities add up across all red balls:

First Red Ball (Index 2):
	•	Initially chosen with probability  1  (it’s the only red ball).
	•	Not replaced by the second red ball:  1/2 .
	•	Not replaced by the third red ball:  1/2 .

Total probability for index 2:

1 x 1/2 x 2/3 = \1/3}.


Second Red Ball (Index 3):
	•	Initially chosen with probability  1/2  when it’s encountered.
	•	Not replaced by the third red ball:  2/3 .

Total probability for index 3:

1/2 x 2/3} = 1/3


Third Red Ball (Index 4):
	•	Chosen with probability 1/3  when it’s encountered.

Total probability for index 4:

1/3

 */


class Solution {

    //Solution 1
    private Map<Integer, List<Integer>> freqMap;
    private Random random;

    //Solution 2
    private int[] nums;

    public Solution(int[] nums) {
       
        random = new Random();

        //Solution 1
        freqMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            freqMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        //Solution 2
        // this.nums = nums;
    }
    
    public int pick(int target) {
        //Solution 1
        List<Integer> indices = freqMap.get(target);
        return indices.get(random.nextInt(indices.size()));

        // Solution 2
        //Count to keep track of how many times we see the target so that we can adjust the pick probability accordingly
        // int count = 0, result = -1; //result is out of bounds of the array

        // for(int i = 0; i < nums.length; i++) {
        //     int n = nums[i];
        //     if(n == target) {
        //         count++;        // Increment target count

        //         if(random.nextInt(count) == 0)
        //             result = i;
        //     }

            
        // }

        // return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */