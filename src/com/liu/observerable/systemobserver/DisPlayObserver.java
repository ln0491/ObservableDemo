package com.liu.observerable.systemobserver;

import java.util.Observable;
import java.util.Observer;

public class DisPlayObserver implements Observer {


	// 温度
	private float temperature;
	// 湿度
	private float humidity;
	// 压力
	private float pressure;

	//站点名称
	private String name;
	
	public DisPlayObserver(String name){
		this.name = name;
	}
	
	@Override
	public void update(Observable o, Object arg) {

		/**
		 * 当notifyObservers为无参数是
		 */
		if(o instanceof WeatherObservable){
			WeatherObservable weatherObservable = (WeatherObservable) o;
			this.temperature = weatherObservable.getTemperature();
			this.humidity = weatherObservable.getHumidity();
			this.pressure = weatherObservable.getPressure();
			System.out.println("===============由被观察推送来的消息=============");
			display();
		}
		
		/**notifyObservers 有参数
		 * arg,可以是一个单独的数据都可以，也可以是一个对象
		 * temperature
		 * humidity
		 * pressure 
		 */
		if(arg instanceof WeatherObservable){
			WeatherObservable weatherObservable = (WeatherObservable) o;
			this.temperature = weatherObservable.getTemperature();
			this.humidity = weatherObservable.getHumidity();
			this.pressure = weatherObservable.getPressure();
			System.out.println("===============由观察主动拉取的的消息=============");
			display();
		}
		
	
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
