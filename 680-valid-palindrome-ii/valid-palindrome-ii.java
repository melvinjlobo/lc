


class Solution {
    
    public static boolean checkPalindrome(String src, int left, int right) {
        while(left < right) {
            if(src.charAt(left) != src.charAt(right))
                return false;

            left++;
            right--;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while (left < right) {
            if(s.charAt(left) != s.charAt(right)) {
               return checkPalindrome(s, left + 1, right) || checkPalindrome(s, left, right-1);
            }

            left++;
            right--;
        }

        return true;
    }
}