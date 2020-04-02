package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.camera.tool.RxCameraTool;
import com.vondear.rxtool.RxBarTool;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxRecyclerViewDividerTool;
import com.vondear.rxtool.RxTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.di.component.DaggerPicComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.PicContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeShowBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.PicPresenter;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.PicAdapter;


import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.vondear.rxtool.RxTool.getContext;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/26/2019 18:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class PicActivity extends BaseActivity<PicPresenter> implements PicContract.View {
    @BindView(R.id.pic_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.pic_refreshlayout)
    SmartRefreshLayout refreshLayout;
    List<PicEntity.ResultBean> mData;
    PicAdapter mAdapter;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPicComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        RxTool.init(this);
        return R.layout.activity_pic; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        initRecycleView();
        initRefreshLayout();
        mPresenter.getPic(true);


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    void initRefreshLayout() {
        //设置加载头和脚样式
        refreshLayout.setRefreshHeader(new WaterDropHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                mPresenter.getPic(true);

                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {

                mPresenter.getPic(false);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }


    void initRecycleView(){
        mData = new ArrayList<>();
        mAdapter = new PicAdapter(mData);
        //设置RecycleView为网格布局
        recyclerView.addItemDecoration(new RxRecyclerViewDividerTool(RxImageTool.dp2px(1f)));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
            Intent intent=new Intent(getContext(), ShowImageActivity.class);
            intent.putExtra("image",mData.get(position).getImg());
            startActivity(intent);
        });

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
    public void showData(List<PicEntity.ResultBean> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreData(List<PicEntity.ResultBean> data) {
        //记录当前的最底部的item位置
        int position=mData.size()-1;
        //增加数据
        mData.addAll(data);
        //只调用notifyItemRangeChanged方法 刷新之后新添加进来的数据的item
        //这样就不会导致动画重复
        mAdapter.notifyItemRangeChanged(position+1,data.size());
    }

    @Override
    public void showJoke(List<JokeShowBean> data) {

    }
}
