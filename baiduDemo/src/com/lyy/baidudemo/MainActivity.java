package com.lyy.baidudemo;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.lyy.overlayutil.PoiOverlay;
import com.lyy.service.myPoi;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity implements OnGetPoiSearchResultListener,OnClickListener{
	private MapView mMapView = null;
	private BaiduMap mBaiduMap = null;
	private PoiSearch search = null;
	private EditText city;
	private EditText place;
	private static int page = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 SDKInitializer.initialize(getApplicationContext()); 
		setContentView(R.layout.activity_main);
		
		 mMapView = (MapView) findViewById(R.id.bmapView);
		 
		 mBaiduMap = mMapView.getMap();  
		 
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		
		search = PoiSearch.newInstance();
		
		search.setOnGetPoiSearchResultListener(this);
		
		Button search = (Button)findViewById(R.id.search);
		Button next = (Button)findViewById(R.id.next);		
		city = (EditText)findViewById(R.id.city);
		place = (EditText)findViewById(R.id.place);
		search.setOnClickListener(this);
		next.setOnClickListener(this);		
		// mBaiduMap.setTrafficEnabled(true);
		// mBaiduMap.setBaiduHeatMapEnabled(true);
		// mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);	 
		// mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE); //remain blank
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	    protected void onDestroy() {  
	        super.onDestroy();  
	        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
	        mMapView.onDestroy();  
	    }  
	    @Override  
	    protected void onResume() {  
	        super.onResume();  
	        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
	        mMapView.onResume();  
	        }  
	    @Override  
	    protected void onPause() {  
	        super.onPause();  
	        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
	        mMapView.onPause();  
	        }

		@Override
		public void onGetPoiDetailResult(PoiDetailResult arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetPoiIndoorResult(PoiIndoorResult arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetPoiResult(PoiResult result) {
			// TODO Auto-generated method stub
			  
		    if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {  
		        return;  
		    }  
		    if (result.error == SearchResult.ERRORNO.NO_ERROR) {  
		        mBaiduMap.clear();  
		        //创建PoiOverlay  
		        PoiOverlay overlay = new myPoi(mBaiduMap);  
		        //设置overlay可以处理标注点击事件  
		        mBaiduMap.setOnMarkerClickListener(overlay);  
		        //设置PoiOverlay数据  
		        overlay.setData(result);  
		        //添加PoiOverlay到地图中  
		        overlay.addToMap();  
		        overlay.zoomToSpan();  
		        return;  
		    }  
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.search:
				citySearch(page);				
				break;
			case R.id.next:
				page+=1;
				citySearch(page);	
				break;
			default:
				break;
			}
			
		}  
	    
		 private void citySearch(int page) {  
		        // 设置检索参数  
		        PoiCitySearchOption citySearchOption = new PoiCitySearchOption();  
		        citySearchOption.city(city.getText().toString());// 城市  
		        citySearchOption.keyword(place.getText().toString());// 关键字  
		        citySearchOption.pageCapacity(15);// 默认每页10条  
		        citySearchOption.pageNum(page);// 分页编号  
		        // 发起检索请求  
		        search.searchInCity(citySearchOption);  
		    }     
}
