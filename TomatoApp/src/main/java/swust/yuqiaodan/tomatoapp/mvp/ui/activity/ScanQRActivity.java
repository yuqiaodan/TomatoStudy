package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.zxing.Result;
import java.util.ArrayList;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import swust.yuqiaodan.tomatoapp.R;

public class ScanQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    FrameLayout content_frame;//放置ZXingScannerView
    TextView cancel_scan;//展示扫码得到的结果
    ArrayList<String> resultList;//存放多次扫描的结果
    ZXingScannerView zBarScannerView;//框架内部的扫码view


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        //初始化一些界面组件
        resultList=new ArrayList<>();
        content_frame=findViewById(R.id.content_frame);
        cancel_scan=findViewById(R.id.cancel_scan);

        //初始化扫码view
        zBarScannerView=new ZXingScannerView(this);
        //添加zBarScannerView到FrameLayout
        content_frame.addView(zBarScannerView);
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

        resultList.add(result.getText());//暂存扫码结果
        cancel_scan.setText(result.getText()+"("+resultList.size()+")");//展示结果
        scanAgain();//重新开始下一次扫码

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
