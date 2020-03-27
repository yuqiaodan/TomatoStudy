package swust.yuqiaodan.tomatoapp.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jess.arms.utils.DataHelper;

import java.util.HashSet;
import java.util.Set;

import static com.jess.arms.utils.DataHelper.SP_NAME;

//DataHelper保存数据类型的扩展 目前主要增加Set类型（保存链表）

public class DataHelperExtension {

    private static SharedPreferences mSharedPreferences;

    //保存Set类型数据
    public static Set<String> getSetSF(Context context, String key) {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        //不直接返回 SharedPreferences中读取到的Set数据 而是通过new HashSet<>来创建一个副本进行操作
        //因为如果是同样的对象进行add 和 remove后 会导致混淆
        return new HashSet<>(mSharedPreferences.getStringSet(key, new HashSet<>()));
    }

    //获取本地保存的Set类型数据
    public static void setSetSF(Context context, String key, Set<String> value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }


        mSharedPreferences.edit().putStringSet(key, value).apply();



    }

}
