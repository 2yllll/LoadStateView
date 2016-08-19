package com.zyl.library.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by yuyidong on 15-4-1.
 */
public class SPUtils {

	private static final String SETTING_NAME = "Setting";

	private static final String FIRST_TIME = "first_time";
	private static final boolean FIRST_TIME_DEFAULT = true;

	private static final String INTRODUCE = "introduce";
	private static final String INTRODUCE_1_2_0 = "introduce 1.2.0";
	private static final boolean INTRODUCE_DEFAULT = false;

	private static final String START_USAGE_TIME = "start_usage_time";
	private static final long START_USAGE_TIME_DEFAULT = 0l;

	private static final String THEME_NAME = "theme";//主题
	private static final int THEME_DEFAULT = 0;//主题默认

	private static final String SETTING_SPLASH_OPEN = "setting_splash_close";//是否打开引导页
	private static final boolean SETTING_SPLASH_OPEN_DEFAULT = true;//默认开启

	private static final String SETTING_STATUS_BAR_TRANSLATION = "setting_status_bar_translation";//状态栏是透明的还是沉浸的
	private static final boolean SETTING_STATUS_BAR_TRANSLATION_DEFAULT = false;//默认沉浸

	private static final String ALBUM_ITEM_NUMBER = "album_item_number";
	private static final int ALBUM_ITEM_NUMBER_DEFAULT = 3;

	private static final String USER_STATUS_ISLOGIN = "user_islogin";

	private static final String SETTING_NEARBY_RADIUS = "setting_nearby_radius";

	private static volatile SharedPreferences mSharedPreferences;

	//    @Inject
//    @Singleton
	public SPUtils(
//            @ContextLife("Application")
			Context context) {
		mSharedPreferences = context.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
		sInstance = this;
	}

	private static SPUtils sInstance;

	//TODO 这里还是同一个对象吗？毕竟变成public了，很多地方都可以调用
	public static SPUtils getInstance(Context context) {
		if (sInstance == null) {
			synchronized (SPUtils.class) {
				if (sInstance == null) {
					sInstance = new SPUtils(context);
				}
			}
		}
		return sInstance;
	}

	/**
	 * 判断是不是第一次进入
	 *
	 * @return
	 */
	public boolean isFirstTime() {
		boolean value = mSharedPreferences.getBoolean(FIRST_TIME, FIRST_TIME_DEFAULT);
		long startTime = getStartUsageTime();
		if (value) {
			SharedPreferences.Editor editor = mSharedPreferences.edit();
			editor.putBoolean(FIRST_TIME, false);
			if (startTime == START_USAGE_TIME_DEFAULT) {
				editor.putLong(START_USAGE_TIME, System.currentTimeMillis());
			}
			editor.commit();
		} else {
			SharedPreferences.Editor editor = mSharedPreferences.edit();
			if (startTime == START_USAGE_TIME_DEFAULT) {
				editor.putLong(START_USAGE_TIME, System.currentTimeMillis());
			}
			editor.commit();
		}
		return value;
	}

	/**
	 * 是不是进入introduce界面
	 *
	 * @return
	 */
	public boolean notGotoIntroduce() {
		boolean value = mSharedPreferences.getBoolean(INTRODUCE_1_2_0, INTRODUCE_DEFAULT);
		long startTime = getStartUsageTime();
		if (!value) {
			SharedPreferences.Editor editor = mSharedPreferences.edit();
			editor.putBoolean(INTRODUCE_1_2_0, true);
			if (startTime == START_USAGE_TIME_DEFAULT) {
				editor.putLong(START_USAGE_TIME, System.currentTimeMillis());
			}
			editor.commit();
		} else {
			SharedPreferences.Editor editor = mSharedPreferences.edit();
			if (startTime == START_USAGE_TIME_DEFAULT) {
				editor.putLong(START_USAGE_TIME, System.currentTimeMillis());
			}
			editor.commit();
		}
		return value;
	}

	/**
	 * 得到第一次使用的时间
	 *
	 * @return
	 */
	public long getStartUsageTime() {
		return mSharedPreferences.getLong(START_USAGE_TIME, START_USAGE_TIME_DEFAULT);
	}

	/**
	 * 判断null
	 *
	 * @param object
	 */
	private void checkNotNull(Object object) {
		if (null == object) {
			throw new NullPointerException("不能是null");
		}
	}

	/**
	 * 保存主题索引
	 *
	 * @param i
	 */
	public void setThemeColor(int i) {
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putInt(THEME_NAME, i);
		editor.commit();
	}

	/**
	 * 获得主题索引
	 *
	 * @return
	 */
	public int getThemeColor() {
		int colorIndex = mSharedPreferences.getInt(THEME_NAME, THEME_DEFAULT);
		if (colorIndex > 16) {
			colorIndex = 16;
		}
		return colorIndex;
	}

	/**
	 * 保存引导页是否开启
	 *
	 * @param bool
	 */
	public void setSplashOpen(boolean bool) {
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putBoolean(SETTING_SPLASH_OPEN, bool);
		editor.commit();
	}

	/**
	 * 获得引导页是否打开
	 *
	 * @return
	 */
	public boolean getSplashOpen() {
		return mSharedPreferences.getBoolean(SETTING_SPLASH_OPEN, SETTING_SPLASH_OPEN_DEFAULT);
	}

	/**
	 * 获得状态栏是透明的还是沉浸的
	 *
	 * @return
	 */
	public boolean getStatusBarTranslation() {
		return mSharedPreferences.getBoolean(SETTING_STATUS_BAR_TRANSLATION, SETTING_STATUS_BAR_TRANSLATION_DEFAULT);
	}

	/**
	 * 设置状态栏味沉浸还是透明
	 *
	 * @param translation
	 */
	public void setStatusBarTranslation(boolean translation) {
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putBoolean(SETTING_STATUS_BAR_TRANSLATION, translation);
		editor.commit();
	}

	public boolean getUserIslogin() {
		if (!TextUtils.isEmpty(mSharedPreferences.getString(USER_STATUS_ISLOGIN, ""))) {
			return true;
		}
		return false;
	}

	public boolean getUserIslogin(String userId) {
		if (!TextUtils.isEmpty(mSharedPreferences.getString(USER_STATUS_ISLOGIN, "")) && TextUtils.equals(mSharedPreferences.getString(USER_STATUS_ISLOGIN, ""), userId)) {
			return true;
		}
		return false;
	}

	public void setUserLogin(String user) {
		mSharedPreferences.edit().putString(USER_STATUS_ISLOGIN, user).apply();
	}

	public int getNearByRadius() {
		return mSharedPreferences.getInt(SETTING_NEARBY_RADIUS, 200);
	}

	public void setNearByRadius(int km) {
		mSharedPreferences.edit().putInt(SETTING_NEARBY_RADIUS, km).apply();
	}
}
