package ya;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length);
        reverse(nums, 0, k);
        reverse(nums, k, nums.length);
    }

    private void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            int tmp = nums[begin];
            nums[begin] = nums[end - 1];
            nums[end - 1] = tmp;
            ++begin;
            --end;
        }
    }
}
