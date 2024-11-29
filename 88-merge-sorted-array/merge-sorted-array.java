/***
Logic: 
    THe idea is the same as using a new array to pick either one of the start elements from nums1 or nums2. It's just that we need to keep 3 pointers here. One for consumes element for nums1, same for nums2 and a `write` pointer for the sorted array. Since we avoid using extra space, we can use nums1 for the same thing. First solution is using an extra array. The one that optimizes space latency needs a novel approach. The only `usable` space if the end of nums1 (space allotted for sorted nums2). This space is in the RHS of nums 1, so we start with that space, since it amounts to minimum disturbance and we don't have to shift all elements to the right when we get a smaller value to be inserted. Since the space on the RHS for nums2 is relatively unused, this works out.


***/


class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //Check the below algorithm first for understanding with extra array
        int n1Ptr = m-1;      //Point to end of m elements in nums1
        int n2Ptr = n-1;      // Point to end of nums2
        //int sortedPtr = m + n - 1;   // Point to end of nums1 (m+n). Defined here for clarity

        //Reverse descending order sort 
        for (int sortedPtr = m + n - 1; sortedPtr >= 0; sortedPtr--) {

            //Use case when we are out of elements for nums2.
            //Note that since nums1 is m+n, it will not be out of elements before nums2
            if (n2Ptr < 0)
                break;

            //Note: (n1Ptr >= 0) is crucial for the use case when m = 0 

            if((n1Ptr >= 0) && (nums1[n1Ptr] > nums2[n2Ptr])) {
                nums1[sortedPtr] = nums1[n1Ptr];
                n1Ptr--;
            } else {
                nums1[sortedPtr] = nums2[n2Ptr];
                n2Ptr--;
            }
        }
    } 


    // public void merge_with_extra_space(int[] nums1, int m, int[] nums2, int n) {
    //     int n1Ptr = 0;
    //     int n2Ptr = 0;
    //     int[] newSortedArr = new int[m+n];

    //     for(int sortedPtr = 0; sortedPtr < m+n; sortedPtr++) {
    //         if((n1Ptr < m) && (n2Ptr < n)) {    //Both pointers have more elements
    //             if (nums1[n1Ptr] > nums2[n2Ptr]) {  
    //                 newSortedArr[sortedPtr] = nums2[n2Ptr];
    //                 n2Ptr++;
    //             }
    //             else {
    //                 newSortedArr[sortedPtr] = nums1[n1Ptr];
    //                 n1Ptr++;
    //             }
    //         } else if (n1Ptr >= m) {        //nums1 is out of elements
    //             newSortedArr[sortedPtr] = nums2[n2Ptr];
    //             n2Ptr++;
    //         } else if (n2Ptr >= n){         //nums2 is out of elements
    //             newSortedArr[sortedPtr] = nums1[n1Ptr];
    //             n1Ptr++;
    //         }
    //     }

    //    System.arraycopy(newSortedArr,0, nums1,0, newSortedArr.length);
    // }
        
}