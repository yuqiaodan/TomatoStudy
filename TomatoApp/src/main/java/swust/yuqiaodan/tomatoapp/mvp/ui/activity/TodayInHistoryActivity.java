package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.vondear.rxui.view.dialog.RxDialogShapeLoading;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMainComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.ChatBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.TodayHistoryBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MainPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class TodayInHistoryActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    RxDialogShapeLoading rxDialogShapeLoading;

    @BindView(R.id.title_day)
    TextView titleDate;
    @BindView(R.id.today_history_list)
    RecyclerView todayHistoryList;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_today_in_history;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }



    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.makeText(this,message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
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


    //显示结果
    @Override
    public void showTodayInHistory(TodayHistoryBean todayHistoryBean) {

    }



    //服务于其他界面的方法 不启用
    @Override
    public void showWeather(WeatherEntity weatherEntity) {

    }

    @Override
    public void showChatContent(ChatBean chatBean) {

    }

}
