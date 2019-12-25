package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.widget.Toast;


import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMainComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MainPresenter;


import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.MainTabAdapter;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.HomeNewsFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.MenuFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.NewsFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.WeatherFragment;


import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/20/2019 16:01
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.main_viewPager)//为什么这里是空对象？
    ViewPager viewPager;
    @BindView(R.id.main_tabLayout)
    TabLayout tabLayout;
    private NewsFragment newsFragment;
    private WeatherFragment weatherFragment;
    private MenuFragment menuFragment;


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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initFragment();
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    private void initFragment() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new HomeNewsFragment());
        fragments.add(NewsFragment.newInstance("头条"));
        fragments.add(new MenuFragment());

        MainTabAdapter mainTabAdapter = new MainTabAdapter(getSupportFragmentManager());
        mainTabAdapter.setFragments(fragments);
        viewPager=findViewById(R.id.main_viewPager);
        tabLayout=findViewById(R.id.main_tabLayout);
        viewPager.setAdapter(mainTabAdapter);
        tabLayout.setupWithViewPager(viewPager);
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

