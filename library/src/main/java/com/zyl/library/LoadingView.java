package com.zyl.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl.library.Utils.NetworkUtil;
import com.zyl.library.Utils.SPUtils;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * Created by iheyh on 2016/1/21.
 */
public class LoadingView extends FrameLayout {
    private Context mContext;

    private GifImageView iv_loading;

    private ImageView iv_loaded;

    private LinearLayout ll_over;

    private LinearLayout ll_loading;

    private TextView tv_loaded;

    private TextView tv_loading;

    private Button btn_loaded;

    public LoadingView(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (View.GONE == visibility && mState == LoadingState.STATE_LOADING && animation != null && animation.isRunning()) {
            animation.stop();
        }
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }

    public LoadingView withLoadingIco(int resId) {
        mLoadingIco = resId;
        return this;
    }

    /**
     * 提示
     */
    private String mLoaded_error_text="(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,我家程序猿跑路了 !";
    private String mLoaded_empty_text = "≥﹏≤// , 暂时还没有东西 !";
    private String mLoaded_not_net_text="你挡着信号啦o(￣ヘ￣o)☞ᗒᗒ 你走开";
    private String mLoaded_not_validate_text="(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,你还不能这么做!";
    private String mLoaded_not_login_text = "你都还没登录呢!";
    private String mLoadingText="加油加油加油↖(^ω^)↗...";

    private int mNoNetIco=R.drawable.ic_wifi_warnning_black_48dp;
    private int mNoValidateIcon=R.drawable.ic_error_black_48dp;
    private int mErrorIco =R.drawable.ic_error_black_48dp;
    private int mNoLoginIcon = R.drawable.ic_warnning_black_48dp;
    private int mEmptyIco = R.drawable.ic_warnning_black_48dp;
    private int mLoadingIco=R.drawable.loading;


    public String btn_empty_text = "我去!";
    public String btn_error_text = "去找回她!!!";
    public String btn_nonet_text = "网管应该弄好啦，再试下";
    public String btn_novalidate_text = "我弄好了!";
    public String btn_nologin_text = "登录去啦!";

    public LoadingView withEmptyIco(int resId) {
        mEmptyIco = resId;
        return this;
    }

    public LoadingView withNoNetIco(int resId) {
        mNoNetIco = resId;
        return this;
    }

    public LoadingView withNoValidateIco(int resId) {
        mNoValidateIcon = resId;
        return this;
    }

    public LoadingView withNoLoginIco(int resId) {
        mNoLoginIcon = resId;
        return this;
    }

    public OnRetryListener mOnRetryListener;

    public LoadingView withOnRetryListener(OnRetryListener mOnRetryListener) {
        this.mOnRetryListener = mOnRetryListener;
        return this;
    }

    private LoadingState mState;

    public void setState(LoadingState state) {
        if (mState == state) {
            return;
        } else if (state == LoadingState.STATE_LOADING) {
            ll_over.setVisibility(GONE);
            ll_loading.setVisibility(VISIBLE);
        } else if (state != LoadingState.STATE_LOADING) {
            ll_loading.setVisibility(GONE);
            ll_over.setVisibility(VISIBLE);
            if (animation != null && mState == LoadingState.STATE_LOADING)
                animation.stop();
        }
        changeState(state);
    }

    public boolean btn_empty_ennable = true;
    public boolean btn_error_ennable = true;
    public boolean btn_nonet_ennable = true;

    public LoadingView withBtnNoNetEnnable(boolean ennable) {
        btn_nonet_ennable = ennable;
        return this;
    }

    public LoadingView withBtnErrorEnnable(boolean ennable) {
        btn_error_ennable = ennable;
        return this;
    }


    public LoadingView withBtnEmptyEnnable(boolean ennable) {
        btn_empty_ennable = ennable;
        return this;
    }

    private GifDrawable animation;

