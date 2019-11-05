package swust.yuqiaodan.tomatoapp.homework2019_10_27;

import java.util.ArrayList;
import java.util.Arrays;

//这个类中完成了第四题
public class MyList4_5 {
    /**
     * 4、利用单链表类型解决实际问题，内容如下：
     * 创建带头结点单链表，单链表中各个元素为为a,b,c,d,e,f,g,h
     * 1）调用输出函数，输出单链表
     * 2）调用插入函数，在第7个结点后插入值为’x’的元素。插入完毕，调用输出函数输出单链表
     * 3）调用删除函数，删除第5个元素。删除完毕，调用输出函数输出单链表
     * 4）编写语句，实现在首结点之前插入值为’y’的元素。插入完毕，调用输出函数输出单链表
     * 5）编写语句，实现在首结点之后插入值为’m’的元素。插入完毕，调用输出函数输出单链表
     * 6）编写语句，实现删除值为’c’的结点。删除完毕，调用输出函数输出单链表
     */
    ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"));

    public void method4() {
        //1）调用输出函数，输出单链表
        System.out.println("列表内数据：\n" + list);
        //* 2）调用插入函数，在第7个结点后插入值为’x’的元素。插入完毕，调用输出函数输出单链表
        list.add(6, "x");
        System.out.println("在第7个结点后插入值为’x’的元素,列表内数据：\n" + list);
        //* 3）调用删除函数，删除第5个元素。删除完毕，调用输出函数输出单链表
        list.remove(4);
        System.out.println("删除第5个元素,列表内数据：\n" + list);
        //* 4）编写语句，实现在首结点之前插入值为’y’的元素。插入完毕，调用输出函数输出单链表
        list.add(0, "y");
        System.out.println("实现在首结点之前插入值为’y’的元素,列表内数据：\n" + list);
        //* 5）编写语句，实现在首结点之后插入值为’m’的元素。插入完毕，调用输出函数输出单链表
        list.add(1, "y");
        System.out.println("实现在首结点之后插入值为’m’的元素,列表内数据：\n" + list);
        //* 6）编写语句，实现删除值为’c’的结点。删除完毕，调用输出函数输出单链表
        list.remove("c");
        System.out.println("删除值为’c’的结点：\n" + list);
    }

    /**
     * 5、在第4题的单链表类中增加以下成员方法，修饰符为public
     * 1）Node first()   //返回单链表的首个元素结点
     * 2）Node last()   //返回单链表最后一个元素结点
     * 3）LinkList subList(int begin,int end ) //返回在begin和end之间的元素组成的子表
     */
    public String first() {
        return list.get(0);

    }

    public String last() {




        return list.get(list.size() - 1);
    }

    public ArrayList<String> subList(int begin, int end) {
        ArrayList<String> mylist=new ArrayList<>();
        if (begin < end && end <= list.size()) {
            for (String i :list){
                if (list.indexOf(i)>=begin&&list.indexOf(i)<end-1)
                {
                    mylist.add(i);

                }
            }
        }
        return mylist;

    }

}
