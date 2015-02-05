package com.symagic.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mylitepal.R;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.ColorPicker.OnColorChangedListener;
import com.larswerkman.holocolorpicker.ColorPicker.OnColorSelectedListener;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.symagic.utils.SharePreferenceUtil;

public class PickerColorDialog extends Dialog {
	private ColorPicker mPicker;
	private View mRoot;
	private SharePreferenceUtil spInfo;

	public PickerColorDialog(Context context) {
		super(context, R.style.dialog);
		spInfo = new SharePreferenceUtil(context);
		setContentView(createView());
		initProperties();
	}

	@Override
	public void show() {
		if (mPicker != null)
			mPicker.setColor(spInfo.getTextBgColor());
		super.show();
	}

	private View createView() {
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.lock_colorpicker_layout, null);
		ColorPicker picker = (ColorPicker) view
				.findViewById(R.id.colorpicker_view);
		SaturationBar bar = (SaturationBar) view
				.findViewById(R.id.staturation_view);
		OpacityBar mOpacityBar = (OpacityBar) view
				.findViewById(R.id.opacitybar);

		picker.addSaturationBar(bar);
		picker.addOpacityBar(mOpacityBar);
		mRoot = view;

		picker.setColor(spInfo.getTextBgColor());
		mPicker = picker;

		return view;
	}

	public void setOnClickListener(android.view.View.OnClickListener listener) {
		if (mRoot == null || listener == null)
			return;

		mRoot.findViewById(R.id.color_picker_ok).setOnClickListener(listener);
		mRoot.findViewById(R.id.color_picker_restore).setOnClickListener(
				listener);
		mRoot.findViewById(R.id.color_picker_cancel).setOnClickListener(
				listener);
	}

	private void initProperties() {
		setCanceledOnTouchOutside(true);
		getWindow().setWindowAnimations(R.style.window_animstyle);
	}

	public void setOnColorSelectedListener(OnColorSelectedListener listener) {
		mPicker.setOnColorSelectedListener(listener);
	}

	public void setOnColorChangedListener(OnColorChangedListener listener) {
		mPicker.setOnColorChangedListener(listener);
	}
}
