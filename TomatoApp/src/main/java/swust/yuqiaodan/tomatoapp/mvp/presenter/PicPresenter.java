package swust.yuqiaodan.tomatoapp.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import swust.yuqiaodan.tomatoapp.app.utils.RxUtils;
import swust.yuqiaodan.tomatoapp.mvp.contract.PicContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseRedponseData;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuBaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuPicJokeBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuTextJokeBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeShowBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;


/**
 * Pic本来是用于图片的请求的 但如你所见 现在完整的一套MVP架构
 * 只请求一个接口 只服务于一个界面 实在是有点杀鸡用户牛刀
 * 所以现在将Pic的整个MVP架构重新定义功能 服务于Menu菜单的扩展界面 比如笑话，脑筋急转弯等等
 * （随着服务的界面增多 再考虑新建MVP架构）
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

    public void getPic(boolean isRefresh) {
        RxUtils.apply(mModel.getPic(), mRootView)
                .subscribe(new ErrorHandleSubscriber<PicEntity>(mErrorHandler) {
                    @Override
                    public void onNext(PicEntity picEntity) {
                        if (isRefresh) {
                            mRootView.showData(picEntity.getResult());
                        } else {
                            mRootView.showMoreData(picEntity.getResult());
                        }
                    }
                });

    }


    public void getTextJoke() {
        RxUtils.apply(mModel.getTextJoke(), mRootView)
                .subscribe(new ErrorHandleSubscriber<JiSuBaseResponse<JiSuTextJokeBean>>(mErrorHandler) {
                    @Override
                    public void onNext(JiSuBaseResponse<JiSuTextJokeBean> jiSuTextJokeBean) {
                        List<JokeShowBean> data = new ArrayList<>();
                        for (JiSuTextJokeBean.ListBean bean : jiSuTextJokeBean.getResult().getList()) {
                            JokeShowBean jokeShowBean = new JokeShowBean(bean.getContent(), "", bean.getUrl(), false);
                            data.add(jokeShowBean);
                        }
                        mRootView.showJoke(data);
                    }
                });
    }

    public void getPicJoke() {
        RxUtils.apply(mModel.getPicJoke(), mRootView)
                .subscribe(new ErrorHandleSubscriber<JiSuBaseResponse<JiSuPicJokeBean>>(mErrorHandler) {
                    @Override
                    public void onNext(JiSuBaseResponse<JiSuPicJokeBean> jiSuPicJokeBean) {
                        List<JokeShowBean> data = new ArrayList<>();
                        for (JiSuPicJokeBean.ListBean bean : jiSuPicJokeBean.getResult().getList()) {
                            JokeShowBean jokeShowBean = new JokeShowBean(bean.getContent(), bean.getPic(), bean.getUrl(), true);
                            data.add(jokeShowBean);
                        }
                        mRootView.showJoke(data);
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
