package huaweiod;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description: 最短模板长度，200分 贪心算法，每次选择填充最小的。当一样大的时候，先填充最右侧的，保证有序性。
 * @link <a href="https://www.algomooc.com/problem/P2497"></a>
 * @author: yzy
 * @date: 2025/3/1 13:42
 */
public class P2497 {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        // 对数组进行排序，以便后续计算相邻元素之间的差值
        Arrays.sort(arr);
        // 初始化长度为n-1的数组diff，用于存储相邻元素的差值
        int[] diff = new int[n - 1];
        // 计算并存储相邻元素的差值
        for (int i = 0; i < n - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }
        // 当还有操作次数时，进行差值调整
        while(m > 0){
            int i = 0;
            // 遍历差值数组，寻找并调整差值大于0的位置
            for (; i < n - 1; i++) {
                if(diff[i] > 0){
                    diff[i] -= 1;
                    arr[i] += 1;
                    m--;
                    // 如果不是第一个元素，调整前一个差值
                    if(i != 0) {
                        diff[i - 1] += 1;
                    }
                    break;
                }
            }
            // 如果遍历完差值数组仍有操作次数，对最后一个元素进行调整
            if(i == n - 1 && m > 0){
                arr[i] += 1;
                m--;
            }
        }
        System.out.println(arr[0]);
    }
}
