/**
Approach 1:
    - Create a new arr, nums3 with elements n1 + n2, where n1 is the size of nums1 and n2 is the size of nums2
    - Create three pointers, pointing to first element of each array: p1 -> nums1[0]; p2 -> nums2[0], p3 -> nums3[0]
    - if p1 < p2, add nums1[p1] to nums3 and move p3 to nums3++, else do the same for p2. The idea is to compare p1 and p2 and move the lower of the two numbers to nums3, so that we have sorted numbers in nums3
    - If n3 is even, then the median is between the elements n3 / 2 - 1 and n3 / 2. E.g. for n3 = 10, it would be between elements 4 and 5, so it would be 4+5/2 = 4.5
    - if n3 is odd, then the median is just the element at n3 / 2.

Approach 2:
    - To reduce space complexity of another array n3, we can first figure out:
        - (n3 / 2) - 1 and n3 / 2 ; if n3 is even
        - n3 / 2 if n3 is odd
    - Once we do that, then we just follow the steps before:
        - ((n3 / 2) - 1) + (n3 / 2) / 2; for Even n3
        - n3 / 2; for Odd n3
 */

class Solution {
    /**
    Approach 1
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;

        int p1 = 0, p2 = 0;
        int n3 = n1 + n2;

        //Note: we only need index2 when n3 is even; while we need index1 regardless
        int index1 = n3 / 2, index2 = (n3 / 2) - 1, med1 = -1, med2 = -1, cnt = 0;  // - 1 to accommodate for zero values

        // While neither pointers have reached the end of their respective arrays...
        while(p1 < n1 && p2 < n2) {
            if(nums1[p1] < nums2[p2]) {
                if(cnt == index1)
                    med1 = nums1[p1];
                else if(cnt == index2) {
                    med2 = nums1[p1];
                }
                p1++;
                cnt++;
            } else {
                if(cnt == index1)
                    med1 = nums2[p2];
                else if(cnt == index2) {
                    med2 = nums2[p2];
                }
                p2++;
                cnt++;
            }
        }
        // Same loop in the if above
        while(p1 < n1) {
            if(cnt == index1)
                med1 = nums1[p1];
            else if(cnt == index2) {
                med2 = nums1[p1];
            }
            p1++;
            cnt++;
        }

        // Same loop in the else above
        while(p2 < n2) {
            if(cnt == index1)
                med1 = nums2[p2];
            else if(cnt == index2) {
                med2 = nums2[p2];
            }
            p2++;
            cnt++;
        }

        if(n3 % 2 == 1)
            return med1;
        else
            return (((double) med1 + med2) / 2.0);
    }
}