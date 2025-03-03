package huaweiod;

import java.util.Scanner;

/**
 * @description: 2024E-贪心的商人 200分 类似leetcode的 122. 买卖股票的最佳时机 II
 * @link <a href="https://www.algomooc.com/problem/P3108"></a>
 * @author: yzy
 * @date: 2025/3/3 17:48
 */
public class P3108 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int number = in.nextInt();
            int days= in.nextInt();
            int[] items = new int[number];
            for(int i=0;i<number;i++){
                items[i] = in.nextInt();
            }
            // 初始化总收益为0
            int res = 0;
            // 外层循环遍历每种商品
            for(int i = 0; i < number; i++){
                // 创建一个临时数组来存储每天的价格
                int[] temp = new int[days];
                // 内层循环读取每天的价格
                for(int j = 0; j < days; j++){
                    temp[j] = in.nextInt();
                }
                // 初始化利润为0，左侧价格为第一天的价格
                int profit = 0, left = temp[0];
                // 遍历每天的价格，计算利润
                for(int j = 1; j < days; j++){
                    // 如果当天的价格大于或等于前一天的价格，则计算利润
                    if(temp[j] >= left) {
                        profit += (temp[j] - left) * items[i];
                    }
                    // 更新左侧价格为当天的价格
                    left = temp[j];
                }
                // 累加每种商品的利润到总收益中
                res += profit;
            }
            System.out.println(res);
        }
    }
}
