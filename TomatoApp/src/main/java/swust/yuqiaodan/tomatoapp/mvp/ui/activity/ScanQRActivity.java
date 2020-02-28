package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.zxing.Result;
import java.util.ArrayList;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;

public class ScanQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    FrameLayout contentFrame;//放置ZXingScannerView
    ZXingScannerView zBarScannerView;//框架内部的扫码view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        //初始化一些界面组件
        contentFrame=findViewById(R.id.content_frame);
        //初始化扫码view
        zBarScannerView=new ZXingScannerView(this);
        //添加zBarScannerView到FrameLayout
        contentFrame.addView(zBarScannerView);
        //添加扫码结果回调
        zBarScannerView.setResultHandler(this);
        //自动对焦
        zBarScannerView.setAutoFocus(true);
        //启动相机
        zBarScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        /**
         * result就是扫码的结果
         * result.getText()可以获取到扫码得到的str
         * 这里可以按业务需求做正则判断
         * 这里可以做扫码完成后需要做的其他操作
         * 比如发出网络请求，展示结果等等
         *
         * */

        //resultList.add(result.getText());//暂存扫码结果

        //scanAgain();//重新开始下一次扫码

        //获取到扫码结果 然后传递回到main
        Intent intent = new Intent();
        intent.putExtra(Constants.QRResult, result.getText());
        setResult(RESULT_OK, intent);
        finish();

    }
    public void scanAgain(){//重新开始下一次扫码
        zBarScannerView.resumeCameraPreview(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zBarScannerView.stopCamera();//关闭相机
    }
}
