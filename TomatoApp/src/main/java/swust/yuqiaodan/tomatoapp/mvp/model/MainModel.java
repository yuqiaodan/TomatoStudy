package swust.yuqiaodan.tomatoapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import io.reactivex.Observable;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.api.Api;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuRobotQaBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.TodayHistoryBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/20/2019 16:01
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<WeatherEntity> getWeather(String version, String city) {
        return mRepositoryManager.obtainRetrofitService(Api.class).getWeather(version, city, "39719144", "AKJr6R6X");
    }

    @Override
    public Observable<JiSuRobotQaBean> chatWithRobot(String question) {
        return mRepositoryManager.obtainRetrofitService(Api.class).chatWithRobot(Constants.JISU_APP_KEY, question);

    }


    /**
     * 历史上的今天
     * @param month 月份
     * @param day   日期
     * @return
     */
    @Override
    public Observable<TodayHistoryBean> todayInHistory(String month, String day) {
        return mRepositoryManager.obtainRetrofitService(Api.class).todayInHistory(Constants.JISU_APP_KEY, month, day);
    }
}