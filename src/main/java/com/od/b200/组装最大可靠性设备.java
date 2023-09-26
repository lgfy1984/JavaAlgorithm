package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130764202
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class ��װ���ɿ����豸 {
    // ������
    static class Device {
        int reliability;
        int price;

        public Device(int reliability, int price) {
            this.reliability = reliability;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int s = sc.nextInt(); // ��Ԥ��
        int n = sc.nextInt(); // ������������

        // �ռ������Ŀɿ���
        TreeSet<Integer> reliabilities = new TreeSet<>();

        // �����༯��
        ArrayList<ArrayList<Device>> kinds = new ArrayList<>();
        // Ϊÿ���������ഴ��һ�����ϣ�����װ��Ӧ���������
        for (int i = 0; i < n; i++) kinds.add(new ArrayList<>());

        int total = sc.nextInt(); // ֮������total�о�������������

        for (int i = 0; i < total; i++) {
            // ��������
            int type = sc.nextInt();

            // �����ɿ���
            int reliability = sc.nextInt();
            reliabilities.add(reliability); // �ռ������Ŀɿ���

            // ������ֵ
            int price = sc.nextInt();

            // ���������뵽��Ӧ��������
            kinds.get(type).add(new Device(reliability, price));
        }

        System.out.println(getResult(s, kinds, reliabilities));
    }

    /**
     * @param s ��Ԥ��
     * @param kinds ���༯��
     * @param reliabilities �ɿ��Լ���
     * @return ���ɿ���
     */
    public static int getResult(
            int s, ArrayList<ArrayList<Device>> kinds, TreeSet<Integer> reliabilities) {

        // ans��¼���
        int ans = -1;

        // ��ÿ�������ڵ��������տɿ�������
        for (ArrayList<Device> kind : kinds) {
            kind.sort((a, b) -> a.reliability - b.reliability);
        }

        // �����������Ŀɿ��Լ��ϣ���Ϊ����
        Integer[] maybe = reliabilities.toArray(new Integer[0]);

        // ����ѡȡ���ܵ����ɿ���maybe
        int low = 0;
        int high = maybe.length - 1;

        while (low <= high) {
            int mid = (low + high) >> 1;
            // ���maybe[mid]�ɿ��Կ��Ա�֤����������������ѡ�����Ҽ۸�֮��С��s
            if (check(kinds, maybe[mid], s)) {
                // ��maybe[mid]�ɿ��Ծ���һ�����ܽ�
                ans = maybe[mid];
                // �������Ը��Ž⣬���Ҹ���Ŀɿ���
                low = mid + 1;
            } else {
                // ����˵���ɿ���ѡ���ˣ�����Ӧ�ü������Ը��͵Ŀɿ���
                high = mid - 1;
            }
        }

        return ans;
    }

    public static boolean check(ArrayList<ArrayList<Device>> kinds, int maxReliability, int s) {
        int sum = 0;
        for (ArrayList<Device> kind : kinds) {
            // ע��kind�ڵ������Ѿ����տɿ���������
            // ������Ҫ�ڸ�kind�������ҵ�һ���ɿ���>=maxReliability������
            int idx = binarySearch(kind, maxReliability);

            // ���idx<0����˵��idx��һ������λ��
            if (idx < 0) {
                idx = -idx - 1;
            }

            // ���idx==kind.size()��˵��kind�����������Ŀɿ��Զ�����maxReliability����˴�kind����ѡȡ����������ֱ�ӷ���false
            if (idx == kind.size()) return false;

            // ����ѡȡ��Ӧidxλ�õ�������������۸��ܼ�
            sum += kind.get(idx).price;
        }

        // ��������ܼ�С����Ԥ��s�����maxReliability����
        return sum <= s;
    }

    public static int binarySearch(ArrayList<Device> kind, int target) {
        int low = 0;
        int high = kind.size() - 1;

        while (low <= high) {
            int mid = (low + high) >> 1;
            Device device = kind.get(mid);

            if (device.reliability > target) {
                high = mid - 1;
            } else if (device.reliability < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -low - 1;
    }
}