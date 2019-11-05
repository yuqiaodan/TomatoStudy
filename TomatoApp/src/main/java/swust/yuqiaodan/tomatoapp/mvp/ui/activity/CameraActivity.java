package swust.yuqiaodan.tomatoapp.mvp.ui.activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import swust.yuqiaodan.tomatoapp.R;

public class CameraActivity extends AppCompatActivity {
    //CameraView对象
    private CameraView camera;
    //shutter快门按钮
    private Button shutter;
    //保存图片的文件夹地址
    String dirpath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "formbackup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //实例化对象
        camera = findViewById(R.id.camera);
        shutter = findViewById(R.id.shutter);
        //设置快门的监听
        shutter.setOnClickListener(view -> takephoto());
    }
    //拍照的方法
    private void takephoto() {
        //调用capturePicture()进行拍照
        camera.capturePicture();
        //camera.takePicture(); //2.3.1修改的拍照方法
    }

    private void initCamera() {
        //初始化相机 设置相机拍照后的处理
        camera.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);
                //通过jpeg获取图片的旋转角度orientation jpeg中包含了照片角度信息
                int orientation = Exif.getOrientation(jpeg);
                //通过jpeg获取bitmap对象
                Bitmap bitmap = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
                //将bitmap按解析出来的角度进行旋转orientation
                //有些机型会将照片旋转90度
                Matrix m = new Matrix();
                m.postRotate(orientation);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);

                //进行拍摄成功后的处理
                takeSuccess(bitmap);
            }

        });

    }


    public void takeSuccess(Bitmap bitmap) {//拍照成功后进行保存
        //创建文件,要保存png，这里后缀就是png，要保存jpg，后缀就用jpg
        //以拍摄时间为图片名进行命名
        File file = new File(dirpath, System.currentTimeMillis() + ".jpg");
        try {
            //文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            //压缩图片，如果要保存png，就用Bitmap.CompressFormat.PNG，要保存jpg就用Bitmap.CompressFormat.JPEG,质量是100%，表示不压缩
            /**
             * quality:100
             * 为不压缩
             * 对于某些性能较低的手机 下一步写入时耗时较长
             * 考虑压缩多少合适
             * */
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);

            //写入，这里会卡顿，因为图片较大
            fileOutputStream.flush();
            //记得要关闭写入流
            fileOutputStream.close();
            //成功的提示，写入成功后，请在对应目录中找保存的图片
            Toast.makeText(this, "拍照成功", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //失败的提示
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            //失败的提示
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        bitmap.recycle();//及时回收
    }


    //动态权限申请
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean valid = true;
        for (int grantResult : grantResults) {
            valid = valid && grantResult == PackageManager.PERMISSION_GRANTED;
        }
        if (valid && !camera.isStarted()) {
            camera.start();
        }
    }

    //根据activity生命周期 将来启动或者销毁相机
    @Override
    protected void onResume() {
        super.onResume();
        camera.start();

        initCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁相机释放资源
        camera.destroy();
    }
}
