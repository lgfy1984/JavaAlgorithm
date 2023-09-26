package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130161763
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class 相同数字组成图形的周长 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Integer[][] lines = new Integer[n][];
        for (int i = 0; i < n; i++) {
            lines[i] =
                    Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        }

        System.out.println(getResult(lines));
    }

    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static String getResult(Integer[][] lines) {
        // 记录每个图形的边长
        ArrayList<Integer> ans = new ArrayList<>();

        // 定义一个64*64的矩阵
        int[][] matrix = new int[64][64];

        // 向64*64的矩阵中填充数字
        for (Integer[] line : lines) {
            // 每行第一个数是矩阵单元格填充的数字
            int num = line[0];

            // 后续每两个一组，表示填充该数字的单元格坐标
            for (int i = 1; i < line.length; i += 2) {
                int x = line[i];
                int y = line[i + 1];
                matrix[x][y] = num; // 填充数字
            }
        }

        // 遍历每个图形的坐标
        for (Integer[] line : lines) {
            int num = line[0];
            int p = 0; // 周长

            for (int i = 1; i < line.length; i += 2) {
                int x = line[i];
                int y = line[i + 1];

                // 遍历图形中元素的上下左右
                for (int[] offset : offsets) {
                    int offsetX = offset[0];
                    int offsetY = offset[1];

                    int newX = x + offsetX;
                    int newY = y + offsetY;

                    // 如果图形元素的上或下或左或右没有越界
                    if (newX >= 0 && newX < 64 && newY >= 0 && newY < 64) {
                        // 则如果图形元素的上或下或左或右不是图形值，那么当前图形的边长+1
                        if (matrix[newX][newY] != num) p++;
                    } else {
                        // 如果图形元素的上或下或左或右越界了，则当前图形边长也+1
                        p++;
                    }
                }
            }

            ans.add(p);
        }

        StringJoiner sj = new StringJoiner(" ");
        for (Integer an : ans) {
            sj.add(an + "");
        }

        return sj.toString();
    }
}