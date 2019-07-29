package com.delicacy.oatmeal.test.example;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yutao
 * @create 2018-12-20 8:58
 **/
public class SuanfaTest {

    public static void main(String[] args) {
//        Queen queen = new Queen();
//        queen.queen(0);

        int[] a = getInts();
        new QuickSort(a);
        System.out.println(Arrays.toString(a));

        a = getInts();
        new MergeSort(a);
        System.out.println(Arrays.toString(a));

        a = getInts();
        new SelectSort(a);
        System.out.println(Arrays.toString(a));

        a = getInts();
        new BubbleSort(a);
        System.out.println(Arrays.toString(a));

        a = getInts();
        new InsertSort(a);
        System.out.println(Arrays.toString(a));

        a = new int[]{4, 5, 7, 8, 9, 16, 18, 92, 114, 165};
        int select = new BinarySelect(a, 9).getSelect();
        System.out.println(select);
    }

    private static int[] getInts() {
        Random r = new Random();
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        return a;
    }


    static class Queen {
        int total = 0;
        int n = 8;
        int a[] = new int[n];

        boolean isOk(int row) {
            for (int i = 0; i != row; i++) {
                if (a[i] == a[row] || Math.abs(a[row] - a[i]) == Math.abs(row - i)) {
                    return false;
                }
            }
            return true;
        }

        void queen(int row) {
            if (row == n) {
                System.out.println(Arrays.toString(a));
                total++;
            } else {
                for (int col = 0; col != n; col++) {
                    a[row] = col;
                    if (isOk(row)) {
                        queen(row + 1);
                    }
                }
            }

        }

    }

    static class QuickSort {

        //int a[] = {4, 5, 7, 8, 3, 6, 8, 92, 14, 6, 5};

        public QuickSort(int a[]) {
            sort(a, 0, a.length - 1);
        }

        /*void sort(int a[], int low, int high) {
            int i, j, p;
            if (high > low) {
                i = low;
                j = high;
                p = a[i];
                while (i < j) {
                    while (i < j && a[j] >= p) j--;
                    a[i] = a[j];
                    while (i < j && a[i] <= p) i++;
                    a[j] = a[i];
                }
                a[i] = p;
                sort(a, low, i - 1);
                sort(a, i + 1, high);
            }
        }*/

        void sort(int a[], int low, int high) {
            int i, j, p;
            if (low < high) {
                i = low;
                j = high;
                p = a[i];
                while (i < j) {
                    while (i < j && p <= a[j]) j--;
                    a[i] = a[j];
                    while (i < j && p >= a[i]) i++;
                    a[j] = a[i];
                }

                a[i] = p;
                sort(a, low, i - 1);
                sort(a, i + 1, high);
            }

        }
    }

    static class MergeSort {


        public MergeSort(int a[]) {
            sort(a, 0, a.length - 1);
        }

        /*public static void sort(int a[], int start, int end) {
            if (a != null && end > start) {
                int mid = (end + start) / 2;
                System.out.print(1);
                sort(a, start, mid);
                System.out.print(2);
                sort(a, mid + 1, end);
                System.out.print(3);
                merge(a, start, mid, end);
            }
        }

        private static void merge(int a[], int start, int mid, int end) {
            int tmp[] = new int[end - start + 1];
            int i = start;
            int j = mid + 1;
            int k = 0;
            //把最小的数移入数组
            while (i <= mid && j <= end) {
                if (a[i] <= a[j]) tmp[k++] = a[i++];
                else tmp[k++] = a[j++];
            }
            //把左边余下的移入数组
            while (i <= mid) tmp[k++] = a[i++];
            //把右边余下的移入数组
            while (j <= end) tmp[k++] = a[j++];
            //覆盖
            for (i = 0; i < k; i++) {
                a[start + i] = tmp[i];
            }
        }*/
        public static void sort(int a[], int start, int end) {
            if (a != null && start < end) {
                int mid = (end + start) / 2;
                sort(a, start, mid);
                sort(a, mid + 1, end);
                merge(a, start, mid, end);
            }
        }

        private static void merge(int a[], int start, int mid, int end) {
            int[] arr = new int[end - start + 1];
            int i = start, j = mid + 1, k = 0;
            while (i <= mid && j <= end) {
                if (a[i] < a[j]) arr[k++] = a[i++];
                arr[k++] = a[j++];
            }
            while (i <= mid) arr[k++] = a[i++];
            while (j <= end) arr[k++] = a[j++];
            for (i = 0; i < k; i++) {
                a[start + i] = arr[i];
            }
        }


    }


    static class BinarySelect {


        private int select;

        public BinarySelect(int[] arr, int a) {
            this.select = binarySelect(arr, a);
        }

        public int getSelect() {
            return select;
        }

        //有序数组arr
        /*private int binarySelect(int[] arr, int a) {
            int min = 0;
            int max = arr.length - 1;
            int mid;
            while (min <= max) {
                mid = (max + min) / 2;
                int t = arr[mid];
                if (t == a) return mid + 1;
                if (t > a) max = mid - 1;
                if (t < a) min = mid + 1;
            }
            return -1;
        }*/

        private int binarySelect(int[] arr, int a) {
            int min = 0;
            int max = arr.length - 1;
            int mid, tmp;
            while (min < max) {
                mid = (max + min) / 2;
                tmp = arr[mid];
                if (a == tmp) return mid + 1;
                if (a > tmp) min = mid + 1;
                if (a < tmp) max = mid - 1;
            }
            return -1;
        }
    }

    static class SelectSort {

        public SelectSort(int a[]) {
            sort(a);
        }

        private void sort(int[] arr) {
            int length = arr.length,i,j, tmp,k;
            for (i = 0;i<length;i++){
                k =i;
                for (j=i;j<length;j++){
                    if (arr[j]<arr[k])k=j;
                }
                tmp = arr[k];
                arr[k] = arr[i];
                arr[i] = tmp;
            }

        }
    }

    static class BubbleSort {

        public BubbleSort(int a[]) {
            sort(a);
        }

        /*private void sort(int[] arr) {
            int length = arr.length, tmp;
            for (int i = 0; i < length - 1; i++) {
                for (int j = i + 1; j < length; j++) {
                    if (arr[i] > arr[j]) {
                        tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        }*/

        private void sort(int[] arr) {
            int length = arr.length, i, j, tmp;
            for (i = 0; i < length - 1; i++) {
                for (j = i; j < length; j++) {
                    if (arr[i] < arr[j]) {
                        tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        }
    }

    static class InsertSort {

        public InsertSort(int a[]) {
            sort(a);
        }

        /*private void sort(int[] arr) {
            int length = arr.length, tmp, j;
            for (int i = 1; i < length; i++) {
                tmp = arr[i];
                //找前面谁比它大
                for (j = i; j > 0 && tmp < arr[j - 1]; j--)
                    arr[j] = arr[j - 1];
                arr[j] = tmp;
            }
        }*/

        private void sort(int[] arr) {
            int length = arr.length, i, j, tmp;
            for (i = 0; i < length; i++) {
                tmp = arr[i];
                for (j = i; j > 0 && tmp < arr[j - 1]; j--)
                    arr[j] = arr[j - 1];
                arr[j] = tmp;
            }
        }
    }


}
