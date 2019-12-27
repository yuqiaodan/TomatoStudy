package swust.yuqiaodan.tomatoapp.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.app.utils.RxUtils;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuSearchNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.OpenApiNewsBean;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/20/2019 16:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class NewsPresenter extends BasePresenter<NewsContract.Model, NewsContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public NewsPresenter(NewsContract.Model model, NewsContract.View rootView) {
        super(model, rootView);
    }



    public void searchNews(String searchKey){

        RxUtils.apply(mModel.searchNews(searchKey), mRootView)
                .subscribe(new ErrorHandleSubscriber<JiSuSearchNewsBean>(mErrorHandler) {
                    @Override
                    public void onNext(JiSuSearchNewsBean jiSuSearchNewsBean) {

                        List<NewsBean> showData = new ArrayList<>();
                        for (JiSuSearchNewsBean.ResultBean.ListBean listBean : jiSuSearchNewsBean.getResult().getList()) {

                            NewsBean newsBean = new NewsBean();
                            newsBean.setUrl(listBean.getUrl());
                            newsBean.setImageUrl(listBean.getPic());
                            newsBean.setTime(listBean.getTime());
                            newsBean.setTitle(listBean.getTitle());
                            //OpenApi获取的新闻默认来源：网易新闻
                            newsBean.setSource(listBean.getSrc());
                            //这个接口没有直接返回html
                            newsBean.setHtmlContent(listBean.getContent());
                            showData.add(newsBean);
                        }

                        mRootView.showNews(showData);
                    }
                });



    }

    public void getJoke(String type, boolean isRefresh) {
        RxUtils.apply(mModel.getJoke(0, 10, type), mRootView)
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<JokeEntity>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<JokeEntity>> listBaseResponse) {

                    }
                });

    }


    public void getNews(String channel, int page) {
        if (Constants.REALTIME.equals(channel)) {//因为数据来源接口不同 所以“实时”作为一个单独的频道
            RxUtils.apply(mModel.getWangYiNews(page, 10), mRootView)
                    .subscribe(new ErrorHandleSubscriber<BaseResponse<List<OpenApiNewsBean>>>(mErrorHandler) {
                        @Override//请求完毕后显示数据
                        public void onNext(BaseResponse<List<OpenApiNewsBean>> listBaseResponse) {
                            List<NewsBean> showData = new ArrayList<>();
                            //进行新闻包装
                            for (OpenApiNewsBean openApiNewsBean : listBaseResponse.getResult()) {
                                NewsBean newsBean = new NewsBean();
                                newsBean.setUrl(openApiNewsBean.getPath());
                                newsBean.setImageUrl(openApiNewsBean.getImage());
                                newsBean.setTime(openApiNewsBean.getPasstime());
                                newsBean.setTitle(openApiNewsBean.getTitle());
                                //OpenApi获取的新闻默认来源：网易新闻
                                newsBean.setSource(Constants.getSource());
                                //这个接口没有直接返回html
                                newsBean.setHtmlContent("");
                                showData.add(newsBean);
                            }
                            mRootView.showNews(showData);
                        }
                    });
            return;
        }

        RxUtils.apply(mModel.getJiSuNews(channel, page), mRootView)
                .subscribe(new ErrorHandleSubscriber<JiSuNewsBean>(mErrorHandler) {
                    @Override
                    public void onNext(JiSuNewsBean jiSuNewsBean) {
                        List<NewsBean> showData = new ArrayList<>();
                        for (JiSuNewsBean.ResultBean.ListBean listBean : jiSuNewsBean.getResult().getList()) {
                            NewsBean newsBean = new NewsBean();
                            newsBean.setUrl(listBean.getUrl());
                            newsBean.setImageUrl(listBean.getPic());
                            newsBean.setTime(listBean.getTime());
                            newsBean.setTitle(listBean.getTitle());
                            //OpenApi获取的新闻默认来源：网易新闻
                            newsBean.setSource(listBean.getSrc());
                            //这个接口没有直接返回html
                            newsBean.setHtmlContent(listBean.getContent());
                            showData.add(newsBean);
                        }
                        mRootView.showNews(showData);
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
