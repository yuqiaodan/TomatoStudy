package swust.yuqiaodan.tomatoapp.homework2019_10_27;

//1~5题test类
public class test1_5 {
    public static void main(String[] args) {
        //1~3题
        MyList1_3 myList1_3 = new MyList1_3();
        System.out.println("***************第一题***************");
        myList1_3.method1();
        System.out.println("\n***************第二题***************");
        myList1_3.replaceAll(5,110);
        myList1_3.removeLast(7);
        System.out.println("\n***************第三题***************");
        myList1_3.method3();


        //4~5题
        MyList4_5 myList4_5=new MyList4_5();
        System.out.println("\n***************第四题***************");
        myList4_5.method4();
        System.out.println("\n***************第五题***************");
        System.out.println("列表第一个元素："+myList4_5.first());
        System.out.println("列表最后一个元素："+myList4_5.last());
        System.out.println("列表2和5之间的元素组成的子表："+myList4_5.subList(2,5));



    }
}

