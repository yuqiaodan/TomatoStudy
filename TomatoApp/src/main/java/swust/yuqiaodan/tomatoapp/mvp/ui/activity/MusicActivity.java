package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMusicComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MusicContract;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MusicPresenter;

import swust.yuqiaodan.tomatoapp.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/02/2019 11:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MusicActivity extends BaseActivity<MusicPresenter> implements MusicContract.View {

    @BindView(R.id.music_rank)
    RecyclerView music_rank;
    @BindView(R.id.music_station)
    RecyclerView music_station;
    @BindView(R.id.music_edit)
    EditText music_edit;
    @BindView(R.id.music_search)
    TextView music_search;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMusicComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_music; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    @OnClick(R.id.music_search)
    void Click(View view) {
        switch (view.getId()) {
            case R.id.music_search:
                //判断输入字符是否为空
                //Log.d("输入内容", String.valueOf(TextUtils.isEmpty("")));

                if (TextUtils.isEmpty(music_edit.getText())) {
                    showMessage("请输入搜索内容");
                } else {
                    Intent intent = new Intent(this, MusicListDetaActivity.class);
                    intent.putExtra("search", String.valueOf(music_edit.getText()));
                    startActivity(intent);
                }

                break;

        }

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
}
