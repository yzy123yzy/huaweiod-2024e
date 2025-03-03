package huaweiod;

import cn.hutool.core.lang.Assert;

import java.util.*;

/**
 * @description: 2024E-比赛的冠亚季军 200分
 * @link <a href="https://www.algomooc.com/problem/P2521">...</a>
 * @author: yzy
 * @date: 2025/3/1 14:24
 */
public class P2521 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> l = new ArrayList<>();
        while (in.hasNextInt()) {
            l.add(in.nextInt());
        }
        for (int i = 0; i < l.size(); i++) {
            q.add(i);
        }
        // 当队列q的大小大于4时，执行下述操作
        while (q.size() > 4) {
            // 计算队列q的大小的一半
            int d = q.size() / 2;
            // 计算队列q的大小除以2的余数
            int r = q.size() % 2;
            // 遍历队列
            for (int i = 0; i < d; i++) {
                // 从队列中移除并获取第一个元素
                int a = q.poll();
                // 从队列中移除并获取下一个元素
                int b = q.poll();
                // 比较列表l中索引a和b对应的值
                if (l.get(a) >= l.get(b)) {
                    // 如果a对应的值大于等于b对应的值，将a重新加入队列
                    q.add(a);
                } else {
                    // 否则，将b重新加入队列
                    q.add(b);
                }
            }
            // 如果队列q的大小为奇数
            if (r == 1) {
                // 将队列中的下一个元素重新加入队列，以保持剩余的元素
                q.add(q.poll());
            }
        }
        int[] res = getRank(q, l);
        for(int i : res){
            System.out.print(i + " ");
        }

    }


    /**
     * 根据给定的队列和列表计算排名
     * 此方法的目的是通过比较列表中特定位置的元素来确定元素的排名
     * 队列用于指定需要比较的元素的顺序，列表包含实际的比较值
     *
     * @param q 队列，包含需要比较的元素的索引
     * @param l 列表，包含每个元素的值
     * @return 返回一个数组，表示比较后的排名
     */
    public static int[] getRank(Queue<Integer> q, List<Integer> l) {
        // 当队列大小为4时，进行两两比较，确定前两名和后两名
        if (q.size() == 4) {
            int a = q.poll(), b = q.poll(), c = q.poll(), d = q.poll();
            int[] vic = new int[2];
            int[] los = new int[2];

            // 比较a和b，确定第一个胜利者和第一个失败者
            if (l.get(a) >= l.get(b)) {
                vic[0] = a;
                los[0] = b;
            } else {
                vic[0] = b;
                los[0] = a;
            }

            // 比较c和d，确定第二个胜利者和第二个失败者
            if (l.get(c) >= l.get(d)) {
                vic[1] = c;
                los[1] = d;
            } else {
                vic[1] = d;
                los[1] = c;
            }

            int[] res = new int[3];

            // 比较两个胜利者，确定最终的胜利者排名
            if (l.get(vic[0]) >= l.get(vic[1])) {
                res[0] = vic[0];
                res[1] = vic[1];
            } else {
                res[0] = vic[1];
                res[1] = vic[0];
            }

            // 比较两个失败者，确定最终的失败者排名
            if (l.get(los[0]) >= l.get(los[1])) {
                res[2] = los[0];
            } else {
                res[2] = los[1];
            }

            return res;
        }
        else {
            int a = q.poll(), b = q.poll(), c = q.poll();
            int[] res = new int[3];
            int[] vic = new int[2];
            vic[1] = c;

            // 比较a和b，确定胜利者和失败者
            if (l.get(a) >= l.get(b)) {
                vic[0] = a;
                res[2] = b;
            } else {
                vic[0] = b;
                res[2] = a;
            }

            // 比较胜利者和c，确定最终的排名
            if (l.get(vic[0]) >= l.get(vic[1])) {
                res[0] = vic[0];
                res[1] = vic[1];
            } else {
                res[0] = vic[1];
                res[1] = vic[0];
            }

            return res;
        }
    }
}
