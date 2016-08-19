package com.zyl.loadstateview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zyl.loadstateview.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyidong on 15/8/9.
 */
public class ActivityCollector {
    private static final String TAG = ActivityCollector.class.getSimpleName();

    private static List<BaseActivity> activities = new ArrayList<>();
    private static LayoutInflater inflater;

    public static void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    public static void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    public static void reStart(BaseActivity fromActivity, Class<?>... toClass) {
        if (toClass != null) {
            for (int i = 0; i < toClass.length; i++) {
                fromActivity.startActivity(new Intent(fromActivity, toClass[i]));
            }
        }
        for (BaseActivity baseActivity : activities) {
            if (!baseActivity.isFinishing()) {
                baseActivity.finish();
            }
        }
    }

    public static View inflate(Context context, int res) {
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        return inflater.inflate(res, null);
    }

    public static int getSreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }


    public static int getSreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 设置View的高
     *
     * @param v
     * @param height
     */
    public static void setViewHeight(View v, int height) {
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) v.getLayoutParams(); // 取控件mGrid当前的布�?参数
        linearParams.height = height;// 当控件的高强制设成height
        v.setLayoutParams(linearParams); // 使设置好的布�?参数应用到控件myGrid
    }

    /**
     * 获取屏幕高度和宽
     *
     * @param mContext
     * @return int[高，宽]
     */
    public static int[] getScreen(Context mContext) {
        DisplayMetrics dm = new DisplayMetrics();
        // 取得窗口属性
        // getWindowManager().getDefaultDisplay().getMetrics(dm);
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 窗口的宽
        int screenWidth = dm.widthPixels;

        // 窗口高度
        int screenHeight = dm.heightPixels;
        int screen[] = {screenHeight, screenWidth};
        return screen;

    }

    public static float dpToPx(final float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    public static int dpToPx(Context c, float dipValue) {
        DisplayMetrics metrics = c.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static int spToPx(Context context, float spValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, metrics);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static void SnackS(View v,String s){
        Snackbar.make(v,s,Snackbar.LENGTH_SHORT).show();
    }
    public static void SnackL(View v,String s){
        Snackbar.make(v,s,Snackbar.LENGTH_LONG).show();
    }

    public static void ToastS(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
    public static void ToastL(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

}
