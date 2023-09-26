package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130774905
 *
 * @author l84309057
 * @since 2023/9/13
 */
import java.util.LinkedList;
import java.util.Scanner;

public class º∆À„ŒÛ¬Î¬  {
    static class ZipStr {
        int num;
        char c;

        public ZipStr(int num, char c) {
            this.num = num;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        System.out.println(getResult(s1, s2));
    }

    public static String getResult(String s1, String s2) {
        LinkedList<ZipStr> link1 = getZipStrLink(s1);
        LinkedList<ZipStr> link2 = getZipStrLink(s2);

        int diff = 0;
        int same = 0;

        while (link1.size() > 0) {
            ZipStr zipStr1 = link1.removeFirst();
            ZipStr zipStr2 = link2.removeFirst();

            int compareCount = Math.min(zipStr1.num, zipStr2.num);

            if (zipStr1.c != zipStr2.c) {
                diff += compareCount;
            } else {
                same += compareCount;
            }

            if (zipStr1.num > compareCount) {
                zipStr1.num -= compareCount;
                link1.addFirst(zipStr1);
                continue;
            }

            if (zipStr2.num > compareCount) {
                zipStr2.num -= compareCount;
                link2.addFirst(zipStr2);
            }
        }

        return diff + "/" + (diff + same);
    }

    public static LinkedList<ZipStr> getZipStrLink(String s) {
        LinkedList<ZipStr> link = new LinkedList<>();

        StringBuilder num = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                num.append(c);
            } else {
                link.add(new ZipStr(Integer.parseInt(num.toString()), c));
                num = new StringBuilder();
            }
        }

        return link;
    }
}