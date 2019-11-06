package swust.yuqiaodan.tomatoapp.mvp.ui.activity.ServiceStudy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.R;

public class ServiceStudyActivity extends BaseActivity {

    @BindView(R.id.startService)
    Button start;
    @BindView(R.id.stopService)
    Button stop;
    Intent serviecIntent;


    @OnClick({R.id.startService,R.id.stopService})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.startService:
                Log.d("按钮点击", "startService");

                startService(serviecIntent);
                break;
            case R.id.stopService:
                stopService(serviecIntent);
                break;

        }


    }


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        serviecIntent = new Intent(this, MyFirstService.class);
        return R.layout.activity_service_study;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
