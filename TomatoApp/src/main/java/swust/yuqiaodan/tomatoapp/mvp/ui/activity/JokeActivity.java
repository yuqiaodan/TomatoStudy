package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.vondear.rxtool.RxTool;
import com.vondear.rxtool.interfaces.OnSimpleListener;
import com.vondear.rxui.view.cardstack.RxCardStackView;
import com.vondear.rxui.view.dialog.RxDialogShapeLoading;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerPicComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.PicContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeShowBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.PicPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.JokeCardAdapter;

//实现接口RxCardStackView.ItemExpendListener
public class JokeActivity extends BaseActivity<PicPresenter> implements PicContract.View, RxCardStackView.ItemExpendListener {
    RxDialogShapeLoading rxDialogShapeLoading;
    @BindView(R.id.toolbar_title)
    TextView title;//界面标题“卡片笑话”
    @BindView(R.id.stackview_main)
    RxCardStackView mStackView;
    @BindView(R.id.button_turn_the_page)
    LinearLayout mButtonContainer;
    @BindView(R.id.button_change_joke_type)
    LinearLayout mButtonChangeType;

    @BindView(R.id.btn_next_group_1)
    Button nextGroup1;
    @BindView(R.id.btn_next_group_2)
    Button nextGroup2;

    @BindView(R.id.btn_text_joke)
    Button switchTextJoke;

    @BindView(R.id.btn_pic_joke)
    Button switchPicJoke;


    Animation animationAppear;
    Animation animationDisappear;

    private JokeCardAdapter mTestStackAdapter;

    public boolean isPicJoke;//当前界面显示的是否的图片笑话

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
        mStackView.setItemExpendListener(this);//可以监听到每张卡片的展开
        mTestStackAdapter = new JokeCardAdapter(this);//实例化adapter
        mStackView.setAdapter(mTestStackAdapter);//设置adapter
        title.setText("卡片笑话");
        isPicJoke = false;

        animationAppear = AnimationUtils.loadAnimation(this, R.anim.anim_card_appear);
        animationDisappear = AnimationUtils.loadAnimation(this, R.anim.anim_card_disappear);
        animationAppear.setFillAfter(true);
        animationDisappear.setFillAfter(true);
        mPresenter.getTextJoke();//请求数据
    }


    @OnClick({R.id.btn_next_group_1, R.id.btn_next_group_2, R.id.btn_text_joke, R.id.btn_pic_joke})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_group_1:
                getNextGroup();
                break;
            case R.id.btn_next_group_2:
                getNextGroup();
                break;
            case R.id.btn_text_joke:

                if (isPicJoke) {
                    isPicJoke = false;
                    getNextGroup();
                } else {
                    showMessage("当前已经是文字笑话");
                }

                break;
            case R.id.btn_pic_joke:

                if (isPicJoke) {
                    showMessage("当前已经是图片笑话");
                } else {
                    isPicJoke = true;
                    getNextGroup();
                }

                break;
        }

    }


    /**
     * 获取下一组笑话
     */
    public void getNextGroup() {

        mStackView.startAnimation(animationDisappear);
        RxTool.delayToDo(1000, new OnSimpleListener() {
            @Override
            public void doSomething() {

                if (isPicJoke) {
                    mPresenter.getPicJoke();
                } else {
                    mPresenter.getTextJoke();
                }

            }
        });


    }

    public void onPreClick(View view) {
        //上一张Card
        mStackView.pre();
    }

    public void onNextClick(View view) {
        //下一站Card
        mStackView.next();
    }

    @Override
    public void onItemExpend(boolean expend) {
        //item展开的时候需要做什么
        mButtonChangeType.setVisibility(expend ? View.GONE : View.VISIBLE);
        mButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);

    }


    //请求成功之后展示数据
    @Override
    public void showJoke(List<JokeShowBean> data) {


        mTestStackAdapter.updateData(data);
        mStackView.startAnimation(animationAppear);

    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.makeText(this, message);
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
    public void showData(List<PicEntity.ResultBean> data) {

    }

    @Override
    public void showMoreData(List<PicEntity.ResultBean> data) {

    }


}
