package com.delicacy.oatmeal.test.suanfa.sort;

import java.util.Arrays;
import java.util.Random;

public class ArraySort {

    public static void main(String[] args) {
        int[] arr = getInts();
        System.out.println("selectSort");
        long a = System.nanoTime();
        selectSort(arr);
        long b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        arr = getInts();
        System.out.println("bubbleSort");
        a = System.nanoTime();
        bubbleSort(arr);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        arr = getInts();
        System.out.println("quickSort");
        a = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        arr = getInts();
        System.out.println("insertSort");
        a = System.nanoTime();
        insertSort(arr);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        arr = getInts();
        System.out.println("Arrays.sort");
        a = System.nanoTime();
        Arrays.sort(arr);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        arr = getInts();
        System.out.println("mergeSort");
        a = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        arr = getInts();
        System.out.println("shellSort");
        a = System.nanoTime();
        shellSort(arr);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        arr = getInts();
        System.out.println("heapSort");
        a = System.nanoTime();
        heapSort(arr);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);


        arr = getInts();
        System.out.println("radixSort");
        a = System.nanoTime();
        radixSort(arr, 5);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

    }

    private static int[] getInts() {
        Random r = new Random();
        int[] arr = new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }

    /**
     * 选择排序<br/>
     * <li>在未排序序列中找到最小元素，存放到排序序列的起始位置</li>
     * <li>再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。</li>
     * <li>以此类推，直到所有元素均排序完毕。</li>
     *
     * @param numbers
     */
//    public static void selectSort(int[] numbers) {
//        int size = numbers.length, temp;
//        for (int i = 0; i < size; i++) {
//            int k = i;
//            for (int j = size - 1; j > i; j--) {
//                if (numbers[j] > numbers[k]) k = j;
//            }
//            temp = numbers[i];
//            numbers[i] = numbers[k];
//            numbers[k] = temp;
//        }
//    }
    public static void selectSort(int[] numbers) {
        int size = numbers.length;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = i + 1; j < size; j++) {
                if (numbers[j] > numbers[k]) {
                    k = j;
                }
            }
            swap(numbers, i, k);
        }
    }

    private static void swap(int[] numbers, int i, int k) {
        int tmp = numbers[i];
        numbers[i] = numbers[k];
        numbers[k] = tmp;
    }

    /**
     * 冒泡法排序<br/>
     * <p>
     * <li>比较相邻的元素。如果第一个比第二个大，就交换他们两个。</li>
     * <li>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。</li>
     * <li>针对所有的元素重复以上的步骤，除了最后一个。</li>
     * <li>持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。</li>
     *
     * @param numbers 需要排序的整型数组
     */
//    public static void bubbleSort(int[] numbers) {
//        int size = numbers.length;
//        for (int i = 0; i < size; i++) {
//            for (int j = 1; j < size - i; j++) {
//                if (numbers[j-1] > numbers[j])swap(numbers, j, j -1);
//            }
//        }
//    }
    public static void bubbleSort(int[] numbers) {
        int size = numbers.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 1; j < size; j++) {
                if (numbers[j - 1] > numbers[j]) {
                    swap(numbers, j, j - 1);
                }
            }
        }
    }


    /**
     * 快速排序<br/>
     * <ul>
     * <li>从数列中挑出一个元素，称为“基准”</li>
     * <li>重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）
     * 。在这个分割之后， 该基准是它的最后位置。这个称为分割（partition）操作。</li>
     * <li>递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。</li>
     * </ul>
     *
     * @param nums
     * @param start
     * @param end
     */
//    public static void quickSort(int[] nums, int start, int end) {
//        //如果left等于right，即数组只有一个元素，直接返回
//        if(start>=end) {
//            return;
//        }
//        //设置最左边的元素为基准值
//        int key=nums[start];
//        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
//        int i=start;
//        int j=end;
//        while(i<j){
//            //j向左移，直到遇到比key小的值
//            while(nums[j]>=key && i<j){
//                j--;
//            }
//            //i向右移，直到遇到比key大的值
//            while(nums[i]<=key && i<j){
//                i++;
//            }
//            //i和j指向的元素交换
//            if(i<j){
//                int temp=nums[i];
//                nums[i]=nums[j];
//                nums[j]=temp;
//            }
//        }
//        nums[start]=nums[i];
//        //i左边的小于numbers[i],右边的则大于
//        nums[i]=key;
//        quickSort(nums,start,i-1);
//        quickSort(nums,i+1,end);
//    }

