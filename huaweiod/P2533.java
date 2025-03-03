package huaweiod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description: 2024E-攀登者2 200分， 困难题
 * @link: <a href="https://www.algomooc.com/problem/P2533"></a>
 * @author: yzy
 * @date: 2025/3/3 19:49
 */
public class P2533 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] strings = s.split(",");
        int[] arr = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }
        int capcity = Integer.parseInt(in.nextLine());
        // 贪心算法，记录最近平地开始的体力消耗
        // left[i][0]记录从左边最近的平地到达当前位置i消耗体力,left[i][1]记录从当前位置回到左边最近的平地消耗体力
        // right[i][0]记录从右边最近的平地到达当前位置i消耗体力,right[i][1]记录从当前位置回到右边最近的平地消耗体力
        int[][] left = new int[arr.length][2];
        int[][] right = new int[arr.length][2];
        List<Integer> list = new ArrayList<>();
        boolean leftGround = false;
        boolean rightGround = false;
        for (int i = 0; i < arr.length; i++) {
            if (!leftGround && arr[i] == 0) {
                leftGround = true;
            }
            if (!rightGround && arr[arr.length - 1 - i] == 0) {
                rightGround = true;
            }
            left[i] = getPhysicalExertion(i, arr, leftGround, 1,left);
            right[arr.length - 1 - i] = getPhysicalExertion(arr.length - 1 - i, arr, rightGround, -1,right);
            if (isPeak(i, arr)) {
                list.add(i);
            }
        }

        int res = 0;
        for (int i : list) {
            res += canClimb(left[i], right[i], capcity) ? 1 : 0;
        }
        System.out.println(res);
    }

    // 判断是否有体力爬山峰
    // 四种情况：左上右下，左上左下，右上右下，右上左下
    private static boolean canClimb(int[] left, int[] right, int capcity) {
        int a = left[0] + left[1], b = right[0] + right[1], c = left[0] + right[1], d = left[1] + right[0];
        int min = Math.min(a, Math.min(b, Math.min(c, d)));
        return min <= capcity;
    }

    // 获取当前体力消耗
    private static int[] getPhysicalExertion(int i, int[] arr, boolean hasGround, int step, int[][] leftOrRight) {
        if (!hasGround) {
            return new int[]{-1, -1};
        } else {
            if (arr[i] == 0) {
                return new int[]{0, 0};
            } else {
                int dif = arr[i] - arr[i - step];
                return returnArray(step, leftOrRight, dif, i);
            }
        }
    }

    // 返回体力消耗的数组
    private static int[] returnArray(int step, int[][] leftOrRight, int dif, int i){
        if (dif >= 0) {
            return new int[]{leftOrRight[i - step][0] + dif * 2, leftOrRight[i - step][1] + dif};
        } else {
            return new int[]{leftOrRight[i - step][0] - dif, leftOrRight[i - step][1] - dif * 2};
        }
    }

    // 判断是否是山峰
    private static boolean isPeak(int i, int[] arr) {
        if (i == 0 && i < arr.length - 1) {
            return arr[i] > arr[i + 1];
        } else if (i == arr.length - 1 && i > 0) {
            return arr[i] > arr[i - 1];
        } else {
            return arr[i] > arr[i - 1] && arr[i] > arr[i + 1];
        }
    }
}
