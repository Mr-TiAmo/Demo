package com.li.sort;

/**
 * @program: demo
 * @description: 选择排序
 * @author: 栗翱
 * @create: 2020-06-15 14:48
 **/
public class ChooseSort {

    public static void sort(int[] nums) {
        int min;
        int temp;
        for (int i = 0; i < nums.length - 1; i++) {
            //min 为当前 值
            min = i;
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[min] > nums[j]) {
                    //选出最小的值
                    min = j;
                }
            }
            if (min != i) {
                temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {5,4,3,6,6,4,7,8,10};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
