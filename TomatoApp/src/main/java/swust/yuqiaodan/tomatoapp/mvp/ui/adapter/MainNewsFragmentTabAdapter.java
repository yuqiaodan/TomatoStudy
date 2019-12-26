package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

//首页新闻fragment适配器
public class MainNewsFragmentTabAdapter extends FragmentPagerAdapter {
    private String[] mTitles=new String[]{"头条",
            "新闻",
            "国内",
            "国际",
            "政治",
            "财经",};
    private List<Fragment> fragments;

    public MainNewsFragmentTabAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setFragments(List<Fragment> fragments){
        this.fragments=fragments;
    }

    public void setTitles(String[] mTitles){
        this.mTitles=mTitles;
    }

    @Override
    public Fragment getItem(int i) {

            return fragments.get(i);

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
