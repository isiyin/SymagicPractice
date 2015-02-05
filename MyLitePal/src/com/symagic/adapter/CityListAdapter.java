package com.symagic.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mylitepal.R;
import com.symagic.bean.City;

public class CityListAdapter extends BaseAdapter {
	private List<City> cityList;
	private Context context;

	public CityListAdapter(Context context, List<City> cityList) {
		super();
		this.cityList = cityList;
		this.context = context;

	}

	public void setData(List<City> cityList) {
		this.cityList = cityList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cityList.size();
	}

	@Override
	public Object getItem(int position) {
		return cityList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.layout_simple_text,
					null);
			holder = new ViewHolder();
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		City city = cityList.get(position);
		holder.tv_content.setText(city.getCity());

		return convertView;
	}

	private static class ViewHolder {
		TextView tv_content;
	}

}
