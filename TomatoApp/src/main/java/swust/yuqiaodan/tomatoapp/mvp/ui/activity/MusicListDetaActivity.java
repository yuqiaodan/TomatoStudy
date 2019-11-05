package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMusicListDetaComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MusicListDetaContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.MusicBean.MusicSearchBean;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MusicListDetaPresenter;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.MusicListAdapter;


import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/11/2019 10:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MusicListDetaActivity extends BaseActivity<MusicListDetaPresenter> implements MusicListDetaContract.View {

    String search;

    @BindView(R.id.srarchBar)
    LinearLayout srarchBar;

    @BindView(R.id.music_list_edit)
    EditText music_edit;
    @BindView(R.id.music_list_search)
    TextView music_search;


    @BindView(R.id.music_list)
    RecyclerView music_list;


    List<MusicSearchBean> mData;

    MusicListAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMusicListDetaComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_music_list_deta; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecycleView();
        search = getIntent().getStringExtra("search");
        //如果搜索内容不是空 显示搜索框
        if (!TextUtils.isEmpty(search)) {
            srarchBar.setVisibility(View.VISIBLE);
            music_edit.setText(search);
            //开始搜索歌曲
            mPresenter.getSearchMusic(search);
        }


    }




    void initRecycleView() {
        mData = new ArrayList<>();
        mAdapter = new MusicListAdapter(mData);
        music_list.setLayoutManager(new LinearLayoutManager(this));
        music_list.setAdapter(mAdapter);

    }


    @OnClick ({R.id.music_list_search})
    public void Click(View view){

        switch (view.getId()){
            case R.id.music_list_search:
                if (!TextUtils.isEmpty(String.valueOf(music_edit.getText()))){
                search=String.valueOf(music_edit.getText());
                mPresenter.getSearchMusic(search);
                }
        }

    }

    //后期完善P层的排行榜和电台音乐请求方法
    void ranksongs() {
    }

    void stationsongs() {
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
    public void showSearchMusic(List<MusicSearchBean> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }


}
