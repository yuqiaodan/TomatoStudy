package swust.yuqiaodan.tomatoapp.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxRecyclerViewDividerTool;
import com.vondear.rxtool.RxTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMainComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MainPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.UiModel.ModelMainItem;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.MusicActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.PicActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.MyTakePhotoActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.ScanQRActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.ServiceStudy.ServiceStudyActivity;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.MainMenuAdapter;

public class MenuFragment extends BaseFragment<MainPresenter> implements MainContract.View {
    @BindView(R.id.tv_city)
    TextView city;//城市
    @BindView(R.id.tv_weather)
    TextView weather;//天气
    @BindView(R.id.tv_tem)
    TextView tem;//温度
    @BindView(R.id.menu_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.banner)
    BGABanner banner;

    private List<ModelMainItem> mData;
    MainMenuAdapter mAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        RxTool.init(mContext);
        mPresenter.getWeather();
        initRecycleView();
        initBanner();
    }


    void initRecycleView() {
        mData = new ArrayList<>();
        mData.add(new ModelMainItem("音乐", R.drawable.pikachu_sit, MusicActivity.class));
        mData.add(new ModelMainItem("拍照的艺术", R.drawable.pikachu_sit, MyTakePhotoActivity.class));
        mData.add(new ModelMainItem("Retrofit学习", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("扫码的艺术", R.drawable.pikachu_sit, ScanQRActivity.class));
        mData.add(new ModelMainItem("Service学习", R.drawable.pikachu_sit, ServiceStudyActivity.class));
        mData.add(new ModelMainItem("EventBus学习", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mData.add(new ModelMainItem("null", R.drawable.pikachu_sit, null));
        mAdapter=new MainMenuAdapter(mData);
        //设置网格RecycleView 每行为3个 设置格子样式
        mRecyclerView.addItemDecoration(new RxRecyclerViewDividerTool(RxImageTool.dp2px(5f)));
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
            if(mData.get(position).getActivity()!=null){
            Intent intent = new Intent(getContext(), mData.get(position).getActivity());//跳转对应activity
            startActivity(intent);
            }
        });


    }



    void initBanner(){
        banner.setAutoPlayAble(true);//设置为可滚动
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        banner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.banner_p1,
                R.drawable.banner_p2,
                R.drawable.banner_p3,
                R.drawable.banner_p4,
                R.drawable.banner_p5,
                R.drawable.banner_p6);

        banner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
                Intent intent = new Intent(getContext(), PicActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showWeather(WeatherEntity weatherEntity) {//天气接口错误 以后再说
        String tem_value;
        tem_value = weatherEntity.getData().get(0).getTem2() + "~" + weatherEntity.getData().get(0).getTem1();
        tem.setText(tem_value);
        city.setText(weatherEntity.getCity());
        weather.setText(weatherEntity.getData().get(0).getWea());

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
