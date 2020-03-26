package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.TodayHistoryBean;
public class TodayHistoryAdapter extends DefaultAdapter<TodayHistoryBean.ResultBean> {
    public TodayHistoryAdapter(List<TodayHistoryBean.ResultBean> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<TodayHistoryBean.ResultBean> getHolder(@NonNull View v, int viewType) {
        return new TodayHistoryHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_today_history;
    }

    public class TodayHistoryHolder extends BaseHolder<TodayHistoryBean.ResultBean>{

        @BindView(R.id.tv_history_years)
        TextView years;
        @BindView(R.id.tv_today_history_time)
        TextView fullTime;
        @BindView(R.id.tv_today_history_title)
        TextView title;



        public TodayHistoryHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull TodayHistoryBean.ResultBean data, int position) {
            years.setText(data.getYear());
            fullTime.setText(data.getYear()+"-"+data.getMonth()+"-"+data.getDay());
            title.setText(data.getTitle());
        }
    }

}
