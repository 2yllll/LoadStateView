package com.zyl.loadstateview;

import android.os.Bundle;

import com.zyl.library.LoadingState;
import com.zyl.library.LoadingView;
import com.zyl.library.OnRetryListener;
import com.zyl.loadstateview.base.BaseActivity;
import com.zyl.loadstateview.base.BasePre;

/**
 * Created by Administrator on 2016-08-19.
 */

public class MainActivity extends BaseActivity implements MainView {
	LoadingView loadingView;
	private MainPre mainPre;

	@Override
	public BasePre getPresenter() {
		mainPre = new MainPre(this, new MainModel());
		return mainPre;
	}

	@Override
	public void bindView(Bundle savedInstanceState) {
		loadingView = (LoadingView) findViewById(R.id.loadingView);
		loadingView.withOnRetryListener(new OnRetryListener() {
			@Override
			public void onRetry() {
				loadingView.show(LoadingState.STATE_GONE);
			}
		}).build();
		mainPre.show();
	}

	@Override
	public int getContentLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void show(LoadingState state) {
		loadingView.show(state);
	}

}
