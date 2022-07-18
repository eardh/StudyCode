package com.dahuang.lambda_stream;

import java.util.*;
import java.util.stream.Collectors;

public class CollectDemo {

    public static void main(String[] args) {

        // 测试数据
        List<Student> students = Arrays.asList(
                new Student("小明", 10, Gender.FEMALE, Grade.ONE),
                new Student("小白", 11, Gender.MALE, Grade.FOUR),
                new Student("小黑", 9, Gender.FEMALE, Grade.TWO),
                new Student("小黄", 12, Gender.MALE, Grade.ONE),
                new Student("小李", 8, Gender.FEMALE, Grade.THREE),
                new Student("小菜", 9, Gender.MALE, Grade.ONE),
                new Student("小丽", 11, Gender.MALE, Grade.TWO),
                new Student("小美", 7, Gender.FEMALE, Grade.ONE),
                new Student("小哥", 10, Gender.MALE, Grade.THREE),
                new Student("小牛", 7, Gender.FEMALE, Grade.FOUR)
        );

        // 得到所有学生的年龄列表
        // s -> s.getAge()  -->  Student::getAge，不会 生成 一个类似lambda$0这样的函数
        Collection<Integer> ages = students.stream()
                .map(Student::getAge)
                //.collect(Collectors.toList())
                .collect(Collectors.toCollection(HashSet::new)); // 可以自定义收集器
        System.out.println(ages);

        // 统计汇总信息
        IntSummaryStatistics ageSummaryStatistics = students.stream()
                .collect(Collectors.summarizingInt(Student::getAge));
        System.out.println(ageSummaryStatistics);

        // 分块
        Map<Boolean, List<Student>> genders = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getGender() == Gender.MALE));
        System.out.println(genders);

        // 分组
        Map<Grade, List<Student>> graders = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
        System.out.println(graders);

        // 得到所有班级学生的个数
        Map<Grade, Long> gradersCount = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        System.out.println(gradersCount);


    }

}

class Student {

    private String name;
    private int age;
    private Gender gender;
    private Grade grade;

    public Student(String name, int age, Gender gender, Grade grade) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", grade=" + grade +
                '}';
    }
}

/**
 * 班级枚举
 */
enum Grade {
    ONE, TWO, THREE, FOUR
}

/**
 * 性别枚举
 */
enum Gender {
    MALE, FEMALE
}
