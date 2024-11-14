class Solution {
    public double myPow(double x, int n) {

        return pow1(x, n);
        //Normalize Negative Case
        // if(n < 0) {
        //     x = 1/x;
        //     n= -n;
        // }
        
        // return pow(x, n);
    }

    // Exponenetiantion by squaring
    public double pow(double x, int n) {

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
    public double pow1(double x, int n) {
        //Normalize Negative Case
        // if(n < 0) {
        //     x = 1/x;
        //     n= -n;
        // }

        // double result = 1.0;

        // while(n > 0) {
        //     if((n & 1) == 1) {      // Is n Odd (For odd numbers, the last bit is always 1)
        //         result *= x;
        //     }

        //     x *= x;             //Multiply by itself for each bit shift
            
        //     n >>= 1;            //Shift the bit (equivalen to divide n by 2)
        // }

        // return result;

        if(n < 0){
            n = -n;
            x = 1 / x;
        }
        
        double pow = 1;
        
        while(n != 0){
            if((n & 1) != 0){
                pow *= x;
            } 
                
            x *= x;
            n >>>= 1;
            
        }
        
        return pow;
    }

}