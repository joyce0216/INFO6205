package edu.northeastern.Joyce;

import java.util.HashMap;
import java.util.Map;

public class codeLab1 {

    //Sum of two Strings: Given two non-negative integers
    //num1 and num2 represented as string, return the sum of num1 and num2.
    public static int sumOfTwoString(String num1, String num2){
        return Integer.valueOf(num1)+Integer.valueOf(num2);
    }

    //Two Sum Problem: Given an array of integers nums and an integer target,
    // return indices of the two numbers such that they add up to target.
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++) {
            int newNum = target - nums[i];
            if (map.containsKey(newNum)&& map.get(newNum) != i){
                return new int[]{i,map.get(newNum)};
            }
        }
        return null;
    }

    //Rotate and array: Given an array,
    //rotate the array to the right by k steps, where k is non-negative.
    public static void rotate(int[] nums, int k) {
        // use a new array to hold the rotate array
        int[] rotateArray = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            rotateArray[(i + k) % nums.length] = nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = rotateArray[i];
        }
    }

    //Is Unique: Implement an algorithm to determine
    //if a string has all unique characters.
    public static boolean isUnique(String s){
        if(s == null || s.length()== 0){
            return false;
        }
        Map <Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                return false;
            }
            map.put(s.charAt(i),1);
        }
        return true;
    }

    //Check Permutation: Given two strings, write a method to decide
    // if one is a permutation of the other
    public static boolean isPermutation(String a, String b){
        if(a == null || b == null || a.length() != b.length()){
            return false;
        }
        int[] char_arr = new int[26];
        int len = a.length();
        for (int i = 0; i < len; i++){
            char_arr[a.charAt(i)]++;
        }
        for (int i = 0; i < len; i++){
            char_arr[b.charAt(i)]--;
        }
        for (int i = 0; i < 26; i++){
            if(char_arr[i] != 0){
                return false;
            }
        }
        return true;
    }
}
