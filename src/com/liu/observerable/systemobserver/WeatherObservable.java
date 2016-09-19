package com.liu.observerable.systemobserver;

import java.util.Observable;
/**
 * 
* @ClassName: WeatherObservable 
* @Description: TODO(使用系统提供的类来完成被观察者) 
* @author 刘楠
* @date 2016年9月19日 下午1:03:27 
*
 */
public class WeatherObservable extends Observable {
	

	// 温度
	private float temperature;
	// 湿度
	private float humidity;
	// 压力
	private float pressure;

	
	/**
	 * 
	 * @Title: setInfomation 
	 * @Description: TODO(设置数据)       
	 * @param @param temperature
	 * @param @param humidity
	 * @param @param pressure    设定文件
	 * @return void    返回类型    
	 * @throws
	 */
	public void setInfomation(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		//设置数据改变了
		
		setChanged();
		/**
		 * 以下2选一
		 */
		//调用无参数的方法，使用拉模型
		notifyObservers();
		//调用有参数的方法，被观察者推送
		//notifyObservers(this);
		
	}

	
	

	public float getTemperature() {
		return temperature;
	}


	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}


	public float getHumidity() {
		return humidity;
	}


	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}


	public float getPressure() {
		return pressure;
	}


	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	
	

}
