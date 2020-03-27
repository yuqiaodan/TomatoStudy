package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import swust.yuqiaodan.tomatoapp.R;

public class ChannelListAdapter extends DefaultAdapter<String> {

    public Set<String> newsChannelsSelected;
    Map<String, Boolean> ischoose = new HashMap<>();

    public ChannelListAdapter(List<String> infos, Set<String> newsChannelsSelected) {
        super(infos);
        this.newsChannelsSelected = newsChannelsSelected;
    }

    @NonNull
    @Override
    public BaseHolder<String> getHolder(@NonNull View v, int viewType) {
        for (String channel : getInfos()) {
            if (newsChannelsSelected.contains(channel)) {
                ischoose.put(channel, true);
            } else {
                ischoose.put(channel, false);
            }
        }
        ChannelListHolder channelListHolder = new ChannelListHolder(v, ischoose);
        channelListHolder.setIsRecyclable(false);
        return channelListHolder;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_channel_list;
    }


    class ChannelListHolder extends BaseHolder<String> {
        CheckBox channelCheckbox;
        Map<String, Boolean> ischoose;

        public ChannelListHolder(View itemView, Map<String, Boolean> ischoose) {
            super(itemView);
            channelCheckbox = itemView.findViewById(R.id.checkbox_channel);
            this.ischoose = ischoose;
        }

        @Override
        public void setData(@NonNull String data, int position) {
            channelCheckbox.setOnClickListener(this);
            channelCheckbox.setChecked(ischoose.get(data));
            channelCheckbox.setText(data);
        }
    }

}
