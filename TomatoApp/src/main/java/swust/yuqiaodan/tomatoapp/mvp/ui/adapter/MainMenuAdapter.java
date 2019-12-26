package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.UiModel.ModelMainItem;

//菜单适配器
public class MainMenuAdapter extends DefaultAdapter<ModelMainItem> {
    public MainMenuAdapter(List<ModelMainItem> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<ModelMainItem> getHolder(@NonNull View v, int viewType) {
        return new MeunHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_recyclerview_main;
    }


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

}
