package com.liu.observerable.systemobserver;

public class Test2 {

	public static void main(String[] args) {

		
		WeatherObservable weatherObservable = new WeatherObservable();
		
		
		DisPlayObserver disPlayObserver1 = new DisPlayObserver("张三");
		DisPlayObserver disPlayObserver2= new DisPlayObserver("李四");
		DisPlayObserver disPlayObserver3 = new DisPlayObserver("王五");
		DisPlayObserver disPlayObserver4 = new DisPlayObserver("赵六");
		DisPlayObserver disPlayObserver5= new DisPlayObserver("钱七");
		
		
		weatherObservable.addObserver(disPlayObserver5);
		weatherObservable.addObserver(disPlayObserver4);
		weatherObservable.addObserver(disPlayObserver3);
		weatherObservable.addObserver(disPlayObserver2);
		weatherObservable.addObserver(disPlayObserver1);
		
		
		weatherObservable.setInfomation(34.0f, 88.9f, 22.4f);
	}

}
