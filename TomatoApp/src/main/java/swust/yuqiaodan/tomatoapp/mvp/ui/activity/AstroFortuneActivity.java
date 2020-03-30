package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;
import com.vondear.rxui.view.dialog.RxDialogShapeLoading;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMainComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.AstroFortuneBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.ChatBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.TodayHistoryBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MainPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static swust.yuqiaodan.tomatoapp.app.Constants.ASTRO;

//星座运势界面
public class AstroFortuneActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    RxDialogShapeLoading rxDialogShapeLoading;
    @BindView(R.id.toolbar_title)
    TextView title;//界面标题“星座运势”
    @BindView(R.id.layout_astro_information)
    LinearLayout astroInformation;//星座基本信息模块 点击切换星座
    @BindView(R.id.img_astro_icon)
    ImageView astroIcon;//星座图标
    @BindView(R.id.tv_astro_name)
    TextView astroName;//星座名称
    @BindView(R.id.tv_astro_english_name)
    TextView astroNameEn;//星座英文名
    @BindView(R.id.tv_astro_time)
    TextView astroTime;//星座对应的时间

    //今天的运势占卜模块 点击查看dialog详情打分
    @BindView(R.id.layout_fortune_today)
    LinearLayout fortuneToday;
    @BindView(R.id.tv_today_content)
    TextView todayContent;
    @BindView(R.id.tv_astro_today_time)
    TextView todayTime;


    //明天的运势占卜模块 点击查看dialog详情打分
    @BindView(R.id.layout_fortune_tomorrow)
    LinearLayout fortuneTomorrow;
    @BindView(R.id.tv_tomorrow_content)
    TextView tomorrowContent;
    @BindView(R.id.tv_astro_tomorrow_time)
    TextView tomorrowTime;

    //更多信息占卜模块
    @BindView(R.id.tv_fortune_week)
    TextView fortuneWeek;
    @BindView(R.id.tv_fortune_month)
    TextView fortuneMonth;
    @BindView(R.id.tv_fortune_year)
    TextView fortuneYear;

    String astro;//界面默认选择的是双鱼座
    int astroId;

    private AlertDialog.Builder builder;

    String todayFortuneMsg;
    String tomorrowFortuneMsg;

    String weekFortuneMsg;
    String monthFortuneMsg;
    String yearFortuneMsg;


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
        return R.layout.activity_horoscope;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        title.setText("星座运势");
        rxDialogShapeLoading = new RxDialogShapeLoading(this);//加载框dialog

        todayContent.setMovementMethod(new ScrollingMovementMethod());//设置textview可以滑动
        todayContent.setScrollbarFadingEnabled(true);//设置scrollbar不一直显示
        tomorrowContent.setMovementMethod(new ScrollingMovementMethod());//设置textview可以滑动
        tomorrowContent.setScrollbarFadingEnabled(true);//设置scrollbar不一直显示

        //读取星座信息
        astro = DataHelper.getStringSF(this, ASTRO);
        if (TextUtils.isEmpty(astro)) {
            astro = "白羊座";//默认白羊座
            DataHelper.setStringSF(this, ASTRO, astro);//重新保存信息
        }
        //填写星座名称 星座图标
        initAstroInformation();
        //astroId+1用于请求接口 接口要求的
        mPresenter.getAstroFortune(String.valueOf(astroId + 1), getTodayTime());

    }


    //展示占卜的数据
    @Override
    public void showAstroFortune(AstroFortuneBean astroFortuneBean) {

        AstroFortuneBean.ResultBean data = astroFortuneBean.getResult();
        todayContent.setText(data.getToday().getPresummary());
        todayTime.setText(data.getToday().getDate());
        tomorrowContent.setText(data.getTomorrow().getPresummary());
        tomorrowTime.setText(data.getTomorrow().getDate());

        AstroFortuneBean.ResultBean.TodayBean todayBean=data.getToday();
        AstroFortuneBean.ResultBean.TomorrowBean tomorrowBean=data.getTomorrow();
        AstroFortuneBean.ResultBean.WeekBean weekBean=data.getWeek();
        AstroFortuneBean.ResultBean.MonthBean monthBean=data.getMonth();
        AstroFortuneBean.ResultBean.YearBean yearBean=data.getYear();

        todayFortuneMsg=todayBean.getDate()+"\n"
                +"今日运势概述："+todayBean.getPresummary()+"\n"+"\n"
                +"幸运颜色："+todayBean.getColor()+"\n"
                +"幸运数字："+todayBean.getNumber()+"\n"
                +"综合运势得分："+todayBean.getSummary()+"\n"
                +"财运运势得分："+todayBean.getMoney()+"\n"
                +"工作运势得分："+todayBean.getCareer()+"\n"
                +"爱情运势得分："+todayBean.getLove()+"\n"
                +"健康运势得分："+todayBean.getHealth();

        tomorrowFortuneMsg=tomorrowBean.getDate()+"\n"
                +"明日运势日概述："+tomorrowBean.getPresummary()+"\n"+"\n"
                +"幸运颜色："+tomorrowBean.getColor()+"\n"
                +"幸运数字："+tomorrowBean.getNumber()+"\n"
                +"综合运势得分："+tomorrowBean.getSummary()+"\n"
                +"财运运势得分："+tomorrowBean.getMoney()+"\n"
                +"工作运势得分："+tomorrowBean.getCareer()+"\n"
                +"爱情运势得分："+tomorrowBean.getLove()+"\n"
                +"健康运势得分："+tomorrowBean.getHealth();

        weekFortuneMsg="时间："+weekBean.getDate()+"\n"
                +"财运："+weekBean.getMoney()+"\n"+"\n"
                +"工作："+weekBean.getCareer()+"\n"+"\n"
                +"爱情："+weekBean.getLove()+"\n"+"\n"
                +"健康："+weekBean.getHealth();

        monthFortuneMsg="时间："+monthBean.getDate()+"\n"
                +"运势概述："+monthBean.getSummary()+"\n"+"\n"
                +"财运："+monthBean.getMoney()+"\n"+"\n"
                +"工作："+monthBean.getCareer()+"\n"+"\n"
                +"爱情："+monthBean.getLove()+"\n"+"\n"
                +"健康："+monthBean.getHealth();

        yearFortuneMsg="时间："+yearBean.getDate()+"\n"
                +"运势概述："+yearBean.getSummary()+"\n"+"\n"
                +"财运："+yearBean.getMoney()+"\n"+"\n"
                +"工作："+yearBean.getCareer()+"\n"+"\n"
                +"爱情："+yearBean.getLove();
    }


    //填写星座信息模块
    public void initAstroInformation() {
        astroId = Constants.allStars.indexOf(astro);
        astroName.setText(astro);
        astroNameEn.setText(Constants.allStarsEnglishName.get(astroId));
        astroTime.setText(Constants.allStarsTime.get(astroId));
        Glide.with(this)
                .load(Constants.allStarsIcon.get(astroId))
                .thumbnail(0.5f).
                into(astroIcon);
    }


    @OnClick({R.id.layout_astro_information,
            R.id.layout_fortune_today,
            R.id.layout_fortune_tomorrow,
            R.id.tv_fortune_week,
            R.id.tv_fortune_month,
            R.id.tv_fortune_year,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_astro_information:
                //选择切换星座
                showAstroList();
                break;
            case R.id.layout_fortune_today:
                showFortuneDialog(todayFortuneMsg);
                break;
            case R.id.layout_fortune_tomorrow:
                showFortuneDialog(tomorrowFortuneMsg);
                break;
            case R.id.tv_fortune_week:
                showFortuneDialog(weekFortuneMsg);
                break;
            case R.id.tv_fortune_month:
                showFortuneDialog(monthFortuneMsg);
                break;
            case R.id.tv_fortune_year:
                showFortuneDialog(yearFortuneMsg);
                break;
        }

    }


    /**
     * @param msg Dialog展示信息的内容
     */
    public void showFortuneDialog(String msg){
        builder = new AlertDialog.Builder(this).setIcon(Constants.allStarsIcon.get(astroId)).setTitle(astro)
                .setMessage(msg).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        //修改透明度 和 宽高（但是修改宽高好像是失效了）?
        AlertDialog dialog=builder.create();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //lp.height = 300;//高度
        lp.alpha = 0.8f; // 透明度
        dialogWindow.setAttributes(lp);
        dialog.show();
    }




    /**
     * 列表 dialog
     */
    public void showAstroList() {
        final String[] items = {
                "白羊座 3-21~4-19",
                "金牛座 4-20~5-20",
                "双子座 5-21~6-21",
                "巨蟹座 6-22~7-22",
                "狮子座 7-23~8-22",
                "处女座 8-23~9-22",
                "天秤座 9-23~10-23",
                "天蝎座 10-24~11-22",
                "射手座 11-23~12-21",
                "摩羯座 12-22~1-19",
                "水瓶座 1-20~2-18",
                "双鱼座 2-19~3-20"};

        builder = new AlertDialog.Builder(this).setIcon(R.drawable.stars_icon)
                .setTitle("星座选择")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        astroId = i;
                        astro = Constants.allStars.get(i);

                        DataHelper.setStringSF(AstroFortuneActivity.this, ASTRO, astro);//重新保存信息
                        initAstroInformation();
                        mPresenter.getAstroFortune(String.valueOf(astroId + 1), getTodayTime());
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        builder.create().show();
    }


    //以下四个方法服务于其他界面 不启用
    @Override
    public void showWeather(WeatherEntity weatherEntity) {

    }

    @Override
    public void showChatContent(ChatBean chatBean) {

    }

    @Override
    public void showTodayInHistory(TodayHistoryBean todayHistoryBean) {

    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.makeText(this, message);
    }

    /**
     * 获取时间
     *
     * @return 2019-03-9
     */
    public static String getTodayTime() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.YEAR) + "-" + (cd.get(Calendar.MONTH) + 1) + "-" + cd.get(Calendar.DATE);
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
}
