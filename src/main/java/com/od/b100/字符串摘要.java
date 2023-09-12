package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130769939
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.ArrayList;
import java.util.Scanner;

public class 字符串摘要 {
    // 字母数字类
    static class Letter {
        char letter;
        int num;

        public Letter(char letter, int num) {
            this.letter = letter;
            this.num = num;
        }

        @Override
        public String toString() {
            return this.letter + "" + this.num;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getResult(sc.nextLine()));
    }

    public static String getResult(String s) {
        // 不区分大小写
        s = s.toLowerCase();

        // 统计每个字母出现的次数
        int[] count = new int[128];

        // 去除字符串中的非字母
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                count[c]++;
                sb.append(c);
            }
        }

        // 加空格是为了避免后续的收尾操作，如果有疑问可以移除下面加空格操作
        s = sb + " ";

        // 记录连续字母和非连续字母
        ArrayList<Letter> ans = new ArrayList<>();

        // 上一个位置的字母
        char pre = s.charAt(0);
        // 该字母连续次数记为1
        int repeat = 1;
        // 后续该字母还有count[pre]-=1个
        count[pre]--;

        for (int i = 1; i < s.length(); i++) {
            // 当前位置的字母
            char cur = s.charAt(i);
            // 后续该字母还有count[cur]-=1个
            count[cur]--;

            if (cur == pre) {
                // 如果当前位置和上一个位置的字母相同，则产生连续
                // 连续次数+1
                repeat++;
            } else {
                // 如果当前位置和上一个位置的字母不同，则连续打断
                // 如果pre字母连续次数>1，则是真连续，那么就是pre+repeat,否则就是假连续,是pre+count[pre]
                ans.add(new Letter(pre, repeat > 1 ? repeat : count[pre]));
                // 更新pre为cur
                pre = cur;
                // 更新pre连续次数为1
                repeat = 1;
            }
        }

        // 字母和紧随的数字作为一组进行排序，数字大的在前，数字相同的，则按字母进行排序，字母小的在前
        ans.sort((a, b) -> a.num != b.num ? b.num - a.num : a.letter - b.letter);

        StringBuilder res = new StringBuilder();
        for (Letter an : ans) {
            res.append(an.toString());
        }
        return res.toString();
    }
}