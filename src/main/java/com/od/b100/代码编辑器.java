package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130770773
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Scanner;

public class ´úÂë±à¼­Æ÷ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = Integer.parseInt(sc.nextLine());

        StringBuilder s = new StringBuilder(sc.nextLine());

        String[][] commands = new String[k][2];
        for (int i = 0; i < k; i++) {
            commands[i] = sc.nextLine().split(" ");
        }

        System.out.println(getResult(s, commands));
    }

    public static String getResult(StringBuilder s, String[][] commands) {
        int curIdx = 0;

        for (String[] command : commands) {
            switch (command[0]) {
                case "FORWARD":
                    curIdx += Integer.parseInt(command[1]);
                    curIdx = Math.min(curIdx, s.length());
                    break;
                case "BACKWARD":
                    curIdx -= Integer.parseInt(command[1]);
                    curIdx = Math.max(curIdx, 0);
                    break;
                case "SEARCH-FORWARD":
                    int i = s.indexOf(command[1], curIdx);
                    if (i != -1) curIdx = i;
                    break;
                case "SEARCH-BACKWARD":
                    int i1 = s.lastIndexOf(command[1], curIdx);
                    if (i1 != -1) curIdx = i1;
                    break;
                case "INSERT":
                    s.insert(curIdx, command[1]);
                    curIdx += command[1].length();
                    break;
                case "REPLACE":
                    s.replace(curIdx, curIdx + command[1].length(), command[1]);
                    break;
                case "DELETE":
                    s.delete(curIdx, curIdx + Integer.parseInt(command[1]));
                    break;
            }
        }

        return s.toString();
    }
}