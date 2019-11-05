package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;
import swust.yuqiaodan.tomatoapp.mvp.ui.Holder.PicHolder;

public class PicAdapter extends DefaultAdapter<PicEntity> {
    public PicAdapter(List<PicEntity> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<PicEntity> getHolder(@NonNull View v, int viewType) {//返回holder对象
        return new PicHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {//返回item的布局文件
        return R.layout.item_pic;
    }
}
