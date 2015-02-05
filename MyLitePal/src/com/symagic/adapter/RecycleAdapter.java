package com.symagic.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylitepal.R;
import com.symagic.bean.Photo;
import com.symagic.utils.SharePreferenceUtil;
import com.symagic.utils.Utils;

public class RecycleAdapter extends
		RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

	private static Context context;
	private List<Photo> photoList;

	public List<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}

	public RecycleAdapter(Context context, List<Photo> photoList) {
		super();
		RecycleAdapter.context = context;
		this.photoList = photoList;
	}

	@Override
	public int getItemCount() {
		return photoList == null ? 0 : photoList.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int i) {
		Photo photo = photoList.get(i);
		holder.mTextView.setText(photo.getDescription());
		holder.mImageView.setImageDrawable(context.getResources().getDrawable(
				Utils.getDrawableResourceId(context, photo.getPicName())));
		holder.setPosition(i);
		holder.setmPhoto(photo);

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.card_view, viewGroup, false);
		LayoutParams params = view.getLayoutParams();
		params.width = LayoutParams.MATCH_PARENT;
		params.height = (int) (dm.widthPixels / 3.0 * 2.0);
		view.setLayoutParams(params);
		return new ViewHolder(view);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView mTextView;
		private ImageView mImageView;
		private int position;
		private Photo mPhoto;

		public void setmPhoto(Photo mPhoto) {
			this.mPhoto = mPhoto;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public ViewHolder(View view) {
			super(view);
			mTextView = (TextView) view.findViewById(R.id.tv_name);
			mTextView.setBackgroundColor(new SharePreferenceUtil(context)
					.getTextBgColor());
			mImageView = (ImageView) view.findViewById(R.id.iv_pic);
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mTextView.setBackgroundColor(new SharePreferenceUtil(
							context).getTextBgColor());
					Toast.makeText(
							v.getContext(),
							"ItemClick" + position + " "
									+ mPhoto.getDescription(),
							Toast.LENGTH_SHORT).show();
				}
			});

		}
	}
}
