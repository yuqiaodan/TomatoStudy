package swust.yuqiaodan.tomatoapp.MyRxjavaStudy;

import android.util.Log;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import swust.yuqiaodan.tomatoapp.MyRxjavaStudy.service.APIService;
import swust.yuqiaodan.tomatoapp.MyRxjavaStudy.service.WeatherBean;

//https://www.jianshu.com/p/464fa025229e

public class RxjavaTest {

    public static void main(String[] args) {
        test6();
    }

    //最简单的使用<一>
    public static void test1() {
        //最简单的使用

        //建立观察者
        Observer<String> observer = new Observer<String>() {
            //开始订阅被观察者的动作
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            //执行完一次操作
            @Override
            public void onNext(String s) {
                System.out.println("Item: " + s);
            }

            //出错
            @Override
            public void onError(Throwable e) {
                System.out.println("Error");
            }

            //完成所有操作
            @Override
            public void onComplete() {
                System.out.println("Complete");
            }


        };

/**************建立被观察者  三种方式 *****************/

        //第一种 正常方式
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                //被观察者 我需要在这里的订阅时间中做些事情
                //比如在这里发出网络请求 最终得到一个数据比如str="Hello"
                //然后在onNext中吧这个str发送给观察者 观察者可以在主线程进行下一步处理

                emitter.onNext("Hello");
                emitter.onNext("My");
                emitter.onNext("Rxjava");
                //这里发出已经完成所有操作
                emitter.onComplete();
            }
        });

        //第二种 just简化方式  将传入的参数依次发送出来
        //Observable observable = Observable.just("Hello", "Hi", "Aloha");

        //第三种 fromArray简化方式 将传入的数组或 Iterable 拆分成具体对象后，依次发出来
/*        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.fromArray(words);*/

        //建立订阅关系
        observable.subscribe(observer);
    }

    //将Test1中的代码精简化<一>
    public static void test2() {
        //将Test1中的代码精简化
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable.fromArray(words).subscribe(new Observer<String>() {
            //开始订阅被观察者的动作
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            //执行完一次操作
            @Override
            public void onNext(String s) {
                System.out.println("Item: " + s);

            }

            //出错
            @Override
            public void onError(Throwable e) {
                System.out.println("Error");
            }

            //完成所有操作
            @Override
            public void onComplete() {
                System.out.println("Complete");
            }
        });


    }

    //在test2中加入线程调度Scheduler<二>
    public static void test3() {
        Observable.just("Hello", "Hi", "Aloha")
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<String>() {
                    //开始订阅被观察者的动作
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    //执行完一次操作
                    @Override
                    public void onNext(String s) {
                        System.out.println("Item: " + s);
                    }

                    //出错
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error");
                    }

                    //完成所有操作
                    @Override
                    public void onComplete() {
                        System.out.println("Complete");
                    }
                });

    }

    //在test3的基础上加入map的使用<三>
    public static void test4() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                //比如我这里说明了是将int转化为String
                //我需要在这里边进行相应的处理 把被观察者传递过来的对象转换为我想要的对象
                String str = "This is result " + String.valueOf(integer);
                return str;
            }
        }).subscribe(new Consumer<String>() {//《一》 Consumer也是一个观察者对象 但是它只在乎上游的noNext方法 不会在乎错误处理等等
            @Override
            public void accept(String s) throws Exception {

                System.out.println(s);
            }
        });
    }

    //在test3的基础上 结合retrofit的使用<二>
    public static void test5() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://apis.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//加入rxjava适配
                .build();

        retrofit.create(APIService.class)
                .getWeather("成都", "19fd22d1c21e46438c7e62f87087e2ef")
                .subscribe(new Observer<WeatherBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {
                        System.out.println(weatherBean.getResult().getCity());
                        System.out.println(weatherBean.getResult().getRealtime().getInfo());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("请求错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    //在test4的基础上学习FlatMap的使用<三>
    public static void test6() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                    return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });




    }

}
