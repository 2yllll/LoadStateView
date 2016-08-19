package com.zyl.loadstateview.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyl.loadstateview.ActivityCollector;
import com.zyl.loadstateview.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePre<IBaseView>> extends AppCompatActivity implements
		ActivityCreat {

	private BasePre mPre;
	private View mRootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPre = getPresenter();
		mPre.attach((IBaseView)this);
		mRootView = createView(null, null, savedInstanceState);
		ButterKnife.bind(this, mRootView);
		setContentView(mRootView);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		bindView(savedInstanceState);
	}

	@Override
	public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return ActivityCollector.inflate(this, getContentLayout());
	}

	@Override
	public View getView() {
		return mRootView;
	}

}
