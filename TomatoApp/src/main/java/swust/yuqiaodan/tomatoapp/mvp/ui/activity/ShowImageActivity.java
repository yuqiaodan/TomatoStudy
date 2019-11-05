package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxTool;
import com.vondear.rxui.view.scaleimage.ImageSource;
import com.vondear.rxui.view.scaleimage.RxScaleImageView;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.utils.RxUtils;

public class ShowImageActivity extends BaseActivity {
    RxScaleImageView imageView;
    private SimpleTarget target;
    String imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        RxTool.init(this);

        initImageView();

        //imageView.setImage(ImageSource.bitmap(bitmap));
    }

void initImageView(){
    imageView = findViewById(R.id.Rx_imageView);
    imageurl=getIntent().getStringExtra("image");

    target=new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            imageView.setImage(ImageSource.bitmap(bitmap));
        }
    };

    Glide
            .with( this )
            .asBitmap()// could be an issue!
            .load( imageurl )
            .into( target );

}


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
