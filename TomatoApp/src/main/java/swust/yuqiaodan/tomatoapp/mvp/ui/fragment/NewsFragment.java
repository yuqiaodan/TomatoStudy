package swust.yuqiaodan.tomatoapp.mvp.ui.fragment;

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
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.OpenApiNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.presenter.NewsPresenter;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.MyWebActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.NewsAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 包含于HomeFragment 用于展示单个新闻频道的具体内容
 *
 *
 * 这个类只用于展示新闻和跳转到网页的加载
 * 由于服务器的问题 不管怎么请求都只返回第1页20条数据
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/20/2019 16:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    List<NewsBean> mData;
    NewsAdapter mAdapter;
    private static int page;//分页发送请求（页数） 每页默认10条新闻 在P中修改

    private static String channel;

    public static NewsFragment newInstance(String mychannel) {
        channel = mychannel;
        NewsFragment fragment = new NewsFragment();
        return fragment;
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
        mPresenter.getOprnApiNews(page, true);

    }

    void initRefreshLayout() {
        //设置加载头和脚样式
        refreshLayout.setRefreshHeader(new WaterDropHeader(this.mContext));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this.mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.getOprnApiNews(page, true);

                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page = page + 1;
                mPresenter.getOprnApiNews(page, false);
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
    public void showData(List<NewsBean> data) {//展示新闻数据
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreData(List<NewsBean> data) {

        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataJoke(List<JokeEntity> data) {

    }

    @Override
    public void showMoreDataJoke(List<JokeEntity> data) {

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
