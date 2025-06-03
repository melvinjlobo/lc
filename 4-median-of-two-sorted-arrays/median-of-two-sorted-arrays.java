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

Approach 3 - Binary Search
The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

This is an interesting approach and can be explained with an example:
arr1 = [1,3, 4, 7, 10, 12]
arr2 = [2,3,6,15]

                                |
Final sorted array = [1,2,3,3,4,|6,7,10,12,15]
                                |

In the final array, the median "4+6/2" = 5 divides the array into 2 halves, left and right. There are "5" elements on the left of the median. In the left half, we have 3 elements from arr1 [1, 3, 4] and 2 from the second arr2 [2, 3].
If we can figure out how many elements to pick from arr1 and arr2, we have the 5 in the first left half, others will be in the second right half. Once we have the 5 elements by knowing exactly what to pick from arr1 and arr2, we have the answer. But how do we figure out how many elements to take from arr1? We try taking 1 element from arr1 and rest (4) from arr2 to make it 5. Now we check by sorting it if both halves are in one big sorted order. If not, we take 2 elements from arr1, 3 from arr2 and try again...and so on

Let's try a split with 1 element from arr1 and 4 from arr2. So, the split looks like. Since we have to do a Binary search on either array, let's do it on the smaller one to be optimal:

          |
 [1,3,4,7 | ,10,12]
       [2 | ,3,6,15]
          |

- So how do we know we made the right split? Well, in the LHS, the max for arr1 is 7 and arr2 is 2, but on the RHS, the min is 10 for arr1 and 3 for arr2. If we need a final sorted sequence, then we can't have something like [1, 2, 3, 4, 7, 3, 6, 10, 12, 15] (flattened and put LHS and RHS in sequence).
- Let's try again with 2 elements from arr1 and 3 from arr2, 
          |
   [1,3,4 | ,7,10,12]
     [2,3 | ,6,15]
          |

 The final array is: [1,2,3,3,4,6,7,10,12,15]. This looks right.

 So, how do we know when to stop or that the split is the final merged and sorted array?
 In order to check we will consider 4 elements, i.e. l1, l2, r1, r2.
    l1 = the maximum element belonging to arr1[] of the left half.
    l2 = the maximum element belonging to arr2[] of the left half.
    r1 = the minimum element belonging to arr1[] of the right half.
    r1 = the minimum element belonging to arr2[] of the right half.

      l1  | r1
   [1,3,4 | ,7,10,12]
     [2,3 | ,6,15]
      l2  | r2


If l1 > r2 (4 > 6): This implies that we have considered more elements from arr1[] than necessary. So, we have to take less elements from arr1[] and more from arr2[]. In such a scenario, we should try smaller values of x. To achieve this, we will eliminate the right half (high = mid-1).

If l2 > r1 (3 > 7): This implies that we have considered more elements from arr2[] than necessary. So, we have to take less elements from arr2[] and more from arr1[]. In such a scenario, we should try bigger values of x. To achieve this, we will eliminate the left half (low = mid+1).

Now, for EVEN nos, the formula for median is: max(l1, l2) + min(r1, r2) / 2.0
Why mmax(l1, l2) and min(r1, r2) -> Both the arrays are sorted, so the rightmost element of the left half is the one we use to calculate the median, while we use leftmost element of the right half. This gives us the middle two elements we want to use to calculate the median. Look at the example above after joining the arrays : [1,2,3,3,4,| 6,7,10,12,15] . THe median is 4 from the left half (max on that side) and 6 from the right half (min on that side).
Its easier for ODD numbers based on a decision where we want more elements. For the above example of making 5 elements, if we want 3 from arr1 on the left and 2 from arr2, the median will be max(l1, l2). Similarly, if we decide that we want just 2 from arr1 and 2 from arr2, the median will be on the RHS of the final arr. So the formula is min(r1, r2). Either one works. Let's keep more elements on the left to make it simpler and fix it to max(l1, l2).

NOTE: For the actual split (vertical bar in the above examples), we just use 2 variables, mid1 and mid2 to simulate this split bar. it just means that if mid1 is element 1 in arr1 (arr1[1]), mid2 is required elements (5 in the above case) - mid1 -> 5-1 -> 4 (arr2[4]).
So the plit essentially moves to the right in arr1 and to the left in arr2, just like two pointers on either ends.

 */

class Solution {
    /**
    Approach 1
     */
    public double findMedianSortedArraysReg(int[] nums1, int[] nums2) {
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

    public double findMedianSortedArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;
        int n = n1 + n2;

        // Pick the smaller of the array to traverse on. Consider the left one as the smallest, so just swap and call the same function again and return it's value
        if(n1 > n2) return findMedianSortedArrays(arr2, arr1);

        int low = 0, high = n1;

        // + 1 is to compensate for the odd numbers as above (since we fixed to have more elements in the left half and we can then do max(l1, l2))
        int elementsOnLeftHalf = (n1 + n2 + 1) / 2;   

        while(low <= high) {
            //Figure out mid1 for our split bar (from the LHS on arr1)
            int mid1 = low + (high - low) / 2;

            //Figure out mid2 for our split bar (from the RHS on arr2)
            int mid2 = elementsOnLeftHalf - mid1;

            //SPL NOTE: when mid1 is zero, there is no l1 since it's beyond the array bounds to the left...same for l2 once it moves to the first element of the array. Similarly for r1 and r2, it moves to the right bounds of the array. SO we initialize l1 and l2 to INT MIN and r1 and r2 to INT MAX
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            //Now check if l1, l2, r1 and r2 are valid and assign them
            //NOTE: mid1 and mid2 are on the RTHS of the split bar because of the way we calculate `elementsOnLeftHalf`
            if(mid1 - 1 >= 0) l1 = arr1[mid1 - 1];
            if(mid2 - 1 >= 0) l2 = arr2[mid2 - 1];
            if(mid1 < n1) r1 = arr1[mid1];
            if(mid2 < n2) r2 = arr2[mid2];

            //Condition that we have the right elements in the left half
            if (l1 <= r2 && l2 <= r1 ) {    // The final array is sorted
                if(n % 2 == 1) return Math.max(l1, l2); // For odd elements it's just the max elment in the left half
                return ((double)(Math.max(l1, l2) + Math.min(r1, r2))) / 2.0; //For even, we use formula
            } else if (l1 > r2) {
                high = mid1 - 1;        //Too many elements from arr1 in the left half
            } else {
                low = mid1 + 1;         // Too many elements from the arr2 in the left half
            }
        }
        
        return 0;
    }
}