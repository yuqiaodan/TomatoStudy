package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMainComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MainPresenter;


import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.MainTabAdapter;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.HomeFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.MenuFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.NewsFragment;


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
        //防止键盘自动弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initFragment();
    }


    private void initFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsFragment(Constants.REALTIME));
        fragments.add(new MenuFragment());

        MainTabAdapter mainTabAdapter = new MainTabAdapter(getSupportFragmentManager());
        mainTabAdapter.setFragments(fragments);
        viewPager.setAdapter(mainTabAdapter);
        tabLayout.setupWithViewPager(viewPager);

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

