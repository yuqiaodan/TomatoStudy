package swust.yuqiaodan.tomatoapp.mvp.ui.activity.ServiceStudy;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyFirstService extends Service {

    public static final String TAG = "MyFirstService";
    int num=0;

    public MyFirstService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        doSth();
    }


    public void doSth(){//service做的事情？
        initTimeRefresh();
    }

    public void initTimeRefresh() {

        int time =1000;//1秒
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // handler处理消息
                if (msg.what == 0) {
                    num=num+1;
                        //这里做自己需要的事情
                    Log.d(TAG, String.valueOf(num));

                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 定义一个消息传过去
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }, time, time);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() executed");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }
}
