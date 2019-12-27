package swust.yuqiaodan.tomatoapp.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtool.RxTool;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.di.component.DaggerNewsComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsBean;
import swust.yuqiaodan.tomatoapp.mvp.presenter.NewsPresenter;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.MyWebActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.NewsAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 包含于HomeFragment 用于展示单个新闻频道的具体内容
 */
@SuppressLint("ValidFragment")
public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.recyclerView_News)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout_News)
    SmartRefreshLayout refreshLayout;

    List<NewsBean> mData;
    NewsAdapter mAdapter;

    private int page;//分页发送请求（页数） 每页默认10条新闻 在P中修改

    private  String channel;

     @SuppressLint("ValidFragment")
     public NewsFragment(String channel){

        this.channel=channel;
    }

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
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        RxTool.init(this.mContext);

        page = 1;
        initRefreshLayout();
        initRecycleView();
        mPresenter.getNews(channel,page);

    }

    void initRefreshLayout() {
        //设置加载头和脚样式
        refreshLayout.setRefreshHeader(new WaterDropHeader(this.mContext));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this.mContext));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                page = 1;

                mPresenter.getNews(channel,page);

                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page = page + 10;
                mPresenter.getNews(channel,page);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void initRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.mContext, DividerItemDecoration.VERTICAL));

        mData = new ArrayList<>();
        mAdapter = new NewsAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
            //跳转到网页加载页面
            Intent intent = new Intent(getContext(), MyWebActivity.class);//跳转到新闻网页
            intent.putExtra("URL", mData.get(position).getUrl());
            startActivity(intent);

            /**
             *
             * 查看图片
             *             Intent intent=new Intent(getContext(), ShowImageActivity.class);
             *             intent.putExtra("image",mData.get(position).getImages());
             *             startActivity(intent);
             * */

        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showNews(List<NewsBean> data) {
        if (page == 1) {//如果是第一页去请求 则是刷新
            mData.clear();
        }
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void setData(@Nullable Object data) {

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

    }


}
