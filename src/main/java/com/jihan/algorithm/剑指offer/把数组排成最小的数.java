package com.jihan.algorithm.剑指offer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Jihan on 2019/8/9
 *
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class 把数组排成最小的数 {
    public String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length == 0){
            return "";
        }

        for(int i = 0; i < numbers.length; i++){
            for(int j = i + 1; j < numbers.length; j++){
                Integer ij = Integer.valueOf(numbers[i]+""+numbers[j]);
                Integer ji = Integer.valueOf(numbers[j]+""+numbers[i]);
                if(ji < ij){
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        String str = "";
        for(int i = 0; i < numbers.length; i++){
            str += numbers[i] + "";
        }

        return str;
    }

    public String PrintMinNumber2(int [] numbers) {
        if(numbers == null || numbers.length == 0){
            return "";
        }
        
        String[] strs = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strs,new Comparator<String>(){
            @Override
            public int compare(String s1,String s2){
                String a = s1 + s2;
                String b = s2 + s1;
                return a.compareTo(b);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }

        return sb.toString();
    }
}
