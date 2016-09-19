package com.liu.observerable;

import com.liu.observerable.custom.impl.CurrentDisplay;
import com.liu.observerable.custom.impl.WeatherData;
/**
 * 
* @ClassName: Test1 
* @Description: TODO(自定义的观察者 与被观察者测试类) 
* @author 刘楠
* @date 2016年9月19日 下午12:35:20 
*
 */
public class Test1 {

	public static void main(String[] args) {
		
		//声明被观察者
		WeatherData weatherDdata = new WeatherData();

		//声明观察者
		CurrentDisplay currentDisplay1= new CurrentDisplay("1号观测站 ");
		CurrentDisplay currentDisplay2= new CurrentDisplay("2号观测站 ");
		CurrentDisplay currentDisplay3= new CurrentDisplay("3号观测站 ");
		CurrentDisplay currentDisplay4= new CurrentDisplay("4号观测站 ");
		CurrentDisplay currentDisplay5= new CurrentDisplay("5号观测站 ");
		
		//注册
		weatherDdata.registerObserver(currentDisplay1);
		weatherDdata.registerObserver(currentDisplay2);
		weatherDdata.registerObserver(currentDisplay3);
		weatherDdata.registerObserver(currentDisplay4);
		weatherDdata.registerObserver(currentDisplay5);
		
		//设置数据
		weatherDdata.setInfomation(38.0f, 40.0f, 20.0f);
		
	}

}
