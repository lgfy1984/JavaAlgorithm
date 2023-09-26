package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130773708
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class �����ĵ�С���� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] kids = sc.nextLine().split(" ");

        System.out.println(getResult(n, kids));
    }

    public static int getResult(int n, String[] kids) {
        // �����ĵ�С��������
        int unHappy = 0;

        // ����ҡҡ���ϵ�С���ѱ��
        HashSet<String> playing = new HashSet<>();
        // �����Ŷӵ�С���ѱ��
        LinkedList<String> waiting = new LinkedList<>();

        for (String kid : kids) {
            if (playing.contains(kid)) {
                // ���kid��ҡҡ���ϵ�С���ѱ��, �����kid�����Ҫ�뿪
                playing.remove(kid);

                // ���kid�뿪��ҡҡ���п�λ�ˣ������ʱ�����Ŷӣ������Ŷӵ����ϳ���
                if (waiting.size() > 0) {
                    playing.add(waiting.removeFirst());
                }

                continue;
            }

            // ���kid����ҡҡ���ϵ�С���ѣ�����kid�ǲ����Ŷӵ�С����
            int index = waiting.indexOf(kid);
            if (index != -1) {
                // ������Ŷӵ�С���ѣ���˵��kidû���浽ҡҡ������˻᲻���ĵ��뿪
                unHappy++;
                waiting.remove(index);
                continue;
            }

            // ���kid�Ȳ���ҡҡ���ϵ�С���ѣ�Ҳ�����Ŷӵ�С���ѣ�����������С����
            if (playing.size() < n) {
                // ���ҡҡ�����п�λ����ֱ����
                playing.add(kid);
            } else {
                // ���ҡҡ��û�п�λ�ˣ�����Ҫ�Ŷ�
                waiting.add(kid);
            }
        }

        return unHappy;
    }
}