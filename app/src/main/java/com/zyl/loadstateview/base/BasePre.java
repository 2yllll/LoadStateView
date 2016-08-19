package com.zyl.loadstateview.base;

import android.content.Context;

/**
 * Created by Administrator on 2016-08-19.
 */

public class BasePre<V extends IBaseView> {
	public V mView;
	public Context mContext;
	public BaseModel mModel;

	public BasePre(Context context,BaseModel model) {
		this.mContext = context;
		this.mModel = model;
	}

	public BasePre attach(V mView) {
		this.mView = mView;
		return this;

	}

	public BasePre detach() {
		if (this.mView != null) {
			this.mView = null;
		}
		return this;
	}
}
