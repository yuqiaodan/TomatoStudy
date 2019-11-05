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
import swust.yuqiaodan.tomatoapp.mvp.contract.PicContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseRedponseData;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/26/2019 18:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class PicPresenter extends BasePresenter<PicContract.Model, PicContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public PicPresenter(PicContract.Model model, PicContract.View rootView) {
        super(model, rootView);
    }

    public void getPic(boolean isRefresh){
        RxUtils.apply(mModel.getPic(),mRootView)
                .subscribe(new ErrorHandleSubscriber<BaseRedponseData<List<PicEntity>>>(mErrorHandler){
                    @Override
                    public void onNext(BaseRedponseData<List<PicEntity>> listBaseResponse) {
                       if(isRefresh)
                       {mRootView.showData(listBaseResponse.getData());}
                       else {mRootView.showMoreData(listBaseResponse.getData());}
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
