package com.liu.observerable.custom.impl;

import com.liu.observerable.custom.Observable;
import com.liu.observerable.custom.Observer;
/**
 * 自定义的观察者实现类
* @ClassName: CurrentDisplay 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 刘楠
* @date 2016年9月19日 下午1:28:33 
*
 */
public class CurrentDisplay implements Observer {

	// 温度
		private float temperature;
		// 湿度
		private float humidity;
		// 压力
		private float pressure;
	
		/**
		 * 站点名称
		 */
		private String name;
		
		
		public CurrentDisplay(String name){
			this.name  = name;
		}
	
	
	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		System.out.println("===============由被观察推送来的消息=============");
		display();
	}



	@Override
	public void update(Observable observable) {
		WeatherData weatherDdata = (WeatherData) observable;
		this.temperature = weatherDdata.getTemperature();
		this.humidity = weatherDdata.getHumidity();
		this.pressure = weatherDdata.getPressure();
		
		System.out.println("===============由观察主动拉取的的消息=============");
		display();
	}

	/**
	 * 显示天气信息
	 * @Title: display 
	 * @Description: TODO(这里用一句话描述这个方法的作用)       
	 * @param     设定文件
	 * @return void    返回类型    
	 * @throws
	 */
	public void display(){
		
		System.out.println(this.name+"  天气 温度: "+this.temperature+"   ,湿度： "+this.humidity+"  ,压力:"+this.pressure);
	}
	
}
