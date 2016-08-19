package com.zyl.loadstateview;

import android.content.Context;

import com.zyl.library.LoadingState;
import com.zyl.loadstateview.base.BasePre;

/**
 * Created by Administrator on 2016-08-19.
 */

public class MainPre extends BasePre<MainView> {

	public MainPre(Context context, MainModel mainModel) {
		super(context,mainModel);
	}

	public void show(){
		mView.show(LoadingState.STATE_LOADING);
	}


}
