package swust.yuqiaodan.tomatoapp.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import swust.yuqiaodan.tomatoapp.di.module.PicModule;
import swust.yuqiaodan.tomatoapp.mvp.contract.PicContract;

import com.jess.arms.di.scope.ActivityScope;

import swust.yuqiaodan.tomatoapp.mvp.ui.activity.PicActivity;


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
@Component(modules = PicModule.class, dependencies = AppComponent.class)
public interface PicComponent {
    void inject(PicActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        PicComponent.Builder view(PicContract.View view);

        PicComponent.Builder appComponent(AppComponent appComponent);

        PicComponent build();
    }
}