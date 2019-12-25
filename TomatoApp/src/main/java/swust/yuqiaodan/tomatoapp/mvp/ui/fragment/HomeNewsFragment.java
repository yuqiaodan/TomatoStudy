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
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.NewsPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.MainNewsFragmentTabAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class HomeNewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View{

    @BindView(R.id.joke_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.joke_viewpager)
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
         return inflater.inflate(R.layout.fragment_weather, container, false);
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        initFragment();

    }


    private void initFragment() {


        fragmentList = new ArrayList<>();
        fragmentList.add(NewsFragment.newInstance(Constants.channelList.get(0)));
        fragmentList.add(NewsFragment.newInstance(Constants.channelList.get(1)));
        fragmentList.add(NewsFragment.newInstance(Constants.channelList.get(2)));
        fragmentList.add(NewsFragment.newInstance(Constants.channelList.get(3)));
        fragmentList.add(NewsFragment.newInstance(Constants.channelList.get(4)));
        fragmentList.add(NewsFragment.newInstance(Constants.channelList.get(5)));


        MainNewsFragmentTabAdapter mJokeTabAdapter = new MainNewsFragmentTabAdapter(getChildFragmentManager());
        mJokeTabAdapter.setFragments(fragmentList);
        viewPager.setAdapter(mJokeTabAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showData(List<NewsEntity> data) {

    }

    @Override
    public void showMoreData(List<NewsEntity> data) {

    }

    @Override
    public void showDataJoke(List<JokeEntity> data) {

    }

    @Override
    public void showMoreDataJoke(List<JokeEntity> data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }
}
