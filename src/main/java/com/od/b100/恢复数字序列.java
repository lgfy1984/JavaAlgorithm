package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130912996
 *
 * @author l84309057
 * @since 2023/9/7
 */
import java.util.HashMap;
import java.util.Scanner;

public class �ָ��������� {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String s = sc.next(); // �����ַ����ַ���
            int k = sc.nextInt(); // �������������еĳ���

            System.out.println(getResult(s, k));
        }

        public static int getResult(String s, int k) {
            // base��ͳ�ƴ����ַ����ַ����� ���ַ�������
            HashMap<Character, Integer> base = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                base.put(c, base.getOrDefault(c, 0) + 1);
            }

            // ��ʼ����������k���и��ַ�������
            HashMap<Character, Integer> count = new HashMap<>();
            for (int i = 1; i <= k; i++) {
                countNumChar(i + "", count, true);
            }

            // �Ƚϻ������ַ���������baseͳ�Ƶĸ��ַ������Ƿ�һ�£���һ�£���˵����ʼ��������һ������Ҫ��������������У������е���СֵΪ1
            if (cmp(base, count)) return 1;

            // ����������Ժ���������ע����Ŀ˵������������1000��������ǿ��Գ�����������������ȡֵ��Χ����1~1000
            for (int i = 2; i <= 1000 - k + 1; i++) {
                // �������һ������ʧȥ������
                String remove = i - 1 + "";
                countNumChar(remove, count, false);

                // �������һ����������������
                String add = i + k - 1 + "";
                countNumChar(add, count, true);

                // �Ƚ�
                if (cmp(base, count)) return i;
            }

            return -1; // ��Ŀ˵����Ψһ����������������������ﷵ��������߲�����
        }

        public static void countNumChar(String num, HashMap<Character, Integer> count, boolean isAdd) {
            for (int j = 0; j < num.length(); j++) {
                char c = num.charAt(j);
                count.put(c, count.getOrDefault(c, 0) + (isAdd ? 1 : -1));
            }
        }

        public static boolean cmp(HashMap<Character, Integer> base, HashMap<Character, Integer> count) {
            for (Character c : base.keySet()) {
                if (!count.containsKey(c) || count.get(c) - base.get(c) != 0) {
                    return false;
                }
            }
            return true;
        }

}
