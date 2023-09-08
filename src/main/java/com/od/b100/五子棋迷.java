package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130766999
 *
 * @author l84309057
 * @since 2023/9/6
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class �������� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int color = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(color, nums));
    }

    public static int getResult(int color, int[] nums) {
        // ��ȡ��ʼ�������������
        int initMaxConstantLen = getInitMaxConstantLen(color, nums);

        ArrayList<int[]> ans = new ArrayList<>();

        // l~r֮�������ֻ�ܰ���һ��0������������һ�Σ����඼��color��ɫ������
        int l = 0;
        int r = 0;

        // l~r֮�������0������������������
        int zero = 0;
        // l~r֮��0��λ�ã�������λ��
        int pos = -1;

        while (r < nums.length) {
            // ���nums[r]�ǿ�λ
            if (nums[r] == 0) {
                // ��������ӣ�������Ӹ���++
                zero++;

                // ���������������1���ˣ����ʱ l~r-1 ��Χ����һ������(PS:rλ�ò�������)��
                // ��������峤�� (r-1) - l + 1 <= 5��PS:������Լ����������һ���Ϸ�������
                // ����Ҫ�����ӿ���ʹ�õ�ǰ�ӵ�����������ȱ��
                if (zero > 1 && r - l <= 5 && r - l > initMaxConstantLen) {
                    ans.add(new int[]{pos, r - l}); // ��¼ l~r-1 ��Χ������λ��pos���Լ���������r-l
                }

                // ����ֻ������һ�Σ����ǰ���������Ҫ�ջأ������� l ����һ������λ�õ��ұ�
                if (zero > 1) {
                    zero--;
                    l = pos + 1;
                }

                // ��������λ��
                pos = r;

                ++r;
            }
            // ���nums[r]λ����������ɫ���ӣ��������ж�
            else if (nums[r] != color) {
                // ��ʱ��Ҫ��� l~r-1 ��Χ�Ƿ�����ӣ����Ƿ����������Լ��
                // ���ǣ����¼ l~r-1 ��Χ������λ��pos���Լ���������r-l
                // ����Ҫ�����ӿ���ʹ�õ�ǰ�ӵ�����������ȱ��
                if (zero == 1 && r - l <= 5 && r - l > initMaxConstantLen) ans.add(new int[]{pos, r - l});
                // ���������ж��ˣ��������λ��pos������������ȫ������
                pos = -1;
                zero = 0;
                // l,rȫ�����µ���ǰr���ұ�һ��λ��
                l = ++r;
            }
            // ���nums[r]λ���е�ǰ��ɫ���ӣ����������
            else {
                ++r;
            }
        }

        // ��β����
        if (zero == 1 && r - l <= 5 && r - l > initMaxConstantLen) {
            ans.add(new int[]{pos, r - l});
        }

        // ���û��ͳ�Ƶ������飬�򷵻�-1
        if (ans.size() == 0) return -1;

        int mid = nums.length / 2;

        // ���ͳ�Ƶ�����
        // �Ȱ������峤�Ƚ������������ͬ�����սӽ�����λ��mid�ľ�������Խ����Խ�ţ��������������λ��mid��ͬ����������λ������ԽС��Խ�ţ�
        ans.sort((a, b) -> a[1] != b[1] ? b[1] - a[1] : cmp(a[0], b[0], mid));
        return ans.get(0)[0]; // ȡ�������������λ��
    }

    // �Ƚ�pos1,pos2˭���ӽ�mid���������mid��ͬ���򷵻ؽ�С��
    public static int cmp(int pos1, int pos2, int mid) {
        int dis1 = Math.abs(pos1 - mid);
        int dis2 = Math.abs(pos2 - mid);

        if (dis1 != dis2) {
            return dis1 - dis2;
        } else {
            return pos1 - pos2;
        }
    }

    // ��ȡ��ʼ����������ȣ���δ����ǰ�������������
    public static int getInitMaxConstantLen(int color, int[] nums) {
        int maxLen = 0;

        int len = 0;
        for (int num : nums) {
            if (num != color) {
                maxLen = Math.max(maxLen, len);
                len = 0;
            } else {
                len++;
            }
        }

        return Math.max(maxLen, len);
    }
}
