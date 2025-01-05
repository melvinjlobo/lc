/**
 COnverting to a string will work, but will take O(N) space where N is the number of digits in the number viz non-constant. Secondly, we can just reverse the number by taking % of last digit and creating a new reverse int.
This one has an issue that if the number is Integer.MAX_INT, we will hit integer overflow with Integer.MIN_VALUE
The idea is to convert half the number and compare it with first half. A palindrome reads the same forward and backward. For numbers, this means that the first half of the digits should mirror the second half. If they are the same, then it's a palindrome. For E.g in 1331, if we convert `31` to `13` and find thAT `13` == `13`, then the number is a palindrome.
NOTE:
1. negative numbers are not palindromes since -121 can never be 121- as sign is always on the front. So rule negative numbers out immediately
2. If the last digit is 0, then the first digit should also be 0, which is not possible. So we rule out numbers that end in 0 (except for 0 of course)

** IMP: How do we know we have reached half of the digits?
Once the reversed half equals or exceeds the original half, we know we’ve processed enough digits to make the comparison. That's the point we stop. While x > reversed, keep reversing the last digit of x and appending it to reversed. This process effectively builds the reversed half of the number. 
Once x <= reversed, the original number is split into two halves:
	•	x contains the first half.
	•	reversed contains the reversed second half.
Compare the two halves:
	•	If x == eversed, the number is a palindrome.
	•	If x == reversed / 10, it’s also a palindrome (accounts for odd-digit numbers, where the middle digit doesn’t need to match).
 E.g. Even: 
 Example: 1221
	1.	Initial state: x = 1221, reversed = 0.
	2.	Reverse half the number:
	•	Iteration 1: x = 122, reversed = 1 (last digit of 1221).
	•	Iteration 2: x = 12, reversed = 12 (next digit added).
	3.	Exit the loop when x <= \text{reversed}: x = 12, \text{reversed} = 12.
	4.	Compare x == \text{reversed}: True, so 1221 is a palindrome.

E.g Odd:
Example: 12321
	1.	Initial state: x = 12321, reversed = 0.
	2.	Reverse half the number:
	•	Iteration 1: x = 1232, reversed = 1.
	•	Iteration 2: x = 123, reversed = 12.
	•	Iteration 3: x = 12, reversed = 123.
	3.	Exit the loop when x <= \text{reversed}: x = 12, \text{reversed} = 123.
	4.	Compare x == \text{reversed} / 10: True, so 12321 is a palindrome.

Time complexity : O(log N).
We divided the input by 10 for every iteration, so the time complexity is O(log N)

Space complexity : O(1).
 */


class Solution {
    public boolean isPalindrome(int x) {

        // Check the initial 3 conditions described above
        if((x < 0) || (x % 10 == 0 && x != 0 ) )
            return false;

        int reverse = 0;
        int num = x;

        while(num > reverse) {
            int digit = num % 10;
            num /= 10;
            reverse = reverse * 10 + digit;
        }

        //When the length is an odd number, we can get rid of the middle digit by revertedNumber/10. Even that will be a palindrome and equal to the first half
        // Even example `1221`, 12 = 12 (reversed 21)
        // Odd example: `12321` 12 = 123 (reversed 321) % 10; -> 12 = 12 (123 / 10). since the middle digit 
        // doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return  (num == reverse || num == reverse / 10) ? true : false;
    }
}