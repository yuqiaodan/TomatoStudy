package swust.yuqiaodan.tomatoapp.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.di.component.DaggerNewsComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsBean;
import swust.yuqiaodan.tomatoapp.mvp.presenter.NewsPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.MainNewsFragmentTabAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 主界面 用于显示新闻的界面
 */
public class HomeFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.home_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.home_viewpager)
    ViewPager viewPager;

    private List<Fragment> fragmentList;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNewsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initFragment();
    }


    private void initFragment() {
        fragmentList = new ArrayList<>();
        for (String channel : Constants.getChannelSelected()) {
            fragmentList.add(new NewsFragment(channel));

        }

        MainNewsFragmentTabAdapter mainNewsFragmentTabAdapter = new MainNewsFragmentTabAdapter(getChildFragmentManager());
        mainNewsFragmentTabAdapter.setFragments(fragmentList);
        viewPager.setAdapter(mainNewsFragmentTabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });


    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showNews(List<NewsBean> data) {

    }



    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }
}
