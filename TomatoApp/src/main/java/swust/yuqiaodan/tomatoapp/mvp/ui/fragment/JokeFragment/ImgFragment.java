package swust.yuqiaodan.tomatoapp.mvp.ui.fragment.JokeFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxui.activity.ActivityWebView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerNewsComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.NewsPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.ShowImageActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.JokeAdapter;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.NewsAdapter;

public class ImgFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    LinearLayoutManager mManager;
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

        mPresenter.getJoke("image", true);
    }

    void initRefreshLayout() {
        //设置加载头和脚样式
        refreshLayout.setRefreshHeader(new WaterDropHeader(this.mContext));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this.mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                mPresenter.getJoke("image", true);

                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                mPresenter.getJoke("image", false);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void iniRecycleView() {
        mManager = new LinearLayoutManager(this.mContext);
        mRecyclerView.setLayoutManager(mManager);

        mData = new ArrayList<>();
        mAdapter = new JokeAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
            //查看图片
/*
            View itemview = mManager.findViewByPosition(position);
            LinearLayout layout= (LinearLayout) itemview;
            ImageView imageView=layout.findViewById(R.id.news_img);//获取到itemview中的图片 此处可以成功获取
            //以后可以通过这种方式来设置监听什么的
            Bitmap bitmap =((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Intent intent=new Intent(getContext(), ShowImageActivity.class);
            intent.putExtra("image" , bitmap) ;
            startActivity(intent);
*/
            Intent intent=new Intent(getContext(), ShowImageActivity.class);
            intent.putExtra("image",mData.get(position).getImages());
            startActivity(intent);

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
