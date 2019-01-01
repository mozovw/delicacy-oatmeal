package com.delicacy.oatmeal.test.example;

import java.util.Arrays;

/**
 * @author yutao
 * @create 2018-12-20 8:58
 **/
public class SuanfaTest {

    public static void main(String[] args) {
//        Queen queen = new Queen();
//        queen.queen(0);

        QuickSort quickSort = new QuickSort();
        quickSort.sort();
        System.out.println(Arrays.toString(quickSort.a));

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort();
        System.out.println(Arrays.toString(mergeSort.a));
    }


    static class Queen{
        int total = 0;
        int n=8;
        int a[] = new int[n];

        boolean isOk(int row){
            for(int i=0;i!=row;i++){
               if(a[i]==a[row]||Math.abs(a[row]-a[i])==Math.abs(row-i)){
                   return false;
               }
            }
            return true;
        }

        void queen(int row){
            if(row==n){
                System.out.println(Arrays.toString(a));
                total ++;
            }else {
                for(int col=0;col!=n;col++){
                    a[row] = col;
                    if(isOk(row)){
                        queen(row+1);
                    }
                }
            }

        }

    }

    static class QuickSort{

        int a[] = {4,5,7,8,3,6,8,92,14,6,5};

        void sort(){sort(a,0,a.length-1);}
        int i=0;
        int j=0;
        int p=0;
        void sort(int a[],int low,int high){
            if (high>low){
                i = low;
                j = high;
                p = a[i];
                while (i<j){
                    while (i<j&&a[j]>=p)j--;
                    a[i]=a[j];
                    while (i<j&&a[i]<=p)i++;
                    a[j]=a[i];
                }
                a[i] = p;
                sort(a,low,i-1);
                sort(a,i+1,high);
            }

        }
    }

    static class MergeSort{

        int a[] = {4,5,7,8,3,6,8,92,14,65};

        void sort(){mergeSort(a,0,a.length-1);}

        void sort(int a[],int start,int mid,int end){
           int tmp[] = new int[end-start+1];
           int i = start;
           int j = mid+1;
           int k = 0;
           while (i<= mid &&j<=end ){
               if(a[i]<=a[j])tmp[k++]=a[i++];
               else tmp[k++]=a[j++];
           }
           while (i<=mid)tmp[k++]=a[i++];
           while (j<=end)tmp[k++]=a[j++];
            for (i = 0;i<k;i++){
                a[i]=tmp[i];
            }

        }

        void mergeSort(int a[],int start,int end){
            if (a!=null && end>start){
                int mid = (end + start) / 2;
                mergeSort(a,start,mid);
                mergeSort(a,mid+1,end);
                sort(a,start,mid,end);
            }
        }



    }
}
