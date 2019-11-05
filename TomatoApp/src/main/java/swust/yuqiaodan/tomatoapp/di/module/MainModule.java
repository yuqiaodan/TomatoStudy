package swust.yuqiaodan.tomatoapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.MainModel;


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
@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);
}