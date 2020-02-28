package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.vondear.rxtool.RxTool;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.app.EventBusTags;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMainComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.ChatBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MainPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.MyCamera.CameraActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.MainTabAdapter;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.HomeFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.MenuFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.RobotFragment;


import static com.jess.arms.utils.Preconditions.checkNotNull;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.main_viewPager)
    ViewPager viewPager;
    @BindView(R.id.main_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.main_search)
    LinearLayout mainSearchView;
    @BindView(R.id.view_search)
    LinearLayout viewSearch;
    @BindView(R.id.main_scan)
    ImageView viewScan;
    @BindView(R.id.main_bang)
    LinearLayout mainBang;

    private int SCANQRCODE = 1011;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        RxTool.init(this);
        //适配全面屏 有冲突 暂不启用
        //RxBarTool.setTransparentStatusBar(this);

        //防止键盘自动弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initFragment();
    }


    private void initFragment() {
        List<Fragment> fragments = new ArrayList<>();
        //主界面 新闻
        fragments.add(new HomeFragment());
        //小I机器人
        fragments.add(new RobotFragment());
        fragments.add(new MenuFragment());


        MainTabAdapter mainTabAdapter = new MainTabAdapter(getSupportFragmentManager());
        mainTabAdapter.setFragments(fragments);
        viewPager.setAdapter(mainTabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.home_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.robot_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.personal_icon);

        //设置tab选择的监听 以判断是否显示搜索框
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选择tab
                if (tab.getPosition() == 2) {
                    mainSearchView.setVisibility(View.GONE);
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //离开tab
                if (tab.getPosition() == 2) {
                    mainSearchView.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次点击选择
            }
        });
    }

    @OnClick({R.id.view_search, R.id.main_scan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_search:
                Intent intent = new Intent(this, SearchNewsActivity.class);
                startActivity(intent);
                break;
            case R.id.main_scan:
                //扫码功能暂无
                Intent scanIntent = new Intent(this, ScanQRActivity.class);
                startActivityForResult(scanIntent, SCANQRCODE);
                break;

        }

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void showWeather(WeatherEntity weathereEntity) {

    }

    @Override
    public void showChatContent(ChatBean chatBean) {

    }


    //订阅回收键盘的消息
    @Subscriber(tag = EventBusTags.RECOVERYKEYBOARD)
    public void recoveryKeyboard(String str) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SCANQRCODE) {
                String qRResult = "";
                qRResult = data.getStringExtra(Constants.QRResult);

                if (qRResult.startsWith("http")) {
                    //如果是扫描到的http开头的网址
                    //跳转到网页加载页面
                    Intent intent = new Intent(this, MyWebActivity.class);//跳转到新闻网页
                    intent.putExtra("URL", qRResult);
                    startActivity(intent);
                } else {
                    //如果是其他字符串
                    showMessage("扫描结果："+qRResult);
                }

            }
        }


    }
}

