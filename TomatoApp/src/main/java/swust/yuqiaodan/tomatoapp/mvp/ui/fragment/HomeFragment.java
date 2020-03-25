package swust.yuqiaodan.tomatoapp.mvp.ui.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.app.utils.DataHelperExtension;
import swust.yuqiaodan.tomatoapp.di.component.DaggerNewsComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsBean;
import swust.yuqiaodan.tomatoapp.mvp.presenter.NewsPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.ChannelListAdapter;
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
    @BindView(R.id.channel_menu)
    ImageView channelSelect;

    private List<Fragment> fragmentList;
    MainNewsFragmentTabAdapter mainNewsFragmentTabAdapter;

    Set<String> newsChannelsSelected;//已经选择的新闻频道

    public SharedPreferences mSharedPreferences;

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
        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        initFragment();
        initChannelSelect();

    }


    private void initFragment() {
        fragmentList = new ArrayList<>();
        //创建SharedPreferences中保存的set数据的一个副本 来进行操作 避免进行数据混淆
        //因为如果是同样的对象newsChannelsSelected 进行add 和 remove后 会导致混淆
        newsChannelsSelected=new HashSet<>(mSharedPreferences.getStringSet(Constants.NEWSCHANNELS, new HashSet<>()));


        if(newsChannelsSelected.size()==0){
            //第一次启动 如果没有任何频道 则选择默认的
            newsChannelsSelected.addAll(Constants.channelDefaultSelected);
        }

        for (String channel : newsChannelsSelected) {
            fragmentList.add(new NewsFragment(channel));
        }

        mainNewsFragmentTabAdapter = new MainNewsFragmentTabAdapter(getChildFragmentManager());
        //设置fragment
        mainNewsFragmentTabAdapter.setFragments(fragmentList);
        //设置标题
        mainNewsFragmentTabAdapter.setTitles(newsChannelsSelected.toArray(new String[0]));

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

    private void initChannelSelect() {
        channelSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //选择弹框的布局
                View dialogView = View.inflate(getContext(), R.layout.dialog_channel_select, null);

                RecyclerView channelList=dialogView.findViewById(R.id.channel_list);

                //设置布局
                channelList.setLayoutManager(new LinearLayoutManager(getContext()));
                ChannelListAdapter channelListAdapter=new ChannelListAdapter(Constants.allChannelList,newsChannelsSelected);
                channelList.setAdapter(channelListAdapter);


                channelListAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {

                        if(newsChannelsSelected.contains(Constants.allChannelList.get(position))){
                            newsChannelsSelected.remove(Constants.allChannelList.get(position));
                        }else{
                            newsChannelsSelected.add(Constants.allChannelList.get(position));
                        }

                        Log.d("选择的频道",newsChannelsSelected.toString());

                    }
                });
                new AlertDialog.Builder(getContext())
                        .setView(dialogView)
                        .setCancelable(false)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               //do nothing

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Log.d("选择的频道---保存的数据",newsChannelsSelected.toString());

                                mSharedPreferences.edit().putStringSet(Constants.NEWSCHANNELS, newsChannelsSelected).apply();

                                Log.d("选择的频道---保存后的数据",mSharedPreferences.getStringSet(Constants.NEWSCHANNELS, new HashSet<>()).toString());

                                //保存配置
                                //DataHelperExtension.setSetSF(getContext(),Constants.NEWSCHANNELS,newsChannelsSelected);

                                fragmentList.clear();
                                for (String channel : newsChannelsSelected) {
                                    fragmentList.add(new NewsFragment(channel));
                                }
                                //设置fragment
                                mainNewsFragmentTabAdapter.setFragments(fragmentList);
                                //设置标题
                                mainNewsFragmentTabAdapter.setTitles(newsChannelsSelected.toArray(new String[0]));


                                mainNewsFragmentTabAdapter.notifyDataSetChanged();
                            }
                        }).create().show();


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
