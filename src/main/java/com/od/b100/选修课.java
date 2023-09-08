package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130765442
 *
 * @author l84309057
 * @since 2023/9/7
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class ѡ�޿� {

    // ѧ����
    static class Student {
        String sid; // ѧ��
        String cid; // ���
        int score1 = -1; // �γ�һ �÷�
        int score2 = -1; // �γ̶� �÷�

        // �ܷ�
        public int getSumScore() {
            return this.score1 + this.score2;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        getResult(s1, s2);
    }

    public static void getResult(String s1, String s2) {
        // key��ѧ�ţ�value��ѧ������
        HashMap<String, Student> students = new HashMap<>();

        // ����ѧ����Ϣ
        divide(s1, 1, students);
        divide(s2, 2, students);

        // ���˳��������γ̵÷ֵ�ѧ��
        Student[] suits =
                students.values().stream()
                        .filter(stu -> stu.score1 != -1 && stu.score2 != -1)
                        .toArray(Student[]::new);

        // ���������������ѧ�����򷵻�NULL
        if (suits.length == 0) {
            System.out.println("NULL");
            return;
        }

        // key�ǰ�ţ�value�Ǹð༶��ѧ������
        HashMap<String, ArrayList<Student>> ans = new HashMap<>();
        for (Student stu : suits) {
            ans.putIfAbsent(stu.cid, new ArrayList<>());
            ans.get(stu.cid).add(stu);
        }

        ans.keySet().stream()
                .sorted(String::compareTo) // ���հ������
                .forEach(
                        cid -> {
                            System.out.println(cid); // ��ӡ���

                            ArrayList<Student> studs = ans.get(cid);
                            studs.sort(
                                    (a, b) ->
                                            a.getSumScore() != b.getSumScore()
                                                    ? b.getSumScore() - a.getSumScore()
                                                    : a.sid.compareTo(b.sid)); // ͬһ���ѧ�������ֽܷ����ܷ���ͬ������ѧ������

                            // ��ӡͬһ���ѧ�ţ��Էֺŷָ�
                            StringJoiner sj = new StringJoiner(";");
                            for (Student stud : studs) sj.add(stud.sid);
                            System.out.println(sj);
                        });
    }

    public static void divide(String str, int courseId, HashMap<String, Student> students) {
        for (String sub : str.split(";")) {
            String[] tmp = sub.split(",");

            String sid = tmp[0]; // ѧ��
            String cid = sid.substring(0, 5); // ���
            int score = Integer.parseInt(tmp[1]); // �γ̵÷�

            students.putIfAbsent(sid, new Student()); // �Ƿ��Ѽ�¼����ѧ������û�������ɶ�Ӧѧ������

            // ��ʼ��ѧ������
            Student stu = students.get(sid);

            stu.sid = sid;
            stu.cid = cid;

            if (courseId == 1) stu.score1 = score;
            else stu.score2 = score;
        }
    }

}
