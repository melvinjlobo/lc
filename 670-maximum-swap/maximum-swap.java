/**

BF: For a given number, iterate over all digits: i
For each digit, iterate over the rest of the digits from L to R after the current i:j
Swap every i with j. Now keep a `max` digit and
compare the swapped number with max Digit. Keep doing this. The max is the answer

Complexity O(n^2)

-------------------------------------------------------------------------

Greedy:
We go from L to R: i. We look at the largest digit we can find from L to R. Swap the two. This is the largest we can find (It's obvious froma human perspective). Now, to avoid the inner loop of going through j, we create an array such that we note the index of every digit in the number. The array is of size 10 (very specific), since the digits can be from 0 to 9 ONLY. Also, when running the inner loop, we specifically run it backwards from 9 to nums[i], so that we are greedy and try to find a number from 9 onwards in our index storage. So , that loop is also kind of specific. Other logic involves book keeping like converting the number to array of digits, etc.

 */


class Solution {
    public int maximumSwap(int num) {
        //Convert the digits to char array
        char[] numChar = Integer.toString(num).toCharArray();
        int[] index = new int[10];

        //Make a note of all the indexes of the digits in nums
        for(int idx = 0; idx < numChar.length; idx++) {
            int digit = Character.getNumericValue(numChar[idx]);
            index[digit] = idx;
        }


        // Run a loop on all of the digits in the array
        for(int i = 0; i < numChar.length; i++) {
            int digit = Character.getNumericValue(numChar[i]);

            //Run a loop from 0-9 to greedily check for the next big integer we can swap with the current 
            //digit
            for(int j = 9; j > digit; j--) {
                // Check if this larger digit
                if(index[j] > i) {      //NOTE: tricky statement. Try to understand why we do this...
                    char temp = numChar[i];
                    numChar[i] = numChar[index[j]]; //index[j] has the index of the digit `j` in the original char array numChar
                    numChar[index[j]] = temp;

                    return Integer.parseInt(new String(numChar));
                }
            }
        }

        return num;
    }
}