package swust.yuqiaodan.tomatoapp.homework2019_10_27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//这个类中完成了1~3题
public class MyList1_3 {

    /**第一题
     * 1、利用顺序表数据类型解决实际问题，内容如下：
     * 已知当前需要定义一个顺序表类存放某一组整型数据（假设当前定义的数据结构最多能存放100个数据），
     * 值为1，5，96，45，3，6，4，88，7，36。现要求编写一个程序对这些数据进行以下操作：
     * 1）要求输出当前这个数据结构中的所有数据;
     * 2）要求在当前存放数据的第一个位置插入一个值为95的整型数据，并输出插入后的数组；
     * 3）要求删除当前存放数据中值为3的结点，并输出删除后的数组；
     * 4）要求在当前存放数据的值45后插入一个值为54的整型数据，并输出插入后的数组；
     */

    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 5, 96, 45, 3, 6, 4, 88, 7, 36));

    public void method1() {
        //1）要求输出当前这个数据结构中的所有数据;
        System.out.println("所有数据：");
        System.out.println(list);
        //2）要求在当前存放数据的第一个位置插入一个值为95的整型数据，并输出插入后的数组；
        list.add(0, 95);
        System.out.println("第一个位置插入95后数据：");
        System.out.println(list);
        //3）要求删除当前存放数据中值为3的结点，并输出删除后的数组；
        list.remove((Integer) 3);
        System.out.println("删除当前存放数据中值为3后的数据：");
        System.out.println(list);
        //4）要求在当前存放数据的值45后插入一个值为54的整型数据，并输出插入后的数组；
        list.add(list.indexOf(45) + 1, 54);
        System.out.println("45后插入一个值为54的整型数据后的数据：");
        System.out.println(list);
    }


    /**第二题
     * method2
     * 2、在第1题的顺序表类中增加以下成员方法，修饰符为public
     * void replaceAll(Object key,Object x)  //将所有关键字为key的元素替换为x
     * void removeLast(Object key) //删除最后出现的关键字为key元素
     */


    public void replaceAll(int key, int x) {
        Collections.replaceAll(list, key, x);
        System.out.println("将" + key + "替换为" + x + "后的List数据");
        System.out.println(list);
    }

    public void removeLast(int key) {
        int index = -1;

        for (int i : list) {
            if (i == key) {
                index = list.indexOf(i);
            }
        }
        if (index != -1) {
            list.remove(index);
            System.out.println("删除最后出现的 " + key + " 后的数据：");
            System.out.println(list);
        } else {
            System.out.println("list中不存在指定元素：" + key);

        }


    }

    /**第三题
     * *3、假设利用LA和LB分别表示两个集合A和B，
     * 现要求编写一个程序实现一个新的集合C=A∩B的求解，
     * 并将集合C的结果输出。
     * 已知当前有两个集合A和B，
     * 集合A中含有以下数据：3 , 6 , 9 , 7 , 8 , 4 , 5；
     * 集合B中含有以下数据：6 , 5 , 9 , 3 , 4。
     * 现要求设计一个程序，将集合A和B中共有的数据存放到一个新的集合C中。
     */

    public void method3() {
        ArrayList<Integer> listA = new ArrayList<Integer>(Arrays.asList(3 , 6 , 9 , 7 , 8 , 4 , 5));
        ArrayList<Integer> listB = new ArrayList<Integer>(Arrays.asList(6 , 5 , 9 , 3 , 4));
        ArrayList<Integer> listC = new ArrayList<Integer>();
        listA.retainAll(listB);
        listC.addAll(listA);
        //A∩B
        System.out.println("A∩B:");
        System.out.println(listC);
    }

}
