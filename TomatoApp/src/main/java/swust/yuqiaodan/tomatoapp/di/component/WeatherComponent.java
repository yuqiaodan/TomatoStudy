package swust.yuqiaodan.tomatoapp.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import swust.yuqiaodan.tomatoapp.di.module.WeatherModule;
import swust.yuqiaodan.tomatoapp.mvp.contract.WeatherContract;

import com.jess.arms.di.scope.FragmentScope;

import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.WeatherFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/20/2019 16:58
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = WeatherModule.class, dependencies = AppComponent.class)
public interface WeatherComponent {
    void inject(WeatherFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        WeatherComponent.Builder view(WeatherContract.View view);

        WeatherComponent.Builder appComponent(AppComponent appComponent);

        WeatherComponent build();
    }
}