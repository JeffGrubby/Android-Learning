package com.lyy.service;

import com.baidu.mapapi.map.BaiduMap;
import com.lyy.overlayutil.PoiOverlay;

public class myPoi extends PoiOverlay {

	public myPoi(BaiduMap baiduMap) {
		super(baiduMap);
		// TODO Auto-generated constructor stub
		
		
	}
	
	
	@Override
	public boolean onPoiClick(int i) {
		// TODO Auto-generated method stub
		super.onPoiClick(i); 
		return true;
	}
	
	
}
