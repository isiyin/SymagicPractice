package com.symagic.mylitepal;

import com.example.mylitepal.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;

public class ShowTimeFragment extends Fragment {
	private View mView;
	private CalendarView calendarView;
	private EditText timeET;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mView == null || mView.getParent() != null) {
			mView = inflater.inflate(R.layout.fragment_showtime, container,
					false);
		}
		initView();
		return mView;
	}

	private void initView() {
		timeET = (EditText) mView.findViewById(R.id.et_time);
		calendarView = (CalendarView) mView.findViewById(R.id.cv_time);
		calendarView.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				timeET.setText(year + "年" + (month + 1) + "月" + dayOfMonth
						+ "日");

			}
		});
	}
}
