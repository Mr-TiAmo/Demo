package com.li.sort;

/**
 * @program: demo
 * @description: 快速排序
 * @author: 栗翱
 * @create: 2020-06-15 16:13
 **/
public class QuicklySort {

    public static void sort(int[] nums, int low, int high) {

        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = nums[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= nums[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= nums[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        nums[low] = nums[i];
        nums[i] = temp;
        //递归调用左半数组
        sort(nums, low, j - 1);
        //递归调用右半数组
        sort(nums, j + 1, high);

    }


    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 6, 6, 4, 7, 8, 10, 2};
        sort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
