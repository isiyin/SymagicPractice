package com.symagic.mylitepal;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mylitepal.R;
import com.larswerkman.holocolorpicker.ColorPicker.OnColorChangedListener;
import com.larswerkman.holocolorpicker.ColorPicker.OnColorSelectedListener;
import com.larswerkman.holocolorpicker.OpacityBar.OnOpacityChangedListener;
import com.symagic.utils.SharePreferenceUtil;
import com.symagic.views.PickerColorDialog;

public class PickerColorFrament extends DialogFragment implements
		OnClickListener, OnColorSelectedListener, OnColorChangedListener,
		OnOpacityChangedListener {
	private boolean mChanged;
	private int mColor;
	private SharePreferenceUtil spInfo;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		PickerColorDialog dialog = new PickerColorDialog(getActivity());
		spInfo = new SharePreferenceUtil(getActivity());
		dialog.setOnClickListener(this);
		dialog.setOnColorChangedListener(this);
		dialog.setOnColorSelectedListener(this);
		return dialog;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.color_picker_ok:
			if (mChanged) {
				spInfo.setTextBgColor(mColor);
				mChanged = false;
			}
			dismiss();
			break;
		case R.id.color_picker_restore:
			dismiss();
			spInfo.setTextBgColor(Color.WHITE);
			break;
		case R.id.color_picker_cancel:
			dismiss();
			break;
		default:
			break;
		}
	}

	@Override
	public void show(FragmentManager fragmentManager, String tag) {
		super.show(fragmentManager, tag);
		mChanged = false;
	}

	@Override
	public int show(FragmentTransaction transaction, String tag) {
		mChanged = false;
		return super.show(transaction, tag);
	}

	@Override
	public void onColorChanged(int color) {
		mColor = color;
		mChanged = true;
	}

	@Override
	public void onColorSelected(int color) {
		mColor = color;
		mChanged = true;
	}

	@Override
	public void onOpacityChanged(int opacity) {
		mChanged = true;

	}
}
