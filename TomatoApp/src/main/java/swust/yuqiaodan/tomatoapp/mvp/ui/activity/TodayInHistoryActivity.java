package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.vondear.rxui.view.dialog.RxDialogShapeLoading;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMainComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.AstroFortuneBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.ChatBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.TodayHistoryBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MainPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.TodayHistoryAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static swust.yuqiaodan.tomatoapp.app.Constants.HTMLCONTENT;
import static swust.yuqiaodan.tomatoapp.app.Constants.HTMLTITLE;

public class TodayInHistoryActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    RxDialogShapeLoading rxDialogShapeLoading;

    @BindView(R.id.toolbar_title)
    TextView titleDate;

    @BindView(R.id.today_history_list)
    RecyclerView mRecyclerView;

    List<TodayHistoryBean.ResultBean> mData;
    TodayHistoryAdapter mAdapter;


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
        initRecyclerview();
        rxDialogShapeLoading = new RxDialogShapeLoading(this);//加载框dialog
        titleDate.setText(getYear() +"-"+ getMonth() +"-"+ getDay());
        //获取当前的时间
        mPresenter.todayInHistory(String.valueOf(getMonth()),String.valueOf(getDay()));

    }


    public void initRecyclerview() {
        mData=new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new TodayHistoryAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
            Intent intent = new Intent(this, ShowHtmlActivity.class);//跳转到新闻网页
            intent.putExtra(HTMLCONTENT, mData.get(position).getContent());
            intent.putExtra(HTMLTITLE, mData.get(position).getTitle());
            startActivity(intent);
        });

    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.makeText(this, message);
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
        mData.addAll(todayHistoryBean.getResult());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAstroFortune(AstroFortuneBean astroFortuneBean) {

    }

    /**
     * 获取年
     * @return
     */
    public static int getYear(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.YEAR);
    }
    /**
     * 获取月
     * @return
     */
    public static int getMonth(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.MONTH)+1;
    }
    /**
     * 获取日
     * @return
     */
    public static int getDay(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.DATE);
    }



    //服务于其他界面的方法 不启用
    @Override
    public void showWeather(WeatherEntity weatherEntity) {

    }

    @Override
    public void showChatContent(ChatBean chatBean) {

    }

}
