package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.util.Log;
import com.vondear.rxtool.RxBarTool;
import com.vondear.rxtool.RxTool;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.MyView.PointPicView;

public class WelcomeScreenActivity extends AppCompatActivity {
    TextView textView;
    PointPicView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_study);
        RxTool.init(this);
        //适配全面屏
        RxBarTool.setTransparentStatusBar(this);
        initView();
    }

    public void initView(){

        textView=findViewById(R.id.tv_view_animation);
        imageView=findViewById(R.id.img_tomato_animation);
        //原本的视图动画 现在不启用了 改为视觉效果更好的属性动画
/*        Animation animation_icon = AnimationUtils.loadAnimation(WelcomeScreenActivity.this,R.anim.lunch_page_icon_animation);
        Animation animation_title = AnimationUtils.loadAnimation(WelcomeScreenActivity.this,R.anim.lunch_page_title_animation);
        animation_icon.setFillAfter(true);
        animation_title.setFillAfter(true);
        textView.startAnimation(animation_title);
        imageView.startAnimation(animation_icon);*/


        //动画结束后 跳转到主界面
        imageView.getmAnimatorSet().addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d("image动画","End");
                jumpToMainActivity();
            }
        });

    }


    @SuppressLint("CheckResult")
    public void jumpToMainActivity(){
        //结束后延迟1秒
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
