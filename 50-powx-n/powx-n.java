/**

Instead of simply multiplying x by itself n times (which would be inefficient for large values of n), 
we can use a method called Exponentiation by Squaring. This method allows us to calculate the power in logarithmic time 
O(logn) instead of O(n).

Key Observations
When n is 0: 
x^0 =1 for any x except when x is 0.
When n is negative: 
n =1/x 
n = −n
When n is positive:
If n is even, we can break it down as 
x^n =(x ^ n/2)×(x * n/2).
If n is odd, we can break it down as 
 x^n =x×(x ^ n−1).
By using these properties, we can reduce the number of multiplications needed.

Example Walkthrough
Suppose we want to calculate 
2^10

Since 10 is even, we can rewrite 
2^10 = (2^5) * (2^5)
Now, to calculate 
 2^5, since 5 is odd, we use 
 2^5 = 2 *  2^4
Then, for 
2^4 = 2^2 * 2^2
, since 4 is even, we use 
For 
2^2 = 2^1  * 2^1
And finally, 
2^1=2 * (2^0 * 2^0)
where 2^0 = 1;
Using this approach, we efficiently compute the power with fewer multiplications.

---------------------------------------------------

Binary Exponentiation:

If n is even:
x ^ n = x ^ n/2 * x ^ n/2
If n is odd:
x^n = x* x ^ n-1 = x * x ^ n/2 * x ^ n/2
y breaking down n in binary form, we can either square the base or multiply by the base selectively based on whether each binary bit in n is 1.
In binary exponentiation:

We square the base for each binary shift to the left (equivalent to doubling the power).
If the current bit is 1, we multiply the result by the base (equivalent to including that power in our calculation).

Example
For x=2.0 and n=10, we break down 10 in binary: 
1010.
Start with result = 1.
For each bit from right to left:
Square x each time (shifting the exponent).
If the bit is 1, multiply result by the current value of x.

Example Walkthrough
For x = 2.0 and n = 10 (binary 1010):

Initial Values: result = 1.0, x = 2.0, power = 10.
Step 1:
power & 1 == 0 (last bit is 0), so don’t multiply result.
Square x: x = 4.0.
Right-shift power: power = 5.
Step 2:
power & 1 == 1 (last bit is 1), so multiply result *= x: result = 4.0.
Square x: x = 16.0.
Right-shift power: power = 2.
Step 3:
power & 1 == 0, don’t multiply result.
Square x: x = 256.0.
Right-shift power: power = 1.
Step 4:
power & 1 == 1, so multiply result *= x: result = 1024.0.
Square x: x = 65536.0.
Right-shift power: power = 0 (end of loop).
Final result is 1024.0, which is 2 ^ 10
 */


class Solution {
    public double myPow(double x, int n) {

        //return pow1(x, n);
        //Normalize Negative Case
        if(n < 0) {
            x = 1/x;
            n= -n;
        }
        
        return pow(x, n);
    }

    // Exponentiation by squaring
    public double pow(double x, long n) {

        //Base Case
        if(n == 0)
            return 1.0;
        
        double half = pow(x, n/2);

        if(n % 2 == 0) {    // If n is even , result is half * half
            return half * half;
        } else {    //If n is odd, result is x * half * half
            return x * half * half;
        }
    }

    // Binary exponentiation...Same concept as above
    public double pow1(double x, int pow) {
        /**
        We have to convert the exponent to long since it does not handle neagtive numbers like -
        pow = -2147483648
        To handle cases where the exponent n is negative, we:

         Convert n to a long so we can safely take the absolute value (this prevents overflow for cases like n = Integer.MIN_VALUE).
        Then, we perform the binary exponentiation process on this positive exponent.

        E.g
        long n = pow;

        Other alternative is to use >>> instead of >> since >> preserves the sign bits...>> just fills the left with zeroes
         */
        long n = pow;

        //Normalize Negative Case
        if(n < 0) {
            x = 1/x;
            n= -n;
            System.out.println("n is: " + n);
        }

        double result = 1.0;

        while(n > 0) {
            if((n & 1) == 1) {      // Is n Odd? (For odd numbers, the last bit is always 1)
                result *= x;
            }

            x *= x;             //Multiply by itself for each bit shift
            
            n >>>= 1;            //Shift the bit (equivalen to divide n by 2)
        }

        return result;
    }

}