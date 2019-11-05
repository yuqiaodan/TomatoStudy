package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.Holder.MeunHolder;
import swust.yuqiaodan.tomatoapp.mvp.ui.UiModel.ModelMainItem;

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
}
