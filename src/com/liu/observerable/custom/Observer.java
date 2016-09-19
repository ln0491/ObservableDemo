package com.liu.observerable.custom;

/**
 * 
 * @ClassName: Observer
 * @Description: TODO(自定义的观察者接口)
 * @author 刘楠
 * @date 2016年9月19日 上午11:26:48
 *
 */
public interface Observer {

	/**
	 * 
	 * @Title: update 
	 * @Description: TODO(更新 由被观察者推送消息)       
	 * @param @param temperature
	 * @param @param humidity
	 * @param @param pressure    设定文件
	 * @return void    返回类型    
	 * @throws
	 */
	public void update(float temperature,	 float humidity,	 float pressure);

	
	/**
	 * 
	 * @Title: update 
	 * @Description: TODO(观察者主动拉取消息)       
	 * @param @param observable    设定文件
	 * @return void    返回类型    
	 * @throws
	 */
	public void update(Observable observable);
}
