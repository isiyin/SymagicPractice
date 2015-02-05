package com.symagic.mylitepal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylitepal.R;
import com.symagic.views.RippleView;

public class SearchFragment extends Fragment {
	private View mView;
	private RippleView sureRV;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mView == null || mView.getParent() != null) {
			mView = inflater.inflate(R.layout.layout_search, container, false);
		}
		initView();
		return mView;
	}

	private void initView() {
		sureRV = (RippleView) mView.findViewById(R.id.rv_test);
		
	}
}
