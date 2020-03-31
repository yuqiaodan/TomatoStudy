package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import swust.yuqiaodan.tomatoapp.R;

public class WelcomeScreenActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_study);
        textView=findViewById(R.id.tv_view_animation);
        imageView=findViewById(R.id.img_tomato_animation);

        Animation animation_icon = AnimationUtils.loadAnimation(WelcomeScreenActivity.this,R.anim.lunch_page_icon_animation);
        Animation animation_title = AnimationUtils.loadAnimation(WelcomeScreenActivity.this,R.anim.lunch_page_title_animation);
        animation_icon.setFillAfter(true);
        animation_title.setFillAfter(true);
        imageView.startAnimation(animation_icon);
        textView.startAnimation(animation_title);

        textView.setOnClickListener(view -> {
            //保持动画结束后的效果
            //animation.setFillAfter(true);
            textView.startAnimation(animation_title);
        });

        imageView.setOnClickListener(view -> {
            //保持动画结束后的效果
            //animation.setFillAfter(true);
            imageView.startAnimation(animation_icon);

        });


        animation_icon.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                jumpToMainActivity();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @SuppressLint("CheckResult")
    public void jumpToMainActivity(){
            Observable.timer(500, TimeUnit.MILLISECONDS)//计时500毫秒
                    .observeOn(AndroidSchedulers.mainThread())//订阅在主线程 因为后续要对界面进行操作（也可以在其他线程）
                    .subscribe(
                            new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    Intent intent=new Intent(WelcomeScreenActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                    );

    }

    private long mBackPressed;
    private static final int TIME_INTERVAL = 2000;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "再次点击返回键退出", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