//    public static void quickSort(int[] nums, int start, int end) {
//        if (start >= end) {
//            return;
//        }
//        int i = start, j = end, tmp = nums[start];
//        while (i < j) {
//            while (i < j && nums[j] >= tmp) {
//                j--;
//            }
//            while (i < j && nums[i] <= tmp) {
//                i++;
//            }
//            if (i < j) {
//                swap(nums, i, j);
//            }
//        }
//        nums[start] = nums[i];
//        nums[i] = tmp;
//        quickSort(nums, start, i - 1);
//        quickSort(nums, i + 1, end);
//    }
    public static void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int i = start, j = end, tmp = nums[start];
        while (i < j) {
            while (i < j && nums[j] >= tmp) {
                j--;
            }
            while (i < j && nums[i] < tmp) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        nums[start] = nums[i];
        nums[i] = tmp;
        quickSort(nums, start, i - 1);
        quickSort(nums, i + 1, end);
    }


    /**
     * 插入排序
     * <p>
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     *
     * @param arr
     */
//    public static void insertSort(int[] arr) {
//        int size = arr.length, temp, j;
//        for (int i = 1; i < size; i++) {
//            temp = arr[i];
//            for (j = i; j > 0 && temp < arr[j - 1]; j--)
//                arr[j] = arr[j - 1];
//            arr[j] = temp;
//        }
//    }
//    public static void insertSort(int[] arr) {
//        int size = arr.length, j, tmp;
//        for (int i = 0; i < size; i++) {
//            tmp = arr[i];
//            for (j = i - 1; j >= 0 && arr[j] > tmp; j--) {
//                arr[j + 1] = arr[j];
//            }
//            arr[j + 1] = tmp;
//        }
//    }
    public static void insertSort(int[] arr) {
        int size = arr.length, j, tmp;
        for (int i = 0; i < size; i++) {
            tmp = arr[i];
            for (j = i - 1; j >= 0 && arr[j] < tmp; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }

    /**
     * 归并排序
     * <p>
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
     * 重复步骤3直到某一指针达到序列尾
     * 将另一序列剩下的所有元素直接复制到合并序列尾
     */
//    public static void mergeSort(int nums[],int start,int end){
//        if (nums!=null && end>start){
//            int mid = (end + start) / 2;
//            mergeSort(nums,start,mid);
//            mergeSort(nums,mid+1,end);
//            merge(nums,start,mid,end);
//        }
//    }
//    private static void merge(int nums[], int start, int mid, int end){
//        int arr[] = new int[end-start+1];
//        int i = start, j = mid+1, k = 0;
//        //把最小的数移入数组
//        while (i<= mid &&j<=end ){
//            if(nums[i]<=nums[j])arr[k++]=nums[i++];
//            else arr[k++]=nums[j++];
//        }
//        //把左边余下的移入数组
//        while (i<=mid)arr[k++]=nums[i++];
//        //把右边余下的移入数组
//        while (j<=end)arr[k++]=nums[j++];
//        //覆盖
//        for (i = 0;i<k;i++){
//            nums[start+i]=arr[i];
//        }
//    }
//    public static void mergeSort(int nums[], int start, int end) {
//        if (nums != null && end > start) {
//            int mid = (end + start) / 2;
//            mergeSort(nums, start, mid);
//            mergeSort(nums, mid + 1, end);
//            merge(nums, start, mid, end);
//        }
//    }
//
//    private static void merge(int nums[], int start, int mid, int end) {
//        int arr[] = new int[end - start + 1];
//        int i = start, j = mid + 1, k = 0;
//        while (i <= mid && j <= end) {
//            if (nums[i] < nums[j]) arr[k++] = nums[j++];
//            else arr[k++] = nums[i++];
//        }
//        while (i <= mid) arr[k++] = nums[i++];
//        while (j <= end) arr[k++] = nums[j++];
//        for (i = 0; i < k; i++) {
//            nums[start + i] = arr[i];
//        }
//    }
    public static void mergeSort(int nums[], int start, int end) {
        if (nums != null && start < end) {
            int mid = (end + start) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }

    private static void merge(int nums[], int start, int mid, int end) {
        int[] arr = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                arr[k++] = nums[i++];
            } else {
                arr[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = nums[i++];
        }
        while (j <= end) {
            arr[k++] = nums[j++];
        }
        for (int l = 0; l < k; l++) {
            nums[start + l] = arr[l];
        }
    }


    /**
     * 希尔排序算法
     * 基本思想：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列
     * 中的记录“基本有序”时，再对全体记录进行依次直接插入排序
     *
     * @param nums
     */
//    public static void shellSort(int[] nums) {
//        int dk = nums.length / 2;
//        while (dk >= 1) {
//            shellInsertSort(nums, dk);
//            dk = dk / 2;
//        }
//    }
//
//    private static void shellInsertSort(int[] nums, int dk) {
//        //类似插入排序，只是插入排序增量是 1，这里增量是 dk,把 1 换成 dk 就可以了
//        for (int i = dk; i < nums.length; i++) {
//            int k = i - dk;
//            int i2 = nums[i]; // 为待插入元素
//            int i1 = nums[k];
//            if (i2 < i1) { // 前面的大于后面的 关键（i2 < i1）
//                int j;
//                for (j = k; j >= 0 && i2 < nums[j]; j = j - dk) { // 关键（i2 < nums[j]）
//                    //通过循环，逐个后移一位找到要插入的位置。
//                    nums[j + dk] = nums[j];
//                }
//                nums[j + dk] = i2;//插入
//            }
//        }
//    }
    public static void shellSort(int[] nums) {
        int k = nums.length / 2;
        while (k >= 1) shellInsertSort(nums, k /= 2);
    }

    private static void shellInsertSort(int[] nums, int dk) {
        for (int i = dk; i < nums.length; i++) {
            int s1 = nums[i - dk], s2 = nums[i], j;
            if (s1 < s2) {
                for (j = i - dk; j >= 0 && nums[j] < s2; j -= dk) {
                    nums[j + dk] = nums[j];
                }
                nums[j + dk] = s2;
            }
        }
    }

    /**
     * 堆排序
     *
     * @param nums 待排序数组序列
     * @return 排好序的数组序列
     */
//    public static int[] heapSort(int[] nums) {
//
//        int size = nums.length;
//        // 建堆
//        for (int i = size / 2 - 1; i >= 0; i--) {
//            heapAdjust(nums, i, size);
//        }
//        //排序
//        for (int i = size - 1; i > 0; i--) {
//            int temp = nums[i]; // 最大的nums[0]调整给nums[i]
//            nums[i] = nums[0];
//            nums[0] = temp;
//            heapAdjust(nums, 0, i);
//        }
//        return nums;
//    }

//    /**
//     * 调整堆 nums[parent]为最大
//     *
//     * @param nums  待排序序列
//     * @param parent   待调整根节点
//     * @param length 数组序列长度
//     */
//    private static void heapAdjust(int[] nums, int parent, int length) {
//        int temp = nums[parent];
//        int childIndex = 2 * parent + 1;  //完全二叉树节点i从编号1开始的左子节点位置在2i，此处数组下标从0开始，即左子节点所在数组索引位置为：2i + 1
//        while (childIndex < length) {
//            int childIndex2 = childIndex + 1;
//            if (childIndex2 < length && nums[childIndex] < nums[childIndex2]) { // 总会得到最大的右节点
//                childIndex++;  //节点有右子节点，且右子节点大于左子节点，则选取右子节点
//            }
//            if (temp > nums[childIndex]) { // 最大的右节点和parent节点比较
//                break; //如果选中节点大于其子节点，直接返回
//            }
//            nums[parent] = nums[childIndex];
//            parent = childIndex;
//            childIndex = 2 * parent + 1;  //继续向下调整
//        }
//        nums[parent] = temp;
//    }

    /**
     * 关键：
     * <p>
     * 1、size / 2-1 找到中位下标的数，并且还要小一位，为了右节点，腾出一位
     * 2、第一次为了建堆
     * 3、第二次排序，每次排完都是下标0位置的数最大
     *
     * @param nums
     */
//    public static void heapSort(int[] nums) {
//        int size = nums.length;
//        for (int i = size / 2 - 1; i >= 0; i--) {
//            heapAdjust(nums, i, size);
//        }
//        for (int i = size - 1; i >= 0; i--) {
//            swap(nums, 0, i);
//            heapAdjust(nums, 0, i);
//        }
//    }
    public static void heapSort(int[] nums) {
        int size = nums.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapAdjust(nums, i, size);
        }
        for (int i = size - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapAdjust(nums, 0, i);
        }
    }


    /**
     * 大顶堆
     * 关键： 左<右<上，则break；左右谁大，赋值给上
     *
     * @param nums
     * @param parent
     * @param length
     */
//    private static void heapAdjust(int[] nums, int parent, int length) {
//        int childIndex = 2 * parent + 1;
//        int tmp = nums[parent];
//        while (childIndex < length) {
//            while (childIndex + 1 < length && nums[childIndex] < nums[1 + childIndex]) childIndex++;
//            if (nums[childIndex] < tmp) break;
//            nums[parent] = nums[childIndex];
//            parent = childIndex;
//            childIndex = 2 * parent + 1;
//        }
//        nums[parent] = tmp;
//    }
    private static void heapAdjust(int[] nums, int parent, int length) {
        int childIndex = 2*parent+1;
        int tmp = nums[parent];
        while (childIndex<length){
            while (childIndex+1<length&&nums[childIndex]<nums[childIndex+1]){
                childIndex++;
            }
            if (nums[childIndex]<tmp){
                break;
            }
            nums[parent] = nums[childIndex];
            parent = childIndex;
            childIndex = 2* parent+1;
        }
        nums[parent] = tmp;
    }


//    //arr是要排序的数组，max是数组中最大的数有几位
//    public static void radixSort(int[] arr, int max) {
//        //count数组用来计数
//        int[] count = new int[arr.length];
//        //bucket用来当桶（在下面你就理解了什么是桶了），放数据，取数据
//        int[] bucket = new int[arr.length];
//        //k表示第几位，1代表个位，2代表十位，3代表百位
//        for (int k = 1; k <= max; k++) {
//            //把count置空，防止上次循环的数据影响
//            for (int i = 0; i < arr.length; i++) {
//                count[i] = 0;
//            }
//            //分别统计第k位是0,1,2,3,4,5,6,7,8,9的数量
//            //以下便称为桶
//            //即此循环用来统计每个桶中的数据的数量
//            for (int i = 0; i < arr.length; i++) {
//                count[getFigure(arr[i], k)]++;
//            }
//
//            //利用count[i]来确定放置数据的位置
//            for (int i = 1; i < arr.length; i++) {
//                count[i] += count[i - 1];
//            }
//            //执行完此循环之后的count[i]就是第i个桶右边界的位置
//
//            //利用循环把数据装入各个桶中，注意是从后往前装
//            //这里是重点，一定要仔细理解
//            for (int i = arr.length - 1; i >= 0; i--) {
//                int j = getFigure(arr[i], k);
//                bucket[count[j] - 1] = arr[i];
//                count[j]--;
//            }
//            //将桶中的数据取出来，赋值给arr
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = bucket[i];
//            }
//        }
//    }
//
//    //此函数返回整型数i的第k位是什么
//    public static int getFigure(int i, int k) {
//        int[] a = {1, 10, 100};
//        return (i / a[k - 1]) % 10;
//    }


//    public static void radixSort(int[] arr, int max) {
//        int size = arr.length;
//        int bucket[] = new int[size];
//        int count[] = new int[size];
//
//        for (int i = 1; i <= max; i++){
//            for (int j = 0; j < size; j++) count[j] = 0;
//            for (int j = 0; j < size; j++) count[getFigure(arr[j],i)]++;
//            for (int j = 1; j < size; j++) count[j]+=count[j-1];
//            for (int j = size-1; j >= 0; j--)
//                bucket[--count[getFigure(arr[j],i)]] = arr[j];
//            for (int j = 0; j < size; j++) arr[j] = bucket[j];
//        }
//    }

//    public static void radixSort(int[] arr, int max) {
//        int size = arr.length,size2 = 10;
//        int bucket[] = new int[size];
//        int count[] = new int[size2];
//        for (int i = 1; i <= max; i++) {
//            for (int j = 0; j < size2; j++) count[j]=0;
//            for (int j = 0; j < size; j++) count[getFigure(arr[j],i)]++;
//            for (int j = 1; j < size2; j++) count[j]+=count[j-1];
//            for (int j = size-1; j >=0; j--) bucket[--count[getFigure(arr[j],i)]] = arr[j];
//            arr = bucket;
//        }
//    }

    /**
     * 根据个位数排序，然后十位数，百位数...
     * 个位数上1有多少个，2有多少个...
     * 如果1有两个2有三个3有四个...，count数组11223333...
     *
     * @param arr
     * @param max
     */
    public static void radixSort(int[] arr, int max) {
        int size = arr.length, size2 = 10;
        int count[] = new int[size2];
        int bucket[] = new int[size];
        for (int i = 1; i <= max; i++) {
            for (int j = 0; j < size2; j++) count[j] = 0;
            for (int j = 0; j < size; j++) count[getFigure(arr[j], i)]++;
            for (int j = 1; j < size2; j++) count[j] += count[j - 1];
            for (int j = size - 1; j >= 0; j--) bucket[--count[getFigure(arr[j], i)]] = arr[j];
            for (int j = 0; j < size; j++) arr[j] = bucket[j];
        }
    }

    /**
     * 此函数返回整型数i的第k位是什么
     *
     * @param num 数
     * @param k   位数
     * @return
     */
//    static int getFigure(int num, int k) {
//        if (k < 1) throw new IllegalArgumentException("k should not be less than 1");
//        return (num / pow(10, k - 1)) % 10;
//    }
//    static int pow(int x, int y) {
//        if (y < 0) throw new IllegalArgumentException("y should not be less than 0");
//        int tmp = 1;
//        for (int i = 0; i < y; i++) {
//            tmp *= x;
//        }
//        return tmp;
//    }
    static int getFigure(int num, int k) {
        if (k < 1) throw new IllegalArgumentException("k should not be less than 1");
        return (num / pow(10, k - 1)) % 10;
    }

    static int pow(int x, int y) {
        if (y < 0) throw new IllegalArgumentException("y should not be less than 0");
        int tmp = 1;
        for (int i = 0; i < y; i++) {
            tmp *= x;
        }
        return tmp;
    }
}