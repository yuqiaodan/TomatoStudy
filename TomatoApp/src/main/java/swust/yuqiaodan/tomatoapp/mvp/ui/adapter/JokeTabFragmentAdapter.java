package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class JokeTabFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles=new String[]{"图片","视频"};
    private List<Fragment> fragments;
    public JokeTabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setFragments(List<Fragment> fragments){
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 1) {
            return fragments.get(1);
        }
        else {
            return fragments.get(0);}
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {//设置页面标题
        return mTitles[position];
    }
}
