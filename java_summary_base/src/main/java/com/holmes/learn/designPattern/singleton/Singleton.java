package com.holmes.learn.designPattern.singleton;

public class Singleton {

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static Singleton singleton = null;

    /**
     * 私有构造方法，防止对象初始化
     */
    private Singleton() {
    }

//	public static Singleton getInstance() {
//		
//		if(singleton == null) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread() + " getInstance...");
//			singleton = new Singleton();
//		}
//		return singleton;
//	}

//	public static synchronized Singleton getInstance() {
//		
//		if(singleton == null) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread() + " getInstance...");
//			singleton = new Singleton();
//		}
//		return singleton;
//	}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


//	/* 此处使用一个内部类来维护单例 */  
//    private static class SingletonFactory {  
//        private static Singleton instance = new Singleton();  
//    }  
//    public static Singleton getInstance() {
//    	return SingletonFactory.instance;
//    }
//    
//    
//    
//    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
//    public Object readResolve() {  
//        return getInstance();  
//    }  
}
