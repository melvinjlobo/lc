/**
 The trick here is to create some additional symbols from the question. once we identify this, it becomes easy.
 Example: While 'M' is 1000, `CM` is 900 (as given in the question).

 Once we identify the symbols, the logic is:
 	1.	Decompose the Problem:
        •	Roman numerals are formed by repeatedly subtracting the largest possible value from the integer.
        •	Append the corresponding Roman numeral symbol for each subtraction.
	2.	Optimal Representation:
	    •	Use a predefined list of Roman numeral symbols and their corresponding integer values, ordered from largest to smallest.
	3.	Iterative Approach:
	    •	For each Roman numeral symbol:
                1.	Determine how many times the symbol fits into the remaining integer (num).
                2.	Append the symbol that many times.
                3.	Subtract the corresponding value from num.
    
    Use two separate arrays for symbols and numerals. This makes it easier to work with the string

    Key Intuitions
	•	Roman numerals are essentially a greedy problem:
	•	Start with the largest possible symbol that fits into num.
	•	Repeat until the number is reduced to 0.
	•	By predefining the Roman numeral values and their corresponding symbols, the problem simplifies to straightforward iteration and subtraction.


Example Walkthrough

Input: num = 1994
	1.	Initialize: roman = "", num = 1994.
	2.	Process values in descending order:
	•	num >= 1000: Append "M". Subtract 1000: roman = "M", num = 994.
	•	num >= 900: Append "CM". Subtract 900: roman = "MCM", num = 94.
	•	num >= 90: Append "XC". Subtract 90: roman = "MCMXC", num = 4.
	•	num >= 4: Append "IV". Subtract 4: roman = "MCMXCIV", num = 0.

Output: "MCMXCIV"

	1.	Time Complexity:
        •	O(1): There are only 13 Roman numeral symbols, and we process each at most once.
        •	This makes the solution constant-time for practical purposes.
	2.	Space Complexity:
	    •	O(1): The space required for the Roman numeral string is proportional to the size of the output, which is bounded.

 */


class Solution {
    public String intToRoman(int num) {
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();
        int val = num;
        for(int i = 0; i < values.length; i++) {
            int currVal = values[i];
            while(val >= currVal) {
                result.append(symbols[i]);
                val -= currVal;
            }
        }

        return result.toString();
    }
}