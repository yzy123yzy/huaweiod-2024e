package huaweiod;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @description: 2024E-找等值元素 200分
 * @link <a href="https://www.algomooc.com/problem/P2832"></a>
 * @author: yzy
 * @date: 2025/3/1 12:52
 */
public class P2832 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = Integer.parseInt(in.next());
        int col = Integer.parseInt(in.next());
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = Integer.parseInt(in.next());
            }
        }
        int[][] res = new int[row][col];
        // 遍历二维数组的每个元素作为起始点
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 初始化访问标记数组，用于记录每个位置是否被访问过
                boolean[][] visited = new boolean[row][col];
                // 使用队列来进行广度优先搜索
                Queue<int[]> q = new ArrayDeque<>();
                // 将当前元素的坐标加入队列，并标记为已访问
                q.add(new int[]{i, j});
                visited[i][j] = true;
                // 计算从当前位置开始到达目标的最小步数
                res[i][j] = minStep(arr, i, j, 0, arr[i][j], visited, q);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int minStep(int[][] arr, int x, int y, int step, int target, boolean[][] visited, Queue<int[]> q) {
        // 广度优先遍历
        int res = -1;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // 使用BFS遍历直到队列为空
        while (!q.isEmpty()) {
            // 获取当前层的元素数量
            int size = q.size();
            // 步数增加
            step++;
            // 遍历当前层的所有元素
            for (int i = 0; i < size; i++) {
                // 取出并移除队列头部的元素
                int[] cur = q.poll();
                // 遍历所有可能的方向
                for (int[] direction : directions) {
                    // 确保当前元素不为空
                    assert cur != null;
                    // 计算新的X坐标
                    int newX = cur[0] + direction[0];
                    // 计算新的Y坐标
                    int newY = cur[1] + direction[1];
                    // 检查新坐标是否在数组范围内且未被访问过
                    if ((newX >= 0 && newX < arr.length && newY >= 0 && newY < arr[0].length) && !visited[newX][newY]) {
                        // 如果找到目标元素，返回当前步数
                        if(arr[newX][newY] == target){
                            return step;
                        }
                        // 标记新坐标为已访问
                        visited[newX][newY] = true;
                        // 将新坐标加入队列以供后续遍历
                        q.add(new int[]{newX, newY});
                    }
                }
            }
        }
        return res;
    }
}
