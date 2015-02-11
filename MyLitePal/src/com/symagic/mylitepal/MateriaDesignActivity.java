package com.symagic.mylitepal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.example.mylitepal.R;
import com.symagic.adapter.RecycleAdapter;
import com.symagic.bean.Photo;

public class MateriaDesignActivity extends FragmentActivity {
	private RecyclerView contentRC;
	private RecycleAdapter adapter;
	private List<Photo> photoList;
	private MenuItem menuItem = null;
	private final String[] picArray = { "p1", "p2", "p3", "p4", "p5" };
	private final String[] desArray = { "Bus Station", "Night Bridge",
			"Underground", "Looking Back", "Work Studio" };
	private boolean isClick;
	private SearchFragment searchFragment;
	private PickerColorFrament colorFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materia_design);
//		setOverflowShowingAlways();
		android.app.ActionBar bar = getActionBar();
		bar.setTitle("MateriaDesign");
		bar.setIcon(R.drawable.icon);
		bar.setSubtitle("what");
		bar.setDisplayHomeAsUpEnabled(true);
		// bar.addTab(bar.newTab().setText("the"));
		// bar.addTab(bar.newTab().setText("held"));
		initView();
		initData();
	}

	private void initView() {
		contentRC = (RecyclerView) findViewById(R.id.rc_content);
		// 设置LinearLayoutManager
		contentRC.setLayoutManager(new LinearLayoutManager(this));
		// 设置ItemAnimator
		contentRC.setItemAnimator(new DefaultItemAnimator());
		// 设置固定大小
		contentRC.setHasFixedSize(true);
		contentRC.addOnItemTouchListener(new OnItemTouchListener() {

			@Override
			public void onTouchEvent(RecyclerView arg0, MotionEvent arg1) {
				Toast.makeText(MateriaDesignActivity.this,
						"RecycleView Clicked", Toast.LENGTH_SHORT).show();
			}

			@Override
			public boolean onInterceptTouchEvent(RecyclerView arg0,
					MotionEvent arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	private void initData() {
		isClick = false;
		photoList = new ArrayList<Photo>();
		int i = 0;
		for (String pic : picArray) {
			Photo photo = new Photo(pic, desArray[i++]);
			photoList.add(photo);
		}
		adapter = new RecycleAdapter(this, photoList);
		contentRC.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_materia_design, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// switch (item.getItemId()) {
	// case android.R.id.home:
	// Toast.makeText(MateriaDesignActivity.this,
	// "back click click click click",
	// Toast.LENGTH_SHORT).show();
	// // finish();
	// break;
	// default:
	// break;
	// }
	// return true;
	// }

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		menuItem = item;
		switch (item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(MateriaDesignActivity.this, "menu back click",
					Toast.LENGTH_SHORT).show();
			// finish();
			return false;
		case R.id.action_search:
			if (isClick) {
				hideSerch();
				item.setIcon(R.drawable.search);
				isClick = false;
			} else {
				openSearch();
				item.setIcon(R.drawable.home);
				isClick = true;
			}
			return true;
		case R.id.action_voice:
			if (colorFragment == null) {
				colorFragment = new PickerColorFrament();
			}
			showColorPicker();
			return true;
		case R.id.action_settings:
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}

	}

	private void showColorPicker() {
		colorFragment.show(getSupportFragmentManager(), "color");
	}

	private void openSearch() {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		if (searchFragment == null) {
			searchFragment = new SearchFragment();
			transaction.add(R.id.rl_root, searchFragment).commit();
		} else {
			transaction.show(searchFragment).commit();
		}

	}

	private void hideSerch() {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.hide(searchFragment).commit();
	}

	public void onTransprentClick(View view) {
		hideSerch();
		isClick = false;
		menuItem.setIcon(R.drawable.search);
	}

	public void onGreenClick(View view) {
		hideSerch();
		isClick = false;
		menuItem.setIcon(R.drawable.search);
	}

	/**
	 * 让物理Menu键默认为没有， 就是使用反射的方式将sHasPermanentMenuKey的值设置成false
	 */
	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
