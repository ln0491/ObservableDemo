package com.liu.observerable.custom;

/**
 * 
* @ClassName: Observable 
* @Description: TODO(自定义的被观察都接口) 
* @author 刘楠
* @date 2016年9月19日 上午11:26:09 
*
 */
public interface Observable {

	/**
	 * 
	 * @Title: registerObserver 
	 * @Description: TODO(注册观察者)       
	 * @param @param observer    设定文件
	 * @return void    返回类型    
	 * @throws
	 */
	public void registerObserver(Observer observer);
	
	/**
	 * 
	 * @Title: removeObserver 
	 * @Description: TODO(移除观察者)       
	 * @param @param observer    设定文件
	 * @return void    返回类型    
	 * @throws
	 */
	public void removeObserver(Observer observer);
	
	/**
	 * 
	 * @Title: notifyObservers 
	 * @Description: TODO(通知观察都数据发生变化)       
	 * @param     设定文件
	 * @return void    返回类型    
	 * @throws
	 */
	public void notifyObservers();
	
}
