package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130775353
 *
 * @author l84309057
 * @since 2023/9/13
 */
import java.util.Scanner;

public class ��ѵĳ��Ʒ��� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getResult(sc.nextLine()));
    }

    // ����������
    static int max_score = 0;

    public static int getResult(String cards) {
        // �����������������, ����Ԫ������������, ���� 0 ��������
        int[] card_count = new int[14];

        // ͳ�Ƹ����Ƶ�����
        for (int i = 0; i < cards.length(); i++) {
            char card = cards.charAt(i);

            // 1-9����Ϊ����1-9��10����Ϊ����0��JQK����Ϊ��д��ĸJQK
            // 1-9 ����������� 1-9, 0����������� 10, J=11,Q=12,K=13, ���Է�����������������ģ����Ժ�card_count�����������Ӧ����
            if (card == '0') card_count[10]++;
            else if (card == 'J') card_count[11]++;
            else if (card == 'Q') card_count[12]++;
            else if (card == 'K') card_count[13]++;
            else card_count[card - '0']++;
        }

        getMaxScore(card_count, cards.length(), 1, 0);

        return max_score;
    }

    /**
     * ��ȡ������
     *
     * @param card_count �����Ƶ�����
     * @param unused_card_count ʣ���Ƶ�������
     * @param startIdx ���ĸ�λ�ÿ�ʼѡ��
     * @param score ��ʱ�ѻ�õ��ܷ���
     */
    public static void getMaxScore(int[] card_count, int unused_card_count, int startIdx, int score) {
        // �����ƶ�������
        if (unused_card_count == 0) {
            // ��Ƚϴ�ʱ���Ʋ��Ի�õ��ܷ�score������ʷ��߷�max_score�������ϴ���
            max_score = Math.max(score, max_score);
            return;
        }

        // ���п��Գ����ƣ����startIdx��ʼ����
        for (int i = startIdx; i < card_count.length; i++) {
            // �����ǰ�Ƶ���������1��
            if (card_count[i] >= 1) {
                // ����1�����Գ��Գ�˳�ӣ���������˳����9,10,J,Q,K,��� i ��Ϊ˳����ʼ�ƵĻ������ܳ���9���Һ������� i+1, i+2, i+3, i+4 ��������������1��
                if (i <= 9
                        && card_count[i + 1] >= 1
                        && card_count[i + 2] >= 1
                        && card_count[i + 3] >= 1
                        && card_count[i + 4] >= 1) {
                    card_count[i] -= 1;
                    card_count[i + 1] -= 1;
                    card_count[i + 2] -= 1;
                    card_count[i + 3] -= 1;
                    card_count[i + 4] -= 1;
                    // ˳����5���ƣ���˳���˳�Ӻ󣬿�������������5�ţ��ܷ����� (i + (i+1) + (i+2) + (i+3) + (i+4)) * 2
                    getMaxScore(card_count, unused_card_count - 5, i, score + (5 * i + 10) * 2);
                    // ����
                    card_count[i] += 1;
                    card_count[i + 1] += 1;
                    card_count[i + 2] += 1;
                    card_count[i + 3] += 1;
                    card_count[i + 4] += 1;
                }

                // ����2��������
                card_count[i] -= 1;
                getMaxScore(card_count, unused_card_count - 1, i, score + i);
                card_count[i] += 1;
            }

            // �����ǰ�Ƶ���������2�ţ���ô���Գ�����
            if (card_count[i] >= 2) {
                card_count[i] -= 2;
                getMaxScore(card_count, unused_card_count - 2, i, score + i * 2 * 2);
                card_count[i] += 2;
            }

            // �����ǰ�Ƶ���������3�ţ���ô���Գ�����
            if (card_count[i] >= 3) {
                card_count[i] -= 3;
                getMaxScore(card_count, unused_card_count - 3, i, score + i * 3 * 2);
                card_count[i] += 3;
            }

            // ��ǰ��ǰ�Ƶ���������4�ţ���ô���Գ�ը��
            if (card_count[i] >= 4) {
                card_count[i] -= 4;
                getMaxScore(card_count, unused_card_count - 4, i, score + i * 4 * 3);
                card_count[i] += 4;
            }
        }
    }
}