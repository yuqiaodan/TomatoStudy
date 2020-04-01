package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.vondear.rxtool.RxTool;
import com.vondear.rxui.view.cardstack.RxCardStackView;
import com.vondear.rxui.view.dialog.RxDialogShapeLoading;

import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerPicComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.PicContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.PicPresenter;

public class JokeActivity extends BaseActivity<PicPresenter> implements PicContract.View,RxCardStackView.ItemExpendListener{
    RxDialogShapeLoading rxDialogShapeLoading;
    @BindView(R.id.stackview_main)
    RxCardStackView mStackView;
    @BindView(R.id.button_container)
    LinearLayout mButtonContainer;
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
        return R.layout.activity_joke;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        RxTool.init(this);
        rxDialogShapeLoading = new RxDialogShapeLoading(this);//加载框dialog

        
        //实现接口RxCardStackView.ItemExpendListener
        mStackView.setItemExpendListener(this);


    }

    @Override
    public void showData(List<PicEntity.ResultBean> data) {

    }

    @Override
    public void showMoreData(List<PicEntity.ResultBean> data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
    @Override
    public void showLoading() {
        //显示加载框
        rxDialogShapeLoading.show();
    }

    @Override
    public void hideLoading() {
        //隐藏加载框
        rxDialogShapeLoading.cancel();
    }

    @Override
    public void onItemExpend(boolean expend) {

    }
}
