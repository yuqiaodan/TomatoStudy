package swust.yuqiaodan.tomatoapp.mvp.ui.Holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.UiModel.ModelMainItem;

public class MeunHolder extends BaseHolder<ModelMainItem> {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.meun_imageView)
    ImageView meun_imageView;

    public MeunHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(@NonNull ModelMainItem data, int position) {
        tv_name.setText(data.getName());
        //meun_imageView.setImage();
    }
}
