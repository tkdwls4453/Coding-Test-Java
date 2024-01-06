package LeetCode;

/**
 * Binary Serach
 * link : https://www.notion.so/Binary-Search-de456e6f35cb4fdfac1507bbbe2185a0
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }
}
