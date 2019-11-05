package swust.yuqiaodan.tomatoapp.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.NewsModel;


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
@Module
public abstract class NewsModule {

    @Binds
    abstract NewsContract.Model bindNewsModel(NewsModel model);
}