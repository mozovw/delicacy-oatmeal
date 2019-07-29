package com.delicacy.oatmeal.test.suanfa.sort;

import java.util.Arrays;
import java.util.Random;

public class ArraySort {

    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100);
        }
        System.out.println("selectSort");
        long a = System.nanoTime();
        selectSort(arr);
        long b = System.nanoTime();
        //System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        System.out.println("bubbleSort");
        a = System.nanoTime();
        bubbleSort(arr);
        b = System.nanoTime();
        //System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        System.out.println("quickSort");
        a = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        b = System.nanoTime();
        //System.out.println(Arrays.toString(arr));
        System.out.println(b - a);


        System.out.println("insertSort");
        a = System.nanoTime();
        insertSort(arr);
        b = System.nanoTime();
        //System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        System.out.println("Arrays.sort");
        a = System.nanoTime();
        Arrays.sort(arr);
        b = System.nanoTime();
        //System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        System.out.println("shellSort");
        a = System.nanoTime();
        shellSort(arr);
        b = System.nanoTime();
        //System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        System.out.println("mergeSort");
        a = System.nanoTime();
        mergeSort(arr,0,arr.length-1);
        b = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println(b - a);

        System.out.println("heapSort");
        a = System.nanoTime();
        heapSort(arr);
        b = System.nanoTime();
        //System.out.println(Arrays.toString(arr));
        System.out.println(b - a);


    }

    /**
     * 选择排序<br/>
     * <li>在未排序序列中找到最小元素，存放到排序序列的起始位置</li>
     * <li>再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。</li>
     * <li>以此类推，直到所有元素均排序完毕。</li>
     *
     * @param numbers
     */
    public static void selectSort(int[] numbers) {
        int size = numbers.length, temp;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] > numbers[k]) k = j;
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
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
    public static void bubbleSort(int[] numbers) {
        int temp; // 记录临时中间值
        int size = numbers.length; // 数组大小
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (numbers[i] < numbers[j]) { // 交换两数的位置
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
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
     * @param numbers
     * @param start
     * @param end
     */
    public static void quickSort(int[] numbers, int start, int end) {
        //如果left等于right，即数组只有一个元素，直接返回
        if(start>=end) {
            return;
        }
        //设置最左边的元素为基准值
        int key=numbers[start];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i=start;
        int j=end;
        while(i<j){
            //j向左移，直到遇到比key小的值
            while(numbers[j]>=key && i<j){
                j--;
            }
            //i向右移，直到遇到比key大的值
            while(numbers[i]<=key && i<j){
                i++;
            }
            //i和j指向的元素交换
            if(i<j){
                int temp=numbers[i];
                numbers[i]=numbers[j];
                numbers[j]=temp;
            }
        }
        numbers[start]=numbers[i];
        numbers[i]=key;
        quickSort(numbers,start,i-1);
        quickSort(numbers,i+1,end);
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
     * @param numbers
     */
    public static void insertSort(int[] numbers) {
        int size = numbers.length, temp, j;
        for (int i = 1; i < size; i++) {
            temp = numbers[i];
            for (j = i; j > 0 && temp < numbers[j - 1]; j--)
                numbers[j] = numbers[j - 1];
            numbers[j] = temp;
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
     *
     */
    public static void mergeSort(int a[],int start,int end){
        if (a!=null && end>start){
            int mid = (end + start) / 2;
            mergeSort(a,start,mid);
            mergeSort(a,mid+1,end);
            merge(a,start,mid,end);
        }
    }
    private static void merge(int a[], int start, int mid, int end){
        int tmp[] = new int[end-start+1];
        int i = start;
        int j = mid+1;
        int k = 0;
        //把最小的数移入数组
        while (i<= mid &&j<=end ){
            if(a[i]<=a[j])tmp[k++]=a[i++];
            else tmp[k++]=a[j++];
        }
        //把左边余下的移入数组
        while (i<=mid)tmp[k++]=a[i++];
        //把右边余下的移入数组
        while (j<=end)tmp[k++]=a[j++];
        //覆盖
        for (i = 0;i<k;i++){
            a[start+i]=tmp[i];
        }
    }


    /**
     * 希尔排序算法
     * 基本思想：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列
     * 中的记录“基本有序”时，再对全体记录进行依次直接插入排序
     *
     * @param a
     */
    public static void shellSort(int[] a) {
        int dk = a.length / 2;
        while (dk >= 1) {
            ShellInsertSort(a, dk);
            dk = dk / 2;
        }
    }

    private static void ShellInsertSort(int[] a, int dk) {
        //类似插入排序，只是插入排序增量是 1，这里增量是 dk,把 1 换成 dk 就可以了
        for (int i = dk; i < a.length; i++) {
            if (a[i] < a[i - dk]) {
                int j;
                int x = a[i];//x 为待插入元素
                a[i] = a[i - dk];
                for (j = i - dk; j >= 0 && x < a[j]; j = j - dk) {
                    //通过循环，逐个后移一位找到要插入的位置。
                    a[j + dk] = a[j];
                }
                a[j + dk] = x;//插入
            }
        }
    }

    /**
     * 堆排序
     * @param nums 待排序数组序列
     * @return 排好序的数组序列
     */
    public static int[] heapSort(int[] nums) {

        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            heapAdjust(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            heapAdjust(nums, 0, i);
        }
        return nums;
    }

    /**
     * 调整堆
     *
     * @param nums  待排序序列
     * @param parent   待调整根节点
     * @param length 数组序列长度
     */
    private static void heapAdjust(int[] nums, int parent, int length) {
        int temp = nums[parent];
        int childIndex = 2 * parent + 1;  //完全二叉树节点i从编号1开始的左子节点位置在2i，此处数组下标从0开始，即左子节点所在数组索引位置为：2i + 1
        while (childIndex < length) {
            if (childIndex + 1 < length && nums[childIndex] < nums[childIndex + 1]) {
                childIndex++;  //节点有右子节点，且右子节点大于左子节点，则选取右子节点
            }
            if (temp > nums[childIndex]) {
                break; //如果选中节点大于其子节点，直接返回
            }
            nums[parent] = nums[childIndex];
            parent = childIndex;
            childIndex = 2 * parent + 1;  //继续向下调整
        }
        nums[parent] = temp;
    }


}
