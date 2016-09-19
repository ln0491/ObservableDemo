##观察者模式

定义了对象之间的一对多依赖，当一个对象改变状态时，它的所有依赖者都会收到通知并自动更新。

观察者模式有两个重要要素：观察者、被观察者。用生活中一个常见的例子来描述：我们通常会订阅天气预报信息，假如天气出现了变化，气象App就会通知我们天气变了要做出相应的准备，那么在这个场景中，我们就充当了“观察者”角色，而气象App则充当了“被观察者”角色，当天气发生了变化，“被观察者”就会通知我们，让我们做出相应改变。

### Observerable

被观察者接口，规定了几个方法，分别是registerObserver():表示将观察者注册到被观察者中，即“订阅”；removeObserver():表示将观察者从被观察者中移除，即“取消订阅”；notifyObservers():当被观察者状态改变的时候，这个方法被调用，通知所有已经注册的观察者。


### Observer

观察者接口，规定了update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。

### WeatherData

被观察者 实现了Observerable接口，对以上的三个方法进行了具体实现，同时有一个List集合，用以保存注册的观察者，等需要通知观察者时，遍历该集合即可。注意，该集合的泛型参数应该是Observer，接口类型，而不应该是具体的Observer实现类，这样做的原因是一个被观察者可能会有多个不同实现类的观察者(但都实现了Observer接口)，

### CurrentDisplay

观察者，实现了update()方法


### 实现步骤

准备Observerable接口，声明注册、移除、通知更新这三个方法，接着需要Observer接口，声明update()方法，然后我们要实现以上两个接口，分别是WeatherData和ConcreteObserver这两个实现类。其中，CurrentDisplay应该有一个成员变量用户保存注册的观察者，当需要通知更新的时候，调用WeatherData#notifyObservers()方法，该方法内部遍历所有的观察者，并调用他们的update()方法，这样便实现了订阅——发布流程。

### 建立接口 被观察者 Observable

			public interface Observable {

			
				public void registerObserver(Observer observer);
				
				
				public void removeObserver(Observer observer);
				
				
				public void notifyObservers();
				
			}


### 建立观察者接口 Observer
	public interface Observer {
		
			//方法2选1
			public void update(float temperature,	 float humidity,	 float pressure);
		
			//方法2选1
			public void update(Observable observable);
		}


### 建立被观察者实现类

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


### 建立观察者实现类

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

###测试类


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



### 推模型和拉模型

推模型：被观察者主动向观察者推送自身的信息，可以是全部信息或者是部分信息。

拉模型：被观察者通过把自身的引用传递给观察者，需要观察者自行通过该引用来获取相关的信息。


### 认识Java内置的观察者模式

在Java中，它已经为我们准备了Observerable类以及Observer接口，只要继承或实现，就能快速实现我们的被观察者和观察者了

### java.util.Observerable类

与自定义的不同它是一个类不是接口

我们的被观察者要继承该类，另外，Observerable类已经帮我们实现了addObserver()、deleteObserver()、notifyObservers()等方法，因此我们在子类不需要再重写这些方法了，另外，该父类还提供了一个setChanged()方法，该方法用来标记状态已经改变的事实，因此要先调用该方法再调用notifyObservers()方法。其实，该类提供了两个notifyObservers()方法，一个有参数，一个无参数，而有参数的适用于推模型，无参数的适用于拉模型。
而java.util.Observer则是一个接口，与我们上面定义的基本一样。


### 建立被观察者类 继承 java.util.Observable类


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
				//notifyObservers();
				//调用有参数的方法，被观察者推送
				notifyObservers(this);
				
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


### 建立观察者类实现 java.util.Observable接口

			
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


 ### 测试类


		
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


### JAVA中的观察者接口

		package java.util;
		
		public interface Observer {
		
		    void update(Observable o, Object arg);
		}




### JAVA中的被观察者类
		
		
		package java.util;
		
		
		public class Observable {
		    private boolean changed = false;
		    private Vector<Observer> obs;
		
		   
			//这里使用 Vector 和List一样这个是线程安全的
		    public Observable() {
		        obs = new Vector<>();
		    }
			
				//添加观察者
		    public synchronized void addObserver(Observer o) {
		        if (o == null)
		            throw new NullPointerException();
		        if (!obs.contains(o)) {
		            obs.addElement(o);
		        }
		    }
		
		 
		    public synchronized void deleteObserver(Observer o) {
		        obs.removeElement(o);
		    }
		
		
			//通知 传一个NULL
		    public void notifyObservers() {
		        notifyObservers(null);
		    }
		
			//上面的会调用下面的  	
		    public void notifyObservers(Object arg) {
		       
		        Object[] arrLocal;
		
		        synchronized (this) { 
		        	//判断是不是数据发生变化了 是changed =false 直接true 没有变化 changed=true 变化了
		            if (!changed)
		                return;
		            arrLocal = obs.toArray();
		            clearChanged();
		        }
		
		        for (int i = arrLocal.length-1; i>=0; i--)
		            ((Observer)arrLocal[i]).update(this, arg);
		    }
		
		   
		    public synchronized void deleteObservers() {
		        obs.removeAllElements();
		    }
		
		   
			//设置为数据变化了。设置为true
		    protected synchronized void setChanged() {
		        changed = true;
		    }
		
		 	//通知完设置为没有变化
		    protected synchronized void clearChanged() {
		        changed = false;
		    }
		
		  
		    public synchronized boolean hasChanged() {
		        return changed;
		    }
		
		    public synchronized int countObservers() {
		        return obs.size();
		    }
		}
