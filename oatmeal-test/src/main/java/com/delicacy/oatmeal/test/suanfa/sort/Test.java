package com.delicacy.oatmeal.test.suanfa.sort;

/**
 * @author yutao
 * @create 2019-03-22 16:59
 **/
public class Test {
    public static void main(String[] args) {
        int[] a={2,3,4,5,6,7,9,8,44};
        System.out.println(biSearch(a,44));

        System.out.println(sqrtDigit(6));
    }


//    public static int biSearch(int []array,int a){
//        int left = 0,right = array.length,mid;
//        while (left < right){
//            mid = (left+right)/2;
//            if (array[mid]<a){
//                left = mid;
//            }else if(array[mid]>a){
//                right = mid;
//            }else {
//                return mid;
//            }
//        }
//        return -1;
//    }


    public static int biSearch(int []array,int a){
        int left = 0,right = array.length;
        while (left<right){
            int mid = (left+right)/2;
            if (array[mid]>a)right=mid;
            else if(array[mid]<a)left= mid;
            else return mid;
        }
        return -1;
    }


//    public static double sqrtDigit(int k) {
//        double left = 0,right = 2,mid=0,tmp;
//        while (String.valueOf(mid).length()<k+2){
//            mid = (left+right)/2;
//            tmp = mid*mid;
//            if (tmp<2)left = mid;
//            else right = mid;
//        }
//        return mid;
//    }

    public static double sqrtDigit(int k) {
        double left = 0,right = 2,tmp = 0;
        while (String.valueOf(tmp).length()< k+2){
            tmp = (left+right)/2;
            if (tmp*tmp<2)left = tmp;
            else right = tmp;
        }
        return tmp;
    }
}
