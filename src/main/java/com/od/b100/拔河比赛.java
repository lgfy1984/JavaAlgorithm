package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130770342
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class °ÎºÓ±ÈÈü {
    static class Employee {
        int height;
        int weight;

        public Employee(String s) {
            int[] tmp = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
            this.height = tmp[0];
            this.weight = tmp[1];
        }

        @Override
        public String toString() {
            return this.height + " " + this.weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Employee> employees = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if ("".equals(line)) {
                getResult(employees);
                break;
            } else {
                employees.add(new Employee(line));
            }
        }
    }

    public static void getResult(ArrayList<Employee> employees) {
        employees.sort((a, b) -> a.height != b.height ? b.height - a.height : b.weight - a.weight);

        for (int i = 0; i < 10; i++) {
            System.out.println(employees.get(i));
        }
    }
}