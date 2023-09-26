package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130775353
 *
 * @author l84309057
 * @since 2023/9/13
 */
import java.util.Scanner;

public class 最佳的出牌方法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getResult(sc.nextLine()));
    }

    // 保存最大分数
    static int max_score = 0;

    public static int getResult(String cards) {
        // 数组索引是牌面分数, 数组元素是牌面数量, 其中 0 索引不用
        int[] card_count = new int[14];

        // 统计各种牌的数量
        for (int i = 0; i < cards.length(); i++) {
            char card = cards.charAt(i);

            // 1-9输入为数字1-9，10输入为数字0，JQK输入为大写字母JQK
            // 1-9 牌面分数就是 1-9, 0的牌面分数是 10, J=11,Q=12,K=13, 可以发现牌面分数是连续的，可以和card_count数组的索引对应起来
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
     * 获取最大分数
     *
     * @param card_count 各种牌的数量
     * @param unused_card_count 剩余牌的总数量
     * @param startIdx 从哪个位置开始选牌
     * @param score 此时已获得的总分数
     */
    public static void getMaxScore(int[] card_count, int unused_card_count, int startIdx, int score) {
        // 所有牌都打完了
        if (unused_card_count == 0) {
            // 则比较此时出牌策略获得的总分score，和历史最高分max_score，保留较大者
            max_score = Math.max(score, max_score);
            return;
        }

        // 还有可以出的牌，则从startIdx开始出牌
        for (int i = startIdx; i < card_count.length; i++) {
            // 如果当前牌的数量至少1张
            if (card_count[i] >= 1) {
                // 策略1、可以尝试出顺子，由于最大的顺子是9,10,J,Q,K,因此 i 作为顺子起始牌的话，不能超过9，且后续牌面 i+1, i+2, i+3, i+4 的数量都至少有1张
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
                    // 顺子是5张牌，因此出掉顺子后，可用牌数量减少5张，总分增加 (i + (i+1) + (i+2) + (i+3) + (i+4)) * 2
                    getMaxScore(card_count, unused_card_count - 5, i, score + (5 * i + 10) * 2);
                    // 回溯
                    card_count[i] += 1;
                    card_count[i + 1] += 1;
                    card_count[i + 2] += 1;
                    card_count[i + 3] += 1;
                    card_count[i + 4] += 1;
                }

                // 策略2、出单张
                card_count[i] -= 1;
                getMaxScore(card_count, unused_card_count - 1, i, score + i);
                card_count[i] += 1;
            }

            // 如果当前牌的数量至少2张，那么可以出对子
            if (card_count[i] >= 2) {
                card_count[i] -= 2;
                getMaxScore(card_count, unused_card_count - 2, i, score + i * 2 * 2);
                card_count[i] += 2;
            }

            // 如果当前牌的数量至少3张，那么可以出三张
            if (card_count[i] >= 3) {
                card_count[i] -= 3;
                getMaxScore(card_count, unused_card_count - 3, i, score + i * 3 * 2);
                card_count[i] += 3;
            }

            // 当前当前牌的数量至少4张，那么可以出炸弹
            if (card_count[i] >= 4) {
                card_count[i] -= 4;
                getMaxScore(card_count, unused_card_count - 4, i, score + i * 4 * 3);
                card_count[i] += 4;
            }
        }
    }
}