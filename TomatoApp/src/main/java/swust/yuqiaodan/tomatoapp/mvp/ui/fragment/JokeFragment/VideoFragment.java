package swust.yuqiaodan.tomatoapp.mvp.ui.fragment.JokeFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerNewsComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.NewsPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.JokeAdapter;

public class VideoFragment extends BaseFragment<NewsPresenter> implements NewsContract.View{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    List<JokeEntity> mData;
    JokeAdapter mAdapter;
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
        //mPresenter.getJoke();
        initRefreshLayout();
        iniRecycleView();
        mPresenter.getJoke("video", true);
    }
    void initRefreshLayout() {
        //设置加载头和脚样式
        refreshLayout.setRefreshHeader(new WaterDropHeader(this.mContext));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this.mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getJoke("video", true);


                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                mPresenter.getJoke("video", false);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void iniRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        mData = new ArrayList<>();
        mAdapter = new JokeAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
            //播放视频JiaoZiVideoPlayer/GSYVideoPlayer


        });

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
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreDataJoke(List<JokeEntity> data) {
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
