package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130767262
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util .*;
import java.util.stream.Collectors;


public class �������ܵ��� {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            // ������¥��, �����ܸ���
            int m = sc.nextInt(), n = sc.nextInt();

            // ����
            Integer[] nums = new Integer[n];
            for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

            System.out.println(getResult(m, n, nums));
        }

        static int expectUpCount; // ����������ϵ�Ԫ�ظ���
        static int limitUpSum; // ����������ϵ�Ԫ��֮�͵�����
        static int maxUpSumBelowLimit = 0; // ��¼������limitUpSum�������������еĺͣ��ñ������ڰ���ans�޳��������Ž����������
        static ArrayList<boolean[]> paths = new ArrayList<>(); // paths��¼����Ϲ����з��ֵķ���Ҫ�������

        public static String getResult(int m, int n, Integer[] nums) {
            // ����expectUpCount��limitUpSum
            int sum = Arrays.stream(nums).reduce(Integer::sum).orElse(0);
            expectUpCount = n / 2 + n % 2;
            limitUpSum = (sum + m) / 2;

            // ��ԭʼ���н���
            Arrays.sort(nums, (a, b) -> b - a);

            // �����
            dfs(nums, 0, new boolean[n], 0, 0);

            // ����û��˵���쳣���������Ӧ�ò������paths.size == 0�����������Ϊ�˱��գ����Ǵ���һ��
            if (paths.size() == 0) {
                return "-1";
            }

            // ���Ƚ�pathsת��Ϊ������У�Ȼ��Խ�����н���������������ǣ����ȴ����ֵ��ԭ��, ���ȡ�����Ž�
            ArrayList<Integer> ans =
                    paths.stream()
                            .map(path -> getSeq(path, nums))
                            .sorted((seq1, seq2) -> compare(seq1, seq2))
                            .collect(Collectors.toList())
                            .get(0);

            // ��ӡ��������
            StringJoiner sj = new StringJoiner(" ");
            for (Integer v : ans) sj.add(v + "");
            return sj.toString();
        }

        /**
         * �÷�������ѡȡ���������е����
         *
         * @param nums  ԭʼ���У����������
         * @param index ��ǰ�㼶��ѡȡ��Ԫ�ط�Χ����ʼ����
         * @param path  ��¼����������ϣ�ע��path������õ���nums.length���ȵ�boolean[]���飬���path[i]Ϊtrue�������nums[i]������������nums[i]�����½�
         * @param sum   ����������ϵ�Ԫ�غ�
         * @param count ����������ϵ�Ԫ�ظ���
         */
        public static void dfs(Integer[] nums, int index, boolean[] path, int sum, int count) {
            // �����������Ԫ�ظ���������expectUpCount, �������Ӧ�ݹ�
            if (count > expectUpCount) return;

            if (count == expectUpCount) {
                // �Ǹ��Ž⣬ֱ���޳�
                if (sum < maxUpSumBelowLimit) return;

                // ���ָ��Ž⣬�����paths
                if (sum > maxUpSumBelowLimit) {
                    maxUpSumBelowLimit = sum;
                    paths.clear();
                }

                // ��¼��Ӧ��
                paths.add(Arrays.copyOf(path, path.length));
                return;
            }

            for (int i = index; i < nums.length; i++) {
                int num = nums[i];

                if (sum + num > limitUpSum) continue;

                path[i] = true;
                dfs(nums, i + 1, path, sum + num, count + 1);
                path[i] = false;
            }
        }

        // ����path�������������к��½����У������������к��½����н��н���ϲ����γ�Ŀ������
        public static ArrayList<Integer> getSeq(boolean[] path, Integer[] nums) {
            // ��������
            LinkedList<Integer> up = new LinkedList<>();
            // �½�����
            LinkedList<Integer> down = new LinkedList<>();

            // ����path�������������С��½�����
            for (int i = 0; i < nums.length; i++) {
                if (path[i]) up.add(nums[i]);
                else down.add(nums[i]);
            }

            // Ŀ����������
            ArrayList<Integer> seq = new ArrayList<>();

            // ����ϲ�
            for (int i = 0; i < nums.length / 2; i++) {
                seq.add(up.removeFirst());
                seq.add(down.removeFirst());
            }

            if (up.size() > 0) {
                seq.add(up.removeFirst());
            }

            return seq;
        }

        // �Ƚ���ͬ���ȵ��������ϵĴ�С, �����Ԫ�رȽ�
        public static int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
            for (int i = 0; i < list1.size(); i++) {
                int v1 = list1.get(i);
                int v2 = list2.get(i);

                if (v1 > v2) return -1;
                if (v1 < v2) return 1;
            }
            return 0;
        }

}
