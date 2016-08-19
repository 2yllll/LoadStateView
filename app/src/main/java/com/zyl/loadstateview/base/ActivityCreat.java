package com.zyl.loadstateview.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016-08-19.
 */

public interface ActivityCreat {
	BasePre getPresenter();

	View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

	void bindView(Bundle savedInstanceState);

	View getView();

	int getContentLayout();
}
