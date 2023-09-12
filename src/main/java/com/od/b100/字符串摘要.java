package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130769939
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.ArrayList;
import java.util.Scanner;

public class �ַ���ժҪ {
    // ��ĸ������
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
        // �����ִ�Сд
        s = s.toLowerCase();

        // ͳ��ÿ����ĸ���ֵĴ���
        int[] count = new int[128];

        // ȥ���ַ����еķ���ĸ
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                count[c]++;
                sb.append(c);
            }
        }

        // �ӿո���Ϊ�˱����������β��������������ʿ����Ƴ�����ӿո����
        s = sb + " ";

        // ��¼������ĸ�ͷ�������ĸ
        ArrayList<Letter> ans = new ArrayList<>();

        // ��һ��λ�õ���ĸ
        char pre = s.charAt(0);
        // ����ĸ����������Ϊ1
        int repeat = 1;
        // ��������ĸ����count[pre]-=1��
        count[pre]--;

        for (int i = 1; i < s.length(); i++) {
            // ��ǰλ�õ���ĸ
            char cur = s.charAt(i);
            // ��������ĸ����count[cur]-=1��
            count[cur]--;

            if (cur == pre) {
                // �����ǰλ�ú���һ��λ�õ���ĸ��ͬ�����������
                // ��������+1
                repeat++;
            } else {
                // �����ǰλ�ú���һ��λ�õ���ĸ��ͬ�����������
                // ���pre��ĸ��������>1����������������ô����pre+repeat,������Ǽ�����,��pre+count[pre]
                ans.add(new Letter(pre, repeat > 1 ? repeat : count[pre]));
                // ����preΪcur
                pre = cur;
                // ����pre��������Ϊ1
                repeat = 1;
            }
        }

        // ��ĸ�ͽ����������Ϊһ������������ִ����ǰ��������ͬ�ģ�����ĸ����������ĸС����ǰ
        ans.sort((a, b) -> a.num != b.num ? b.num - a.num : a.letter - b.letter);

        StringBuilder res = new StringBuilder();
        for (Letter an : ans) {
            res.append(an.toString());
        }
        return res.toString();
    }
}