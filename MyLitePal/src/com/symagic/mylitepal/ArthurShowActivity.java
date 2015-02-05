package com.symagic.mylitepal;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mylitepal.R;

public class ArthurShowActivity extends ActionBarActivity implements
		TabListener {

	private ShowTimeFragment fragment = new ShowTimeFragment();
	private boolean isFirstAdd = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arthur_show);
		ActionBar actionBar = getSupportActionBar();// 如果不使用Android Support
													// Library,
													// 则调用getActionBar()方法
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);// NAVIGATION_MODE_TABS常量表示Tab导航模式
		actionBar.setDisplayShowTitleEnabled(true);// 这里的Title显示的是Activity的android:label属性指定的文字，也就是图1中”Google
													// Play”
		Tab tab = actionBar.newTab().setText("第一项").setTabListener(this);
		actionBar.addTab(tab);
		tab = actionBar.newTab().setText("第二项").setTabListener(this);
		actionBar.addTab(tab);
		tab = actionBar.newTab().setText("第三项").setTabListener(this);
		actionBar.addTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.arthur_show, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if (isFirstAdd) {
			ft.add(R.id.ll_content, fragment);
			isFirstAdd = false;
		}
		if (tab.getPosition() == 0) {
			ft.hide(fragment).setCustomAnimations(R.anim.push_left_in,
					R.anim.push_left_out);
		} else {
			ft.show(fragment).setCustomAnimations(R.anim.push_left_in,
					R.anim.push_left_out);
		}

	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

}
