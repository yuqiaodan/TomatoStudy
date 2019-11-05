package swust.yuqiaodan.tomatoapp.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.functions.Consumer;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import swust.yuqiaodan.tomatoapp.app.utils.RxUtils;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
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
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    @SuppressLint("CheckResult")
    public void getWeather(){
        RxUtils.apply(mModel.getWeather("v1","成都"),mRootView).subscribe(new Consumer<WeatherEntity>() {
            @Override
            public void accept(WeatherEntity weatherEntity) throws Exception {

                mRootView.showWeather(weatherEntity);
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
