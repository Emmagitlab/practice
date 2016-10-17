/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

// NineChapter
public class Solution {
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        int mid;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[start] < A[mid]) {
                // situation 1, red line
                if (A[start] <= target && target <= A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                // situation 2, green line
                if (A[mid] <= target && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        } // while
        
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }
}


// Float Value??

public class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
        	return -1;
        }
        int start = 0;
        int end =  nums.length - 1;
        int mid = start + (end - start) / 2;
        while (start + 1 < end) {
        	mid = start + (end - start) / 2;
        	if (nums[mid] == target) {
                return mid;
            } 
            if (nums[start] > nums[mid]) {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }
        if (nums[start] == target) {
        	return start;
        }
        if (nums[end] == target) {
        	return end;
        }
        return -1;
    }
}


//mine

public class Solution {
    public int search(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length - 1;
    while (lo < hi) {
        int mid = (lo + hi) / 2;
        if (nums[mid] == target) return mid;
        
        if (nums[lo] <= nums[mid]) {
            if (target >= nums[lo] && target < nums[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        } else {
            if (target > nums[mid] && target <= nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
    }
    return nums[lo] == target ? lo : -1;
        
    }
}

