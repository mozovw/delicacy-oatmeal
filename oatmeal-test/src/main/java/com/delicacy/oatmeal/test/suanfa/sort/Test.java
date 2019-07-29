package com.delicacy.oatmeal.test.suanfa.sort;

/**
 * @author yutao
 * @create 2019-03-22 16:59
 **/
public class Test {
    public static void main(String[] args) {
        int[] a={11,2,3,4,5,6,7,9,8};
        System.out.println(biSearch(a,3));
    }


    public static int biSearch(int []array,int a){
        int low=0;
        int high=array.length-1;
        int mid;
        while(low<=high){
            mid=(low+high)/2;//中间位置
            if(array[mid]==a){
                return mid+1;
            }else if(array[mid]<a){ //向右查找
                low=mid+1;
            }else{ //向左查找
                high=mid-1;
            }
        }
        return -1;
    }
}
