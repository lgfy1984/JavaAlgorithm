package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/131185797
 *
 * @author l84309057
 * @since 2023/9/13
 */
import java.util.Arrays;
import java.util.Scanner;
public class �������бȴ�С {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(n, a, b));
    }

    public static int getResult(int n, int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int la = 0; // ָ�������������
        int ra = n - 1; // ָ�����������

        int lb = 0; // ָ��������������
        int rb = n - 1; // ָ������������

        int ans = 0; // ��¼��ɻ��������

        while (la <= ra) {
            if (a[ra] > b[rb]) {
                // ��������� �� ����������Ҫ��, ��ֱ�ӱ�
                ans += 1;
                ra--;
                rb--;
            } else if (a[ra] < b[rb]) {
                // ��������� �� ����������Ҫ��, �����϶���, Ϊ�˱������������, ����Ӧ���������������ȥ���ĵ�����������
                ans -= 1;
                la++;
                rb--;
            } else {
                // ��������� �� �������� �ٶ���ͬ, ��ʱ���ƽ�ֵĻ�������������ʧ�������������Ӧ���ҵ������������, ����ɱ�����������ĵ�����������
                if (a[la] > b[lb]) {
                    // �������������� �� ������������ ��, ���ʱ������������Ǳ������
                    ans += 1;
                    la++;
                    lb++;
                } else {
                    // ���������������ٶ� <= �������������ٶ�, ��ʱӦ��������������� ȥ����  ����������

                    // ��������������ٶ� > ������������ٶȣ������ʧȥ����
                    // ��������������ٶ� == ������������ٶȣ�����ɲ�ʧȥ����
                    if (b[rb] > a[la]) ans -= 1;
                    la++;
                    rb--;
                }
            }
        }

        return ans;
    }
}