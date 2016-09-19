package com.liu.observerable.custom.impl;

import java.util.ArrayList;
import java.util.List;

import com.liu.observerable.custom.Observable;
import com.liu.observerable.custom.Observer;

/**
 * 
 * @ClassName: WeatherDdata
 * @Description: TODO(被观察者接口实现类)
 * @author 刘楠
 * @date 2016年9月19日 上午11:30:19
 *
 */
public class WeatherData implements Observable {

	// 温度
	private float temperature;
	// 湿度
	private float humidity;
	// 压力
	private float pressure;

	List<Observer> Observers;

	public WeatherData() {
		Observers = new ArrayList<>();
	}

	@Override
	public void registerObserver(Observer observer) {

		if (!Observers.contains(observer)) {
			Observers.add(observer);
		}

	}

	@Override
	public void removeObserver(Observer observer) {
		int indexOf = Observers.indexOf(observer);

		if (indexOf > 0) {
			Observers.remove(observer);
		}

	}

	@Override
	public void notifyObservers() {

		for (Observer ob : Observers) {

			// 2选1
			//由被观察者推送
			ob.update(this.temperature, this.humidity, this.pressure);
			//由观察者主动拉取
			ob.update(this);
		}

	}

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
		// 通知更新数据
		notifyObservers();
	}

	/*
	 * getter与setter方法
	 */
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
