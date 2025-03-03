package huaweiod;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @description: 2024E-简易内存池 200分
 * @link <a href="https://www.algomooc.com/problem/P2582"></a>
 * @author: yzy
 * @date: 2025/3/3 15:48
 */
public class P2582 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = Integer.parseInt(in.next());
            // 初始化一个哈希表来存储已分配的内存块，键为起始地址，值为大小
            Map<Integer, Integer> malloc = new HashMap<>();
            // 初始化一个优先队列来存储未分配的内存块，按起始地址排序
            PriorityQueue<int[]> unMalloc = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            // 初始时添加一个起始地址为0，大小为100的内存块到未分配队列
            unMalloc.add(new int[]{0, 100});
            // 遍历每个操作
            for (int i = 0; i < n; i++) {
                // 读取下一个操作
                String s = in.next();
                // 将操作按等号分割成类型和大小或地址
                String[] ss = s.split("=");
                // 如果是内存分配请求
                if ("REQUEST".equals(ss[0])) {
                    // 解析请求的内存大小
                    int size = Integer.parseInt(ss[1]);
                    // 如果请求的大小为0，输出错误并继续下一个操作
                    if (size == 0) {
                        System.out.println("error");
                        continue;
                    }
                    // 查找未分配队列中满足大小要求的内存块
                    int[] array = unMalloc.stream().anyMatch(a -> a[1] >= size) ? unMalloc.stream().filter(a -> a[1] >= size).findFirst().get() : null;
                    // 如果找到合适的内存块
                    if (array != null) {
                        // 从未分配队列中移除该内存块
                        unMalloc.remove(array);
                        // 输出内存块的起始地址
                        System.out.println(array[0]);
                        // 将内存块添加到已分配哈希表中
                        malloc.put(array[0], size);
                        // 更新内存块的起始地址和大小
                        array[0] += size;
                        array[1] -= size;
                        // 将更新后的内存块重新添加到未分配队列
                        unMalloc.add(array);

                    } else {
                        // 如果未找到合适的内存块，输出错误
                        System.out.println("error");
                    }

                } else {
                    // 如果是内存释放操作，解析要释放的内存地址
                    int point = Integer.parseInt(ss[1]);
                    // 如果地址存在于已分配哈希表中
                    if (malloc.containsKey(point)) {
                        // 获取并从已分配哈希表中移除该地址对应的内存块大小
                        Integer remove = malloc.remove(point);
                        // 标记释放的内存是否与现有未分配内存块合并
                        boolean union = false;
                        // 遍历未分配队列中的每个内存块
                        for (int[] array : unMalloc) {
                            // 如果内存块的起始地址等于释放内存的结束地址
                            if (array[0] == remove + point) {
                                // 合并内存块
                                array[0] = point;
                                array[1] += remove;
                                union = true;
                                break;
                            }
                        }
                        // 如果释放的内存未与任何现有未分配内存块合并
                        if (!union) {
                            // 将释放的内存作为新的未分配内存块添加到未分配队列
                            unMalloc.add(new int[]{point, remove});
                        }
                    } else {
                        // 如果地址不存在于已分配哈希表中，输出错误
                        System.out.println("error");
                    }
                }
            }

        }
    }
}
