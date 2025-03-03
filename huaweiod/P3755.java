package huaweiod;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @description: 2024E-BOSS的收入
 * @link <a href="https://www.algomooc.com/problem/P3755"></a>
 * @author: yzy
 * @date: 2025/3/2 13:21
 */
public class P3755 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 初始化一个二维数组，用于存储每个节点的信息，包括节点ID、依赖的节点ID和节点的值
        int[][] arr = new int[n + 1][3];
        // 初始化一个数组，用于记录每个节点的依赖数量
        int[] depend = new int[n + 1];

        // 遍历所有节点，输入节点信息，并更新依赖数量
        for (int i = 1; i <= n; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
            arr[i][2] = in.nextInt();
            depend[arr[i][1]] += 1;
        }

        // 初始化一个队列，用于存储当前没有依赖的节点
        Queue<int[]> q = new ArrayDeque<>();
        // 遍历所有节点，将没有依赖的节点添加到队列中
        for(int i = 1; i <=n; i++){
            if(depend[i] == 0){
                q.add(arr[i]);
            }
        }

        // 初始化结果变量，用于累计最终的结果值
        int res = 0;
        // 当队列不为空时，进行循环处理
        while(!q.isEmpty()){
            // 获取当前队列的大小，用于后续遍历
            int size = q.size();
            // 遍历当前队列中的所有节点
            for (int i = 0; i < size; i++) {
                // 取出队列中的节点
                int[] cur = q.poll();
                // 获取当前节点的父节点ID
                int parent = cur[1];
                // 如果当前节点没有父节点，则直接计算结果并累加到最终结果中
                if(parent == 0){
                    res += (cur[2] / 100) * 15;
                }else{
                    // 如果当前节点有父节点，则将计算结果累加到父节点的值上
                    arr[parent][2] += (cur[2] / 100) * 15;
                    // 更新父节点的依赖数量，如果父节点没有依赖了，则将父节点添加到队列中
                    if(--depend[parent] == 0){
                        q.add(arr[parent]);
                    }
                }
            }
        }
        System.out.println(res);
    }
}

//6
//        1 0 789
//        2 1 625
//        3 1 100
//        4 3 200
//        5 3 1001
//        6 5 98
