package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerNewsComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsBean;
import swust.yuqiaodan.tomatoapp.mvp.presenter.NewsPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.MyCamera.CameraActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.NewsAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class SearchNewsActivity extends BaseActivity<NewsPresenter> implements NewsContract.View {
    @BindView(R.id.tv_search)
    TextView search;
    @BindView(R.id.edit_search_news)
    EditText searchEdit;
    @BindView(R.id.recyclerView_searchNews)
    RecyclerView newsRrecyclerView;
    String searchKey;
    List<NewsBean> mData;
    NewsAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerNewsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_search_news;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecycleView();
    }

    public void initRecycleView() {
        newsRrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRrecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mData = new ArrayList<>();
        mAdapter = new NewsAdapter(mData);
        newsRrecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
            //跳转到网页加载页面
            Intent intent = new Intent(this, MyWebActivity.class);//跳转到新闻网页
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

    @OnClick({R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                searchKey = searchEdit.getText().toString();
                if (TextUtils.isEmpty(searchKey)) {
                    showMessage("请输入搜索内容");
                } else {
                    mPresenter.searchNews(searchKey);

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                break;
        }

    }

    @Override
    public void showNews(List<NewsBean> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }
}
