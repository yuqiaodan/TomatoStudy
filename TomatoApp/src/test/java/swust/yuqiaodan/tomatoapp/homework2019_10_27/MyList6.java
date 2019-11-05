package swust.yuqiaodan.tomatoapp.homework2019_10_27;

//在此实现第六题

import java.util.ArrayList;

/**
 * 6、编程实现学生成绩管理系统，此系统具有查询、修改、删除、增加和求全班各门课的平均分功能，要求采用顺序存储结构。
 * 1）定义学生Student类，包括学号、姓名、语文、数学、英语成绩属性，以及对应构造方法和配套get、set方法、toString()
 * 2）定义一个学生管理系统类StudentManage，以一个顺序表为成员变量，要求提供下述方法：
 * 	构造方法，允许根据用户录入的学生人数，进行顺序表初始化
 * 	insert(Student newOne,int i)方法，允许用户输入一个学生的newOne信息，在顺序表第i个位置插入
 * 	remove(String sno)，允许删除学号为sno的学生信息
 * 	find（String sno）,提供对于学号为sno的学生的信息查找，找到，则输入该学生所有信息，没有找到，则返回无该生
 * 	modify(String sno,int chinese,int math,int english),允许对学号为sno的学生进行成绩修改，chinese对应语文成绩，math对应数学成绩，english对应英语成绩（如果成绩为-1，表示不修改，为0-150，则为修改设置新值）
 * 3）定义一个测试类
 */

public class MyList6 {

    public static void main(String args[]) {

        Student xiaoming = new Student("1234", "xiaoming");
        Student xiaohong = new Student("1235", "xiaohong");
        Student xiaojun = new Student("1236", "xiaojun");
        StudentManage studentManage = new StudentManage(3);

        studentManage.insert(xiaoming, 0);
        studentManage.insert(xiaohong, 1);
        studentManage.insert(xiaojun, 2);

        studentManage.remove("1236");//删除学号“1236”

        Student findstudent;
        findstudent = studentManage.find("1234");//查找学号为1234的学生
        System.out.println("查找学生：\n" + findstudent.toString());
        studentManage.modify("1235", 122, 130, 145);
        System.out.println("修改学生1235成绩：\n" + studentManage.find("1235"));


    }

}

class StudentManage {
    int size;

    StudentManage(int size) {
        this.size = size;
    }

    ArrayList<Student> studentsList = new ArrayList<Student>();

    public void insert(Student newOne, int i) {
        studentsList.add(i, newOne);
    }

    public void remove(String sno) {
        for (int i = 0; i < studentsList.size() - 1; i++) {
            Student student = studentsList.get(0);
            if (student != null) {
                if (student.getSno() == sno) {
                    studentsList.remove(student);
                }

            }

        }

    }

    public Student find(String sno) {
        for (Student student : studentsList) {

            if (student != null) {
                if (student.getSno() == sno) {
                    return student;
                }

            }

        }
        return null;
    }

    public void modify(String sno, int chinese, int math, int english) {

        for (Student student : studentsList) {
            if (student != null) {
                if (student.getSno() == sno) {
                    if (chinese > 0 && chinese <= 150) {
                        student.setChinese(chinese);
                    }
                    if (english > 0 && english <= 150) {
                        student.setEnglish(english);

                    }
                    if (math > 0 && math <= 150) {
                        student.setMath(math);

                    }

                }

            }

        }


    }


}

class Student {
    private String sno;//学号
    private String name;
    private int chinese;
    private int math;
    private int english;

    Student(String sno, String name) {
        this.sno = sno;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                '}';
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }


    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
}