    private void changeState(LoadingState state) {
        switch (state) {
            case STATE_LOADING:
                mState = LoadingState.STATE_LOADING;
                iv_loading.setImageResource(mLoadingIco);
                tv_loading.setText(mLoadingText);
                if (animation == null) {
                    animation = (GifDrawable) iv_loading.getDrawable();
                }
                if (animation != null)
                    animation.start();
                break;
            case STATE_EMPTY:
                mState = LoadingState.STATE_EMPTY;
                iv_loaded.setImageResource(mEmptyIco);
                tv_loaded.setText(mLoaded_empty_text);
                if (btn_empty_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_empty_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
            case STATE_ERROR:
                mState = LoadingState.STATE_ERROR;
                iv_loaded.setImageResource(mErrorIco);
                tv_loaded.setText(mLoaded_error_text);
                if (btn_error_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_error_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
            case STATE_NO_NET:
                mState = LoadingState.STATE_NO_NET;
                iv_loaded.setImageResource(mNoNetIco);
                tv_loaded.setText(mLoaded_not_net_text);
                if (btn_nonet_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_nonet_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
            case STATE_NO_VALIDATE:
                mState = LoadingState.STATE_NO_VALIDATE;
                iv_loaded.setImageResource(mNoValidateIcon);
                tv_loaded.setText(mLoaded_not_validate_text);
                if (btn_error_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_novalidate_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
            case STATE_NO_LOGIN:
                mState = LoadingState.STATE_NO_LOGIN;
                iv_loaded.setImageResource(mNoLoginIcon);
                tv_loaded.setText(mLoaded_not_login_text);
                if (btn_error_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_nologin_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
        }
        if (mOnRetryListener != null) {
            mOnRetryListener.onRetry(mState);
        }
    }


    public LoadingView withErrorIco(int resId) {
        mErrorIco = resId;
        return this;
    }

    public LoadingView withLoadedEmptyText(int resId) {
        mLoaded_empty_text = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadedEmptyText(String mLoadedemptyText) {
        this.mLoaded_empty_text = mLoadedemptyText;
        return this;
    }

    public LoadingView withLoadedNoNetText(int resId) {
        mLoaded_not_net_text = getResources().getString(resId);
        return this;
    }

    public LoadingView withbtnEmptyText(String text) {
        this.btn_empty_text = text;
        return this;
    }

    public LoadingView withbtnErrorText(String text) {
        this.btn_error_text = text;
        return this;
    }

    public LoadingView withbtnNoNetText(String text) {
        this.btn_nonet_text = text;
        return this;
    }


    public LoadingView withLoadedNoNetText(String mLoadedNoNetText) {
        this.mLoaded_not_net_text = mLoadedNoNetText;
        return this;
    }

    public LoadingView withLoadedErrorText(int resId) {
        mLoaded_error_text = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadedErrorText(String mLoadedErrorText) {
        this.mLoaded_error_text = mLoadedErrorText;
        return this;
    }

    public LoadingView withLoadingText(int resId) {
        mLoadingText = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadingText(String mLoadingText) {
        this.mLoadingText = mLoadingText;
        return this;
    }

    public LoadingView withNovalidateText(String mLoadingText) {
        this.mLoaded_not_validate_text = mLoadingText;
        return this;
    }

    public LoadingView withbtnNovalidate(String mLoadingText) {
        this.btn_novalidate_text = mLoadingText;
        return this;
    }

    public LoadingView withNoLoginText(String mLoginText) {
        this.mLoaded_not_login_text = mLoginText;
        return this;
    }

    public LoadingView withbtnNoLogin(String mLoginText) {
        this.btn_nologin_text = mLoginText;
        return this;
    }


    public void build() {
        View view = View.inflate(mContext, R.layout.layout_loading, this);
        iv_loading = (GifImageView) view.findViewById(R.id.iv_loading);
        iv_loaded = (ImageView) view.findViewById(R.id.iv_loaded);
        ll_over = (LinearLayout) view.findViewById(R.id.ll_over);
        ll_loading = (LinearLayout) view.findViewById(R.id.ll_loading);
        tv_loaded = (TextView) view.findViewById(R.id.tv_loaded);
        tv_loading = (TextView) view.findViewById(R.id.tv_loading);
        btn_loaded = (Button) view.findViewById(R.id.btn_loaded);
        btn_loaded.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRetryListener != null) {
                    if (SPUtils.getInstance(mContext).getUserIslogin()) {
                        setState(LoadingState.STATE_LOADING);
                    } else {
                        setState(LoadingState.STATE_NO_LOGIN);
                    }
                    mOnRetryListener.onRetry(mState);
                }
            }
        });
        if (NetworkUtil.isNetWorkConnected(mContext)) {
            if (SPUtils.getInstance(mContext).getUserIslogin()) {
                setState(LoadingState.STATE_LOADING);
            } else {
                setState(LoadingState.STATE_NO_LOGIN);
            }
        } else {
            setState(LoadingState.STATE_NO_NET);
        }
    }


    public void show(LoadingState state) {
        setVisibility(View.VISIBLE);
        switch (state) {
            case STATE_EMPTY:
                setState(LoadingState.STATE_EMPTY);
                break;
            case STATE_ERROR:
                setState(LoadingState.STATE_ERROR);
                break;
            case STATE_NO_NET:
                setState(LoadingState.STATE_NO_NET);
                break;
            case STATE_NO_LOGIN:
                setState(LoadingState.STATE_NO_LOGIN);
                break;
            case STATE_NO_VALIDATE:
                setState(LoadingState.STATE_NO_VALIDATE);
                break;
            case STATE_LOADING:
                setState(LoadingState.STATE_LOADING);
                break;
            default:
                setVisibility(View.GONE);
                break;
        }
    }
}
