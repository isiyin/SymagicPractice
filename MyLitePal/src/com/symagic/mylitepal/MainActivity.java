package com.symagic.mylitepal;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylitepal.R;
import com.symagic.adapter.CityListAdapter;
import com.symagic.bean.City;
import com.symagic.customdialog.dialog.Effectstype;
import com.symagic.customdialog.dialog.NiftyDialogBuilder;
import com.symagic.utils.StringUtil;
import com.symagic.utils.Utils;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {

	NiftyDialogBuilder dialogBuilder;
	private List<City> cityList;
	private LinearLayout resultLL;
	private EditText contentET;
	private ListView cityLV;
	private CityListAdapter adapter = null;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			int i = 1;
			adapter = new CityListAdapter(MainActivity.this, cityList);
			cityLV.setAdapter(adapter);
			for (City bean : cityList) {
				bean.setId(i);
				if (bean.save()) {
					Log.d("Lite", bean.getCity() + "Successful");
				} else {
					Log.d("Lite", bean.getCity() + "Failed");
				}
				i++;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dialogBuilder = NiftyDialogBuilder.getInstance(this);
		initView();
		initDB();
		initOverLayview();

	}

	private void showDialog(String title, String content, Effectstype type) {
		dialogBuilder
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage(content)
				// .withMessage(null) no Msg
				.withMessageColor(
						getResources().getString(R.color.text_light_orange))
				// def
				.withIcon(getResources().getDrawable(R.drawable.ic_launcher))
				.isCancelableOnTouchOutside(true) // def |
													// isCancelable(true)
				.withDuration(700) // def
				.withEffect(type) // def Effectstype.Slidetop
				.withButton1Text("取消") // def gone
				.withButton2Text("确定") // def gone
				// .setCustomView(R.layout.custom_view, MainActivity.this)
				// // .setCustomView(View
				// or
				// ResId,context)
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
					}
				}).show();

	}

	public void onSureClick(View view) {
		resultLL.removeAllViews();
		String searchContent = contentET.getText().toString();
		if (StringUtil.isBlank(searchContent)) {
			this.showDialog(
					"内容不能为空",
					getResources().getString(
							R.string.please_input_search_content),
					Effectstype.RotateBottom);

		} else {
			List<City> cityList = DataSupport.select("city")
					.where("cityId=?", searchContent).find(City.class);
			adapter.setData(cityList);
			adapter.notifyDataSetChanged();
			if (cityList != null && cityList.size() > 0) {
				for (City city : cityList) {
					TextView myView = new TextView(this);
					myView.setText(city.getCity());
					resultLL.addView(myView);
				}
			}
		}

	}

	private void initView() {
		resultLL = (LinearLayout) findViewById(R.id.ll_result);
		contentET = (EditText) findViewById(R.id.et_content);
		cityLV = (ListView) findViewById(R.id.lv_city);
		View view = View.inflate(MainActivity.this, R.layout.view_header, null);
		cityLV.addHeaderView(view, null, false);
	}

	private void initOverLayview() {
		final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);

		// 获得手势库
		final GestureLibrary library = GestureLibraries.fromRawResource(this,
				R.raw.gestures);
		library.load();// 导入库

		// 找到和监听手写view
		GestureOverlayView OverlayView = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
		OnGesturePerformedListener listener = new OnGesturePerformedListener() {
			@Override
			public void onGesturePerformed(GestureOverlayView overlay,
					Gesture gesture) {
				checkBox.setChecked(false);
				ArrayList<Prediction> recognizeList = library
						.recognize(gesture);
				for (Prediction prediction : recognizeList) {

					if (prediction.score >= 5)// 相似度score取值范围0-10
					{
						Toast.makeText(
								MainActivity.this,
								"识别结果：" + prediction.name + " 分数： "
										+ prediction.score, Toast.LENGTH_SHORT)
								.show();
						TextView tmpTV = (TextView) View.inflate(
								MainActivity.this, R.layout.layout_simple_text,
								null);
						tmpTV.setText(prediction.name);
						resultLL.addView(tmpTV);
						if (prediction.name.equals("arrow"))// 判断name是否相同
						{
							checkBox.setChecked(true);
							int notificationId = 011;
							// Toast.makeText(MainActivity.this,
							// "" + notificationId, Toast.LENGTH_SHORT)
							// .show();
							String eventTitle = "Hello,Arthur";
							String eventDes = "Here comes your ghost again.";
							// Build intent for notification content
							Intent viewIntent = new Intent(MainActivity.this,
									ArthurShowActivity.class);
							PendingIntent viewPendingIntent = PendingIntent
									.getActivity(MainActivity.this, 0,
											viewIntent, 0);

							NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
									MainActivity.this)
									.setSmallIcon(R.drawable.ic_lebang)
									.setContentTitle(eventTitle)
									.setContentText(eventDes)
									.setContentIntent(viewPendingIntent)
									.setAutoCancel(true).setColor(Color.RED);

							// Get an instance of the NotificationManager
							// service
							NotificationManagerCompat notificationManager = NotificationManagerCompat
									.from(MainActivity.this);

							// Build the notification and issues it with
							// notification manager.
							notificationManager.notify(notificationId,
									notificationBuilder.build());
						} else if (prediction.name.equals("forward")) {
							Intent intent = new Intent(MainActivity.this,
									MateriaDesignActivity.class);
							startActivity(intent);
						}
						break;
					}
				}
			}
		};
		OverlayView.addOnGesturePerformedListener(listener);
	}

	private void initDB() {
		SQLiteDatabase db = Connector.getDatabase();
	}

	// 初次读取城市信息加载线程
	private class InitCityListThread extends Thread {
		public void run() {
			// cityList = ReadExcelUtil
			if (cityList == null) {
				cityList = Utils.getCityListFromJson(MainActivity.this);
			}
			handler.sendEmptyMessage(0);
		}
	}

	public void onTextClick(View view) {
		Log.d("Lite", "click");
		InitCityListThread thread = new InitCityListThread();
		DataSupport.deleteAll(City.class);
		thread.start();
	}
}
