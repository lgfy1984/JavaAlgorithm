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

public class 选修课 {

    // 学生类
    static class Student {
        String sid; // 学号
        String cid; // 班号
        int score1 = -1; // 课程一 得分
        int score2 = -1; // 课程二 得分

        // 总分
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
        // key是学号，value是学生对象
        HashMap<String, Student> students = new HashMap<>();

        // 解析学生信息
        divide(s1, 1, students);
        divide(s2, 2, students);

        // 过滤出有两个课程得分的学生
        Student[] suits =
                students.values().stream()
                        .filter(stu -> stu.score1 != -1 && stu.score2 != -1)
                        .toArray(Student[]::new);

        // 如果不存在这样的学生，则返回NULL
        if (suits.length == 0) {
            System.out.println("NULL");
            return;
        }

        // key是班号，value是该班级的学生集合
        HashMap<String, ArrayList<Student>> ans = new HashMap<>();
        for (Student stu : suits) {
            ans.putIfAbsent(stu.cid, new ArrayList<>());
            ans.get(stu.cid).add(stu);
        }

        ans.keySet().stream()
                .sorted(String::compareTo) // 按照班号升序
                .forEach(
                        cid -> {
                            System.out.println(cid); // 打印班号

                            ArrayList<Student> studs = ans.get(cid);
                            studs.sort(
                                    (a, b) ->
                                            a.getSumScore() != b.getSumScore()
                                                    ? b.getSumScore() - a.getSumScore()
                                                    : a.sid.compareTo(b.sid)); // 同一班的学生按照总分降序，总分相同，则按照学号升序

                            // 打印同一班的学号，以分号分隔
                            StringJoiner sj = new StringJoiner(";");
                            for (Student stud : studs) sj.add(stud.sid);
                            System.out.println(sj);
                        });
    }

    public static void divide(String str, int courseId, HashMap<String, Student> students) {
        for (String sub : str.split(";")) {
            String[] tmp = sub.split(",");

            String sid = tmp[0]; // 学号
            String cid = sid.substring(0, 5); // 班号
            int score = Integer.parseInt(tmp[1]); // 课程得分

            students.putIfAbsent(sid, new Student()); // 是否已记录过该学生，若没有则生成对应学生对象

            // 初始化学生对象
            Student stu = students.get(sid);

            stu.sid = sid;
            stu.cid = cid;

            if (courseId == 1) stu.score1 = score;
            else stu.score2 = score;
        }
    }

}
