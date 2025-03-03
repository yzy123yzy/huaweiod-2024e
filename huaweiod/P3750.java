package huaweiod;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @description: 2024E-启动多任务排序 200分
 * @link <a href="https://www.algomooc.com/problem/P3750"></a>
 * @author: yzy
 * @date: 2025/3/2 11:36
 */
public class P3750 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] strings = s.split(" ");
        int[] alp = new int[26];
        boolean[] has = new boolean[26];

        boolean[][] depend = new boolean[26][26];

        // 'A' = 65
        for(String ss : strings){
            char a = ss.charAt(0);
            char b = ss.charAt(3);
            has[a - 65] = true;
            has[b - 65] = true;
            alp[a - 65]++;
            depend[a-65][b -65] = true;
        }

        // 拓扑排序
        Queue<Integer> q = new ArrayDeque<>();
        // 遍历所有字母，寻找入度为0且存在的字母，将其加入队列
        for(int i = 0; i < 26; i++){
            if(alp[i] == 0 && has[i]){
                q.add(i);
            }
        }

        // 当队列不为空时，持续处理队列中的元素
        while(!q.isEmpty()){
            // 从队列中移除并获取当前元素，表示当前处理的字母
            int cur = q.poll();
            // 将当前字母转换为大写字符并打印
            System.out.print((char)(cur + 65));

            // 遍历所有字母，寻找依赖于当前字母的其他字母
            for(int i = 0; i < 26; i++){
                if(depend[i][cur]){
                    // 减少依赖当前字母的其他字母的入度
                    alp[i]--;
                    // 如果入度减为0，则将该字母加入队列
                    if(alp[i] == 0){
                        q.add(i);
                    }
                }
            }
        }
        System.out.println();
    }
}
