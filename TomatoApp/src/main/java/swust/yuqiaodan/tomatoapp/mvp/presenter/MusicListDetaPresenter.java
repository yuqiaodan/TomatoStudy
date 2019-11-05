package swust.yuqiaodan.tomatoapp.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import swust.yuqiaodan.tomatoapp.app.utils.RxUtils;
import swust.yuqiaodan.tomatoapp.mvp.contract.MusicListDetaContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.MusicBean.MusicSearchBean;


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
@ActivityScope
public class MusicListDetaPresenter extends BasePresenter<MusicListDetaContract.Model, MusicListDetaContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MusicListDetaPresenter(MusicListDetaContract.Model model, MusicListDetaContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }




    public void getSearchMusic(String name){
        RxUtils.apply(mModel.getSearchMusic(name),mRootView)
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<MusicSearchBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<MusicSearchBean>> listBaseResponse) {

                        mRootView.showSearchMusic(listBaseResponse.getResult());

                    }
                });
    }


}
