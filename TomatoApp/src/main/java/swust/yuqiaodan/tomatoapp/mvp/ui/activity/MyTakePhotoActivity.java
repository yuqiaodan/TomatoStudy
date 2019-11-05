package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.R;
import timber.log.Timber;

public class MyTakePhotoActivity extends BaseActivity implements TakePhoto.TakeResultListener,InvokeListener{
    @BindView(R.id.takephoto1)
    Button takephoto;
    @BindView(R.id.takephoto2)
    Button takephoto_activity;
    @BindView(R.id.photo_image)
    ImageView photo_image;
    @BindView(R.id.image_local)
    TextView image_local;
    InvokeParam invokeParam = null;
    TakePhoto takePhoto;
    String TAG = "TakePhotoActivity_image";
    String dirpath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "formbackup";
    String imagepath="";
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_take_photo;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }


    @OnClick({R.id.takephoto1, R.id.takephoto2})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.takephoto1:
                Log.d(TAG, "1点击");
                startTakephoto();
                break;
            case R.id.takephoto2:
                Intent intent=new Intent(this,CameraActivity.class);
                startActivity(intent);
                Log.d(TAG, "2点击");
                break;

        }

    }





    public void startTakephoto() {
        File file = new File(dirpath, String.valueOf(System.currentTimeMillis()) + ".jpg");
        Log.d(TAG,"imageUri"+file.getAbsolutePath());
        imagepath=file.getAbsolutePath();
        Uri imageUri = Uri.fromFile(file);
        Log.d(TAG,"imageUri"+imageUri);
        getTakePhoto().onPickFromCapture(imageUri);
    }


    @Override
    public void takeSuccess(TResult result) {
        //String path=result.getImage().getOriginalPath();
        Log.d(TAG,imagepath);
        if (!TextUtils.isEmpty(imagepath)){
            Glide.with(this)
                    .load(new File(imagepath))
                    .into(photo_image);
        }



    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.i(TAG, "拍照失败:" + msg);
    }

    @Override
    public void takeCancel() {
        Log.i(TAG, "拍照取消:");
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
